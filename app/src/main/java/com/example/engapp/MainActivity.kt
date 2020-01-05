package com.example.engapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.database.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation : BottomNavigationView = findViewById(R.id.nav_view)
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        val db: AppDatabase = App.instance!!.database!!
        val userDao = db.userDao()

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.favorite -> {
                    navController.navigate(R.id.favoriteFragment)
                    true
                }
                R.id.works -> {
                    navController.navigate(R.id.worksFragment)
                    true
                }
                R.id.profile ->{
                    if(userDao?.getUserData()?.userId != null) {
                        navController.navigate(R.id.userFragment)
                    }else{
                        navController.navigate(R.id.profileFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }


}
