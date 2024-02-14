package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLocalLogin: AppCompatButton = findViewById(R.id.btn_local_login)

        // 버튼 클릭 리스너 설정
        btnLocalLogin.setOnClickListener {
            // 다음 액티비티로 이동
            val intent = Intent(this, SigninTypeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
