package com.example.engapp.addfile

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.R
import com.example.engapp.database.DataWorks


/*
FILE_SECTION:
    0-Делаем список для аудио
    1-Делаем список для фото
 */
class FileAdapter(private val listFile: List<String?>?, private val FILE_SECTION: Int,
                  private val workAdded: DataWorks):
    RecyclerView.Adapter<FileAdapter.ViewHolder>(){
    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_file, parent, false)
        navController =
        Navigation.findNavController(context as Activity,R.id.nav_host_fragment)

        return ViewHolder(view, FILE_SECTION, listFile, navController, workAdded)
    }

    override fun getItemCount(): Int {
        return listFile!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    class ViewHolder internal constructor(view: View,
                                          private val FILE_SECTION: Int,
                                          private val listFile: List<String?>?,
                                          private val navController: NavController,
                                          private val workAdded: DataWorks):
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private val nameFile = view.findViewById<TextView>(R.id.file)
        private val numbFile = view.findViewById<TextView>(R.id.Numb)
        init {
            //Присваем обработчик и стиль
            view.setOnClickListener(this)
            view.setBackgroundResource(R.drawable.border_radius)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            //Добавляем файл в бд в качестве работы
            when(FILE_SECTION){
                0->{
                    workAdded.pathAudio = "/audioAppEng/" + listFile!![position]!!
                    println(workAdded.pathAudio)
                    navController.popBackStack()
                }
                1->{
                    workAdded.pathImage = "/imageAppEng/" + listFile!![position]!!
                    println(workAdded.pathImage)
                    navController.popBackStack()
                }
            }
        }

        fun bind(position: Int){
            numbFile.text = (position + 1).toString()
            nameFile.text =
                listFile!![position]!!.substringBeforeLast('.')
        }
    }

}