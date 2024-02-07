package com.example.elderus

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.RemoteMessage.Notification
import com.google.firebase.messaging.FirebaseMessaging
class MainActivity : AppCompatActivity() {

    private val frame: ConstraintLayout by lazy { // activity_main의 화면 부분
        findViewById(R.id.body_container)
    }

    private val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bottom_navigation)
    }

    //알림 권한 요청
 /*   private val PERMISSION_REQUEST_NOTIFICATION = 1

    private fun checkNotificationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATION)
            != PackageManager.PERMISSION_GRANTED){ //권한 없는 경우 권한 요청
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_NOTIFICATION
                )
            }
        }
    }

*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if(!task.isSuccessful){
                    Log.w("FCM 토큰", "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                val token = task.result
                Log.d("FCM 토큰", "FCM registration token : $token")

                val tokensRef = FirebaseDatabase.getInstance().getReference("tokens")
                val query = tokensRef.orderByValue().equalTo(token)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            Log.d("FCM 토큰", "Token already exists")
                        } else {
                            tokensRef.push().setValue(token)
                            Log.d("FCM 토큰", "Token saved to database")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.d("FCM 토큰", "DATAbase error : ${error.message}")
                    }
                })

                }


        // 애플리케이션 실행 후 첫 화면 설정
        supportFragmentManager.beginTransaction().add(frame.id, ConditionFragment()).commit()

        // 하단 네비게이션 바 클릭 이벤트 설정
        bottomNagivationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.nav_condition -> {
                    replaceFragment(ConditionFragment())
                    true
                }
                R.id.nav_hospital -> {
                    replaceFragment(HospitalFragment())
                    true
                }
                R.id.nav_my_page -> {
                    replaceFragment(MyPageFragment())
                    true
                }
                else -> false
            }
        }
    }

    // 화면 전환 구현 메소드
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }




}