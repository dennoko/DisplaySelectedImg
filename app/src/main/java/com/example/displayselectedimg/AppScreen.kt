package com.example.displayselectedimg

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.displayselectedimg.ui.theme.MainVM


@Composable
fun AppScreen() {
    // VMを作成する
    val vm = MainVM()
    // collectAsState()でFlowを監視する
    val uri: Uri? by vm.imgUri.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {

        Text(text = "This app displays the image you selected from your phone's gallery.")
        Spacer(modifier = Modifier.height(16.dp))

        // 画像を選択するボタン
        ImgSelectButton(vm = vm)
        Spacer(modifier = Modifier.height(16.dp))

        // 選択した画像を表示する
        uri?.let {
            Log.d("hoge", "AppScreen() 画像の表示")
            // AsyncImageはcoil-composeライブラリの関数
            AsyncImage(model = uri.toString(), contentDescription = null)
        }
    }
}

@Composable
fun ImgSelectButton(vm: MainVM) {
    // rememberLauncherForActivityResult()はregisterForActivityResult()のラッパー関数
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if(uri != null) {
            // 選択した画像でURIを更新する
            vm.updateImgUri(uri)
        }
    }

    Button(
        onClick = {
            // 画像を選択する
            getContent.launch("image/*")
        }
    ) {
        Text(text = "Select an image")
    }
}