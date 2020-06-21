package com.example.engapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.engapp.database.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), StatePermission {

    private val REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE: Int = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getStatePermissionRead()

        val bottomNavigation : BottomNavigationView = findViewById(R.id.nav_view)
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        val db: AppDatabase = App.instance!!.database!!
        val userDao = db.userDao()
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

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getStatePermissionRead(): Boolean {
        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE )
        return if (permissionStatus == PackageManager.PERMISSION_GRANTED){
            true;
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE)
            false;
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                }
                else {
                    Toast.makeText(this, "Разрешение на чтения нет", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }


}
