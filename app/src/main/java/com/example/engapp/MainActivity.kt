package com.example.engapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation : BottomNavigationView = findViewById(R.id.nav_view)
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNavigation.selectedItemId = R.id.works

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


        }
    }


}
