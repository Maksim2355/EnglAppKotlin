package com.example.engapp.profile


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.R
import com.example.engapp.UI.User


/*
Данная часть программы должна запоминать пользователя и выполнить
вход в приложение и запомнить данные для входа в userPref
 */
class ProfileFragment : Fragment(){
    private lateinit var user: User
    private lateinit var userPref: SharedPreferences




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //userPref = this.activity!!.getSharedPreferences(user.APP_PREFERENCES, Context.MODE_PRIVATE)
        val myFragmentInf: View =
            inflater.inflate(R.layout.fragment_profile, container, false)
        val navController: NavController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }!!
        val regist: Button =
            myFragmentInf.findViewById(R.id.passwordAc)
        regist.setOnClickListener{
            navController.navigate(R.id.addProfileFragment)
        }
        val loginIn: Button = myFragmentInf.findViewById(R.id.loginAc)



        // Inflate the layout for this fragment
        return myFragmentInf
    }

    override fun onPause() {
        super.onPause()

    }


}
