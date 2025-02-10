package eu.tutorials.portfolioapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Bio:",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Hi, I’m Humble Coders, a passionate Android Developer. " +
                                "I love building sleek, user-friendly apps using Kotlin and Jetpack Compose.",
                        fontSize = 18.sp,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        item {
            Text(
                text = "Education Qualifications:",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "• College \n• School \n• Online Courses",
                        fontSize = 18.sp,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        item {
            Text(
                text = "Mentionable Achievements:",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "• Completed 200 Leetcode Problems\n• Completed App Dev course from  HUMBLE CODERS",
                        fontSize = 18.sp,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}
