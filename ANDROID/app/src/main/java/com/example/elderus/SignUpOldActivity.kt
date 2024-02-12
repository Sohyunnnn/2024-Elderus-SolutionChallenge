package com.example.elderus

import android.app.AlertDialog
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


class SignUpOldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_old)

        // 캘린더 이미지뷰를 찾아서 클릭 리스너를 설정합니다.
        val calendarImageView = findViewById<ImageView>(R.id.iv_calender)
        calendarImageView.setOnClickListener {
            // 클릭 이벤트가 발생했을 때 수행할 작업을 여기에 작성합니다.
            showCalendarDialog()
        }
    }

    private fun showCalendarDialog() {
        // 캘린더를 표시하는 다이얼로그를 생성합니다.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Date")

        // 다이얼로그에 캘린더뷰를 추가합니다.
        val calendarView = CalendarView(this)
        builder.setView(calendarView)

        // 다이얼로그에 확인 버튼을 추가하고 클릭 이벤트를 처리합니다.
        builder.setPositiveButton(
            "OK"
        ) { dialog, which -> // 사용자가 선택한 날짜를 가져옵니다.
            val selectedDateInMillis = calendarView.date
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDateInMillis
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

            // 선택한 날짜에 대한 작업을 수행합니다.
            val selectedDate = year.toString() + "-" + (month + 1) + "-" + dayOfMonth
            Toast.makeText(applicationContext, "Selected Date: $selectedDate", Toast.LENGTH_SHORT)
                .show()
        }

        // 다이얼로그를 표시합니다.
        val dialog = builder.create()
        dialog.show()
    }
}
