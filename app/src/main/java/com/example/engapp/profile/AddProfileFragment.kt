package com.example.engapp.profile


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.App
import com.example.engapp.R
import com.example.engapp.database.AccountInData
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.DataAccount
import com.example.engapp.database.DataAccountDao


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

    //Обработчик событий. Создаем экземпляр БД и добавляем аккаунт, если поле ввода не пусто
    override fun onClick(v: View?) {
        if (emptyIn())
        {
            db = App.instance!!.database!!
            val accountDao = db.accountDao()
            val account = DataAccount(loginInput.text.toString(), emailInput.text.toString(),
                passwordInput.text.toString(),
                "Add Description", null, "", "")
            if (profileInDb(accountDao!!, account)) {
                accountDao.insertAccount(account)

            navController.popBackStack()
        } }else{
            val toast = Toast.makeText(context,
                "Input lines are empty or take up very few characters",
                Toast.LENGTH_SHORT)
            toast.show()
        }

    }
    //Объявляем все переменные и присваем обработчик
    private fun initView(){
        loginInput = myFragment.findViewById(R.id.loginReg)
        emailInput = myFragment.findViewById(R.id.emailReg)
        passwordInput = myFragment.findViewById(R.id.passwordReg)
        regBut = myFragment.findViewById(R.id.regbutton)
        regBut.setOnClickListener(this)
    }

    //Проверяем, пустое ли поле для ввода
    private fun emptyIn(): Boolean{
        return (loginInput.text.toString() != "" && loginInput.text.length > 4 &&
                emailInput.text.toString()!= "" && emailInput.text.length > 8
                && passwordInput.text.toString()!= "" && passwordInput.text.length > 6)
    }
    //Проверяем, есть ли в базе данных логин, который вводит пользователь
    private fun profileInDb(acDao: DataAccountDao, ac:DataAccount) : Boolean{
        val allAcc: List<AccountInData> = acDao.getLoginInfo()!!
        for(item in allAcc){
            if (item.login == ac.login){
                return false
            }
        }
        return true
    }

}
