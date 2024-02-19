package com.example.elderus

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.storage.storage

class ConditionFragment : Fragment() {
    private lateinit var fallimageView: ImageView
    private lateinit var TextView: TextView
    private lateinit var fallDanView: ConstraintLayout
    private lateinit var fallText : TextView
    private lateinit var checkText : TextView
    private lateinit var CallButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_condition, container, false)

        fallDanView = view.findViewById(R.id.fall_result_dan)
        fallimageView = view.findViewById(R.id.fall_image)
        TextView = view.findViewById(R.id.fall_check)
        fallText = view.findViewById(R.id.tv_hello_name)
        checkText = view.findViewById(R.id.txt_pls_check)

        CallButton = view.findViewById(R.id.btn_911)

        // CallButton 클릭 이벤트 설정
        CallButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // 권한이 없는 경우 권한을 요청
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PERMISSION)
            } else {
                // 권한이 있는 경우 전화 걸기
                makePhoneCall()
            }
        }

        if(MyFirebaseMessagingService.isMessageReceived){
            fallDanView.visibility = View.VISIBLE
            loadFirstImageToImageView(fallimageView)
            fallText.text = "It's an emergency."
            fallText.setTextColor(Color.parseColor("#CA3000"))
            checkText.visibility = View.GONE
            fallText.setTextSize(24F)

            TextView.setOnClickListener {
                fallDanView.visibility = View.GONE
                fallText.text = "Hello, Yongja Ko!"
                fallText.setTextColor(Color.BLACK)
                fallText.setTextSize(20F)
                checkText.visibility = View.VISIBLE
            }
        }

        return view
    }

    // 전화 걸기 권한 요청 결과 처리
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 허용됨
                makePhoneCall()
            } else {
                // 권한 거부됨
                // 권한이 필요한 이유를 사용자에게 설명하거나 다른 조치를 취할 수 있음
            }
        }
    }

    // 전화 걸기
    private fun makePhoneCall() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:911")
        startActivity(intent)
    }

    //가장 최근 이미지 올리기
    private fun loadFirstImageToImageView(imageView: ImageView){
        val storage = Firebase.storage
        val storageRef3 = storage.reference.child("images")

        storageRef3.listAll().addOnSuccessListener { result ->
            if(result.items.isNotEmpty()){
                result.items.first().downloadUrl.addOnSuccessListener { uri->
                    Glide.with(imageView.context)
                        .load(uri)
                        .into(imageView)
                }.addOnFailureListener { exception->
                    //다운로드 실패 처리
                }
            }
        }.addOnFailureListener { exception->

        }
    }

    companion object {
        private const val REQUEST_CALL_PERMISSION = 1
    }
}
