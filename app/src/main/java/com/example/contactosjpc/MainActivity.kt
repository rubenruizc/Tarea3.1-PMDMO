package com.example.contactosjpc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactosjpc.ui.theme.ContactosJPCTheme
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           MaterialTheme{
               ItemList(
                   ListaContacto = listOf(
                       Contacto("Amaru", "123456789",0),
                       Contacto( "Jenri", "987654321",0),
                       Contacto( "Messi",  "875643928",1),
                       Contacto("Cristiano",  "234098749",1),
                       Contacto( "Joaquin",  "120904398",1),
                       Contacto("Fekir",  "876453098",1),
                       Contacto( "LoCelso",  "239458760",1),
                       Contacto( "Amaro",  "120987234",0),
                       Contacto( "Neymar",  "546789432",1),
                       Contacto( "Sabaly",  "564386542",1)

                   )
               )
           }
        }
    }
}


@Composable
fun ItemList(ListaContacto: List<Contacto>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(ListaContacto.size) { index ->
            // Pasar el contacto y el estado actual de mostrarDetalles
            ContactoView(
                contacto = ListaContacto[index],
                onIconClick = {
                    ListaContacto[index].mostrarDetalles = !ListaContacto[index].mostrarDetalles
                }
            )
        }
    }
}


@Composable
fun ContactoView(contacto: Contacto, onIconClick: () -> Unit) {
    var mostrarDetalles by remember { mutableStateOf(contacto.mostrarDetalles) }
    val context = LocalContext.current // Obtiene el contexto

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .animateContentSize(),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onIconClick()
                    mostrarDetalles = !mostrarDetalles // Actualizar el estado local
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono que se puede hacer clic
            Image(
                painter = painterResource(id = if (contacto.imagen == 0) R.drawable.mujer else R.drawable.hombre),
                contentDescription = "Foto contacto",
                modifier = Modifier
                    .height(100.dp)
                    .padding(end = 16.dp)
            )

            // Mostrar iniciales o detalles según el estado dentro del contacto
            if (mostrarDetalles) {
                Column {
                    Text(
                        text = contacto.nombre,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = contacto.telefono,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { realizarLlamada(context, contacto.telefono) } // Llamar a la función de realizar llamada
                            .padding(4.dp), // Añadir un poco de padding
                        color = MaterialTheme.colorScheme.primary // Cambia el color del texto para indicar que es clicable
                    )
                }
            } else {
                val iniciales = contacto.nombre.split(" ").map { it.first() }.joinToString("")
                Text(
                    text = iniciales,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }
    }
}

// Función para realizar la llamada
private fun realizarLlamada(context: Context, telefono: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$telefono") // Crear la URI del número de teléfono
    }
    context.startActivity(intent) // Usar el contexto para iniciar la actividad
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactosJPCTheme {
        Greeting("Android")
    }
}

