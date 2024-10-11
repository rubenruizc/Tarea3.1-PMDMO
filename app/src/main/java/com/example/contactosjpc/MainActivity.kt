package com.example.contactosjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactosjpc.ui.theme.ContactosJPCTheme

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
                       Contacto( "Lo Celso",  "239458760",1),
                       Contacto( "Amaro",  "120987234",0),
                       Contacto( "Neymar",  "546789432",1),

                   )
               )
           }
        }
    }
}

@Composable
fun ItemList(ListaContacto: List<Contacto>){
    LazyColumn {
        items(ListaContacto){ itemContacto ->
            ContactoView(contacto = itemContacto)
        }
    }
}

@Composable
fun ContactoView(contacto: Contacto) {
    Card(Modifier.fillMaxWidth()) {
        Row {
            Column {
                Image(
                    painter = painterResource(id = if(contacto.imagen == 0)R.drawable.mujer else R.drawable.hombre),
                    contentDescription = "Foto contacto",
                    Modifier.height(100.dp)
                )
            }
            Column {
                Text(
                    text = contacto.nombre,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )}}}}




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

