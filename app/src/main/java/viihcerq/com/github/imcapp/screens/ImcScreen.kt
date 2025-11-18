package viihcerq.com.github.imcapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.DecimalFormat

@Composable
fun ImcScreen(modifier: Modifier = Modifier, navController: NavController) {

    var nome by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFA9A9A9))
            .padding(32.dp)
    ) {
        Text(
            text = "Calculadora IMC",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Seu nome") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Seu Peso (kg)") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Sua altura (m)") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val p = peso.toDoubleOrNull()
                    val a = altura.toDoubleOrNull()
                    if (nome.isNotBlank() && p != null && a != null) {
                        val imc = p / (a * a)
                        val df = DecimalFormat("#.##")
                        resultado = "$nome, seu IMC é ${df.format(imc)}"
                    } else {
                        resultado = "Valores inválidos"
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Text(text = "Calcular", fontSize = 20.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("menu") },
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Text(text = "Voltar", fontSize = 20.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(16.dp))

            resultado?.let {
                Text(text = "Resultado: $it", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}