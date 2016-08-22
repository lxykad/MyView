package com.lxy.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mBt;
    private TextView mTvJump;
    private GestureDetector mGestureDetector;
    private ViewConfiguration mConfig;

    private VelocityTracker mTracker;

    private ViewDragHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTracker = VelocityTracker.obtain();
        mConfig = ViewConfiguration.get(this);
        mBt = (TextView) findViewById(R.id.bt);
        mTvJump = (TextView) findViewById(R.id.tv_jump);

        mTvJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                System.out.println("777777777777========onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                System.out.println("777777777777========onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                System.out.println("777777777777========onSingleTapUp");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float x, float y) {
                System.out.println("777777777777========onScrollx===" + x);
                System.out.println("777777777777========onScrolly===" + y);

                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                System.out.println("777777777777========onLongPress");

            }

            /**
             * e1：第1个ACTION_DOWN MotionEvent
             e2：最后一个ACTION_MOVE MotionEvent
             velocityX：X轴上的移动速度，像素/秒
             velocityY：Y轴上的移动速度，像素/秒
             *
             */
            @Override
            public boolean onFling(MotionEvent m1, MotionEvent m2, float x, float y) {
                System.out.println("777777777777========onFling");
                System.out.println("777777777777====x====" + x);
                System.out.println("777777777777====y====" + y);
                //
                float xDistance = m2.getX() - m1.getX();
                float yDistance = m2.getY() - m1.getY();

                //距离
                System.out.println("888888888888====xDistance====" + xDistance);
                System.out.println("888888888888====yDistance====" + yDistance);
                //距离和速度一起判断 上下左右滑动
                return false;
            }
        });

        mBt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //把button的触摸时间委托给手势识别器
                mGestureDetector.onTouchEvent(motionEvent);
                mTracker.addMovement(motionEvent);
                return false;
            }
        });


        //双击
        mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                System.out.println("777777777777========onSingleTapConfirmed");
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent motionEvent) {
                System.out.println("777777777777========onDoubleTap");
                Toast.makeText(MainActivity.this, "双击", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                System.out.println("777777777777========onDoubleTapEvent");
                return false;
            }
        });
    }

    //将事件拦截交给ViewDragHelper处理
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void jump(View view) {

    }
}
