package com.example.displayselectedimg.ui.theme

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainVM: ViewModel() {
    // 画像のURIを格納する
    val imgUri = MutableStateFlow<Uri?>(null)

    // imgUriを更新する
    fun updateImgUri(uri: Uri) {
        imgUri.value = uri
    }
}