package com.example.collegeschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.collegeschedule.data.local.FavoritesManager
import com.example.collegeschedule.data.theme.CollegeScheduleTheme
import com.example.collegeschedule.ui.schedule.ScheduleScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CollegeScheduleTheme {
                val context = LocalContext.current
                val favoritesManager = remember { FavoritesManager(context) }

                var selectedGroup by rememberSaveable {
                    mutableStateOf("ИС-22")
                }

                var favorites by remember {
                    mutableStateOf(favoritesManager.getFavorites().toList())
                }

                ScheduleScreen(
                    selectedGroup = selectedGroup,
                    onGroupSelected = { selectedGroup = it },
                    isFavorite = favorites.contains(selectedGroup),
                    onToggleFavorite = { group ->
                        if (favorites.contains(group)) {
                            favoritesManager.removeFavorite(group)
                        } else {
                            favoritesManager.addFavorite(group)
                        }
                        favorites = favoritesManager.getFavorites().toList()
                    }
                )
            }
        }
    }
}