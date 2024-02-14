package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.elderus.R

class SigninTypeActivity : AppCompatActivity() {

    private lateinit var btnSenior: ConstraintLayout
    private lateinit var btnGuardian: ConstraintLayout
    private lateinit var btnContinue: AppCompatButton
    private lateinit var backButton: ImageView

    private lateinit var tvSenior: TextView
    private lateinit var tvSeniorInfo: TextView
    private lateinit var tvGuardian: TextView
    private lateinit var tvGuardianInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_type)

        btnSenior = findViewById(R.id.btn_type_senior)
        btnGuardian = findViewById(R.id.btn_type_gurdian)

        btnContinue = findViewById(R.id.btn_continue)

        backButton= findViewById(R.id.iv_sign_in_type_back)

        tvSenior= findViewById(R.id.tv_title_senior)
        tvSeniorInfo= findViewById(R.id.tv_senior_description)

        tvGuardian= findViewById(R.id.tv_title_guardian)
        tvGuardianInfo= findViewById(R.id.tv_guardian_description)

        btnSenior.setOnClickListener {
            // 클릭 이벤트가 발생했을 때 배경 색상 변경
            btnSenior.backgroundTintList = ContextCompat.getColorStateList(this, R.color.main_color)
            btnGuardian.backgroundTintList = ContextCompat.getColorStateList(this, R.color.gray3)
            btnContinue.isEnabled = true // 버튼 활성화
            btnContinue.backgroundTintList = ContextCompat.getColorStateList(this, R.color.main_color) // 버튼 배경색 변경

            tvSenior.setTextColor(ContextCompat.getColor(this, R.color.white))
            tvSeniorInfo.setTextColor(ContextCompat.getColor(this, R.color.white))

            tvGuardian.setTextColor(ContextCompat.getColor(this, R.color.black))
            tvGuardianInfo.setTextColor(ContextCompat.getColor(this, R.color.gray1))

        }

        btnGuardian.setOnClickListener {
            // 클릭 이벤트가 발생했을 때 배경 색상 변경
            btnGuardian.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary)
            btnSenior.backgroundTintList = ContextCompat.getColorStateList(this, R.color.gray3)
            btnContinue.isEnabled = true // 버튼 활성화
            btnContinue.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary) // 버튼 배경색 변경

            tvSenior.setTextColor(ContextCompat.getColor(this, R.color.black))
            tvSeniorInfo.setTextColor(ContextCompat.getColor(this, R.color.gray1))

            tvGuardian.setTextColor(ContextCompat.getColor(this, R.color.white))
            tvGuardianInfo.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        // 초기에는 버튼 비활성화 상태로 설정
        btnContinue.isEnabled = false

        // backButton 클릭 이벤트 처리
        backButton.setOnClickListener {
            // LoginActivity로 이동하는 Intent 생성
            val intent = Intent(this, LoginActivity::class.java)
            // 새로운 액티비티 시작
            startActivity(intent)
            // 현재 액티비티 종료
            finish()
        }

        // btnContinue 클릭 이벤트 처리
        btnContinue.setOnClickListener {
            // btnContinue의 배경색을 기준으로 SignUpOldActivity 또는 SignUpGuardianActivity로 이동
            val nextActivityIntent = if (btnContinue.backgroundTintList == ContextCompat.getColorStateList(this, R.color.main_color)) {
                Intent(this, SignUpOldActivity::class.java)
            } else {
                Intent(this, SignUpGuardianActivity::class.java)
            }
            startActivity(nextActivityIntent)
            finish()
        }

    }
}
