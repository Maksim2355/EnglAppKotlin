package com.example.engapp.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.R

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment: View =
            inflater.inflate(R.layout.fragment_user, container, false)
        val navController: NavController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }!!
        val addContent: Button = myFragment.findViewById(R.id.butAddWorks)
        addContent.setOnClickListener{
            navController.navigate(R.id.addWorkFragment)
        }



        // Inflate the layout for this fragment
        return myFragment
    }


}
