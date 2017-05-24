package zhy.scau.com.personalmemo.bussiness.perform.common.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import zhy.scau.com.personalmemo.bussiness.perform.common.CalendarCell

/**
 * Created by ZhengHy on 2017-05-23.
 */
class CalendarPanel (ctx: Context, attrs: AttributeSet?): TableLayout(ctx, attrs){

    constructor (ctx: Context) : this(ctx, null) {
        println("1 params")
    }

    init {
        isStretchAllColumns = true
        orientation = TableLayout.VERTICAL

        var datesData :Array<CalendarWeek> = CalendarUtils.getMonth().week?: emptyArray()
        var tableRow: TableRow? = null
        var cell: CalendarCell? = null

        var cellList: Array<CalendarCell?> = arrayOfNulls<CalendarCell>(42)
        var index: Int = 0
        for (weekData in datesData){
            tableRow = TableRow(ctx)
            tableRow.orientation = LinearLayout.HORIZONTAL
            addView(tableRow, TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT))
            for (dateData in weekData.dates?: emptyArray()){
                cell = CalendarCell(ctx)

                cell.setOnClickListener {
                    val c = (it as CalendarCell)
                    Toast.makeText(ctx,"第"+c.rowNum+"周，星期"+c.colNum+","+c.text+"号", Toast.LENGTH_LONG).show()
                }

                cell.text = dateData.day.toString()
                cell.rowNum = weekData.week
                cell.colNum = dateData.week
                tableRow?.addView(cell,TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT))
                cellList[index++] = cell
            }
        }



    }




}

