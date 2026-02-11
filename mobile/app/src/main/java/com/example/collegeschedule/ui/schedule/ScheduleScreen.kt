package com.example.collegeschedule.ui.schedule

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.collegeschedule.data.dto.ScheduleByDateDto
import com.example.collegeschedule.data.network.RetrofitInstance

@Composable
fun ScheduleScreen() {

    var schedule by remember { mutableStateOf<List<ScheduleByDateDto>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            schedule = RetrofitInstance.api.getSchedule(
                "ИС-12",
                "2026-01-12",
                "2026-01-17"
            )
        } catch (e: Exception) {
            error = e.message
        } finally {
            loading = false
        }
    }

    when {
        loading -> CircularProgressIndicator()
        error != null -> Text("Error: $error")
        else -> ScheduleList(schedule)
    }
}
