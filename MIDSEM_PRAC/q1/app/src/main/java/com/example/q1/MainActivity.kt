package com.example.q1

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "001"
        private const val POST_NOTIFICATIONS_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener {
            showAlertDialog()
        }
    }
    private var tempScore: Int = 0
    private fun createNotificationChannel() {
        val name = "Results"
        val descriptionText = "Your Score is Good"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun checkPermissionsAndShowNotification(score: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), POST_NOTIFICATIONS_REQUEST_CODE)
            } else {
                showNotification(score)
            }
        } else {
            showNotification(score)
        }
    }
    @SuppressLint("MissingPermission")
    private fun showNotification(score: Int) {
        createNotificationChannel() // Ensure the channel is created before showing a notification
        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.images) // Ensure you have a drawable named `images` in your drawable resources
            .setContentTitle("Quiz Result")
            .setContentText("Your score is $score") // Incorporate the score into the notification text
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build()) // The first parameter is a unique ID for the notification
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Submit Confirmation")
            .setMessage("Are you sure you want to submit?")
            .setPositiveButton("Yes") { dialog, which ->
                dialog.dismiss()
                Toast.makeText(this, "Submission Confirmed", Toast.LENGTH_SHORT).show()
                calcres()

            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun calcres() {
        val radioGroupIds = arrayOf(
            R.id.RG1, R.id.RG2, R.id.RG3, R.id.RG4, R.id.RG5,
            R.id.RG6, R.id.RG7, R.id.RG8, R.id.RG9, R.id.RG10,
            R.id.RG11, R.id.RG12 // Add IDs for the new RadioGroups
        )

        val correctans = mutableListOf(
            "True", "False", "True", "False", "True",
            "False", "True", "False", "True", "False",
            "True", "False" // Add the correct answers for the new questions
        )
        val urans = mutableListOf<String>()
        var score = 0

        radioGroupIds.forEachIndexed { index, radioGroupId ->
            val radioGroup = findViewById<RadioGroup>(radioGroupId)
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val radioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedText = radioButton.text.toString()
                urans.add(selectedText)
                if (selectedText == correctans[index]) {
                    score++
                }
            } else {
                urans.add("Not Answered")
            }
        }

        tempScore = score
        checkPermissionsAndShowNotification(score)
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("score", score)
            putStringArrayListExtra("ans", ArrayList(urans))
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == POST_NOTIFICATIONS_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showNotification(tempScore) // Use tempScore here
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}

