package com.example.engapp.addfile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.engapp.R

/**
 * A simple [Fragment] subclass.
 */
class AddFileFragment : Fragment() {
    private lateinit var myFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment = inflater.inflate(R.layout.fragment_add_file, container, false)
        // Inflate the layout for this fragment
        return myFragment
    }


}
