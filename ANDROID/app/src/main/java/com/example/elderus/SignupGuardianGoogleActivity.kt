package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class SignupGuardianGoogleActivity : AppCompatActivity() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var continueButton: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_guardian_google)

        // EditText 및 DatePicker 입력 변경 감지
        etPhoneNumber = findViewById(R.id.phone_txt)
        continueButton = findViewById(R.id.btn_signup_old)


        etPhoneNumber.addTextChangedListener(textWatcher)

        updateContinueButtonState() // 초기 상태 설정


    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            updateContinueButtonState()
        }
    }

    private fun updateContinueButtonState() {
        // 입력 필드에서 텍스트 가져오기
        val phoneNumber = etPhoneNumber.text.toString()

        // 모든 필드가 비어 있지 않은지 확인
        val isNotEmptyFields =
            phoneNumber.isNotBlank()


        val isContinueEnabled = isNotEmptyFields

        continueButton.isEnabled = isContinueEnabled

        // continueButton의 색상 변경
        if (isContinueEnabled) {
            continueButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary)
        } else {
            continueButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.gray2)
        }

        continueButton.setOnClickListener {
//            val intent = Intent(this, SignUpOldCompleteActivity::class.java)
//            startActivity(intent) // 다음 액티비티 시작
//            finish() // 현재 액티비티 종료
        }

        val BackButton = findViewById<ImageView>(R.id.iv_back)
        BackButton.setOnClickListener {
            // 다른 액티비티로 이동하는 Intent 생성
            val intent = Intent(this, SignUpTypeActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
    }


}