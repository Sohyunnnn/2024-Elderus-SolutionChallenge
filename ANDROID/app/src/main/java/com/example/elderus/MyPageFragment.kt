package com.example.elderus

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MyPageFragment : Fragment() {

    private lateinit var btnLogOut: ConstraintLayout
    private lateinit var btnCanellation: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogOut = view.findViewById(R.id.btn_log_out)

        btnLogOut.setOnClickListener {
            showLogoutDialog()
        }

        btnCanellation = view.findViewById(R.id.tv_cancel)

        btnCanellation.setOnClickListener {
            showCancellationDialog()
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to log out?")
        builder.setPositiveButton("Yes(Log out)") { dialog, which ->
            navigateToLoginActivity()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Optional: Finish the current activity after logging out
    }

    private fun showCancellationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Account cancellation")
        builder.setMessage("If you select Yes, the account will be withdrawn, and the information so far cannot be recovered.")
        builder.setPositiveButton("Yes(account cancellation)") { dialog, which ->
            navigateToLoginActivity()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}
