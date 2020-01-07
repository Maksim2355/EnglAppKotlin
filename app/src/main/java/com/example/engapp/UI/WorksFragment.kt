package com.example.engapp.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engapp.DataAdapter
import com.example.engapp.R

/**
 * A simple [Fragment] subclass.
 */
class WorksFragment : Fragment() {
    private lateinit var listRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment: View =
        inflater.inflate(R.layout.fragment_works, container, false)
        listRecycler = myFragment.findViewById(R.id.allWokrsRecycler)
        val layoutManager = LinearLayoutManager(context)
        listRecycler.layoutManager = layoutManager
        val adapter = DataAdapter(context, 0)
        listRecycler.adapter = adapter
        return myFragment
    }


}
