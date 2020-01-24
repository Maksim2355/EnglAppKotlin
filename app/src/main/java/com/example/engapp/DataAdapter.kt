package com.example.engapp

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.UI.WorksFragment
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.ItemList


//Тут еще должны быть ебаные обработчики
//Передаем из фрагмента в адаптер Recycler
/* Второй аргумент говорит
0- Делаем список для раздела All works
1- Делаем список для раздела Favorite
2- Делаем список для раздела Profile
 */

class DataAdapter(private val idRecycler: Int, listWorks: List<ItemList?>?) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    //Получаем экземпляры
    //Из этого листа вытягиваем id с помощью позиции
    private val listWoks = listWorks
    private var size =  listWorks!!.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_work, parent, false)

        return ViewHolder(view, idRecycler, listWoks, context)
    }

    override fun getItemCount(): Int {
        //Возвращаем количество элементов
        return size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(listWoks!![position]!!)

    }
    class ViewHolder internal constructor(view: View,
                                          private val idRecycler: Int,
                                          private var listWorks: List<ItemList?>?,
                                          private val context: Context) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private var imageView: ImageView = view.findViewById<View>(R.id.imageWorks) as ImageView
        private var titleView: TextView = view.findViewById<View>(R.id.titleWorks) as TextView
        private var contentDescView: TextView =
            view.findViewById<View>(R.id.contentDescWorks) as TextView
        private var delAddButton: ImageButton = view.findViewById(R.id.butDelAdd) as ImageButton
        private val db: AppDatabase? = App.instance!!.database!!
        private val accountDao = db!!.accountDao()!!
        private val userDao = db!!.userDao()!!
        private val worksDao = db!!.worksDao()
        private val userId = userDao.getUserData()!!.userId

        init {
            delAddButton.setOnClickListener(this)
            view.setOnClickListener(this)
            view.setBackgroundResource(R.drawable.border_radius)
        }

        fun bind(itemWorks: ItemList) {
            //Проверяем, имеется ли автарка

            if (itemWorks.pathImage == null) {
                imageView.visibility = GONE
            } else {
                val uriTest = Uri.parse("file:///storage/emulated/0" +
                        itemWorks.pathImage)
                imageView.setImageURI(uriTest)
            }
            when(idRecycler){
                0-> {
                    val idWork = itemWorks.id
                    if (userId != null) {
                        val favoriteListId =
                            ListId(accountDao.getById(userId)!!.idFavorites)
                        if (favoriteListId.elemInList(idWork)) {
                            delAddButton.setBackgroundResource(R.drawable.del_add_favorite) }
                        else {
                            delAddButton.setBackgroundResource(R.drawable.add_in_favorite) }
                    }else{
                        delAddButton.visibility = GONE
                    }
                }
                1-> {
                        delAddButton.setBackgroundResource(R.drawable.favorite_del_half)
                     }
                2-> {delAddButton.setBackgroundResource(R.drawable.ic_clear_black_24dp) }
            }
            titleView.text = itemWorks.title
            contentDescView.text = itemWorks.contentDesc
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            val workId = listWorks!![position]!!.id
            val navController =
                Navigation.findNavController(context as Activity,R.id.nav_host_fragment)
            when (v!!.id) {
                R.id.butDelAdd -> {
                    if (userId != null) {
                        val acRed = accountDao.getById(userId)
                        if(userDao.getUserData()!!.userId != null) {
                            when (idRecycler) {
                                //Реализация добавления в раздел favorite
                                0 -> {
                                    val addFavorite = ListId(acRed!!.idFavorites)
                                    //Если этого элемента нет в favorite
                                    if (!addFavorite.elemInList(workId)) {
                                        //Нашли данные о нашем аккаунте
                                        //Вытянули id работы, по которой был сделан клик
                                        addFavorite.addItem(workId)
                                        acRed.idFavorites = addFavorite.getList().toString()
                                        accountDao.update(acRed)
                                    } else {
                                        val delFavorite = ListId(acRed.idFavorites)
                                        delFavorite.delItem(workId)
                                        acRed.idFavorites = delFavorite.getList().toString()
                                        accountDao.update(acRed)
                                    }
                                    navController.navigate(R.id.worksFragment)
                                }
                                //Реализация удаления из Favorite
                                1 -> {
                                    val delFavorite = ListId(acRed!!.idFavorites)
                                    delFavorite.delItem(workId)
                                    acRed.idFavorites = delFavorite.getList().toString()
                                    accountDao.update(acRed)
                                    navController.navigate(R.id.favoriteFragment)
                                }
                                //Реализация удаления из всей БД. Удаляем из списка его работ и всей бд
                                2 -> {
                                    //Получаем наш аккаунт
                                    val delWorks = ListId(acRed!!.idWorks)
                                    val delFavor = ListId(acRed.idFavorites)
                                    delFavor.delItem(workId)
                                    delWorks.delItem(workId)
                                    val del = worksDao!!.getById(workId)
                                    acRed.idFavorites = delFavor.getList().toString()
                                    acRed.idWorks = delWorks.getList().toString()
                                    worksDao.deleteWorks(del)
                                    accountDao.update(acRed)
                                    navController.navigate(R.id.userFragment)
                                }
                            }
                        }
                    }
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putInt("workId", workId)
                    navController.navigate(R.id.elementWorksFragment, bundle)
                }
            }
        }
    }
}