package com.example.elderus

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class RegistrationOldCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_old_complete)

        val startButton = findViewById<AppCompatButton>(R.id.btn_regisguard_complete)
        startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // 메인 엑티비티 시작
//            finish() // 현재 액티비티 종료
        }

        val BackButton = findViewById<ImageView>(R.id.iv_regi_old_complete)
        BackButton.setOnClickListener {
            val intent = Intent(this, RegistrationOldActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
    }
}