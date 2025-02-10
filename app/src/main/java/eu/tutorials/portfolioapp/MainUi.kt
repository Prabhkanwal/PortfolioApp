package eu.tutorials.portfolioapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PortfolioApp() {
    var selectedScreen by remember {
        mutableStateOf(eu.tutorials.portfolioapp.Screen.Home)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedScreen) { selectedScreen = it } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()
            when (selectedScreen) {
                eu.tutorials.portfolioapp.Screen.Home -> HomeScreen()
                eu.tutorials.portfolioapp.Screen.Projects -> ProjectsScreen()
                eu.tutorials.portfolioapp.Screen.Skills -> SkillScreen()
                eu.tutorials.portfolioapp.Screen.Contact -> ContactScreen()
                else -> {}
            }
        }
    }
}


@Composable
fun BottomNavigationBar(selectedScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedScreen == eu.tutorials.portfolioapp.Screen.Home,
            onClick = { onScreenSelected(eu.tutorials.portfolioapp.Screen.Home) },
            icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedScreen == eu.tutorials.portfolioapp.Screen.Projects,
            onClick = { onScreenSelected(eu.tutorials.portfolioapp.Screen.Projects) },
            icon = { Icon(painterResource(id = R.drawable.ic_projects), contentDescription = "Projects") },
            label = { Text("Projects") }
        )
        NavigationBarItem(
            selected = selectedScreen == eu.tutorials.portfolioapp.Screen.Skills,
            onClick = { onScreenSelected(eu.tutorials.portfolioapp.Screen.Skills) },
            icon = { Icon(painterResource(id = R.drawable.ic_skills), contentDescription = "Skills") },
            label = { Text("Skills") }
        )
        NavigationBarItem(
            selected = selectedScreen == eu.tutorials.portfolioapp.Screen.Contact,
            onClick = { onScreenSelected(eu.tutorials.portfolioapp.Screen.Contact) },
            icon = { Icon(painterResource(id = R.drawable.ic_contact), contentDescription = "Contact") },
            label = { Text("Contact") }
        )
    }
}


@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Profile Picture using Image instead of Icon
        Image(
            painter = painterResource(id = R.drawable.ic_person1), // Ensure the image is in res/drawable
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "ABC XYZ", fontSize = 32.sp)
        Text(text = "App Developer", fontSize = 18.sp)
    }
}


enum class Screen {
    Home, Projects, Contact , Skills
}