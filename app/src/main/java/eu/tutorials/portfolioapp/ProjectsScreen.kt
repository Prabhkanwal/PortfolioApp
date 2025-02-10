package eu.tutorials.portfolioapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ProjectsScreen() {
    var selectedCategory by remember { mutableStateOf("Completed") }
    var projects by remember { mutableStateOf(getInitialProjects()) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Project", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Buttons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedCategory = "Completed" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedCategory == "Completed") MaterialTheme.colorScheme.primary else Color.Gray)
                ) {
                    Text("Completed", color = Color.White)
                }
                Button(
                    onClick = { selectedCategory = "In Progress" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedCategory == "In Progress") MaterialTheme.colorScheme.primary else Color.Gray)
                ) {
                    Text("In Progress", color = Color.White)
                }
            }

//            Text(
//                text = "Projects: $selectedCategory",
//                fontSize = 22.sp,
//                modifier = Modifier.padding(vertical = 10.dp),
//                color = MaterialTheme.colorScheme.primary
//            )

            val filteredProjects = projects.filter {
                if (selectedCategory == "Completed") it.completed else !it.completed
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredProjects) { project ->
                    ProjectItem(project)
                }
            }
        }
    }

    if (showDialog) {
        AddProjectDialog(
            onDismiss = { showDialog = false },
            onAddProject = { name, techStack, description ->
                projects = projects + Project(name, techStack, description, completed = selectedCategory == "Completed")
                showDialog = false
            }
        )
    }
}

// Data Class for Project
data class Project(
    val name: String,
    val techStack: String,
    val description: String,
    val completed: Boolean
)

@Composable
fun ProjectItem(project: Project) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.name,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand Details",
                        tint = Color.Black
                    )
                }
            }

            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Text(text = "🔧 Tech Stack: ${project.techStack}", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "📖 Description: ${project.description}", fontSize = 14.sp)
                }
            }
        }
    }
}

// AlertDialog for Adding a New Project
@Composable
fun AddProjectDialog(onDismiss: () -> Unit, onAddProject: (String, String, String) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var techStack by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                if (name.text.isNotEmpty() && techStack.text.isNotEmpty() && description.text.isNotEmpty()) {
                    onAddProject(name.text, techStack.text, description.text)
                }
            }) {
                Text("Add Project")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        title = { Text("Add New Project") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Project Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = techStack,
                    onValueChange = { techStack = it },
                    label = { Text("Tech Stack") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Project Description") }
                )
            }
        }
    )
}

// Initial Projects
fun getInitialProjects(): List<Project> {
    return listOf(
        Project("CGPA Calculator", "Kotlin, Jetpack Compose", "An app to calculate CGPA.", completed = true),
        Project("WishList App", "Kotlin, Firebase", "An app to manage your wish list.", completed = true),
        Project("Portfolio App", "Jetpack Compose, Room DB", "A portfolio management app.", completed = false),
        Project("E-commerce App", "Kotlin, Retrofit", "An online shopping app.", completed = false)
    )
}
