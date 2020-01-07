package com.example.engapp.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.App
import com.example.engapp.DataAdapter
import com.example.engapp.ListId
import com.example.engapp.R
import com.example.engapp.database.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {
    private val db: AppDatabase? = App.instance!!.database!!
    private val worksDao: DataWorksDao = db!!.worksDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!
    private val userDao: UserDataDao = db!!.userDao()!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment: View =
            inflater.inflate(R.layout.fragment_favorite, container, false)
        if (App.instance!!.database!!.userDao()!!.getUserData()?.userId != null){
            val listRecycler = myFragment.findViewById<RecyclerView>(R.id.favoriteWokrsRecycler)
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
        }
        return myFragment
    }


}
