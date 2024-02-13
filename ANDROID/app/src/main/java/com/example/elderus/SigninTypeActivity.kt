package com.example.elderus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.elderus.R

class SigninTypeActivity : AppCompatActivity() {

    private lateinit var btnSenior: ConstraintLayout
    private lateinit var btnGuardian: ConstraintLayout
    private lateinit var btnContinue: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_type)

        btnSenior = findViewById(R.id.btn_type_senior)
        btnGuardian = findViewById(R.id.btn_type_gurdian)

        btnContinue = findViewById(R.id.btn_continue)

        btnSenior.setOnClickListener {
            // 클릭 이벤트가 발생했을 때 배경 색상 변경
            btnSenior.backgroundTintList = ContextCompat.getColorStateList(this, R.color.main_color)
            btnGuardian.backgroundTintList = ContextCompat.getColorStateList(this, android.R.color.white)
            btnContinue.isEnabled = true // 버튼 활성화
            btnContinue.backgroundTintList = ContextCompat.getColorStateList(this, R.color.main_color) // 버튼 배경색 변경
        }

        btnGuardian.setOnClickListener {
            // 클릭 이벤트가 발생했을 때 배경 색상 변경
            btnGuardian.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary)
            btnSenior.backgroundTintList = ContextCompat.getColorStateList(this, android.R.color.white)
            btnContinue.isEnabled = true // 버튼 활성화
            btnContinue.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary) // 버튼 배경색 변경
        }

        // 초기에는 버튼 비활성화 상태로 설정
        btnContinue.isEnabled = false
    }
}
