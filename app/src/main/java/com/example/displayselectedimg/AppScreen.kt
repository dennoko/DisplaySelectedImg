package com.example.displayselectedimg

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    val vm = remember { MainVM() }
    val uri: Uri? by vm.imgUri.observeAsState(null)

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        Text(text = "This app displays the image you selected from your phone's gallery.")
        Spacer(modifier = Modifier.height(16.dp))

        ImgSelectButton(vm = vm)
        Spacer(modifier = Modifier.height(16.dp))

        if(uri != null) {
            AsyncImage(model = uri.toString(), contentDescription = "selected image")
        }
    }
}

@Composable
fun ImgSelectButton(vm: MainVM) {
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if(uri != null) {
            vm.updateImgUri(uri)
        }
    }

    Button(
        onClick = {
            getContent.launch("image/*")
        }
    ) {
        Text(text = "Select an image")
    }
}