package com.example.engapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.database.*

//Тут еще должны быть ебаные обработчики
//Передаем из фрагмента в адаптер Recycler
/* Второй аргумент говорит
0- Делаем список для раздела All works
1- Делаем список для раздела Favorite
2- Делаем список для раздела Profile
 */
class DataAdapter(context: Context?, private val idRecycler: Int) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    //Получаем экземпляры
    private val db: AppDatabase? = App.instance!!.database!!
    //Из этого листа вытягиваем id с помощью позиции
    private var listWoks: List<ItemList?>? = db!!.worksDao()!!.getItem()!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if(idRecycler == 1 || idRecycler == 2) {
            val worksDao: DataWorksDao = db!!.worksDao()!!
            val accountDao: DataAccountDao = db.accountDao()!!
            val userDao: UserDataDao = db.userDao()!!
            when (idRecycler) {
                1 -> {
                    //Получим список избранных работ пользователя
                    val favoriteIdList =
                        accountDao.getById(userDao.getUserData()!!.userId!!)!!.idFavorites
                    val idWorks = ListId(favoriteIdList)
                    val mutList = mutableListOf<ItemList>()
                    //Ищу заголовки во всех работах и добавляю в новый лист
                    for (i in idWorks.getList()) {
                        mutList.add(worksDao.getItemById(i)!!)
                    }
                    listWoks = mutList.toList()
                }
                2 -> {
                    val worksIdList =
                        accountDao.getById(userDao.getUserData()!!.userId!!)!!.idWorks
                    val idWorks = ListId(worksIdList)
                    val mutList = mutableListOf<ItemList>()
                    //Ищу заголовки во всех работах и добавляю в новый лист
                    for (i in idWorks.getList()) {
                        mutList.add(worksDao.getItemById(i)!!)
                    }
                    listWoks = mutList.toList()
                }
            }
        }
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_work, parent, false)

        return ViewHolder(view, idRecycler, listWoks)
    }

    override fun getItemCount(): Int {
        //Возвращаем количество элементов
        return listWoks!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(listWoks!![position]!!)

    }

    class ViewHolder internal constructor(view: View, private val idRecycler: Int,
                                          private val listWorks: List<ItemList?>? ) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        private var imageView: ImageView = view.findViewById<View>(R.id.imageWorks) as ImageView
        private var titleView: TextView = view.findViewById<View>(R.id.titleWorks) as TextView
        private var contentDescView: TextView = view.findViewById<View>(R.id.contentDescWorks) as TextView
        private var delAddButton: Button = view.findViewById(R.id.butDelAdd) as Button
        private val db: AppDatabase? = App.instance!!.database!!
        private val accountDao = db!!.accountDao()!!
        private val userDao = db!!.userDao()!!
        private val worksDao = db!!.worksDao()
        private val userId = userDao.getUserData()!!.userId

        init {
            delAddButton.setOnClickListener(this)
            view.setOnClickListener(this)
        }


        fun bind(itemWorks: ItemList) {
            if (itemWorks.pathImage == null) {
                imageView.setImageResource(R.drawable.photo_ots)
            } else { println("")//Тут вставим фото
                    }
            titleView.text = itemWorks.title
            contentDescView.text = itemWorks.contentDesc
        }

        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.butDelAdd-> {
                    val position: Int = adapterPosition
                    val workId = listWorks!![position]!!.id
                    when(idRecycler){
                        //Реализация добавления в раздел favorite
                        0->{
                            if (userId != null){
                                val acRed = accountDao.getById(userId)
                                //Вытянули id работы, по которой был сделан клик
                                val addFavorite = ListId(acRed!!.idFavorites)
                                addFavorite.addItem(workId)
                                acRed.idFavorites = addFavorite.getList().toString()
                                accountDao.update(acRed)
                            }
                        }
                        //Реализация удаления из Favorite
                        1->{
                            val acRed = accountDao.getById(userId!!)
                            val delFavorite = ListId(acRed!!.idFavorites)
                            delFavorite.delItem(workId)
                            acRed.idFavorites = delFavorite.getList().toString()
                            accountDao.update(acRed)
                        }
                        //Реализация удаления из всей БД. Удаляем из списка его работ и всей бд
                        2->{
                            val acRed = accountDao.getById(userId!!)
                            val delWorks = ListId(acRed!!.idWorks)
                            delWorks.delItem(workId)
                            val del = worksDao!!.getById(workId)
                            acRed.idFavorites = delWorks.getList().toString()
                            accountDao.update(acRed)
                            worksDao.deleteWorks(del)
                        }
                    }
                }
                R.layout.item_work-> {

                }

            }
        }
    }
}