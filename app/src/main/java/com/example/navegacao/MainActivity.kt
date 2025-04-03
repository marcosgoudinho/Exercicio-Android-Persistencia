package com.example.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.*
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreasureHuntApp()
        }
    }
}

@Composable
fun TreasureHuntApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("clue1") { ClueScreen(navController, "Qual é o maior planeta do sistema solar?", "Júpiter", "clue2") }
        composable("clue2") { ClueScreen(navController, "Quantos continentes existem?", "7", "clue3") }
        composable("clue3") { ClueScreen(navController, "Que cor resulta da mistura de azul e amarelo?", "Verde", "treasure") }
        composable("treasure") { TreasureScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo à Caça ao Tesouro!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("clue1") }) {
            Text("Iniciar Caça ao Tesouro")
        }
    }
}

@Composable
fun ClueScreen(navController: NavController, clue: String, answer: String, nextRoute: String) {
    var userAnswer by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(clue)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Digite sua resposta") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { if (userAnswer.text.equals(answer, ignoreCase = true)) navController.navigate(nextRoute) }) {
            Text("Próxima Pista")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun TreasureScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Parabéns! Você encontrou o tesouro!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Reiniciar Caça")
        }
    }
}