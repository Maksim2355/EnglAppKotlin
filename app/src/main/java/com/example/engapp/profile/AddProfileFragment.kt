package com.example.engapp.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.App
import com.example.engapp.R
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.DataAccount


/**
 * A simple [Fragment] subclass.
 */
class AddProfileFragment : Fragment(), View.OnClickListener {
    //Поля ввода
    private lateinit var loginInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    //Кнопка, которая добавляет аккаунт в базу данных
    private lateinit var regBut: Button
    //Фрагмент и контроллер к нему
    private lateinit var myFragment: View
    private lateinit var navController: NavController
    //Экземпляры базы данных
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(R.layout.fragment_add_profile, container, false)
        navController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }!!
        initView()

        return myFragment
    }

    override fun onClick(v: View?) {
        if (emptyIn())
        {
            db = App.instance!!.database!!
            val accountDao = db.accountDao()
            val account = DataAccount(loginInput.text.toString(), emailInput.text.toString(),
                passwordInput.text.toString(), "Add Description",null)
            accountDao!!.insertAccount(account)

            navController.popBackStack()
        }

    }

    private fun initView(){
        loginInput = myFragment.findViewById(R.id.loginReg)
        emailInput = myFragment.findViewById(R.id.emailReg)
        passwordInput = myFragment.findViewById(R.id.passwordReg)
        regBut = myFragment.findViewById(R.id.regbutton)
        regBut.setOnClickListener(this)
    }

    private fun emptyIn(): Boolean{
        return (loginInput.text.toString()!= "" && emailInput.text.toString()!= ""
                && passwordInput.text.toString()!= "")

    }

}
