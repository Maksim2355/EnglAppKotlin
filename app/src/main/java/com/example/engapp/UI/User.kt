package com.example.engapp.UI

data class User(val APP_PREFERENCES: String = "user",
         val APP_PREFERENCES_ACTIVE: String = "active",
         var active: Boolean = false,
         val APP_PREFERENCES_ID: String = "id",
         var id: Int = 0) {
}