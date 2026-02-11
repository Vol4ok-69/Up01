package com.example.collegeschedule.ui.schedule

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.collegeschedule.data.dto.ScheduleByDateDto
import com.example.collegeschedule.data.network.RetrofitInstance
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import com.example.collegeschedule.data.dto.GroupDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen() {

    var groups by remember { mutableStateOf<List<GroupDto>>(emptyList()) }
    var selectedGroup by remember { mutableStateOf<GroupDto?>(null) }
    var schedule by remember { mutableStateOf<List<ScheduleByDateDto>>(emptyList()) }

    var expanded by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        loading = true
        try {
            groups = RetrofitInstance.api.getGroups()
            selectedGroup = groups.firstOrNull()
        } catch (e: Exception) {
            error = e.message
        } finally {
            loading = false
        }
    }

    LaunchedEffect(selectedGroup?.groupName
) {
        selectedGroup?.groupName
?.let { groupName ->
            loading = true
            try {
                schedule = RetrofitInstance.api.getSchedule(
                    groupName,
                    "2026-01-12",
                    "2026-01-17"
                )
            } catch (e: Exception) {
                error = e.message
            } finally {
                loading = false
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(selectedGroup?.groupName
 ?: "Select group")
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {

            // Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                OutlinedTextField(
                    value = selectedGroup?.groupName
 ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Group") },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    groups.forEach { group ->
                        DropdownMenuItem(
                            text = { Text(group.groupName ?: "Unknown") },
                            onClick = {
                                selectedGroup = group
                                expanded = false
                            }
                        )
                    }
                }
            }

            when {
                loading -> CircularProgressIndicator()
                error != null -> Text("Error: $error")
                else -> ScheduleList(schedule)
            }
        }
    }
}

