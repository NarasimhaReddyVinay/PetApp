package fi.project.petcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.CropRotate
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.ModelTraining
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.project.petcare.R
import fi.project.petcare.ui.nav.Screen

@Preview
@Composable
fun PreviewProfileScreen() {
//    ProfileScreen(petName = "Fluppy")
}

@Composable
fun ProfileScreen(petName: String, navController: NavController) {
    var showQrDialog by remember { mutableStateOf(false) }
    var isLost by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Cover photo
                Image(
                    painter = painterResource(id = R.drawable.pet_icon_1),
                    contentDescription = "Pet Cover Photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 20.dp),
                    contentScale = ContentScale.Crop
                )
                // Pet profile details
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Basic Info",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "Edit",
                        )
                    }
                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Column for Icon, Name, and Fluppy
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.Pets,
                                    contentDescription = "Name Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Name",
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                    Text(
                                        text = petName,
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.Male,
                                    contentDescription = "sex",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Sex",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "Male",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.MonitorWeight,
                                    contentDescription = "Weight",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Weight:",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "10 kg",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                        }

                        // Column for Species and Breed
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.Animation,
                                    contentDescription = "Species",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Species",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "Dog",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.Pets,
                                    contentDescription = "Name Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Breed",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "Golden Retriever",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "birthdate",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Birthdate:",
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                    Text(
                                        text = "30/08/2020",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                        }
                    }
                    HorizontalDivider()
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Insurance",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "Edit"
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CropRotate,
                                    contentDescription = "Name Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Provider",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "PetPlan",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }

                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.Pets,
                                    contentDescription = "Name Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Policy Number",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "123456789",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                        }

                    }
                    HorizontalDivider()
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Diet",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "Edit"
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    // Add your icon here
                                    imageVector = Icons.Default.FoodBank,
                                    contentDescription = "food",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Food",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "Royal Canin",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }

                        }

                    }
                    HorizontalDivider()
                    Button(
                        onClick = { navController.navigate(Screen.HealthHistory.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.MedicalInformation, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("View Health History & Reminders")
                    }
                    
                    Button(
                        onClick = { navController.navigate(Screen.PetSkills.route) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Icon(Icons.Default.ModelTraining, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Pet Training & Skills")
                    }
                    
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { showQrDialog = true },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.QrCode, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Digital ID")
                        }
                        
                        Button(
                            onClick = { isLost = !isLost },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isLost) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(if (isLost) Icons.Default.ReportProblem else Icons.Default.Pets, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (isLost) "Report Found" else "Report Lost")
                        }
                    }
                }
            }
        }
    }
    
    if (showQrDialog) {
        AlertDialog(
            onDismissRequest = { showQrDialog = false },
            title = { Text("Pet Digital ID") },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("Scan to see $petName's profile and emergency contacts.")
                    Spacer(modifier = Modifier.height(16.dp))
                    // Mock QR Code
                    androidx.compose.foundation.Canvas(modifier = Modifier.size(150.dp)) {
                        drawRect(Color.Black, style = Stroke(width = 2.dp.toPx()))
                        // Draw some random squares to look like a QR code
                        val size = 150.dp.toPx()
                        val cellSize = size / 10
                        for (i in 0 until 10) {
                            for (j in 0 until 10) {
                                if ((i+j) % 3 == 0 || (i*j) % 5 == 0) {
                                    drawRect(
                                        Color.Black,
                                        topLeft = Offset(i * cellSize, j * cellSize),
                                        size = androidx.compose.ui.geometry.Size(cellSize, cellSize)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showQrDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}
