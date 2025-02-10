package eu.tutorials.portfolioapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen() {
    var contacts by remember {
        mutableStateOf(
            listOf(
                ContactData(R.drawable.ic_email, "Email", "example@email.com"),
                ContactData(R.drawable.ic_phone, "Phone", "+123 456 7890"),
                ContactData(R.drawable.ic_linkedin, "LinkedIn", "https://linkedin.com/in/yourprofile"),
                ContactData(R.drawable.ic_git, "GitHub", "https://github.com/yourprofile")
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Contact Me",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 12.dp),
            color = MaterialTheme.colorScheme.primary)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(contacts) { contact ->
                ContactCard(contact) { updatedText ->
                    contacts = contacts.map {
                        if (it.label == contact.label) it.copy(value = updatedText) else it
                    }
                }
            }
        }
    }
}

// Data class for Contact Information
data class ContactData(val icon: Int, val label: String, var value: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactCard(contact: ContactData, onTextChange: (String) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf(contact.value) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { isEditing = true },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = contact.icon),
                contentDescription = contact.label,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = contact.label, fontSize = 14.sp, color = Color.Gray)
                if (isEditing) {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black
                        )
                    )
                } else {
                    Text(text = inputText, fontSize = 16.sp, color = Color.Black)
                }
            }

            IconButton(
                onClick = {
                    if (isEditing) onTextChange(inputText)
                    isEditing = !isEditing
                }
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color.Black)
            }
        }
    }
}
