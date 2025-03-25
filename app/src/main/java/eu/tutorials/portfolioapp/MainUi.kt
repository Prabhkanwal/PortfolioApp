package eu.tutorials.portfolioapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PortfolioApp() {
    // State to track which screen is currently selected
    var selectedScreen by remember {
        mutableStateOf(Screen.Home)
    }

    ProfileHeader()

    // Main app scaffold with bottom navigation
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedScreen = selectedScreen,
                onScreenSelected = { selectedScreen = it }
            )
        }
    ) { paddingValues ->
        // Main content column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile header stays consistent across all screens
            ProfileHeader()

            // Show the appropriate screen based on selection
            when (selectedScreen) {
                Screen.Home -> HomeScreen()
                Screen.Projects -> ProjectsScreen()
                Screen.Skills -> SkillScreen()
                Screen.Contact -> ContactScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar {
        // Home navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Home,
            onClick = { onScreenSelected(Screen.Home) },
            icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            label = { Text("Home") }
        )

        // Projects navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Projects,
            onClick = { onScreenSelected(Screen.Projects) },
            icon = { Icon(painterResource(id = R.drawable.ic_projects), contentDescription = "Projects") },
            label = { Text("Projects") }
        )

        // Skills navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Skills,
            onClick = { onScreenSelected(Screen.Skills) },
            icon = { Icon(painterResource(id = R.drawable.ic_skills), contentDescription = "Skills") },
            label = { Text("Skills") }
        )

        // Contact navigation item
        NavigationBarItem(
            selected = selectedScreen == Screen.Contact,
            onClick = { onScreenSelected(Screen.Contact) },
            icon = { Icon(painterResource(id = R.drawable.ic_contact), contentDescription = "Contact") },
            label = { Text("Contact") }
        )
    }
}



// Simple enum to represent different screens
enum class Screen {
    Home, Projects, Skills, Contact
}

@Composable
fun ProfileHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Image(
                painter = painterResource(id = R.drawable.ic_person1),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Name with slightly larger, bolder text
            Text(
                text = "John Doe",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            // Job title
            Text(
                text = "Android Developer",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}