package zhy.scau.com.personalmemo.bussiness.perform.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import zhy.scau.com.personalmemo.bussiness.perform.common.calendar.ICalendarCellColorPolicy

/**
 * Created by ZhengHy on 2017-05-20.
 */
class CalendarCell(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) :View(ctx, attrs, defStyleAttr){

    /**
     * 行数
     */
    var rowNum: Int = 0

    /**
     * 列数
     */
    var colNum: Int = 0

    /**
     * 绘制的文字
     */
    var text :String = ""

    /**
     * 提示文字
     */
    var tip :String = ""

    /**
     * 单元宽度
     */
    var cellWidth: Int = 0

    /**
     * 单元高度
     */
    var cellHeight: Int = 0

    /**
     * 画笔
     */
    var paint : Paint = Paint()

    /**
     * 颜色配置
     */
    var colorPolicy: ICalendarCellColorPolicy = DefaultCdellColorPolicy(ctx)


    constructor (ctx: Context, attrs: AttributeSet) : this(ctx, attrs, 0) {
        println("2 params")
    }

    constructor (ctx: Context) : this(ctx, null, 0) {
        println("1 params")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val rad = Math.min(measuredWidth,measuredHeight)
        setMeasuredDimension(rad, rad)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val length =  Math.min(measuredHeight, measuredWidth)

        val dotHeight = length * 1 / 5
        val dateNumHeight = length / 2
        val tipTextHeight = length * 3 / 10

        paint.reset()
        paint.textSize = 20.0f
        paint.color = colorPolicy.getCircleBgColor()
        canvas?.drawCircle((measuredWidth/2).toFloat(), (measuredHeight/2).toFloat(), (length/2).toFloat(), paint)


        drawDot(canvas, dotHeight, 0 , dotHeight)

        drawDateNum(canvas, dateNumHeight, dotHeight , dotHeight + dateNumHeight)

        drawTipText(canvas, tipTextHeight, dotHeight + dateNumHeight , dotHeight + dateNumHeight + tipTextHeight)



    }

    /**
     * 绘制圆点
     */
    private fun drawDot(canvas: Canvas?, dotHeight: Int, up: Int, down: Int) {
        val dotRidus = dotHeight /3
        paint.reset()
        paint.color = colorPolicy.getDateTipDotColor()
        paint.isAntiAlias = true
        canvas?.drawCircle((measuredWidth/2).toFloat(), ((up + down) / 2).toFloat(), dotRidus.toFloat(), paint)
    }

    /**
     * 绘制日期
     */
    private fun drawDateNum(canvas: Canvas?, dateNumHeight: Int, up: Int, down: Int) {
        paint.reset()
        paint.color = colorPolicy.getDateTipDotColor()
        paint.isAntiAlias = true
        paint.textSize = (dateNumHeight * 9 / 10).toFloat()
        canvas?.drawText(text, measuredWidth/2 - getTextWidth(paint, text)/2, (up + dateNumHeight/2) + getTextHeight(paint, text)/2, paint)

    }

    /**
     * 绘制提示
     */
    private fun drawTipText(canvas: Canvas?, tipTextHeight: Int, up: Int, down: Int) {
        paint.reset()
        paint.color = colorPolicy.getDateTipDotColor()
        paint.isAntiAlias = true
        paint.textSize = (tipTextHeight * 3 / 4).toFloat()
        canvas?.drawText(tip, measuredWidth/2 - getTextWidth(paint, tip)/2, (up + tipTextHeight/2) + getTextHeight(paint, tip)/2, paint)
    }


    /**
     * 获得要绘制的文字的宽度
     * @param text 文字
     * @param paint 画笔
     * @return
     */
    fun getTextWidth(paint : Paint, text : String): Float{
        return paint.measureText(text)
    }

    /**
     * 获得要绘制的文字的高度
     * @param text 文字
     * @param paint 画笔
     * @return
     */
    private fun getTextHeight(paint: Paint, text: String): Float {
        val textBounds = Rect()
        paint.getTextBounds(text, 0, text.length, textBounds)
        return textBounds.height().toFloat()
    }
}