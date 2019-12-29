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
import com.example.engapp.UI.User
import com.example.engapp.App

import com.example.engapp.database.AppDatabase

import android.R
import com.example.engapp.database.AccountInData
import com.example.engapp.database.DataAccountDao
import com.example.engapp.database.UserDataDao


/*
Данная часть программы должна запоминать пользователя и выполнить
вход в приложение и запомнить данные для входа в userPref
 */
class ProfileFragment : Fragment(), View.OnClickListener{
    private lateinit var  myFragment: View
    private lateinit var  navController: NavController
    private lateinit var regButton: Button
    private lateinit var loginIn: Button
    private val db:AppDatabase? = App.instance!!.database!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(com.example.engapp.R.layout.fragment_profile, container, false)
        init()
        // Inflate the layout for this fragment
        return myFragment
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            com.example.engapp.R.id.regbutton-> navController.navigate(com.example.engapp.R.id.addProfileFragment)
            //Необходимо реализовать авторизацию, изменение активного юзера
            com.example.engapp.R.id.loginAc-> {
                val listAccount: List<AccountInData> = accountDao.getLoginInfo()!!
                

            }

        }
    }

    private fun init(){
        navController =
            activity?.let { Navigation.findNavController(it, com.example.engapp.R.id.nav_host_fragment) }!!
        regButton =
            myFragment.findViewById<Button>(com.example.engapp.R.id.passwordAc)
        loginIn =
            myFragment.findViewById<Button>(com.example.engapp.R.id.loginAc)
        regButton.setOnClickListener(this)

        loginIn.setOnClickListener(this)
    }


}
