package eu.tutorials.portfolioapp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for skill information
data class SkillData(@DrawableRes val icon: Int, val name: String, val level: Float = 0.75f)

@Composable
fun SkillScreen() {
    // Skills data with initial values
    var skills by remember {
        mutableStateOf(
            listOf(
                SkillData(R.drawable.ic_custom_skill, "Kotlin", 0.9f),
                SkillData(R.drawable.ic_custom_skill, "Jetpack Compose", 0.85f),
                SkillData(R.drawable.ic_custom_skill, "Android", 0.8f),
                SkillData(R.drawable.ic_custom_skill, "UI/UX", 0.7f),
                SkillData(R.drawable.ic_custom_skill, "Java", 0.75f),
                SkillData(R.drawable.ic_custom_skill, "Git", 0.8f)
            )
        )
    }

    // State for add skill dialog
    var showAddSkillDialog by remember { mutableStateOf(false) }
    var newSkillName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Section title
        Text(
            text = "My Skills",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Grid of skills - easier to understand than chunking in a lazy column
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(skills) { skill ->
                SkillCard(skill)
            }
        }

        // Add skill button at the bottom
        Button(
            onClick = { showAddSkillDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Skill",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Add New Skill")
        }
    }

    // Add skill dialog
    if (showAddSkillDialog) {
        AlertDialog(
            onDismissRequest = { showAddSkillDialog = false },
            title = { Text("Add New Skill") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newSkillName,
                        onValueChange = { newSkillName = it },
                        label = { Text("Skill Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newSkillName.isNotBlank()) {
                            // Add new skill with default values
                            skills = skills + SkillData(
                                R.drawable.ic_custom_skill,
                                newSkillName
                            )
                            newSkillName = ""
                            showAddSkillDialog = false
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddSkillDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun SkillCard(skill: SkillData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Skill icon in a circle
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = skill.icon),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Skill name
            Text(
                text = skill.name,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Skill level indicator
            LinearProgressIndicator(
                progress = skill.level,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            // Skill level as percentage
            Text(
                text = "${(skill.level * 100).toInt()}%",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}