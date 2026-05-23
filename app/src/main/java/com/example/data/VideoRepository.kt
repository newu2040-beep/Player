package com.example.data

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(private val context: Context) {

    suspend fun getAllVideos(): List<VideoFile> = withContext(Dispatchers.IO) {
        val videoList = mutableListOf<VideoFile>()
        
        val collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.RESOLUTION
        )
        
        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"
        
        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val resolutionColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)
            
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn) ?: "Unknown"
                val path = cursor.getString(pathColumn) ?: ""
                val duration = cursor.getLong(durationColumn)
                val size = cursor.getLong(sizeColumn)
                val dateAdded = cursor.getLong(dateAddedColumn)
                val resolution = runCatching { cursor.getString(resolutionColumn) }.getOrNull()
                
                val uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                
                videoList.add(
                    VideoFile(
                        id = id,
                        title = name,
                        path = path,
                        uri = uri,
                        duration = duration,
                        size = size,
                        dateAdded = dateAdded,
                        resolution = resolution
                    )
                )
            }
        }
        
        videoList
    }
}
