package com.lxy.myview.ui;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by user on 2016/8/22.
 * 自定义linearlayout
 */
public class DragLayout extends LinearLayout {

    private ViewDragHelper mHelper;

    public DragLayout(Context context) {
        super(context);
        init();
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {

        mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            //处理垂直方向的越界
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            //处理水平方向的越界
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            //监听拖动状态的改变
            @Override
            public void onViewDragStateChanged(int state) {
                System.out.println("22222222222222222========onViewDragStateChanged");
                super.onViewDragStateChanged(state);
            }

            //捕获View
            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                System.out.println("22222222222222222========onViewCaptured");
                super.onViewCaptured(capturedChild, activePointerId);
            }

            //释放View
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                System.out.println("22222222222222222========onViewReleased");
                super.onViewReleased(releasedChild, xvel, yvel);
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return mHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return true;
    }
}
