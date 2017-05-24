package zhy.scau.com.personalmemo.bussiness.perform.common.calendar

import java.util.*

/**
 * Created by ZhengHy on 2017-05-23.
 */
class CalendarUtils {

    companion object Utils{
//        fun getMonth():CalendarMonth{
//            var calendarInstance = Calendar.getInstance()
//
//            var today = calendarInstance.get(Calendar.DAY_OF_MONTH)
//            var tomonth = calendarInstance.get(Calendar.MONTH)
//            var toyear = calendarInstance.get(Calendar.YEAR)
//            var toweek = calendarInstance.get(Calendar.DAY_OF_WEEK) - 1
//            var maxNum = calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH)
//
//            var dates = Array<CalendarDate>(42){
//               CalendarDate(0,0,0,0,false)
//            }
//
//            var needPreDays = toweek - today % 7 +1
//            if(needPreDays > 0){
//                //需要拿上个月的几天
//                calendarInstance.set(toyear, tomonth-1, 1)
//                var maxNumPre = calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH)
//
//                for(n in 0 .. needPreDays - 1 ){
//                    dates[n].day = maxNumPre - needPreDays + 1
//                    dates[n].month = calendarInstance.get(Calendar.MONTH) + 1
//                    dates[n].year = calendarInstance.get(Calendar.YEAR)
//                    dates[n].isToday = isToday(dates[n], toyear, tomonth, today)
//                }
//                calendarInstance.set(toyear, tomonth, today)
//            }
//
//            for(n in needPreDays .. needPreDays + maxNum - 1){
//                dates[n].day = n - needPreDays + 1
//                dates[n].month = tomonth +1
//                dates[n].year = toyear
//                dates[n].isToday = isToday(dates[n], toyear, tomonth, today)
//
//            }
//
//            //需要下个月的几天
//            var needNextDays = 42 - needPreDays - maxNum
//            calendarInstance.set(toyear, tomonth + 1, 1)
//
//            for(n in needPreDays + maxNum .. needPreDays + maxNum + needNextDays - 1){
//                dates[n].day = n - needPreDays - maxNum+ 1
//                dates[n].month = calendarInstance.get(Calendar.MONTH) + 1
//                dates[n].year = calendarInstance.get(Calendar.YEAR)
//                dates[n].isToday = isToday(dates[n], toyear, tomonth, today)
//
//            }
//
//            calendarInstance.set(toyear, tomonth, today)
//
//            var weekArray : Array<CalendarWeek> = Array(6){
//                CalendarWeek(0,0,0,null,-1)
//            }
//
//            var week :CalendarWeek? = null
//            var weekCount: Int = 1
//
//            for (n in 0 .. 5 ){
//
//                //构建星期数组
//                var datesInner: Array<CalendarDate> = Array(7){
//                    dates[n * 7 + it]
//                }
//
//                //是否包含今天
//                var hasToday: Int = -1
//                for(i in 0 .. 6){
//                    if(datesInner[i].isToday){
//                        hasToday = i
//                    }
//                    datesInner[i].week = weekCount
//                }
//
//                //构建星期实例
//                week = CalendarWeek(datesInner[6].year, datesInner[6].month, weekCount++, datesInner, hasToday)
//                weekArray[n] = week
//            }
//
//            //是否包含今天
//            var hasTodayM: Int = -1
//            for(i in 0 .. 5){
//                if(weekArray[i].hasToday != -1){
//                    hasTodayM = i
//                }
//            }
//
//            var resultMonth : CalendarMonth  = CalendarMonth(weekArray[0].year, weekArray[0].month, weekArray, hasTodayM)
//
//            return resultMonth
//        }

        fun getMonth(): CalendarMonth{
            var calendarInstance = Calendar.getInstance()

            var tomonth = calendarInstance.get(Calendar.MONTH) + 1
            var toyear = calendarInstance.get(Calendar.YEAR)
            var today = calendarInstance.get(Calendar.DAY_OF_MONTH)

            return getMonth(toyear, tomonth, today)
        }

        fun getMonth(year: Int, month: Int, day: Int): CalendarMonth{
            var calendarInstance = Calendar.getInstance()

            var today = calendarInstance.get(Calendar.DAY_OF_MONTH)
            var tomonth = calendarInstance.get(Calendar.MONTH)
            var toyear = calendarInstance.get(Calendar.YEAR)

            calendarInstance.set(year,month-1,1)

            var toweek = calendarInstance.get(Calendar.DAY_OF_WEEK) - 1
            var maxNum = calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH)


            var dates = Array<CalendarDate>(42){
                CalendarDate(0,0,0,0,false)
            }

//            var distance: Int = toweek - day % 7
//            var needPreDays = toweek - distance
//            if(distance <= 0 ){
//                needPreDays = toweek - distance
//            }else{
//                needPreDays = toweek
//            }
            if(toweek > 0){
                //需要拿上个月的几天
                calendarInstance.set(toyear, month-2, 1)
                var maxNumPre = calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH)

                for(n in 0 .. toweek - 1 ){
                    dates[n].day = maxNumPre - toweek + n + 1
                    dates[n].month = calendarInstance.get(Calendar.MONTH) + 1
                    dates[n].year = calendarInstance.get(Calendar.YEAR)
                    dates[n].isToday = isToday(dates[n], toyear, tomonth, today)
                }
                calendarInstance.set(year, month-1, 1)
            }

            for(n in toweek .. toweek + maxNum - 1){
                dates[n].day = n - toweek + 1
                dates[n].month = month
                dates[n].year = toyear
                dates[n].isToday = isToday(dates[n], toyear, tomonth, today)

            }

            //需要下个月的几天
            var needNextDays = 42 - toweek - maxNum
            calendarInstance.set(toyear, month , 1)

            for(n in toweek + maxNum .. toweek + maxNum + needNextDays - 1){
                dates[n].day = n - toweek - maxNum+ 1
                dates[n].month = calendarInstance.get(Calendar.MONTH) + 1
                dates[n].year = calendarInstance.get(Calendar.YEAR)
                dates[n].isToday = isToday(dates[n], toyear, tomonth, today)

            }

            //复位
            calendarInstance.set(toyear, tomonth, today)

            var weekArray : Array<CalendarWeek> = Array(6){
                CalendarWeek(0,0,0,null,-1)
            }

            var week :CalendarWeek? = null
            var weekCount: Int = 1

            for (n in 0 .. 5 ){

                //构建星期数组
                var datesInner: Array<CalendarDate> = Array(7){
                    dates[n * 7 + it]
                }

                //是否包含今天
                var hasToday: Int = -1
                for(i in 0 .. 6){
                    if(datesInner[i].isToday){
                        hasToday = i
                    }
                    datesInner[i].week = i
                }

                //构建星期实例
                week = CalendarWeek(datesInner[6].year, datesInner[6].month, weekCount++, datesInner, hasToday)
                weekArray[n] = week
            }

            //是否包含今天
            var hasTodayM: Int = -1
            for(i in 0 .. 5){
                if(weekArray[i].hasToday != -1){
                    hasTodayM = i
                }
            }

            var resultMonth : CalendarMonth  = CalendarMonth(weekArray[0].year, weekArray[0].month, weekArray, hasTodayM)

            return resultMonth
        }

        fun isToday(date: CalendarDate, yearR: Int, monthR: Int, dayR: Int): Boolean{
            if(date.year != yearR){
                return false
            }

            if(date.month != monthR + 1){
                return false
            }

            if(date.day != dayR){
                return false
            }

            return true
        }
    }

}