package com.example.engapp.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.App

import android.widget.EditText
import com.example.engapp.R
import com.example.engapp.database.*


/*
Данная часть программа должна либо авторизовать пользователя, либо включать
фрагмент для авторизации
 */
class ProfileFragment : Fragment(), View.OnClickListener{
    //View фрагмента и контроллер
    private lateinit var  myFragment: View
    private lateinit var  navController: NavController
    //Кнопка регистрации и входа
    private lateinit var regBut: Button
    private lateinit var loginBut: Button
    //Поля для ввода текста

    //Экземпляры базы данных и интерфейсы для получения/Вставки данных



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(R.layout.fragment_profile, container, false)
        navController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }!!
        init()


        // Inflate the layout for this fragment
        return myFragment
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.regBut->
                navController.navigate(R.id.addProfileFragment)
            //Необходимо реализовать авторизацию, изменение активного юзера
            R.id.loginBut-> {
                val login: EditText = myFragment.findViewById(R.id.loginIn)
                val password = myFragment.findViewById<EditText>(R.id.passwordIn)

                val db:AppDatabase = App.instance!!.database!!
                val userDao: UserDataDao = db.userDao()!!
                val accountDao: DataAccountDao = db.accountDao()!!
                //Получаем информацию об аккаунтах
                val listAccount: List<AccountInData> = accountDao.getLoginInfo()!!
                //Перебираем каждое значение, если оно подходит, то выполняем авторизацию
                for(item in listAccount){
                    if(login.text.toString() == item.login &&
                            password.text.toString() == item.password){
                        val user = UserData()
                        user.id = userDao.getUserData()!!.id
                        user.userId = item.id
                        userDao.update(user)
                        navController.navigate(R.id.userFragment)
                    }
                }
                login.text = null
                password.text = null
            }

        }
    }
    //Создаем переменные и присваем обработчики
    private fun init(){
        regBut =
            myFragment.findViewById(R.id.regBut)
        loginBut =
            myFragment.findViewById(R.id.loginBut)
        regBut.setOnClickListener(this)
        loginBut.setOnClickListener(this)
    }


}
