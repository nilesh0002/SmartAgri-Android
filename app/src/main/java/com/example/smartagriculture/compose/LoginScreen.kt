package com.example.smartagriculture.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartagriculture.compose.components.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            title = "Welcome Back!",
            subtitle = "Login to continue to your account",
            imageRes = com.example.smartagriculture.R.drawable.login_image
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            icon = Icons.Default.Email
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            icon = Icons.Default.Lock,
            isPassword = true
        )
        
        Text(
            text = "Forgot Password?",
            color = PurpleEnd,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 24.dp, top = 8.dp, bottom = 32.dp)
                .clickable { Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show() }
        )

        AuthButton(
            text = "Login",
            isLoading = isLoading,
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@AuthButton
                }
                isLoading = true
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                            onLoginSuccess()
                        } else {
                            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Social login buttons removed
        
        Spacer(modifier = Modifier.weight(1f))
        
        Row(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account? ", color = Color.DarkGray, fontSize = 14.sp)
            Text(
                text = "Sign Up",
                color = PurpleEnd,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}
