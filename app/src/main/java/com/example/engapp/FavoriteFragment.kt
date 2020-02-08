package com.example.engapp


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment(), View.OnClickListener {
    private val db: AppDatabase? = App.instance!!.database!!
    private val worksDao: DataWorksDao = db!!.worksDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private var adapter: DataAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment: View =
            inflater.inflate(R.layout.fragment_favorite, container, false)
        activity!!.title = "Favorite"
        if (userDao.getUserData()!!.userId != null){
            val listRecycler =
                myFragment.findViewById<RecyclerView>(R.id.favoriteWokrsRecycler)
            val layoutManager = LinearLayoutManager(context)
            listRecycler.layoutManager = layoutManager
            //Получим список избранных работ пользователя
            val favoriteIdList =
                accountDao.getById(userDao.getUserData()!!.userId!!)!!.idFavorites
            val idWorks = ListId(favoriteIdList)
            val mutList = mutableListOf<ItemList>()
            //Ищу заголовки во всех работах и добавляю в новый лист
            for (i in idWorks.getList()) {
                mutList.add(worksDao.getItemById(i)!!)
            }
            val listWoks = mutList.toList()
            val adapter = DataAdapter(1, listWoks)
            listRecycler.adapter = adapter
    }else{
            val butNav: Button = myFragment.findViewById(R.id.butInLoginNav)
            val noLogin: LinearLayout = myFragment.findViewById(R.id.noRegist)
            noLogin.visibility = View.VISIBLE
            butNav.setOnClickListener(this)
        }
        return myFragment
    }

    override fun onResume() {
        if (adapter != null){
            adapter!!.notifyDataSetChanged()
        }
        super.onResume()
    }

    override fun onClick(v: View?) {
        val navController =
            Navigation.findNavController(context as Activity,R.id.nav_host_fragment)
        navController.navigate(R.id.profileFragment)
    }


}
