package fi.project.petcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fi.project.petcare.R

@Composable
fun CommunityScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Feed", "Groups", "Meetups")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> SocialFeed()
                1 -> GroupsList()
                2 -> MeetupsList()
            }

            FloatingActionButton(
                onClick = { /* TODO: Add post/group/meetup */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}

@Composable
fun SocialFeed() {
    val posts = listOf(
        Post("Anna K.", "My golden retriever puppy just finished his first training session!", "2h ago", 124, 12),
        Post("Markus L.", "Found a great new pet-friendly cafe in Helsinki center.", "5h ago", 85, 4),
        Post("Sari M.", "Looking for recommendations for a good vet in Vantaa.", "1d ago", 42, 28)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(posts) { post ->
            PostCard(post)
        }
    }
}

data class Post(val author: String, val content: String, val time: String, val likes: Int, val comments: Int)

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(post.author.take(1), fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(post.author, style = MaterialTheme.typography.titleMedium)
                    Text(post.time, style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(post.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", modifier = Modifier.size(20.dp))
                    Text(" ${post.likes}", modifier = Modifier.padding(start = 4.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Comment", modifier = Modifier.size(20.dp))
                    Text(" ${post.comments}", modifier = Modifier.padding(start = 4.dp))
                }
                Icon(Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun GroupsList() {
    val groups = listOf("Golden Retriever Owners", "Helsinki Dog Walkers", "Cat Health Tips", "Puppy Training")
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(groups) { group ->
            ListItem(
                headlineContent = { Text(group) },
                supportingContent = { Text("${(10..500).random()} members") },
                leadingContent = {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                },
                trailingContent = {
                    Button(onClick = { }) { Text("Join") }
                }
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun MeetupsList() {
    val meetups = listOf(
        Meetup("Sunday Morning Run", "Kaivopuisto, Helsinki", "Sun, 10:00"),
        Meetup("Puppy Socialization", "Töölö Dog Park", "Sat, 14:00"),
        Meetup("Cat Lovers Meetup", "Kallio Library", "Fri, 18:00")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(meetups) { meetup ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(meetup.title, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp))
                        Text(meetup.location, style = MaterialTheme.typography.bodyMedium)
                    }
                    Text(meetup.time, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                        Text("I'm Interested")
                    }
                }
            }
        }
    }
}

data class Meetup(val title: String, val location: String, val time: String)
