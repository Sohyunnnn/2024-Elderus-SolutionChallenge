package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class SignUpGuardianActivity : AppCompatActivity() {

    private lateinit var etGuardianEmail: EditText
    private lateinit var etGuardianPassword: EditText
    private lateinit var etGuardianCheckPassword: EditText
    private lateinit var etGuardianFamilyName: EditText
    private lateinit var etGuardianGivenName: EditText
    private lateinit var etGuardianPhoneNumber: EditText
    private lateinit var GuardiancontinueButton: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_guardian)

        // EditText 및 DatePicker 입력 변경 감지
        etGuardianEmail = findViewById(R.id.et_email_guardian_input)
        etGuardianPassword = findViewById(R.id.et_password_guardian_input)
        etGuardianCheckPassword = findViewById(R.id.et_check_password_guardian_input)
        etGuardianFamilyName = findViewById(R.id.et_fmaily_name_guardian)
        etGuardianGivenName = findViewById(R.id.et_given_name_guardian)
        etGuardianPhoneNumber = findViewById(R.id.et_phone_number_guardian)
        GuardiancontinueButton = findViewById(R.id.btn_gardian_continue)

        etGuardianEmail.addTextChangedListener(textWatcher)
        etGuardianPassword.addTextChangedListener(textWatcher)
        etGuardianCheckPassword.addTextChangedListener(textWatcher)
        etGuardianGivenName.addTextChangedListener(textWatcher)
        etGuardianGivenName.addTextChangedListener(textWatcher)
        etGuardianPhoneNumber.addTextChangedListener(textWatcher)

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
        val email = etGuardianEmail.text.toString()
        val password = etGuardianPassword.text.toString()
        val checkPassword = etGuardianCheckPassword.text.toString()
        val familyName = etGuardianFamilyName.text.toString()
        val givenName = etGuardianGivenName.text.toString()
        val phoneNumber = etGuardianPhoneNumber.text.toString()

        // 모든 필드가 비어 있지 않은지 확인
        val isNotEmptyFields = email.isNotBlank() &&
                password.isNotBlank() &&
                checkPassword.isNotBlank() &&
                familyName.isNotBlank() &&
                givenName.isNotBlank() &&
                phoneNumber.isNotBlank()

        // 유효성 검사를 통과하면 계속 버튼 활성화
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordMatch = password == checkPassword

        val isContinueEnabled = isNotEmptyFields && isValidEmail && isPasswordMatch

        GuardiancontinueButton.isEnabled = isContinueEnabled

        // continueButton의 색상 변경
        if (isContinueEnabled) {
            GuardiancontinueButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary)

        } else {
            GuardiancontinueButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.gray2)
        }

        GuardiancontinueButton.setOnClickListener {
            val intent = Intent(this, signUpGuardianCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }


        val BackButton = findViewById<ImageView>(R.id.iv_sign_up_guardian_back)
        BackButton.setOnClickListener {
            // 다른 액티비티로 이동하는 Intent 생성
            val intent = Intent(this, SigninTypeActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }

    }
}