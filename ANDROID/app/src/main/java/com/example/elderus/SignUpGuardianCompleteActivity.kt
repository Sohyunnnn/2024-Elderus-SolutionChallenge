package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

class SignUpGuardianCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_guardian_complete)


        val AddWardButton = findViewById<AppCompatButton>(R.id.btn_add_ward)
        AddWardButton.setOnClickListener {
            val intent = Intent(this, RegistrationGuardianActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }

        val BackButton = findViewById<ImageView>(R.id.iv_complete_guardian_back)
        BackButton.setOnClickListener {
//            val intent = Intent(this, SignUpGuardianActivity::class.java)
//            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
    }
}