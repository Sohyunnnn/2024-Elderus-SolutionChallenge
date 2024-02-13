package com.example.elderus

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class RegistrationGuardianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_guardian)

        var btnRegisOld : Button
        btnRegisOld = findViewById(R.id.btn_regisold)

        btnRegisOld.setOnClickListener {
            showDialog()
        }

        val ContinueButton = findViewById<AppCompatButton>(R.id.btn_regisold)
        ContinueButton.setOnClickListener {
            val intent = Intent(this, RegistrationGuardCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }

        val BackButton = findViewById<ImageView>(R.id.iv_regi_guardian_back)
        BackButton.setOnClickListener {
            val intent = Intent(this, signUpGuardianCompleteActivity::class.java)
            startActivity(intent) // 다음 액티비티 시작
            finish() // 현재 액티비티 종료
        }

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Yongja Ko")
        builder.setMessage("Is your protected one 'Yongja Ko'?")

        builder.setPositiveButton("Yes (Registration)"){ dialogInterface : DialogInterface, i : Int ->
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("No (Enter again)"){ dialogInterface : DialogInterface, i : Int ->
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}