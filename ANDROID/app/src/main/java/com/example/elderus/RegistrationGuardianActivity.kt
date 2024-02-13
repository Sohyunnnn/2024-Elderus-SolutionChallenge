package com.example.elderus

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class RegistrationGuardianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_guardian)

        var btnRegisOld : Button
        btnRegisOld = findViewById(R.id.btn_regisold)

        btnRegisOld.setOnClickListener {
            showDialog()
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