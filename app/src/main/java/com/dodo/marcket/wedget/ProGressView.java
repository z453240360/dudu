package com.dodo.marcket.wedget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.dodo.marcket.R;


public class ProGressView extends View {

    /**进度条最大值*/
    private float maxCount;
    /**进度条当前值*/
    private double currentCount;
    /**画笔*/
    private Paint mPaint;

    private int mWidth,mHeight;
    private int outColor;
    private int proColor;
    private float currentPro;


    public ProGressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ProGressView, defStyle, 0);

        proColor = a.getColor(R.styleable.ProGressView_main_color,
                Color.RED);
        currentPro = a.getFloat(R.styleable.ProGressView_pro,0);

        a.recycle();

    }

    public ProGressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProGressView(Context context) {
        this(context,null);


    }

    /***
     * 设置最大的进度值
     * @param maxCount
     */
    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
    }
    /**
     * 得到最大进度值
     */
    public double getMaxCount(){
        return maxCount;
    }
    /***
     * 设置当前的进度值
     * @param currentCount
     */
    public void setCurrentCount(double currentCount) {
        this.currentCount = currentCount > maxCount ? maxCount : currentCount;
        /**
         * invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，
         * 调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view从主UI
         * 线程队列中pop掉。
         */
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        mPaint = new Paint();
        //设置抗锯齿效果
        mPaint.setAntiAlias(true);
        //设置画笔颜色
//        mPaint.setColor(Color.BLACK);
        int round = mHeight/2;
//        /**
//         * RectF：绘制矩形，四个参数分别是left,top,right,bottom
//         * 类型是单精度浮点数
//         */
//        RectF rf = new RectF(0, 0, mWidth, mHeight);
//        /*绘制圆角矩形，背景色为画笔颜色*/
//        canvas.drawRoundRect(rf, round, round, mPaint);
        /*设置progress内部是灰色*/
        mPaint.setColor(Color.rgb(211, 211, 211));
        RectF rectBlackBg = new RectF(2, 2, mWidth-2, mHeight-2);
        canvas.drawRoundRect(rectBlackBg, round, round, mPaint);
        //设置进度条进度及颜色
        float section =(float) (currentCount/maxCount);
        RectF rectProgressBg = new RectF(3, 3, (mWidth-3)*section, mHeight-3);
        if(section!=0.0f){
            mPaint.setColor(proColor);
        }else{
            mPaint.setColor(Color.TRANSPARENT);
        }
//        setCurrentCount(currentPro);

        canvas.drawRoundRect(rectProgressBg, round, round, mPaint);
    }
    //dip * scale + 0.5f * (dip >= 0 ? 1 : -1)
    private int dipToPx(int dip){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));//加0.5是为了四舍五入
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //MeasureSpec.EXACTLY，精确尺寸
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        //MeasureSpec.AT_MOST，最大尺寸，只要不超过父控件允许的最大尺寸即可，MeasureSpec.UNSPECIFIED未指定尺寸
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(20);
        } else {
            mHeight = heightSpecSize;
        }
        //设置控件实际大小
        setMeasuredDimension(mWidth, mHeight);
    }
}
