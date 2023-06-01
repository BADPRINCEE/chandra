package com.example.chandra

import com.google.firebase.database.Exclude

data class Wisata(
    var name:String? = null,
    var imageUrl:String? = null,
    var description:String? = null,
    @get:Exclude
    @set:Exclude
    var key:String? = null
)