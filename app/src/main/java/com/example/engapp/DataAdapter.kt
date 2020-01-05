package com.example.engapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.DataWorks
import com.example.engapp.database.DataWorksDao
import com.example.engapp.database.ItemList

//Тут еще должны быть ебаные обработчики
//Передаем из фрагмента в адаптер Recycler
class DataAdapter(context: Context?, private val idRecycler: Int) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    //Получаем экземпляры
    private val db: AppDatabase? = App.instance!!.database!!
    private val worksDao: DataWorksDao = db!!.worksDao()!!
    private val listWoks: List<ItemList?>? = worksDao.getItem()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_work, parent, false)
        val viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        //Возвращаем количество элементов
        return listWoks!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listWoks != null) {
            holder.bind(listWoks[position], idRecycler)
        }

    }

    class ViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById<View>(R.id.imageWorks) as ImageView
        private val titleView: TextView = view.findViewById<View>(R.id.titleWorks) as TextView
        private val contentDescView: TextView = view.findViewById<View>(R.id.contentDescWorks) as TextView
        private val delAddButton: Button = view.findViewById(R.id.butDelAdd) as Button

        fun bind(itemWorks: ItemList?, idRecycler: Int) {
            if (itemWorks!!.pathImage == null) {
                imageView.setImageResource(R.drawable.photo_ots)
            } else { //Тут вставим фото
                    }
            titleView.text = itemWorks.title
            contentDescView.text = itemWorks.contentDesc
        }
    }
}