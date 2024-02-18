package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLocalLogin: AppCompatButton = findViewById(R.id.btn_local_login)

        val btnSignIn: AppCompatButton = findViewById(R.id.btn_sign_in)

        val GoogleLogin: ImageView = findViewById(R.id.iv_google)

        // 버튼 클릭 리스너 설정
        btnLocalLogin.setOnClickListener {
            // 다음 액티비티로 이동
            val intent = Intent(this, SigninTypeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignIn.setOnClickListener {
            // 다음 액티비티로 이동
            val intent = Intent(this, GuardianMainActivity::class.java)
            startActivity(intent)
            finish()
        }

        GoogleLogin.setOnClickListener {
            // 다음 액티비티로 이동
            val intent = Intent(this, SignUpTypeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
