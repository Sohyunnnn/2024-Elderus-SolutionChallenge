package com.example.elderus

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class RegistrationGuardCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_guard_complete)

        val StartButton = findViewById<AppCompatButton>(R.id.btn_regisold_complete)
        StartButton.setOnClickListener {
//            val intent = Intent(this, ConditionFragment::class.java) // ConditionFragment로 변경
//            startActivity(intent) // 다음 액티비티 시작
//            finish() // 현재 액티비티 종료
        }

        val BackButton = findViewById<ImageView>(R.id.iv_regi_guardian_complete)
        BackButton.setOnClickListener {
            val intent = Intent(this, RegistrationGuardianActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
    }
}