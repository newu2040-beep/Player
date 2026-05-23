package com.example.data

import android.net.Uri

data class VideoFile(
    val id: Long,
    val title: String,
    val path: String,
    val uri: Uri,
    val duration: Long,
    val size: Long,
    val dateAdded: Long,
    val resolution: String? = null
)
