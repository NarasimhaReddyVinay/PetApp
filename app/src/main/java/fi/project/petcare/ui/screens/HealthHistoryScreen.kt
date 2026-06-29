package fi.project.petcare.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import fi.project.petcare.model.data.HealthRecord
import fi.project.petcare.model.data.HealthRecordType
import java.util.*

@Composable
fun HealthHistoryScreen() {
    val records = remember {
        listOf(
            HealthRecord(HealthRecordType.WEIGHT_MEASUREMENT, Date(1704067200000), "5.2"),
            HealthRecord(HealthRecordType.WEIGHT_MEASUREMENT, Date(1706745600000), "5.8"),
            HealthRecord(HealthRecordType.WEIGHT_MEASUREMENT, Date(1709251200000), "6.3"),
            HealthRecord(HealthRecordType.WEIGHT_MEASUREMENT, Date(1711929600000), "6.5"),
            HealthRecord(HealthRecordType.VETERINARIAN_VISIT, Date(1711929600000), "Annual checkup"),
            HealthRecord(HealthRecordType.MEDICATION, Date(1712534400000), "Flea treatment")
        ).sortedByDescending { it.date }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Record")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Weight Growth Chart", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                WeightChart(records.filter { it.type == HealthRecordType.WEIGHT_MEASUREMENT })
            }

            item {
                Text("Upcoming Reminders", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                ReminderCard("Vaccination Booster", "In 12 days", Icons.Default.Notifications)
            }

            item {
                Text("Health History", style = MaterialTheme.typography.titleLarge)
            }

            items(records) { record ->
                HealthRecordItem(record)
            }
        }
    }
}

@Composable
fun WeightChart(weightRecords: List<HealthRecord>) {
    val weights = weightRecords.mapNotNull { it.details.toDoubleOrNull() }.reversed()
    if (weights.isEmpty()) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)) {
            val maxWeight = (weights.maxOrNull() ?: 0.0).toFloat() + 1f
            val minWeight = (weights.minOrNull() ?: 0.0).toFloat() - 1f
            val range = maxWeight - minWeight
            
            val width = size.width
            val height = size.height
            val stepX = width / (weights.size - 1).coerceAtLeast(1)

            val path = Path().apply {
                weights.forEachIndexed { i, weight ->
                    val x = i * stepX
                    val y = height - ((weight.toFloat() - minWeight) / range * height)
                    if (i == 0) moveTo(x, y) else lineTo(x, y)
                }
            }

            drawPath(
                path = path,
                color = Color(0xFF4CAF50),
                style = Stroke(width = 3.dp.toPx())
            )
            
            // Draw points
            weights.forEachIndexed { i, weight ->
                val x = i * stepX
                val y = height - ((weight.toFloat() - minWeight) / range * height)
                drawCircle(Color(0xFF4CAF50), radius = 4.dp.toPx(), center = Offset(x, y))
            }
        }
    }
}

@Composable
fun ReminderCard(title: String, time: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(time, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun HealthRecordItem(record: HealthRecord) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val color = when (record.type) {
                HealthRecordType.WEIGHT_MEASUREMENT -> Color.Gray
                HealthRecordType.VETERINARIAN_VISIT -> Color.Blue
                HealthRecordType.MEDICATION -> Color.Magenta
                else -> Color.Green
            }
            Surface(modifier = Modifier.size(12.dp), shape = CircleShape, color = color) {}
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(record.type.name.replace("_", " "), style = MaterialTheme.typography.bodySmall)
                Text(record.details, style = MaterialTheme.typography.bodyLarge)
                Text(record.date.toString(), style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
