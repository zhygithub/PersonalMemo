package zhy.scau.com.personalmemo.bussiness.perform.common

import android.content.Context
import zhy.scau.com.personalmemo.R
import zhy.scau.com.personalmemo.bussiness.perform.common.calendar.ICalendarCellColorPolicy

/**
 * Created by ZhengHy on 2017-05-23.
 */
class DefaultCdellColorPolicy(val ctx: Context): ICalendarCellColorPolicy {
    override fun getCircleBgColor(): Int {
        return ctx.getColor(R.color.xj_color_black)
    }

    override fun getDateNumColor(): Int {
        return ctx.getColor(R.color.xj_color_gray_cccccc)

    }

    override fun getDateTextColor(): Int {
        return ctx.getColor(R.color.xj_color_gray_cccccc)

    }

    override fun getDateTipDotColor(): Int {
        return ctx.getColor(R.color.xj_color_white)

    }
}