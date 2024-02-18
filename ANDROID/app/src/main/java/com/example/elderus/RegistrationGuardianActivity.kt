package com.example.elderus

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class RegistrationGuardianActivity : AppCompatActivity() {

    private lateinit var etWardEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_guardian)

        // EditText 초기화
        etWardEmail = findViewById(R.id.et_ward_email)

        var btnRegisOld : AppCompatButton = findViewById(R.id.btn_regisold)



        val BackButton = findViewById<ImageView>(R.id.iv_regi_guardian_back)
        BackButton.setOnClickListener {
            val intent = Intent(this, SignUpGuardianCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }

        // EditText의 텍스트 변경 감지 리스너 설정
        etWardEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // EditText에 텍스트가 입력되었는지 여부 확인
                val isInputNotEmpty = s.toString().isNotEmpty()

                // 버튼 활성화/비활성화 설정
                etWardEmail.isEnabled = isInputNotEmpty

                // 배경색 변경
                if (isInputNotEmpty) {
                    btnRegisOld.backgroundTintList =
                        ContextCompat.getColorStateList(this@RegistrationGuardianActivity, R.color.Secondary)
                } else {
                    // 입력이 없을 경우 기본 배경색으로 설정
                    btnRegisOld.backgroundTintList =
                        ContextCompat.getColorStateList(this@RegistrationGuardianActivity, R.color.gray2)
                }

                // 버튼 클릭 리스너 설정
                btnRegisOld.setOnClickListener {
                    if (btnRegisOld.isEnabled) {
                        showDialog()
                    }
                }
            }
        })

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Yongja Ko")
        builder.setMessage("Is your protected one 'Yongja Ko'?")

        builder.setPositiveButton("Yes (Registration)"){ dialogInterface : DialogInterface, i : Int ->
            dialogInterface.dismiss()
            val intent = Intent(this, RegistrationGuardCompleteActivity::class.java)
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