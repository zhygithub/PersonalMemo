package zhy.scau.com.personalmemo.bussiness.perform.common.calendar

/**
 * Created by ZhengHy on 2017-05-23.
 */
interface ICalendarCellColorPolicy {

    /**
     * 获取单元背景圆的颜色
     */
    fun getCircleBgColor(): Int

    /**
     * 获取单元日期文字的颜色
     */
    fun getDateNumColor(): Int

    /**
     * 获取单元日期提醒文字的颜色
     */
    fun getDateTextColor(): Int

    /**
     * 获取单元日期提醒远点的颜色
     */
    fun getDateTipDotColor(): Int

}