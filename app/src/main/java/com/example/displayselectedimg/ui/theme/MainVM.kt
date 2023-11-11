package com.example.displayselectedimg.ui.theme

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVM: ViewModel() {
    // FlowでimgUriを流す
    val imgUri = MutableLiveData<Uri>()

    // imgUriを更新する
    fun updateImgUri(uri: Uri) {
        imgUri.value = uri
    }
}