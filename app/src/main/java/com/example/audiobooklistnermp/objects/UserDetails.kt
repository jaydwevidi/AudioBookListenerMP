package com.example.audiobooklistnermp.objects

class UserDetails(
    val name: String,
    val email: String,
    val listOfPlaylists : List<PlaylistObject> = mutableListOf()
    )
