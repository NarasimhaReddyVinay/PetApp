package fi.project.petcare.ui.screens.skills

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fi.project.petcare.domain.model.Skill
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SkillScreen(
    petId: String,
    viewModel: SkillViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(petId) {
        viewModel.handleIntent(SkillContract.Intent.LoadSkills(petId))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SkillContract.Effect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { 
                viewModel.handleIntent(SkillContract.Intent.AddSkill(petId, "New Trick", "Teach your pet something new"))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Skill")
            }
        }
    ) { padding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Training Progress",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Track your pet's skills and obedience training.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(state.skills) { skill ->
                    SkillCard(
                        skill = skill,
                        onProgressChange = { 
                            viewModel.handleIntent(SkillContract.Intent.UpdateProgress(skill.id, it))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SkillCard(
    skill: Skill,
    onProgressChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(text = skill.title, style = MaterialTheme.typography.titleLarge)
                    Text(text = skill.category.name, style = MaterialTheme.typography.labelMedium)
                }
                if (skill.isCompleted) {
                    Icon(Icons.Default.Star, contentDescription = "Completed", tint = Color(0xFFFFD700))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = skill.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { skill.progress / 100f },
                    modifier = Modifier.weight(1f),
                    color = if (skill.isCompleted) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${skill.progress}%")
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Slider(
                value = skill.progress.toFloat(),
                onValueChange = { onProgressChange(it.toInt()) },
                valueRange = 0f..100f,
                steps = 10
            )
        }
    }
}
