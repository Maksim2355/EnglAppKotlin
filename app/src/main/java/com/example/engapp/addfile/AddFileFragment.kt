package com.example.engapp.addfile


import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.App
import com.example.engapp.DataAdapter
import com.example.engapp.R
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.DataWorks
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
/*
0-Мы берем данные для аудиофайла
1-Мы берем данные для изображения
 */

class AddFileFragment : Fragment() {
    private lateinit var myFragment: View
    private lateinit var listRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment = inflater.inflate(R.layout.fragment_add_file, container, false)
        println("Bundle IS : " + arguments?.getParcelable("Work") )
        listRecycler = myFragment.findViewById(R.id.addFileRecycler)
        val layoutManager = LinearLayoutManager(context)
        listRecycler.layoutManager = layoutManager
        val pathSection = arguments!!.getInt("Folder")
        val worksAdded = arguments!!.getParcelable<DataWorks>("Work")!!
        val adapter = FileAdapter(getFiles(pathSection), pathSection,  worksAdded)
        listRecycler.adapter = adapter

        return myFragment
    }

    private fun getFiles(idAdd: Int): List<String?>? {
        var dir = "/storage/emulated/0/"
        when(idAdd){
            0-> {dir += "audioAppEng/"}
            1-> {dir += "imageAppEng/"} }
        val f = File(dir)
        return f.list().toList()
    }

}
