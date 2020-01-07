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

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {
    private lateinit var listRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment: View =
            inflater.inflate(R.layout.fragment_works, container, false)
        if (App.instance!!.database!!.userDao()!!.getUserData()?.userId != null){
            listRecycler = myFragment.findViewById(R.id.favoriteWokrsRecycler)
            val layoutManager = LinearLayoutManager(context)
            listRecycler.layoutManager = layoutManager
            val adapter = DataAdapter(context, 1)
            listRecycler.adapter = adapter
        }
        return myFragment
    }


}
