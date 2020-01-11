package com.basefas.loopflipview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环公告 View
 */

public class LoopFlipView extends ViewFlipper {

    private Context mContext;
    private List<String> info;
    private OnItemClickListener onItemClickListener;

    private int textColor = 0xFFFFFFFF;
    private int textSize = 14;
    private boolean singleLine = true;
    private int interval = 2000;
    private int gravity = Gravity.START | Gravity.CENTER_VERTICAL;

    public LoopFlipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;
        if (info == null) {
            info = new ArrayList<>();
        }
    }

    /**
     * 启动循环
     *
     * @return 启动成功返回true，启动失败返回false
     */
    public boolean start() {
        if (info.isEmpty())
            return false;

        removeAllViews();

        for (int i = 0; i < info.size(); i++) {
            final TextView textView = createTextView(info.get(i), i);
            final int position = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(textView, position);
                    }
                }
            });
            addView(textView);
        }
        setFlipInterval(interval);
        startFlipping();
        return true;
    }

    /**
     * 停止循环
     *
     * @return 停止成功返回true，停止失败返回false
     */
    public boolean stop() {
        if (isFlipping()) {
            stopFlipping();
            return true;
        } else {
            return false;
        }

    }

    /**
     * 创建 TextView
     *
     * @param text     TextView的文字
     * @param position TextView的序号
     * @return 返回创建的TextView
     */
    private TextView createTextView(String text, int position) {
        TextView tv = new TextView(mContext);
        tv.setGravity(gravity);
        tv.setText(text);
        tv.setTextColor(textColor);
        tv.setTextSize(textSize);
        if (singleLine) {
            tv.setSingleLine(true);
            tv.setEllipsize(TextUtils.TruncateAt.END);
        }
        tv.setTag(position);
        return tv;
    }

    /**
     * 设置时间间隔
     *
     * @param interval 时间间隔，单位毫秒
     * @return 获取循环信息列表
     */
    public LoopFlipView setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    /**
     * 获取循环信息
     *
     * @return 获取循环信息列表
     */
    public List<String> getInfo() {
        return info;
    }

    /**
     * 设置循环信息
     *
     * @param info 循环信息列表
     * @return 获取循环信息列表
     */
    public LoopFlipView setInfo(List<String> info) {
        this.info = info;
        return this;
    }

    /**
     * 设置文字颜色
     *
     * @param color like R.color.xxx
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setTextColor(int color) {
        textColor = ContextCompat.getColor(mContext, color);
        return this;
    }

    /**
     * 设置文字颜色
     *
     * @param color like #xxxxxx
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setTextColor(String color) {
        textColor = Color.parseColor(color);
        return this;
    }

    /**
     * 设置文字大小
     *
     * @param textSize 文字大小
     * @return 返回本身用于链式调用
     */

    public LoopFlipView setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    /**
     * 设置对齐位置
     *
     * @param gravity Gravity.xxx
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 是否设置默认动画
     *
     * @param anim true or false
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setDefaultAnimation(boolean anim) {
        if (anim) {
            Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_flip_in);
            animIn.setDuration(500);
            setInAnimation(animIn);

            Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_flip_out);
            animOut.setDuration(500);
            setOutAnimation(animOut);
        }
        return this;
    }

    /**
     * 是否设置默认动画,并设置in和out的持续时间
     *
     * @param anim true or false
     * @param inDuration   in持续时间
     * @param outDuration  out持续时间
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setDefaultAnimationWithDuration(boolean anim, int inDuration, int outDuration) {
        if (anim) {
            Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_flip_in);
            animIn.setDuration(inDuration);
            setInAnimation(animIn);

            Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_flip_out);
            animOut.setDuration(outDuration);
            setOutAnimation(animOut);
        }
        return this;
    }

    /**
     * 设置动画
     *
     * @param inAnimation  like R.anim.xxx
     * @param outAnimation like R.anim.xxx
     * @param inDuration   in持续时间
     * @param outDuration  out持续时间
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setAnimation(int inAnimation, int outAnimation, int inDuration, int outDuration) {
        Animation animIn = AnimationUtils.loadAnimation(mContext, inAnimation);
        animIn.setDuration(inDuration);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, outAnimation);
        animOut.setDuration(outDuration);
        setOutAnimation(animOut);
        return this;
    }

    /**
     * 设置是否单行
     *
     * @param singleLine true or false
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
        return this;
    }

    /**
     * item点击事件监听
     *
     * @param onItemClickListener item点击事件监听
     * @return 返回本身用于链式调用
     */
    public LoopFlipView setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    /**
     * item点击事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(TextView textView, int position);
    }

}
