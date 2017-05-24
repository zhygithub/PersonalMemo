package zhy.scau.com.personalmemo.bussiness.perform.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import zhy.scau.com.personalmemo.R
import zhy.scau.com.personalmemo.bussiness.perform.common.calendar.CalendarMonth
import zhy.scau.com.personalmemo.bussiness.perform.common.calendar.CalendarPanel
import zhy.scau.com.personalmemo.bussiness.perform.common.calendar.CalendarUtils

class KotlinTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)

        val calendar = findViewById(R.id.calendarview) as CalendarPanel

        val month: CalendarMonth = CalendarUtils.getMonth(2017, 10, 5)


    }
}
