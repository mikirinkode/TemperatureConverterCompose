package com.mikirinkode.tempconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun TempConverterApp(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column(modifier) {
        StatefulTemperatureInput()
        StatelessTemperatureInput(
            input = input,
            output = output,
            onValueChange = { newInput ->
                input = newInput
                output = convertToFahrenheit(newInput)
            })
        TwoWayConverter()
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TempConverterAppPreview() {
    TempConverterTheme {
        TempConverterApp()
    }
}

@Composable
fun StatefulTemperatureInput(
    modifier: Modifier = Modifier,
) {
    var input by remember {
        mutableStateOf("")
    }

    var output by remember {
        mutableStateOf("")
    }

    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.stateful_converter),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = input,
            onValueChange = { newValue ->
                input = newValue
                output = convertToFahrenheit(newValue)
            },
            label = { Text(stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun StatelessTemperatureInput(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {


    Column(modifier.padding(16.dp)) {
        Text(text = "Stateless Converter", style = MaterialTheme.typography.h5)
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            label = { Text(stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(
                    text = stringResource(
                        id = R.string.enter_temperature,
                        scale.scaleName
                    )
                )
            }
        )
    }
}

@Composable
fun TwoWayConverter(modifier: Modifier = Modifier) {
    var celsius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf("") }

    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.two_way_converter),
            style = MaterialTheme.typography.h5
        )
        GeneralTemperatureInput(scale = Scale.CELSIUS, input = celsius, onValueChange = { newInput ->
            celsius = newInput
            fahrenheit = convertToFahrenheit(newInput)
        })
        GeneralTemperatureInput(scale = Scale.FAHRENHEIT, input = fahrenheit, onValueChange = { newInput ->
            fahrenheit = newInput
            celsius = convertToCelsius(newInput)
        })
    }
}

fun convertToFahrenheit(celsius: String): String {
    return celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()
}

fun convertToCelsius(fahrenheit: String): String {
    return fahrenheit.toDoubleOrNull()?.let {
        (it - 32) * 5 / 9
    }.toString()
}

enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}