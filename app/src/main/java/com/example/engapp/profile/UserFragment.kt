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

class UserFragment : Fragment(), View.OnClickListener {
    //Преобразуем наш фрагмент во View и получаем контроллер фрагментов
    private var  myFragment: View? = null
    private var navController: NavController? = null
    //Кнопка добавления поста и выхода из аккаунта
    private lateinit var addContent: Button
    private lateinit var signOut: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Делаем преобразование в onCreate
        myFragment =
            inflater.inflate(R.layout.fragment_user, container, false)
        //Получаем контроллер для передвижения по фрагментам
        drawUI()
        init()

        // Inflate the layout for this fragment
        return myFragment
    }


    private fun init(){
        navController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }!!
        //Кнопка, с помощью которой переключаемся на фрагмент, с помощью которой добавляем работу
        addContent = myFragment!!.findViewById(R.id.AddContentBut)
        signOut = myFragment!!.findViewById(R.id.SignOutBut)
        addContent.setOnClickListener(this)
        signOut.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.AddContentBut -> {navController!!.navigate(R.id.addWorkFragment)}
            R.id.SignOutBut -> {

            }
        }

    }

    private fun drawUI() {

    }


}
