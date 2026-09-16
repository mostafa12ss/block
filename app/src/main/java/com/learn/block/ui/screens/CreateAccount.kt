package com.learn.block.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.learn.block.R
import kotlinx.coroutines.launch

@Composable
fun CreateAccount(
    onBack: () -> Unit = {},
    onLoginInstead: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Neutral))
    ) {
        // الخط البرتقالي
        Box(
            modifier = Modifier
                .width(5.dp)
                .height(70.dp)
                .background(color = colorResource(R.color.Primary))
                .align(Alignment.TopStart)
        )

        // العنوان الأول
        Text(
            text = "JOIN THE FORGE",
            color = colorResource(R.color.white),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(10.dp).background(color = colorResource(R.color.Primary)))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "INITIALIZE OPERATOR PROFILE",
                color = colorResource(R.color.white).copy(0.6F),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Column(
            modifier = Modifier.padding(top = 110.dp, start = 20.dp)
        ) {
            Text(
                text = "FULL NAME",
                color = colorResource(R.color.white).copy(0.6F),
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp, top = 5.dp).height(50.dp),
                placeholder = { Text("Enter designation", color = Color.White.copy(0.4f), fontSize = 14.sp) },
                singleLine = true,
                shape = RectangleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.Primary),
                    unfocusedBorderColor = Color.Gray.copy(0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = colorResource(R.color.Secondary),
                    unfocusedContainerColor = colorResource(R.color.Secondary).copy(0.5f)
                )
            )

            Spacer(Modifier.height(15.dp))
            Text(
                text = "EMAIL ADDRESS",
                color = colorResource(R.color.white).copy(0.6F),
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp, top = 5.dp).height(50.dp),
                placeholder = { Text("name@domain.com", color = Color.White.copy(0.4f), fontSize = 14.sp) },
                singleLine = true,
                shape = RectangleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.Primary),
                    unfocusedBorderColor = Color.Gray.copy(0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = colorResource(R.color.Secondary),
                    unfocusedContainerColor = colorResource(R.color.Secondary).copy(0.5f)
                )
            )

            Spacer(Modifier.height(15.dp))
            Text(
                text = "ACCESS CODE (PASSWORD)",
                color = colorResource(R.color.white).copy(0.6F),
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp, top = 5.dp).height(50.dp),
                placeholder = { Text("**********", color = Color.White.copy(0.4f), fontSize = 14.sp) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                shape = RectangleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.Primary),
                    unfocusedBorderColor = Color.Gray.copy(0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = colorResource(R.color.Secondary),
                    unfocusedContainerColor = colorResource(R.color.Secondary).copy(0.5f)
                )
            )

            Spacer(Modifier.height(15.dp))
            Text(
                text = "CONFIRM ACCESS CODE",
                color = colorResource(R.color.white).copy(0.6F),
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp, top = 5.dp).height(50.dp),
                placeholder = { Text("**********", color = Color.White.copy(0.4f), fontSize = 14.sp) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                shape = RectangleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.Primary),
                    unfocusedBorderColor = Color.Gray.copy(0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = colorResource(R.color.Secondary),
                    unfocusedContainerColor = colorResource(R.color.Secondary).copy(0.5f)
                )
            )

            Spacer(Modifier.height(20.dp))
            Box(Modifier.padding(end = 20.dp).height(1.dp).fillMaxWidth().background(colorResource(R.color.white).copy(0.3f)))
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.padding(end = 20.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier.size(24.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(R.color.Primary),
                        uncheckedColor = Color.White.copy(alpha = 0.5f),
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = "Subscribe to Field Notes - transmit periodic operational updates directly to your terminal.",
                    color = colorResource(R.color.white).copy(0.5F),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            Spacer(Modifier.height(30.dp))
            val shadowOffset: Dp = 4.dp
            Box(modifier = Modifier.fillMaxWidth().height(56.dp + shadowOffset)) {
                Box(
                    modifier = Modifier.padding(end = 20.dp).fillMaxWidth().height(56.dp).offset(x = shadowOffset, y = shadowOffset).background(Color.Blue)
                )
                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                            isLoading = true
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    isLoading = false
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Operator Profile Initialized", Toast.LENGTH_SHORT).show()
                                        onSuccess()
                                    } else {
                                        Toast.makeText(context, "Initialization Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                        } else if (password != confirmPassword) {
                            Toast.makeText(context, "Access Codes mismatch", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Fill all required fields", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.padding(end = 20.dp).fillMaxWidth().height(56.dp),
                    shape = RectangleShape,
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0044FF), contentColor = Color.White)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                            Text(
                                text = "CREATE ACCOUNT",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(30.dp))
            Box(Modifier.padding(end = 20.dp).height(1.dp).fillMaxWidth().background(colorResource(R.color.white).copy(0.3f)))
            Spacer(Modifier.height(15.dp))
            Row(Modifier.padding(end = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "STATUS: UNREGISTERED",
                    color = colorResource(R.color.white).copy(0.6F),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = colorResource(R.color.Primary)
                )
                Text(
                    text = "LOG IN INSTEAD",
                    color = colorResource(R.color.Primary),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.clickable { onLoginInstead() }
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateAccountPreview() {
    CreateAccount()
}
