package com.example.elderus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.elderus.network_utils.LoginData
import com.example.elderus.network_utils.LoginResponse
import com.example.elderus.network_utils.UserInfo
import com.example.elderus.network_utils.UserInterface
import com.example.elderus.network_utils.getRetrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: AppCompatButton

    // get retrofit
    val retrofitService = getRetrofit().create(UserInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLocalLogin: AppCompatButton = findViewById(R.id.btn_local_login)

        btnSignIn = findViewById(R.id.btn_sign_in)

        val GoogleLogin: ImageView = findViewById(R.id.iv_google)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)

        updateContinueButtonState() // 초기 상태 설정

        val loginData = LoginData(
            email = etEmail.text.toString(),
            password = etPassword.text.toString()
        )

        // 버튼 클릭 리스너 설정
        btnLocalLogin.setOnClickListener {
            // 다음 액티비티로 이동
            val intent = Intent(this, SigninTypeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignIn.setOnClickListener {

            val loginData = LoginData(
                email = etEmail.text.toString(),
                password = etPassword.text.toString()
            )
            retrofitService.userLogin(loginData).enqueue(object : retrofit2.Callback<LoginResponse> {

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {


                    Log.d("login-result", response.toString())
                    if (response.isSuccessful) {
                        Log.d("login-onSuccess", response.toString())

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "login failed!", Toast.LENGTH_SHORT).show()
                    Log.d("login-onFailure", t.toString())
                }

            })

            // 다음 액티비티로 이동
            val intent = Intent(this, MainActivity::class.java)
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


        // 모든 필드가 비어 있지 않은지 확인
        val isNotEmptyFields = email.isNotBlank() &&
                password.isNotBlank()

        // 유효성 검사를 통과하면 계속 버튼 활성화
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        val isSignInEnabled = isNotEmptyFields && isValidEmail

        btnSignIn.isEnabled = isSignInEnabled

        // continueButton의 색상 변경
        if (isSignInEnabled) {
            btnSignIn.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.main_color)
        } else {
            btnSignIn.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.gray2)
        }


    }


}
