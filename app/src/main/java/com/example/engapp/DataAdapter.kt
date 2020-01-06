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
    private val worksDao: DataWorksDao = db!!.worksDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private var listWoks: List<ItemList?>? = db!!.worksDao()!!.getItem()!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when(idRecycler){
            1-> {
                //Получим список избранных работ пользователя
                val favoriteIdList =
                    db!!.accountDao()!!.getById(db.userDao()!!.getUserData()!!.userId!!)!!.idFavorites
                val idWorks = ListId(favoriteIdList)
                val mutList = mutableListOf<ItemList>()
                //Ищу заголовки во всех работах и добавляю в новый лист
                for (i in idWorks.getList()){
                    mutList.add(db.worksDao()!!.getItemById(i)!!)
                }
                listWoks = mutList.toList()
            }
            2-> {
                val worksIdList =
                    db!!.accountDao()!!.getById(db.userDao()!!.getUserData()!!.userId!!)!!.idWorks
                val idWorks = ListId(worksIdList)
                val mutList = mutableListOf<ItemList>()
                //Ищу заголовки во всех работах и добавляю в новый лист
                for (i in idWorks.getList()){
                    mutList.add(db.worksDao()!!.getItemById(i)!!)
                }
                listWoks = mutList.toList()
            }
        }
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_work, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //Возвращаем количество элементов
        return listWoks!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(listWoks!![position]!!)

    }

    class ViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById<View>(R.id.imageWorks) as ImageView
        private val titleView: TextView = view.findViewById<View>(R.id.titleWorks) as TextView
        private val contentDescView: TextView = view.findViewById<View>(R.id.contentDescWorks) as TextView
        private val delAddButton: Button = view.findViewById(R.id.butDelAdd) as Button

        fun bind(itemWorks: ItemList) {
            if (itemWorks.pathImage == null) {
                imageView.setImageResource(R.drawable.photo_ots)
            } else { println("")//Тут вставим фото
                    }
            titleView.text = itemWorks.title
            contentDescView.text = itemWorks.contentDesc
        }
    }
}