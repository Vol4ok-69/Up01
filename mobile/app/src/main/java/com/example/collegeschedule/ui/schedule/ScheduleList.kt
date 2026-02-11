package com.example.collegeschedule.ui.schedule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.collegeschedule.data.dto.ScheduleByDateDto

@Composable
fun ScheduleList(data: List<ScheduleByDateDto>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(data) { day ->

            Text(
                text = "${day.lessonDate} (${day.weekday})",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            if (day.lessons.isEmpty()) {
                Text(
                    text = "No lessons",
                    modifier = Modifier.padding(start = 16.dp)
                )
            } else {

                day.lessons.forEach { lesson ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(Modifier.padding(8.dp)) {

                            Text("Lesson ${lesson.lessonNumber}")
                            Text(lesson.time)

                            lesson.groupParts.forEach { (part, info) ->
                                if (info != null) {
                                    Text("$part: ${info.subject}")
                                    Text(info.teacher)
                                    Text("${info.building}, ${info.classroom}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
