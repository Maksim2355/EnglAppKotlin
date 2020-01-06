package com.example.engapp.profile


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.engapp.App
import com.example.engapp.ListId
import com.example.engapp.R
import com.example.engapp.database.*


class AddWorkFragment : Fragment(), View.OnClickListener {
    private lateinit var  myFragment: View
    private var count = 1
    //Кнопка добавления файла и кнопка промежуточного/окончательного добавления
    private lateinit var addPath: Button
    private lateinit var nextAdd: Button
    //Поля ввода для title/desc и для контента на английском/русском
    private lateinit var titleDesc: EditText
    private lateinit var enru: EditText
    //Экземлпяры базы данных и для обновления данных о работе пользователя
    private val db: AppDatabase? = App.instance!!.database!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!
    //С помощью данного интерфейса добавляем работу в базу данных
    private val worksDao: DataWorksDao = db!!.worksDao()!!
    private val work  = DataWorks()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(R.layout.fragment_add_work, container, false)
        init()
        // Inflate the layout for this fragment
        return myFragment
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            //Нажимая данную кнопку, мы заставляем пользователя дать аудио/картинку
            R.id.addPath-> {  }
            /*Нажимая данную кнопку, мы сохраняем значения в переменные и вставляем в объект
            который позже заполняем до конца и вставляем в базу данных
             */
            R.id.nextAdd-> {
                if(count == 1 && (titleDesc.text.toString() != "") &&
                    (enru.text.toString() != ""))
                {
                    work.title = titleDesc.text.toString()
                    work.contentEn = enru.text.toString()
                    drawTwo()
                    count++
                }
                else {
                    if(count == 2 && (titleDesc.text.toString() != "") &&
                        (enru.text.toString() != "")) {
                        //Заполняем работу
                        work.contentDesc = titleDesc.text.toString()
                        work.contentRu = enru.text.toString()
                        work.idAuthor = userDao.getUserData()!!.userId!!
                        //Получаем аккаунт по id
                        val acRed =
                            accountDao.getById(work.idAuthor!!)
                        //Добавляем в данные об аккаунте дополнительную работу и обновляем значение
                        val ac = ListId(acRed!!.idWorks)
                        work.id?.let { ac.addItem(it) }
                        accountDao.update(acRed)
                        worksDao.insertAll(work)
                        val navController =
                            activity?.let {
                                Navigation.findNavController(
                                    it,
                                    R.id.nav_host_fragment
                                )
                            }!!
                        navController.navigate(R.id.userFragment)
                    }
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun drawTwo(){
        addPath.text = "Add image to work (optional)"
        nextAdd.text = "Add work"
        titleDesc.hint = "Add a short description of the work"
        enru.hint = "Add a translation of this work"
        titleDesc.text = null
        enru.text = null

    }

    private fun init(){
        addPath = myFragment.findViewById(R.id.addPath)
        nextAdd = myFragment.findViewById(R.id.nextAdd)
        titleDesc = myFragment.findViewById(R.id.titleDesc)
        enru = myFragment.findViewById(R.id.ENRU)
        addPath.setOnClickListener(this)
        nextAdd.setOnClickListener(this)
    }

}
