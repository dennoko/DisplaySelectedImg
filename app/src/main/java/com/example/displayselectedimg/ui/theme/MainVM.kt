package com.example.displayselectedimg.ui.theme

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainVM: ViewModel() {
    // 画像のURIを保持する
    private val _imgUri = MutableStateFlow<Uri?>(null)
    // imgUriをFlowとして公開する
    val imgUri = _imgUri.asStateFlow()

    // imgUriを更新する
    fun updateImgUri(uri: Uri) {
        _imgUri.value = uri
    }
}