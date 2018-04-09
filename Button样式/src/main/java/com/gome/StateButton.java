package com.gome;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


public class StateButton extends AppCompatButton {

    private int mNormalTextColor = 0;
    private int mPressedTextColor = 0;
    private int mUnableTextColor = 0;
    ColorStateList mTextColorStateList;

    private int mDuration = 0;

    private float mRadius = 0;
    //设置是否为椭圆形button
    private boolean mIsRound;

    private float mStrokeDashWidth = 0;
    private float mStrokeDashGap = 0;
    private int mNormalStrokeWidth = 0;
    private int mPressedStrokeWidth = 0;
    private int mUnableStrokeWidth = 0;
    private int mNormalStrokeColor = 0;
    private int mPressedStrokeColor = 0;
    private int mUnableStrokeColor = 0;

    private int mNormalBackgroundColor = 0;
    private int mPressedBackgroundColor = 0;
    private int mUnableBackgroundColor = 0;

    private GradientDrawable mNormalBackground;
    private GradientDrawable mPressedBackground;
    private GradientDrawable mUnableBackground;

    private int[][] mBtnStates;

    private StateListDrawable mStateBackground;

    public StateButton(Context context) {
        this(context, null);
    }

    public StateButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        mBtnStates = new int[4][];

        Drawable drawable = getBackground();
        if (drawable != null && drawable instanceof StateListDrawable) {
            mStateBackground = (StateListDrawable) drawable;
        } else {
            mStateBackground = new StateListDrawable();
        }
        mNormalBackground = new GradientDrawable();
        mPressedBackground = new GradientDrawable();
        mUnableBackground = new GradientDrawable();
        mBtnStates[0] = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
        mBtnStates[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        mBtnStates[2] = new int[]{android.R.attr.state_enabled};
        mBtnStates[3] = new int[]{-android.R.attr.state_enabled};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StateButton);
        //设置文本颜色
        mTextColorStateList = getTextColors();
        int mDefaultNormalTextColor = mTextColorStateList.getColorForState(mBtnStates[2], getCurrentTextColor());
        int mDefaultPressedTextColor = mTextColorStateList.getColorForState(mBtnStates[0], getCurrentTextColor());
        int mDefaultUnableTextColor = mTextColorStateList.getColorForState(mBtnStates[3], getCurrentTextColor());
        mNormalTextColor = typedArray.getColor(R.styleable.StateButton_normalTextColor, mDefaultNormalTextColor);
        mPressedTextColor = typedArray.getColor(R.styleable.StateButton_pressedTextColor, mDefaultPressedTextColor);
        mUnableTextColor = typedArray.getColor(R.styleable.StateButton_unableTextColor, mDefaultUnableTextColor);
        setTextColor();

        mDuration = typedArray.getInteger(R.styleable.StateButton_animationDuration, mDuration);
        mStateBackground.setEnterFadeDuration(mDuration);
        mStateBackground.setExitFadeDuration(mDuration);
        //设置不同状态GradientDrawable颜色
        mNormalBackgroundColor = typedArray.getColor(R.styleable.StateButton_normalBackgroundColor, getResources().getColor(android.R.color.darker_gray));
        mPressedBackgroundColor = typedArray.getColor(R.styleable.StateButton_pressedBackgroundColor,  getResources().getColor(android.R.color.darker_gray));
        mUnableBackgroundColor = typedArray.getColor(R.styleable.StateButton_unableBackgroundColor, getResources().getColor(android.R.color.darker_gray));
        mNormalBackground.setColor(mNormalBackgroundColor);
        mPressedBackground.setColor(mPressedBackgroundColor);
        mUnableBackground.setColor(mUnableBackgroundColor);

        //设置圆角半径
        mRadius = typedArray.getDimensionPixelSize(R.styleable.StateButton_radius, 0);
        mIsRound = typedArray.getBoolean(R.styleable.StateButton_isRound, false);
        mNormalBackground.setCornerRadius(mRadius);
        mPressedBackground.setCornerRadius(mRadius);
        mUnableBackground.setCornerRadius(mRadius);

        mStrokeDashWidth = typedArray.getDimensionPixelSize(R.styleable.StateButton_strokeDashWidth, 0);
        mStrokeDashGap = typedArray.getDimensionPixelSize(R.styleable.StateButton_strokeDashWidth, 0);
        mNormalStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.StateButton_normalStrokeWidth, 0);
        mPressedStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.StateButton_pressedStrokeWidth, 0);
        mUnableStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.StateButton_unableStrokeWidth, 0);
        mNormalStrokeColor = typedArray.getColor(R.styleable.StateButton_normalStrokeColor, 0);
        mPressedStrokeColor = typedArray.getColor(R.styleable.StateButton_pressedStrokeColor, 0);
        mUnableStrokeColor = typedArray.getColor(R.styleable.StateButton_unableStrokeColor, 0);
        setStroke();
        //设置StateListDrawable不同状态下的drawable
        mStateBackground.addState(mBtnStates[0], mPressedBackground);
        mStateBackground.addState(mBtnStates[1], mPressedBackground);
        mStateBackground.addState(mBtnStates[2], mNormalBackground);
        mStateBackground.addState(mBtnStates[3], mUnableBackground);
        setBackgroundDrawable(mStateBackground);

        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setRound(mIsRound);
    }

    private void setTextColor() {
        int[] colors = new int[]{mPressedTextColor, mPressedTextColor, mNormalTextColor, mUnableTextColor};
        mTextColorStateList = new ColorStateList(mBtnStates, colors);
        setTextColor(mTextColorStateList);
    }

    private void setStroke() {
        setStroke(mNormalBackground, mNormalStrokeColor, mNormalStrokeWidth);
        setStroke(mPressedBackground, mPressedStrokeColor, mPressedStrokeWidth);
        setStroke(mUnableBackground, mUnableStrokeColor, mUnableStrokeWidth);
    }

    private void setStroke(GradientDrawable mBackground, int mStrokeColor, int mStrokeWidth) {
        mBackground.setStroke(mStrokeWidth, mStrokeColor, mStrokeDashWidth, mStrokeDashGap);
    }

    public void setRound(boolean round) {
        this.mIsRound = round;
        int height = getMeasuredHeight();
        if (mIsRound) {
            setCornerRadius(height / 2f);
        }
    }

    public void setCornerRadius(@FloatRange(from = 0) float radius) {
        this.mRadius = radius;
        mNormalBackground.setCornerRadius(mRadius);
        mPressedBackground.setCornerRadius(mRadius);
        mUnableBackground.setCornerRadius(mRadius);
    }

    public void setCornerRadius(float[] radii) {
        mNormalBackground.setCornerRadii(radii);
        mPressedBackground.setCornerRadii(radii);
        mUnableBackground.setCornerRadii(radii);
    }


    public void setNormalStrokeColor(@ColorInt int normalStrokeColor) {
        this.mNormalStrokeColor = normalStrokeColor;
        setStroke(mNormalBackground, mNormalStrokeColor, mNormalStrokeWidth);
    }

    public void setPressedStrokeColor(@ColorInt int pressedStrokeColor) {
        this.mPressedStrokeColor = pressedStrokeColor;
        setStroke(mPressedBackground, mPressedStrokeColor, mPressedStrokeWidth);
    }

    public void setUnableStrokeColor(@ColorInt int unableStrokeColor) {
        this.mUnableStrokeColor = unableStrokeColor;
        setStroke(mUnableBackground, mUnableStrokeColor, mUnableStrokeWidth);
    }

    public void setStateStrokeColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable) {
        mNormalStrokeColor = normal;
        mPressedStrokeColor = pressed;
        mUnableStrokeColor = unable;
        setStroke();
    }


    public void setNormalStrokeWidth(int normalStrokeWidth) {
        this.mNormalStrokeWidth = normalStrokeWidth;
        setStroke(mNormalBackground, mNormalStrokeColor, mNormalStrokeWidth);
    }

    public void setPressedStrokeWidth(int pressedStrokeWidth) {
        this.mPressedStrokeWidth = pressedStrokeWidth;
        setStroke(mPressedBackground, mPressedStrokeColor, mPressedStrokeWidth);
    }

    public void setUnableStrokeWidth(int unableStrokeWidth) {
        this.mUnableStrokeWidth = unableStrokeWidth;
        setStroke(mUnableBackground, mUnableStrokeColor, mUnableStrokeWidth);
    }

    public void setStateStrokeWidth(int normal, int pressed, int unable) {
        mNormalStrokeWidth = normal;
        mPressedStrokeWidth = pressed;
        mUnableStrokeWidth = unable;
        setStroke();
    }

    public void setStrokeDash(float strokeDashWidth, float strokeDashGap) {
        this.mStrokeDashWidth = strokeDashWidth;
        this.mStrokeDashGap = strokeDashWidth;
        setStroke();
    }


    public void setStateBackgroundColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable) {
        mNormalBackgroundColor = normal;
        mPressedBackgroundColor = pressed;
        mUnableBackgroundColor = unable;
        mNormalBackground.setColor(mNormalBackgroundColor);
        mPressedBackground.setColor(mPressedBackgroundColor);
        mUnableBackground.setColor(mUnableBackgroundColor);
    }

    public void setNormalBackgroundColor(@ColorInt int normalBackgroundColor) {
        this.mNormalBackgroundColor = normalBackgroundColor;
        mNormalBackground.setColor(mNormalBackgroundColor);
    }

    public void setPressedBackgroundColor(@ColorInt int pressedBackgroundColor) {
        this.mPressedBackgroundColor = pressedBackgroundColor;
        mPressedBackground.setColor(mPressedBackgroundColor);
    }

    public void setUnableBackgroundColor(@ColorInt int unableBackgroundColor) {
        this.mUnableBackgroundColor = unableBackgroundColor;
        mUnableBackground.setColor(mUnableBackgroundColor);
    }

    public void setAnimationDuration(@IntRange(from = 0) int duration) {
        this.mDuration = duration;
        mStateBackground.setEnterFadeDuration(mDuration);
    }

    public void setStateTextColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable) {
        this.mNormalTextColor = normal;
        this.mPressedTextColor = pressed;
        this.mUnableTextColor = unable;
        setTextColor();
    }

    public void setNormalTextColor(@ColorInt int normalTextColor) {
        this.mNormalTextColor = normalTextColor;
        setTextColor();

    }

    public void setPressedTextColor(@ColorInt int pressedTextColor) {
        this.mPressedTextColor = pressedTextColor;
        setTextColor();
    }

    public void setUnableTextColor(@ColorInt int unableTextColor) {
        this.mUnableTextColor = unableTextColor;
        setTextColor();
    }
}
