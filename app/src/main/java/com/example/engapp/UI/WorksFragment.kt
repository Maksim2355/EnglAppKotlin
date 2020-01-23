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
import com.example.engapp.R
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.ItemList

/**
 * A simple [Fragment] subclass.
 */
class WorksFragment : Fragment() {
    private lateinit var listRecycler: RecyclerView
    private val db: AppDatabase? = App.instance!!.database!!
    //Из этого листа вытягиваем id с помощью позиции
    private var listWoks: List<ItemList?>? = db!!.worksDao()!!.getItem()!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.title = "All Works"
        val myFragment: View =
            inflater.inflate(R.layout.fragment_works, container, false)
            listRecycler = myFragment.findViewById(R.id.allWokrsRecycler)
            val layoutManager = LinearLayoutManager(context)
            listRecycler.layoutManager = layoutManager
            val adapter = DataAdapter(0, listWoks)
            listRecycler.adapter = adapter

        return myFragment
    }


}
