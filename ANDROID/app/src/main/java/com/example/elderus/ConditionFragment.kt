package com.example.elderus

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConditionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConditionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var vi = com.google.android.material.R.id.layout

    private lateinit var fallimageView: ImageView
    private lateinit var TextView: TextView
    private lateinit var fallDanView: ConstraintLayout
    private lateinit var testBtn : Button
    private lateinit var fallText : TextView
    private lateinit var checkText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

        testBtn = view.findViewById(R.id.btn_test)
/*
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
        }*/

        testBtn.setOnClickListener {
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
    //가장 최근 이미지 올리기
    fun loadFirstImageToImageView(imageView: ImageView){
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


}