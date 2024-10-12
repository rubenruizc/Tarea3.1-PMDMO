package com.example.contactosjpc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }

    @Composable
    fun LoginScreen() {
        // Variables de estado para los campos de entrada
        var usernameInput by remember { mutableStateOf("") }  // Cambia a "by" para un comportamiento más sencillo
        var passwordInput by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf("") }  // Para mostrar mensajes de error

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Iniciar Sesión", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de entrada para el nombre de usuario
            OutlinedTextField(
                value = usernameInput,
                onValueChange = { usernameInput = it }, // Actualiza el valor al escribir
                label = { Text("Nombre de usuario") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de entrada para la contraseña
            OutlinedTextField(
                value = passwordInput,
                onValueChange = { passwordInput = it }, // Actualiza el valor al escribir
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            // Mostrar mensaje de error si hay uno
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de inicio de sesión
            Button(
                onClick = {
                    // Verificar las credenciales
                    if (usernameInput == "admin" && passwordInput == "1234") {
                        // Navegar a la pantalla de contactos
                        val intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Cierra la actividad de inicio de sesión
                    } else {
                        // Mostrar error si las credenciales son incorrectas
                        errorMessage = "Usuario o contraseña incorrectos"
                    }
                }
            ) {
                Text("Iniciar Sesión")
            }
        }
    }
}