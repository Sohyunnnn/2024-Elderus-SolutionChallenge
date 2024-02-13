package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

class signUpOldCompleteActivity : AppCompatActivity() {

    private lateinit var AddGuardianButton: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_old_complete)

        AddGuardianButton = findViewById(R.id.btn_add_guardian)
        AddGuardianButton.setOnClickListener {
//            val intent = Intent(this, signUpOldCompleteActivity::class.java)
//            startActivity(intent) // 다음 액티비티 시작
//            finish() // 현재 액티비티 종료
        }

        val BackButton = findViewById<ImageView>(R.id.iv_complete_old_back)
        BackButton.setOnClickListener {
            val intent = Intent(this, SignUpOldActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
    }
}