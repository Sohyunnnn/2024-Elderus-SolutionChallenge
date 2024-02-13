package com.example.elderus

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class RegistrationOldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_old)
        var btnRegisGuard : Button
        btnRegisGuard = findViewById(R.id.btn_regisguard)

        btnRegisGuard.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Hoja Bo")
        builder.setMessage("Is your guardian 'Hoja Bo'?")

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