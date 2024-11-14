package com.example.locapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locapp.ui.theme.LocAppTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()
        setContent {
            val viewModel: LocationViewModel = viewModel()
            LocAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding: PaddingValues ->
                    NavigationScreen(
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
            }
            else -> {requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)}
        }
    }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            getLastLocation()
        } else {
            Toast.makeText(this, "Разрешение на доступ к местоположению отклонено. Функция не будет работать.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLastLocation() {}

}
