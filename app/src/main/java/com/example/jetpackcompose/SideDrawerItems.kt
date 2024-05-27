package com.example.jetpackcompose

data class SideDrawerItems(
    val id : String,
    val heading : String,
    val itemList : ArrayList<SideDrawerItems> ? = null
)
