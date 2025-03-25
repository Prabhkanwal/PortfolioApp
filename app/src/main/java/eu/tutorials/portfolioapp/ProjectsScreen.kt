package eu.tutorials.portfolioapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for project information
data class ProjectData(
    val name: String,
    val techStack: String,
    val description: String,
    val completed: Boolean
)

@Composable
fun ProjectsScreen() {
    // State for selected project category
    var selectedCategory by remember { mutableStateOf("Completed") }

    // State for projects list
    var projects by remember { mutableStateOf(getSampleProjects()) }

    // State for add project dialog
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Project category selection
        ProjectCategoryTabs(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filter projects based on selected category
        val filteredProjects = projects.filter {
            if (selectedCategory == "Completed") it.completed else !it.completed
        }

        // Project list
        if (filteredProjects.isEmpty()) {
            // Empty state
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No ${selectedCategory.lowercase()} projects yet",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        } else {
            // Project list
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredProjects) { project ->
                    ProjectItem(project)
                }
            }
        }

        // Add project button
        Button(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Project",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Add New Project")
        }
    }

    // Add project dialog
    if (showAddDialog) {
        AddProjectDialog(
            onDismiss = { showAddDialog = false },
            onAddProject = { name, techStack, description ->
                projects = projects + ProjectData(
                    name = name,
                    techStack = techStack,
                    description = description,
                    completed = selectedCategory == "Completed"
                )
                showAddDialog = false
            }
        )
    }
}

@Composable
fun ProjectCategoryTabs(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    // Tabs for project categories
    TabRow(
        selectedTabIndex = if (selectedCategory == "Completed") 0 else 1,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
        divider = { Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer) }
    ) {
        // Completed projects tab
        Tab(
            selected = selectedCategory == "Completed",
            onClick = { onCategorySelected("Completed") },
            text = { Text("Completed") }
        )

        // In progress projects tab
        Tab(
            selected = selectedCategory == "In Progress",
            onClick = { onCategorySelected("In Progress") },
            text = { Text("In Progress") }
        )
    }
}

@Composable
fun ProjectItem(project: ProjectData) {
    // State for expanding/collapsing project details
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (project.completed)
                Color(0xFFE8F5E9) // Light green for completed
            else
                Color(0xFFFFF8E1) // Light amber for in-progress
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Project header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = project.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (project.completed) "Completed" else "In Progress",
                        fontSize = 14.sp,
                        color = if (project.completed)
                            Color(0xFF388E3C) // Green text for completed
                        else
                            Color(0xFFFFA000) // Amber text for in-progress
                    )
                }

                // Expand/collapse icon
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse" else "Expand"
                    )
                }
            }

            // Expandable project details
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = tween(200)),
                exit = shrinkVertically(animationSpec = tween(200))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))

                    // Tech stack
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            text = "Tech Stack:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(text = project.techStack)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Description
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            text = "Description:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(text = project.description)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectDialog(
    onDismiss: () -> Unit,
    onAddProject: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var techStack by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Add New Project") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Project name field
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Project Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Tech stack field
                OutlinedTextField(
                    value = techStack,
                    onValueChange = { techStack = it },
                    label = { Text("Tech Stack") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Description field
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Project Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    maxLines = 5
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && techStack.isNotBlank() && description.isNotBlank()) {
                        onAddProject(name, techStack, description)
                    }
                },
                enabled = name.isNotBlank() && techStack.isNotBlank() && description.isNotBlank()
            ) {
                Text("Add Project")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

// Function to provide sample projects data
fun getSampleProjects(): List<ProjectData> {
    return listOf(
        ProjectData(
            name = "CGPA Calculator",
            techStack = "Kotlin, Jetpack Compose",
            description = "An app to calculate CGPA based on course credits and grades.",
            completed = true
        ),
        ProjectData(
            name = "WishList App",
            techStack = "Kotlin, Firebase, Material Design",
            description = "An app to create and manage your wishlist with cloud synchronization.",
            completed = true
        ),
        ProjectData(
            name = "Portfolio App",
            techStack = "Jetpack Compose, Room Database",
            description = "A portfolio management app to showcase your projects and skills.",
            completed = false
        ),
        ProjectData(
            name = "E-commerce App",
            techStack = "Kotlin, Retrofit, MVVM",
            description = "An online shopping app with product catalog and cart functionality.",
            completed = false
        )
    )
}