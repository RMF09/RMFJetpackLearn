package com.example.rmfjetpacklearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.rmfjetpacklearn.ui.theme.RMFJetpackLearnTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(Modifier.fillMaxSize()) {

                val color = remember {
                    mutableStateOf(Color.Blue)
                }

                State(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    color.value = it
                }

                Box(
                    modifier = Modifier
                        .background(color.value)
                        .weight(1f)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun State(modifier: Modifier = Modifier, updatedColor: (Color) -> Unit) {

    Box(modifier = modifier
        .background(Color.Green)
        .clickable {
            updatedColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat()
                )
            )
        }) {

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RMFJetpackLearnTheme {

    }
}