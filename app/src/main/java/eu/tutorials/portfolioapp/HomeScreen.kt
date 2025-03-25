package eu.tutorials.portfolioapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    // Using Column with verticalScroll instead of LazyColumn for simple content
    // This is easier for beginners to understand than LazyColumn for static content

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Bio Section
        SectionTitle(title = "Bio")
        ContentCard {
            Text(
                text = "Hi, I'm John Doe, a passionate Android Developer. " +
                        "I love building sleek, user-friendly apps using Kotlin and Jetpack Compose.",
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }

        // Education Section
        SectionTitle(title = "Education")
        ContentCard {
            Column {
                EducationItem(
                    degree = "Bachelor of Computer Science",
                    institution = "Tech University",
                    year = "2018-2022"
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                EducationItem(
                    degree = "Android Development Certification",
                    institution = "Google Developers",
                    year = "2022"
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                EducationItem(
                    degree = "Kotlin Masterclass",
                    institution = "Online Course Platform",
                    year = "2023"
                )
            }
        }

        // Achievements Section
        SectionTitle(title = "Achievements")
        ContentCard {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AchievementItem(text = "Completed 200+ Leetcode Problems")
                AchievementItem(text = "Published 3 apps on Google Play Store")
                AchievementItem(text = "Winner of Regional Hackathon 2023")
                AchievementItem(text = "Open Source Contributor")
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ContentCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun EducationItem(degree: String, institution: String, year: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = degree,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = institution,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = year,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun AchievementItem(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "•",
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}

