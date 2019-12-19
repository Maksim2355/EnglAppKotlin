package com.example.engapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val TAG = "myLogs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "присваиваем обработчик кнопкам")

        var bottomNavigation : BottomNavigationView = findViewById(R.id.nav_view)
        var navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)


    }
}
