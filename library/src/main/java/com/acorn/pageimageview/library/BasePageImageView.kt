package com.acorn.pageimageview.library

import android.content.Context
import android.os.Handler
import android.support.v4.view.MotionEventCompat
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast

/**
 * Created by acorn on 2020/1/15.
 */
class BasePageImageView : ImageView {
    private var downX = 0
    private var downY = 0
    private val mHandler = Handler()
    private val mSingleClickRunnable = Runnable {

    }
    private var gestureDetector: GestureDetector


    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(context, attr, defStyleAttr) {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent?) {
                super.onLongPress(e)
            }
        })
        gestureDetector.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                toast("onDoubleTap:(${e?.x},${e?.y})")
                return false
            }

            override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                toast("onDoubleTapEvent:(${e?.x},${e?.y})")
                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                toast("onSingleTapConfirmed:(${e?.x},${e?.y})")
                return false
            }
        })

        setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return gestureDetector.onTouchEvent(event)
            }

        })
    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        if (!isEnabled)
//            return super.onTouchEvent(event)
//        var isConsume = false
//        //兼容
//        val actionMasked = MotionEventCompat.getActionMasked(event)
//        when (MotionEventCompat.getActionMasked(event)) {
//            MotionEvent.ACTION_DOWN -> {
//                downX = event.x.toInt()
//                downY = event.y.toInt()
//            }
//        }
//        return isConsume || super.onTouchEvent(event)
//    }

    private fun toast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
}