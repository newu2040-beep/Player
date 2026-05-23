package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.VideoFile
import com.example.data.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VideoRepository(application)

    private val _videoList = MutableStateFlow<List<VideoFile>>(emptyList())
    val videoList: StateFlow<List<VideoFile>> = _videoList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadVideos() {
        viewModelScope.launch {
            _isLoading.value = true
            val videos = repository.getAllVideos()
            _videoList.value = videos
            _isLoading.value = false
        }
    }
}
