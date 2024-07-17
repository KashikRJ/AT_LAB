package com.example.lab1_1

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab1_1.ui.theme.Lab1_1Theme

data class UserProfile(
    val name:String,
    val jobTitle: String,
    val company:String,
    val email:String
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userprofile=UserProfile(
                        name="John Doe",
                        jobTitle = "Android Developer",
                        company = "Tech Corp",
                        email="john.doe@techcrop.com"
                    )
                    UserProfileView(userProfile=userprofile)
                }
            }
        }
    }
}

@Composable
fun UserProfileView(userProfile: UserProfile){
    val image= painterResource(id = R.drawable.face)

    Column(modifier=Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Box(
            contentAlignment = Alignment.TopCenter, // This centers the content inside the Box
            modifier = Modifier.wrapContentSize() // Box occupies the entire available space
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
        Text(text="Name: ${userProfile.name}")
        Text(text="Job Title:${userProfile.jobTitle}")
        Text(text="Company:${userProfile.company}")
        Text(text="Email:${userProfile.email}")


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab1_1Theme {
        val userprofile=UserProfile(
            name="John Doe",
            jobTitle = "Android Developer",
            company = "Tech Corp",
            email="john.doe@techcrop.com"
        )
        UserProfileView(userProfile=userprofile)
    }
}