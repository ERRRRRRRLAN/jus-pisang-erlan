package com.example.jus_pisang

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.jus_pisang.ui.theme.JuspisangTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuspisangTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        JusPisangApp()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JusPisangApp(modifier:Modifier = Modifier) {
    var step by remember { mutableStateOf(0) }
    var clickCount by remember {mutableStateOf(0)}
    var targetClicks by remember {mutableStateOf((1..5).random())}
    val imageResource = when (step){
        0 -> R.drawable.pisang
        1 -> R.drawable.blender
        2 -> R.drawable.gelas_pisang
        else -> R.drawable.gelas
    }
    val descriptionText = when (step) {
        0 -> "Tekan tombol gambar pisang untuk mengupasnya! (Sisa klik: ${targetClicks - clickCount})"
        1 -> "Pisang sudah masuk ke blender! Tekan gambar ${targetClicks - clickCount} kali untuk putar blade blender."
        2 -> "Pisang sudah jadi dan masuk kedalam gelas, tekan gambar untuk minum jus pisang."
        else -> "Gelas sudah kosong! Tekan tombol untuk mengulang lagi."
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                windowInsets = WindowInsets(top=15.dp),
                modifier = Modifier.height(60.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Banana Smoothies",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ){ innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    when (step) {
                        0 -> {
                            clickCount++
                            if (clickCount >= targetClicks) {
                                step = 1
                                clickCount = 0
                                targetClicks = (1..5).random()
                            }
                        }
                        1 -> {
                            clickCount++
                            if (clickCount >= targetClicks) {
                                step = 2
                            }
                        }
                        2 -> {
                            step = 3
                        }
                        3 -> {
                            step = 0
                            clickCount = 0
                            targetClicks = (1..5).random()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = descriptionText,
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = descriptionText,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JuspisangTheme {
        JusPisangApp(modifier = Modifier)
    }
}