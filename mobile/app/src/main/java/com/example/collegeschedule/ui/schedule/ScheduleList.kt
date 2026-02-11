package com.example.collegeschedule.ui.schedule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.collegeschedule.data.dto.ScheduleByDateDto
import com.example.collegeschedule.utils.formatDate
import androidx.compose.material3.ElevatedCard

@Composable
fun ScheduleList(data: List<ScheduleByDateDto>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(data) { day ->

            Text(
                text = "${formatDate(day.lessonDate)} • ${day.weekday}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            if (day.lessons.isEmpty()) {
                Text(
                    text = "No lessons",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {

                day.lessons.forEach { lesson ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Lesson ${lesson.lessonNumber}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = lesson.time,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            lesson.groupParts.forEach { (part, info) ->
                                if (info != null) {

                                    Text(
                                        text = info.subject,
                                        style = MaterialTheme.typography.bodyLarge
                                    )

                                    Text(
                                        text = info.teacher,
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Text(
                                        text = "${info.building}, ${info.classroom}",
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
