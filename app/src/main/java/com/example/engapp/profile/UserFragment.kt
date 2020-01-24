package com.example.engapp.profile


import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.App
import com.example.engapp.DataAdapter
import com.example.engapp.ListId
import com.example.engapp.R
import com.example.engapp.database.*

class UserFragment : Fragment(), View.OnClickListener {
    //Преобразуем наш фрагмент во View и получаем контроллер фрагментов
    private var  myFragment: View? = null
    private var navController: NavController? = null
    private lateinit var listRecycler: RecyclerView
    //Кнопка добавления поста и выхода из аккаунта
    private lateinit var addContent: Button
    private lateinit var signOut: Button
    //Экземпляры базы данных и интерфейсы для получения/Вставки данных
    private val db: AppDatabase? = App.instance!!.database!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.title = "Your profile"
        //Делаем преобразование в onCreate
        myFragment =
            inflater.inflate(R.layout.fragment_user, container, false)
        //Получаем контроллер для передвижения по фрагментам
        drawUI()
        init()
        drawRecycler()

        // Inflate the layout for this fragment
        return myFragment
    }


    private fun init(){
        navController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }!!
        //Кнопка, с помощью которой переключаемся на фрагмент, с помощью которой добавляем работу
        addContent = myFragment!!.findViewById(R.id.AddContentBut)
        signOut = myFragment!!.findViewById(R.id.SignOutBut)
        addContent.setOnClickListener(this)
        signOut.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.AddContentBut -> {navController!!.navigate(R.id.addWorkFragment)}
            R.id.SignOutBut -> {
                val active = UserData()
                active.id = userDao.getUserData()!!.id
                active.userId = null
                userDao.update(active)
                navController!!.navigate(R.id.profileFragment)
            }
            R.id.iconProfile -> {
                val bundle = Bundle()
                bundle.putInt("Folder", 2)
                navController!!.navigate(R.id.addFileFragment, bundle)
            }
        }

    }

    private fun drawUI() {
        //Получаем элементы для разрисовки профиля
        val nameLogin = myFragment!!.findViewById<TextView>(R.id.loginUser)
        val desc = myFragment!!.findViewById<Button>(R.id.accountDescription)
        val avatar = myFragment!!.findViewById<ImageView>(R.id.iconProfile)
        //Получим данного из авторизированного аккаунта
        val activeId = userDao.getUserData()!!.userId
        //Получим сам авторизированный аккаунт
        val activeAccount = accountDao.getById(activeId!!)
        nameLogin.text = activeAccount!!.login
        desc.text = activeAccount.accountDesc
        if(activeAccount.pathAvatar == null){
            avatar.setImageResource(R.drawable.avatar_not)
        }else{
            val uri = Uri.parse("file:///storage/emulated/0" + activeAccount.pathAvatar)
            avatar.setImageURI(uri)
        }
        avatar.setOnClickListener(this)

    }

    private fun drawRecycler(){
        listRecycler = myFragment!!.findViewById(R.id.userWorksRecycler)
        val layoutManager = LinearLayoutManager(context)
        listRecycler.layoutManager = layoutManager
        val worksDao: DataWorksDao = db!!.worksDao()!!
        val accountDao: DataAccountDao = db.accountDao()!!
        val userDao: UserDataDao = db.userDao()!!


        val worksIdList =
            accountDao.getById(userDao.getUserData()!!.userId!!)!!.idWorks
        val idWorks = ListId(worksIdList)
        val mutList = mutableListOf<ItemList>()
        //Ищу заголовки во всех работах и добавляю в новый лист
        for (i in idWorks.getList()) {
            mutList.add(worksDao.getItemById(i)!!)
        }
        val listWoks = mutList.toList()
        val adapter = DataAdapter(2, listWoks)
        listRecycler.adapter = adapter
    }


}
