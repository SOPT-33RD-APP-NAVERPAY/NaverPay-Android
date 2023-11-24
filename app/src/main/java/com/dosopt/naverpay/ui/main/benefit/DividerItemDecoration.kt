package com.dosopt.naverpay.ui.main.benefit

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dosopt.naverpay.R

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val divider: ShapeDrawable

    init {
        // 원하는 색상으로 PaintDrawable 생성
        val color = ContextCompat.getColor(context, R.color.grayscale_gray2) // 원하는 색상 지정

        // 1dp 높이의 ShapeDrawable 생성
        divider = ShapeDrawable(RectShape()).apply {
            paint.color = color // 직접 색상을 설정
            intrinsicHeight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1f, context.resources.displayMetrics
            ).toInt()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) { // 마지막 아이템 제외
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + divider.intrinsicHeight

            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            divider.draw(c)
        }
    }
}

