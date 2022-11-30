package com.mikirinkode.tempconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mikirinkode.tempconverter.theme.TempConverterTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // supaya bisa menambahkan composable function di dalam Activity
        setContent {
            // A Theme untuk mengatur tema yang digunakan
            TempConverterTheme {
                TempConverterApp()
            }
        }
    }
}

@Composable
fun TempConverterApp() {

}

@Preview(showBackground = true)
@Composable
fun TempConverterAppPreview() {
    TempConverterTheme {
        TempConverterApp()
    }
}