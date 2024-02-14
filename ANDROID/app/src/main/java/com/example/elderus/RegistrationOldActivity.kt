package com.example.elderus

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class RegistrationOldActivity : AppCompatActivity() {

    private lateinit var etGuardianEmail: EditText
    private lateinit var btnRegisguard: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_old)

        // EditText 초기화
        etGuardianEmail = findViewById(R.id.et_guardian_email)

        // 버튼 초기화
        btnRegisguard = findViewById(R.id.btn_regisguard)

        // ContinueButton 이벤트 처리
//        val ContinueButton = findViewById<AppCompatButton>(R.id.btn_regisguard)
//        ContinueButton.setOnClickListener {
//            val intent = Intent(this, RegistrationOldCompleteActivity::class.java)
//            startActivity(intent) // 다음 액티비티 시작
//            finish() // 현재 액티비티 종료
//        }

        // BackButton 이벤트 처리
        val BackButton = findViewById<ImageView>(R.id.iv_registration_old_back)
        BackButton.setOnClickListener {
            val intent = Intent(this, SignUpOldCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }

        // 버튼 클릭 리스너 설정
        btnRegisguard.setOnClickListener {
            showDialog()
        }

        // EditText의 텍스트 변경 감지 리스너 설정
        etGuardianEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // EditText에 텍스트가 입력되었는지 여부 확인
                val isInputNotEmpty = s.toString().isNotEmpty()

                // 버튼 활성화/비활성화 설정
                btnRegisguard.isEnabled = isInputNotEmpty
            }
        })

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Hoja Bo")
        builder.setMessage("Is your guardian 'Hoja Bo'?")

        builder.setPositiveButton("Yes (Registration)"){ dialogInterface : DialogInterface, i : Int ->
            dialogInterface.dismiss()
            //엑티비티 이동
            val intent = Intent(this, RegistrationOldCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }
        builder.setNegativeButton("No (Enter again)"){ dialogInterface : DialogInterface, i : Int ->
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
