package zhy.scau.com.personalmemo.bussiness.perform.common.calendar

/**
 * Created by ZhengHy on 2017-05-23.
 */
data class CalendarMonth(var year: Int,var month: Int,var week: Array<CalendarWeek>?, val hasToday: Int)