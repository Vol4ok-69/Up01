package com.example.collegeschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.collegeschedule.data.theme.CollegeScheduleTheme
import com.example.collegeschedule.ui.schedule.ScheduleScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CollegeScheduleTheme {
                ScheduleScreen()
            }
        }
    }
}