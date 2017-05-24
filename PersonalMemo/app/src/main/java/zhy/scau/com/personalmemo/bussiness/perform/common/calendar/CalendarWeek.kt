package zhy.scau.com.personalmemo.bussiness.perform.common.calendar

/**
 * Created by ZhengHy on 2017-05-23.
 */
data class CalendarWeek(var year: Int,var month: Int, var week: Int, var dates:Array<CalendarDate>?, val hasToday: Int)
