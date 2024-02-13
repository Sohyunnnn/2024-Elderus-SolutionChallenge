package com.example.elderus

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import java.util.Calendar

class SignUpOldActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCheckPassword: EditText
    private lateinit var etFamilyName: EditText
    private lateinit var etGivenName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var birthdayTextView: TextView
    private lateinit var continueButton: AppCompatButton

    private lateinit var BackButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_old)

        // 캘린더 이미지뷰를 찾아서 클릭 리스너를 설정합니다.
        val calendarImageView = findViewById<ImageView>(R.id.iv_calender)
        calendarImageView.setOnClickListener {
            // 클릭 이벤트가 발생했을 때 수행할 작업을 여기에 작성합니다.
            showCalendarDialog()
        }

        // EditText 및 DatePicker 입력 변경 감지
        etEmail = findViewById(R.id.et_email_input)
        etPassword = findViewById(R.id.et_password_input)
        etCheckPassword = findViewById(R.id.et_check_password_input)
        etFamilyName = findViewById(R.id.et_fmaily_name)
        etGivenName = findViewById(R.id.et_given_name)
        etPhoneNumber = findViewById(R.id.et_phone_number)
        birthdayTextView = findViewById(R.id.birthday_txt)
        continueButton = findViewById(R.id.btn_sign_up_old_continue)

        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        etCheckPassword.addTextChangedListener(textWatcher)
        etFamilyName.addTextChangedListener(textWatcher)
        etGivenName.addTextChangedListener(textWatcher)
        etPhoneNumber.addTextChangedListener(textWatcher)
        birthdayTextView.addTextChangedListener(textWatcher)

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
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val checkPassword = etCheckPassword.text.toString()
        val familyName = etFamilyName.text.toString()
        val givenName = etGivenName.text.toString()
        val phoneNumber = etPhoneNumber.text.toString()
        val birthday = birthdayTextView.text.toString()

        // 모든 필드가 비어 있지 않은지 확인
        val isNotEmptyFields = email.isNotBlank() &&
                password.isNotBlank() &&
                checkPassword.isNotBlank() &&
                familyName.isNotBlank() &&
                givenName.isNotBlank() &&
                phoneNumber.isNotBlank() &&
                birthday.isNotBlank()

        // 유효성 검사를 통과하면 계속 버튼 활성화
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8
        val isPasswordMatch = password == checkPassword

        val isContinueEnabled = isNotEmptyFields && isValidEmail && isPasswordValid && isPasswordMatch

        continueButton.isEnabled = isContinueEnabled

        // continueButton의 색상 변경
        if (isContinueEnabled) {
            continueButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.main_color)
            // 다른 액티비티로 이동하는 Intent 생성
            val intent = Intent(this, signUpOldCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        } else {
            continueButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.gray2)
        }

        val BackButton = findViewById<ImageView>(R.id.iv_sign_up_old_back)
        BackButton.setOnClickListener {
            // 다른 액티비티로 이동하는 Intent 생성
            val intent = Intent(this, SigninTypeActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
    }

    private fun showCalendarDialog() {
        // 캘린더를 표시하는 다이얼로그를 생성합니다.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Date")

        // 다이얼로그에 캘린더뷰를 추가합니다.
        val calendarView = CalendarView(this)
        builder.setView(calendarView)

        // 사용자가 날짜를 선택했을 때 호출되는 리스너를 설정합니다.
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 선택한 날짜에 대한 작업을 수행합니다.
            // TextView를 찾습니다.
            val birthdayTextView = findViewById<TextView>(R.id.birthday_txt)

            // 선택한 날짜로 텍스트를 설정합니다.
            val formattedMonth = String.format("%02d", month + 1)
            val formattedDay = String.format("%02d", dayOfMonth)
            val selectedDate = "$year-$formattedMonth-$formattedDay"
            birthdayTextView.text = "$selectedDate"

            // 텍스트의 색상을 변경합니다.
            birthdayTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        // 다이얼로그에 확인 버튼을 추가하고 클릭 이벤트를 처리합니다.
        builder.setPositiveButton(
            "OK"
        ) { dialog, which ->
            // 다이얼로그를 닫습니다.
            dialog.dismiss()
        }

        // 다이얼로그를 표시합니다.
        val dialog = builder.create()
        dialog.show()
    }
}