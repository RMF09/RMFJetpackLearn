package com.example.rmfjetpacklearn

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.rmfjetpacklearn.ui.theme.RMFJetpackLearnTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RMFJetpackLearnTheme {
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )

                val lifeCycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifeCycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        if(event == Lifecycle.Event.ON_START){
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }
                    lifeCycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifeCycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionState.permissions.forEach { perm ->
                        when (perm.permission) {
                            Manifest.permission.CAMERA -> {
                                when (perm.status) {
                                    is PermissionStatus.Granted -> {
                                        Text(text = "Camera permission accepted")

                                    }
                                    is PermissionStatus.Denied -> {
                                        val text =
                                            if ((perm.status as PermissionStatus.Denied).shouldShowRationale)
                                                "The camera is important for this app. Please grant the permission."
                                            else
                                                "Camera permission required for this feature to be available ." +
                                                        "Please grant the permission"

                                        Text(text = text)

                                    }
                                }
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                when (perm.status) {
                                    is PermissionStatus.Granted -> {
                                        Text(text = "Record Audio permission accepted")

                                    }
                                    is PermissionStatus.Denied -> {
                                        val text =
                                            if ((perm.status as PermissionStatus.Denied).shouldShowRationale)
                                                "The record audio is important for this app. Please grant the permission."
                                            else
                                                "Record audio permission required for this feature to be available. " +
                                                        "Please grant the permission"

                                        Text(text = text)

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RMFJetpackLearnTheme {
    }
}