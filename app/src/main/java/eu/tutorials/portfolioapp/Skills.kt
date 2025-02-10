package eu.tutorials.portfolioapp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SkillScreen() {
    var skills by remember {
        mutableStateOf(
            listOf(
                SkillItemData(R.drawable.ic_custom_skill, "Kotlin"),
                SkillItemData(R.drawable.ic_custom_skill, "Jetpack Compose"),
                SkillItemData(R.drawable.ic_custom_skill, "UI/UX"),
                SkillItemData(R.drawable.ic_custom_skill, "Java"),
                SkillItemData(R.drawable.ic_custom_skill, "C++"),
                SkillItemData(R.drawable.ic_custom_skill, "Marketing")
            )
        )
    }
    var showDialog by remember { mutableStateOf(false) }
    var newSkillName by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "My Skills : ",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(skills.chunked(3)) { _, rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowItems.forEach { skill ->
                            SkillItem(skill.imageRes, skill.title)
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Project", tint = Color.White)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add New Skill") },
            text = {
                OutlinedTextField(
                    value = newSkillName,
                    onValueChange = { newSkillName = it },
                    label = { Text("Skill Name") },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newSkillName.text.isNotBlank()) {
                            skills = skills + SkillItemData(R.drawable.ic_custom_skill, newSkillName.text)
                            newSkillName = TextFieldValue("")
                            showDialog = false
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun SkillItem(@DrawableRes imageRes: Int, skillTitle: String) {
    Column(
        modifier = Modifier
            .size(110.dp) // Circular skill card
            .clip(CircleShape)
            .background(Color(0xFFF5F5F5)) // Light gray background
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = skillTitle,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

data class SkillItemData(@DrawableRes val imageRes: Int, val title: String)
