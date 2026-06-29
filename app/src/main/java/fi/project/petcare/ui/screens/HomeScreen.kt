package fi.project.petcare.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Emergency
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.project.petcare.model.data.User

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        user = User(
            id = "456fdsfs-456fd4sf-4f5d6sfds-45f64ds",
            name = "John Doe",
            email = "efdscv"
        )
    )
}

class Veterinarian(
    val name: String,
    val location: String,
    val rating: Float,
    val imageUrl: String,
)

val veterinarianList = listOf(
    Veterinarian(
        name = "Dr. Jane Johnson",
        location = "Vantaa",
        rating = 4.6f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    Veterinarian(
        name = "Dr. John Brown",
        location = "Helsinki",
        rating = 4.7f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    Veterinarian(
        name = "Dr. Jane Brown",
        location = "Espoo",
        rating = 4.1f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    Veterinarian(
        name = "Dr. John White",
        location = "Vantaa",
        rating = 4.4f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    Veterinarian(
        name = "Dr. Jane White",
        location = "Helsinki",
        rating = 4.9f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
)

class HairSalon(
    val name: String,
    val location: String,
    val rating: Float,
    val imageUrl: String,
)

val hairSalonList = listOf(
    HairSalon(
        name = "Hair Salon 1",
        location = "Vantaa",
        rating = 4.6f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    HairSalon(
        name = "Hair Salon 2",
        location = "Helsinki",
        rating = 4.7f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    HairSalon(
        name = "Hair Salon 3",
        location = "Espoo",
        rating = 4.1f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    HairSalon(
        name = "Hair Salon 4",
        location = "Vantaa",
        rating = 4.4f,
        imageUrl = "https://www.example.com/image.jpg"
    ),
    HairSalon(
        name = "Hair Salon 5",
        location = "Helsinki",
        rating = 4.9f,
        imageUrl = "https://www.example.com/image.jpg"
    ),

)

@Composable
fun HomeScreen(
    user: User,
    onNavigateToAi: () -> Unit = {}
) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)

                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(75.dp)
                    )
                    Column {
                        Text(
                                text = ("Hi " + if (user.name == "null") "there!" else user.name?.trim('"')),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        Text(
                            text = user.email,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                onClick = onNavigateToAi,
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.AutoAwesome, contentDescription = null, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("AI Pet Assistant", style = MaterialTheme.typography.titleMedium)
                        Text("Ask questions about your pet's health and behavior.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* TODO: Emergency Call */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Outlined.Emergency, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("EMERGENCY: FIND NEAREST VET")
            }
            Text(
                text = "Nearby",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        item {
             Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
             ) {
                 Text(
                     text = "Veterinarians",
                        style = MaterialTheme.typography.titleMedium,
                 )
                 TextButton(
                     onClick = { /*TODO*/ }
                 ) {
                     Text(text = "See all")
                 }
             }
        }
        items(veterinarianList.size) { veterinarian ->
            VeterinarianCard(
                veterinarian = veterinarianList[veterinarian],
                onClick = { /* Navigate to veterinarian profile */ }
            )
        }
        item {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Grooming Salons",
                    style = MaterialTheme.typography.titleMedium,
                )
                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "See all")
                }
            }
        }
        items(hairSalonList.size) { salon ->
            HairSalonCard(
                hairSalon = hairSalonList[salon],
                onClick = { /* Navigate to hair salon profile */ }
            )
        }
    }
}

@Composable
fun HairSalonCard(
    hairSalon: HairSalon,
    onClick: () -> Unit
) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Store,
                contentDescription = "Store Icon",
                modifier = Modifier.size(65.dp)
            )
            Column (
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = hairSalon.name,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = hairSalon.location,
                    )
                    Text(
                        text = "Rating: ${hairSalon.rating}",
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.align(Alignment.End),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                ) {
                    Text("Book Now")
                }
            }
        }

    }
}

@Composable
fun VeterinarianCard(
    veterinarian: Veterinarian,
    onClick: () -> Unit
) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Store,
                contentDescription = "Store Icon",
                modifier = Modifier.size(65.dp)
            )
            Column (
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = veterinarian.name,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = veterinarian.location,
                    )
                    Text(
                        text = "Rating: ${veterinarian.rating}",
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.align(Alignment.End),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                ) {
                    Text("Book Now")
                }
            }
        }
    }
}
