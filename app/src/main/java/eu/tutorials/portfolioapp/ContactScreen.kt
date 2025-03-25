package eu.tutorials.portfolioapp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen() {
    val contacts = listOf(
        Contact(Icons.Default.Email, "Email", "johndoe@gmail.com"),
        Contact(Icons.Default.Phone, "Phone", "+1 123 456 7890"),
        Contact(Icons.Default.Share, "LinkedIn", "linkedin.com/in/johndoe"),
        Contact(Icons.Default.List, "GitHub", "github.com/johndoe")
    )

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Contact Me",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        items(contacts) { contact ->
            ContactCard(contact)
        }
    }
}

data class Contact(val icon: ImageVector, val label: String, val value: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactCard(contact: Contact) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Icon(
                contact.icon,
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contact.label,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                )
                if (expanded) {
                    Text(
                        text = contact.value,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}
