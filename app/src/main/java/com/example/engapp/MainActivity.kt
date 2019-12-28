package com.example.engapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.engapp.UI.User
import com.example.engapp.database.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var userPref: SharedPreferences
    // имя файла настроек
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = User()
        userPref = getSharedPreferences(user.APP_PREFERENCES, Context.MODE_PRIVATE)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.nav_view)
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).build()

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
                    if(user.active) {
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

    override fun onPause() {
        super.onPause()

        // Запоминаем данные
        val editor = userPref.edit()
        editor.putBoolean(user.APP_PREFERENCES_ACTIVE, user.active)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (userPref.contains(user.APP_PREFERENCES_ACTIVE)) {
            // Получаем число из настроек
            user.active = userPref.getBoolean(user.APP_PREFERENCES_ACTIVE, false)
        }
    }


}
