package com.example.locapp

import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationViewModel : ViewModel() {

    private val _currentLocation = MutableStateFlow<LatLng?>(null)
    val currentLocation: StateFlow<LatLng?> = _currentLocation

    private val _savedLocations = MutableStateFlow<List<LatLng>>(emptyList())
    val savedLocations: StateFlow<List<LatLng>> = _savedLocations

    fun updateUserLocation(location: Location) {
        _currentLocation.value = LatLng(location.latitude, location.longitude)
    }

    fun saveCurrentLocation() {
        _currentLocation.value?.let { location ->
            _savedLocations.value += location
        }
    }
}
