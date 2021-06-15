package com.example.audiobooklistnermp.objects

data class PlaylistObject(
    val title : String = "No Title Provided",
    val videoIDs : List<String> = mutableListOf<String>(),
    val size : Int = videoIDs.size
)