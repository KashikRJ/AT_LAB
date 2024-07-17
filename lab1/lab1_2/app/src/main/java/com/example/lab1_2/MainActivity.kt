package com.example.lab1_2

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab1_2.ui.theme.Lab1_2Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegistrationForm()
                }
            }
        }
    }
}
/*Write a program to create a registration form :
a. Form includes email id, mobile number and password as its field.
b. Perform validation for email id and mobile number.
c. Display customized toast message based on user input.*/

fun isValidEmail(email:String):Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

}
fun isValidPhoneNumber(number:String):Boolean {
    return number.matches("^\\d{10}$".toRegex())
}
fun isCorrectEmail(email:String):Boolean{
        return email=="kashikjhasg@gmail.com"
    }

fun isCorrectPassword(password:String):Boolean {
    return password=="jmd@2002"
}



@Composable
fun RegistrationForm() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), // Ensures the Column takes up the full width
        verticalArrangement = Arrangement.spacedBy(10.dp) // Adds spacing between each child
    ) {
        // Email Field
        val context= LocalContext.current
        Text(text = "Email ID", modifier = Modifier.padding(bottom = 8.dp))
        var email by remember { mutableStateOf("") }
        var emailerror by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it
                            emailerror=!isValidEmail(email)
            },
            label = { Text("abc@xxx.com") },
            modifier = Modifier.fillMaxWidth(),
            isError=emailerror

        )
        if (emailerror) {
            Text("Invalid email address", color = Color.Red)
        }
        // Mobile Number Field
        Spacer(modifier = Modifier.height(16.dp)) // Adds space between fields
        Text(text = "Mobile Number", modifier = Modifier.padding(bottom = 8.dp))
        var mobileNumber by remember { mutableStateOf("") }
        var numbererror by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it
                            numbererror=!isValidPhoneNumber(mobileNumber)
            },
            label = { Text("Only Number") },
            modifier = Modifier.fillMaxWidth(),
            isError=numbererror
        )
        if (numbererror) {
            Text("Invalid mobile phone Number", color = Color.Red)
        }
        // Password Field
        Spacer(modifier = Modifier.height(16.dp)) // Adds space between fields
        Text(text = "Password", modifier = Modifier.padding(bottom = 8.dp))
        var password by remember { mutableStateOf("") }
        var passworderror by remember {
            mutableStateOf(false)
        }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it
                            passworderror=!isCorrectPassword(password)
            },
            label = { Text("No special chara") },
            visualTransformation = PasswordVisualTransformation(mask = '*'),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp)) // Adds space between fields
        Button(onClick = {
            var email2error=!isCorrectEmail(email)
            passworderror=!isCorrectPassword(password)

            when{
                email2error->showToast(context,"Invalid Email ID")
                passworderror->showToast(context,"Invalid Password")
                else->showToast(context,"Sign In Successful")
            }
        },
            colors = ButtonDefaults.buttonColors(
                 containerColor = Color.Blue ,
                contentColor = Color.White, // Text color
                // Define pressed color if needed
            )
            ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Submit")

        }
    }
}

fun showToast(context: Context,message: String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab1_2Theme {
        RegistrationForm()
    }
}