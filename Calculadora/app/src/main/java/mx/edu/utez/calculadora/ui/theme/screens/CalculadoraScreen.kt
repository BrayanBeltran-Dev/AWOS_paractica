package mx.edu.utez.calculadora.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CalculadoraScreen() {
    var primerNumero by remember { mutableStateOf("") }
    var operador by remember { mutableStateOf("") }
    var segundoNumero by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resultado: $resultado")

        Row {
            Button(onClick = { primerNumero += "7" }) { Text("7") }
            Button(onClick = { primerNumero += "8" }) { Text("8") }
            Button(onClick = { primerNumero += "9" }) { Text("9") }
            Button(onClick = { operador = "*" }) { Text("*") }
        }

        Row {
            Button(onClick = { primerNumero += "4" }) { Text("4") }
            Button(onClick = { primerNumero += "5" }) { Text("5") }
            Button(onClick = { primerNumero += "6" }) { Text("6") }
            Button(onClick = { operador = "/" }) { Text("/") }
        }

        Row {
            Button(onClick = { primerNumero += "1" }) { Text("1") }
            Button(onClick = { primerNumero += "2" }) { Text("2") }
            Button(onClick = { primerNumero += "3" }) { Text("3") }
            Button(onClick = { operador = "+" }) { Text("+") }
        }

        Row {
            Button(onClick = {
                primerNumero = ""
                operador = ""
                segundoNumero = ""
                resultado = ""
            }) { Text("AC") }

            Button(onClick = { primerNumero += "0" }) { Text("0") }

            Button(onClick = {
                val num1 = primerNumero.toDoubleOrNull() ?: 0.0
                val num2 = segundoNumero.toDoubleOrNull() ?: 0.0
                resultado = when (operador) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Error"
                    else -> "0"
                }.toString()
            }) { Text("=") }

            Button(onClick = { operador = "-" }) { Text("-") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview(){
    CalculadoraScreen()
}