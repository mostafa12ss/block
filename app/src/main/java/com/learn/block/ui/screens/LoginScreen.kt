package com.learn.block.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.learn.block.R


@Composable
fun LoginScreen(
    onBack: () -> Unit = {},
    onCreateAccount: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {

    val lineColor = Color.Gray.copy(alpha = 0.5f)
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }

    var passcode by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.Neutral)
            )
    ) {



        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(80.dp)
                .height(150.dp)
                .drawBehind {

                    val stroke = 1.dp.toPx()

                    // Right line
                    drawLine(
                        color = lineColor,
                        start = androidx.compose.ui.geometry.Offset(
                            size.width,
                            0f
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            size.width,
                            size.height
                        ),
                        strokeWidth = stroke
                    )

                    // Bottom line
                    drawLine(
                        color = lineColor,
                        start = androidx.compose.ui.geometry.Offset(
                            0f,
                            size.height
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            size.width,
                            size.height
                        ),
                        strokeWidth = stroke
                    )
                }
        )




        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .width(80.dp)
                .height(150.dp)
                .drawBehind {

                    val stroke = 1.dp.toPx()

                    // Left line
                    drawLine(
                        color = lineColor,
                        start = androidx.compose.ui.geometry.Offset(
                            0f,
                            0f
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            0f,
                            size.height
                        ),
                        strokeWidth = stroke
                    )

                    // Top line
                    drawLine(
                        color = lineColor,
                        start = androidx.compose.ui.geometry.Offset(
                            0f,
                            0f
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            size.width,
                            0f
                        ),
                        strokeWidth = stroke
                    )
                }
        )



        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.Center)
                .background(
                    color = colorResource(R.color.Secondary)
                ).border(width = 1.dp, colorResource(R.color.Secondary))
        ) {
Box(
    Modifier.background(colorResource(R.color.Neutral)).align(Alignment.BottomCenter).fillMaxWidth().height(15.dp)
)


            Box(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Box(
                    modifier = Modifier
                        .size(5.dp, 1.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                )

                Box(
                    modifier = Modifier
                        .size(1.dp, 5.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                )
            }


            Box(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Box(
                    modifier = Modifier.align(Alignment.BottomStart)
                        .size(5.dp, 1.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                )

                Box(
                    modifier = Modifier
                        .size(1.dp, 5.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                )
            }


            Box(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(5.dp, 1.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                )

                Box(
                    modifier = Modifier.align(Alignment.TopEnd)
                        .size(1.dp, 5.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                )
            }


            Box(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Box(
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .size(5.dp, 1.dp)
                        .background(Color.White.copy(alpha = 0.4f))
                )

                Box(
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .size(1.dp, 5.dp)
                        .background(Color.White.copy(alpha = 0.4f))
                )
            }




            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ) {

                Text(
                    text = "WELCOME \nBACK",
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(start = 10.dp),
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp
                )



                Row(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 15.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .height(5.dp)
                            .background(
                                colorResource(R.color.Primary)
                            )
                    )

                    Column {

                        Text(
                            text = "AUTHENTICATE TO ACCESS THE",
                            color = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.padding(start = 5.dp),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "FORGE.",
                            color = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.padding(
                                start = 5.dp,
                                top = 5.dp
                            ),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(20.dp)
                )



                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Color.White.copy(alpha = 0.3f)
                        )
                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )




                Text(
                    text = "EMAIL ADDRESS",
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 10.dp),
                    fontFamily = FontFamily.Monospace
                )


                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 10.dp
                        )
                        .height(55.dp),

                    placeholder = {
                        Text(
                            text = "system@forge.io",
                            color = Color.White.copy(alpha = 0.6f),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )
                    },

                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = "Email",
                            tint = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(18.dp)
                        )
                    },

                    singleLine = true,

                    shape = RectangleShape,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(R.color.Primary),
                        unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedContainerColor = Color.Black.copy(alpha = 0.6f),

                        cursorColor = colorResource(R.color.Primary)
                    )
                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )



                Text(
                    text = "PASSCODE",
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 10.dp),
                    fontFamily = FontFamily.Monospace
                )


                OutlinedTextField(
                    value = passcode,
                    onValueChange = {
                        passcode = it
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 10.dp
                        )
                        .height(55.dp),

                    placeholder = {
                        Text(
                            text = "••••••••",
                            color = Color.White.copy(alpha = 0.6f),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )
                    },

                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Passcode",
                            tint = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(18.dp)
                        )
                    },

                    visualTransformation = PasswordVisualTransformation(),

                    singleLine = true,

                    shape = RectangleShape,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(R.color.Primary),
                        unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedContainerColor = Color.Black.copy(alpha = 0.6f),

                        cursorColor = colorResource(R.color.Primary)
                    )
                )
                // Neo-Brutalist Button Container
                val shadowOffset = 4.dp // مقدار البروز/الظل

                Row(
                    Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp + shadowOffset)
                    ) {
                        // 1. الظل الخلفي الأسود الصلب
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .offset(x = shadowOffset, y = shadowOffset)
                                .background(Color.Blue)
                        )

                        // 2. الزرار الأساسي فوق الظل
                        Button(
                            onClick = {
                                if (email.isNotEmpty() && passcode.isNotEmpty()) {
                                    isLoading = true
                                    auth.signInWithEmailAndPassword(email, passcode)
                                        .addOnCompleteListener { task ->
                                            isLoading = false
                                            if (task.isSuccessful) {
                                                Toast.makeText(context, "Operator Authenticated", Toast.LENGTH_SHORT).show()
                                                onSuccess()
                                            } else {
                                                Toast.makeText(context, "Authentication Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                } else {
                                    Toast.makeText(context, "Enter credentials", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Blue,
                                    shape = RectangleShape
                                ),
                            shape = RectangleShape,
                            enabled = !isLoading,
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.Primary)
                            )
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                            } else {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "SING IN",
                                        fontSize = 17.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "sing in",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Color.White.copy(alpha = 0.3f)
                        )
                )
                Spacer(Modifier.height(10.dp))
Column(
    modifier = Modifier.fillMaxWidth().padding(top= 10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Row(
        modifier = Modifier.padding(bottom = 20.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_key_24),
            contentDescription = "key",
            Modifier.size(15.dp),
            tint = Color.White.copy(0.5f)
        )
                Text(
                    text = "FORGOT PASSCODE?",
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 10.dp).clickable(
                        onClick = {

                        }
                    ),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
                 }

    Text(
        text = "Create Account",
        color = Color.White.copy(alpha = 0.8f),
        modifier = Modifier.padding(start = 10.dp).clickable(
            onClick = onCreateAccount
        ),
        fontFamily = FontFamily.SansSerif,
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal
    )
               }
                Spacer(Modifier.height(20.dp))
Row(
    Modifier.fillMaxWidth().background(colorResource(R.color.Neutral)).padding(10.dp)
) {
Box(
    Modifier.background(color = colorResource(R.color.Secondary)).border(
        width = 1.dp,color = Color.Gray.copy(0.5F), shape = RectangleShape
    )
){
    Text(
        text = "SVS.V.1.04",
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
        color = Color.White.copy(alpha = 0.6f),
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
        fontSize = 15.sp
    )
}
    Spacer(Modifier.width(10.dp))
    Box(
        Modifier.background(color = colorResource(R.color.Secondary)).border(
            width = 1.dp,color = Color.Gray.copy(0.5F), shape = RectangleShape
        )
    ){
        Spacer(Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "star",
            tint = colorResource(R.color.Primary),
            modifier = Modifier.size(10.dp).align (Alignment.CenterStart)
        )
        Text(
            text = "SECURE",
            modifier = Modifier.padding( start = 10.dp,end = 10.dp, top = 5.dp, bottom = 5.dp),
            color = colorResource(R.color.Primary),
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif,
            fontSize = 15.sp
        )
    }

}
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
