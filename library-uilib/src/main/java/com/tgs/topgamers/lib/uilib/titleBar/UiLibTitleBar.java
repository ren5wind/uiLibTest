package com.tgs.topgamers.lib.uilib.titleBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.tgs.topgamers.lib.uilib.R;
import com.tgs.topgamers.lib.uilib.base.IUiLibCombination;
import com.tgs.topgamers.lib.uilib.base.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description : titlebar集合控件
 * test&problem:
 */
public class UiLibTitleBar extends RelativeLayout implements ViewTreeObserver.OnGlobalFocusChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
    private Context mContext;
    private List<IUiLibCombination> mIUiLibInitList;

    private UiLibTitleBarTitle mTitle;
    private UiLibTitleBarLeft mLeft;
    private UiLibTitleBarRight mRight;
    private UiLibTitleBarBottom mBottom;
    private int mHeight = -1;
    private int mMaxWidthOff;

    public UiLibTitleBar(Context context) {
        super(context);
        mContext = context;
        init(context, null, 0, 0);
    }

    public UiLibTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context, attrs, 0, 0);
    }

    public UiLibTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UiLibTitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeight != -1) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 添加view模块
     *
     * @param viewMode view模块
     */
    private void addViewModule(IUiLibCombination viewMode) {
        mIUiLibInitList.add(viewMode);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        initModule(context);
        //加载属性
        initAtribute(context, attrs);
    }

    /**
     * 初始化module
     *
     * @param context
     */
    private void initModule(Context context) {
        mIUiLibInitList = new ArrayList<>();
        mTitle = new UiLibTitleBarTitle(context, this);
        mLeft = new UiLibTitleBarLeft(context, this);
        mRight = new UiLibTitleBarRight(context, this);
        mBottom = new UiLibTitleBarBottom(context, this);

        addViewModule(mTitle);
        addViewModule(mLeft);
        addViewModule(mRight);
        addViewModule(mBottom);
    }

    /**
     * 初始化module属性
     *
     * @param context
     * @param attrs
     */
    private void initAtribute(Context context, AttributeSet attrs) {
        final TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.UiLibTitleBar);
        initTitleBarAtribute(typeArray);
        for (IUiLibCombination iUiLibInit : mIUiLibInitList) {
            iUiLibInit.loadStyle(typeArray);
            iUiLibInit.initView(context, attrs);
            iUiLibInit.loadTypedArray(typeArray);
        }
        for (IUiLibCombination iUiLibInit : mIUiLibInitList) {
            iUiLibInit.setParams();
        }
        typeArray.recycle();
    }

    /**
     * 初始化TitleBar属性
     *
     * @param typeArray
     */
    private void initTitleBarAtribute(TypedArray typeArray) {
        int count = typeArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int typeArrayIndex = typeArray.getIndex(i);
            if (typeArrayIndex == R.styleable.UiLibTitleBar_titleBarHeight) {
                mHeight = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_titleBarHeight, -1);
            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_maxWidthOff) {
                mMaxWidthOff = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_title_maxWidthOff, 15);
            }
        }
    }

    /**
     * view销毁时回收资源
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mIUiLibInitList != null) {
            for (IUiLibCombination iUiLibInit : mIUiLibInitList) {
                iUiLibInit.onDestroy();
            }
            mIUiLibInitList.clear();
            mIUiLibInitList = null;
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {

    }

    private boolean isFirst = true;

    @Override
    public void onGlobalLayout() {
        if (isFirst) {
            int titlebarWidth = getWidth();
            int leftWidth = mLeft.getView().getWidth();
            int rightWidth = mRight.getView().getWidth();
            int off = mMaxWidthOff;
            int width = leftWidth > rightWidth ? leftWidth : rightWidth;

            int titleMaxWidth = titlebarWidth - (width << 1) - off;
            mTitle.setMaxWidth(titleMaxWidth);
            isFirst = false;
        }
    }


    public void setTitleText(String text) {
        mTitle.setText(text);
    }

    public void setTitleTextSize(float size) {
        mTitle.setTextSize(size);
    }

    public void setTitleTextColor(ColorStateList colors) {
        mTitle.setTextColor(colors);
    }

    public void setTitleTextColor(int color) {
        mTitle.setTextColor(color);
    }

    public void setTitlePadingLeft(int padingLeft) {
        mTitle.setPadingLeft(padingLeft);
    }

    public void setTitlePadingRight(int padingRight) {
        mTitle.setPadingRight(padingRight);
    }

    public void setTitlePadingTop(int padingTop) {
        mTitle.setPadingTop(padingTop);
    }

    public void setTitlePadingBottom(int padingBottom) {
        mTitle.setPadingBottom(padingBottom);
    }

    public void setTitlePading(int pading) {
        mTitle.setPading(pading);
    }

    public void setTitleSingleLine(boolean singleLine) {
        mTitle.setSingleLine(singleLine);
    }

    //左边
    public void setLeftText(String text) {
        mLeft.setText(text);
    }

    public void setLeftTextSize(float size) {
        mLeft.setTextSize(size);
    }

    public void setLeftTextColor(ColorStateList colors) {
        mLeft.setTextColor(colors);
    }

    public void setLeftTextColor(int color) {
        mLeft.setTextColor(color);
    }

    public void setLeftPadingLeft(int padingLeft) {
        mLeft.setPadingLeft(padingLeft);
    }

    public void setLeftPadingRight(int padingRight) {
        mLeft.setPadingRight(padingRight);
    }

    public void setLeftPadingTop(int padingTop) {
        mLeft.setPadingTop(padingTop);
    }

    public void setLeftPadingBottom(int padingBottom) {
        mLeft.setPadingBottom(padingBottom);
    }

    public void setLeftPading(int pading) {
        mLeft.setPading(pading);
    }

    public void setLeftDrawable(Drawable drawable) {
        mLeft.setDrawable(drawable);
    }

    public void setLeftEventClickBack(boolean eventClickable) {
        mLeft.setEventClickBack(eventClickable);
    }

    public void setLeftCompoundDrawablePadding(int padding) {
        mLeft.setCompoundDrawablePadding(padding);
    }

    //右边
    public void setRightText(String text) {
        mRight.setText(text);
    }

    public void setRightTextSize(float size) {
        mRight.setTextSize(size);
    }

    public void setRightTextColor(ColorStateList colors) {
        mRight.setTextColor(colors);
    }

    public void setRightTextColor(int color) {
        mRight.setTextColor(color);
    }

    public void setRightPadingLeft(int padingLeft) {
        mRight.setPadingLeft(padingLeft);
    }

    public void setRightPadingRight(int padingRight) {
        mRight.setPadingRight(padingRight);
    }

    public void setRightPadingTop(int padingTop) {
        mRight.setPadingTop(padingTop);
    }

    public void setRightPadingBottom(int padingBottom) {
        mRight.setPadingBottom(padingBottom);
    }

    public void setRightPading(int pading) {
        mRight.setPading(pading);
    }

    public void setRightDrawable(Drawable drawable) {
        mRight.setDrawable(drawable);
    }

    public void setRightTopDrawable(Drawable drawable) {
        mRight.setDrawable(drawable);
    }

    public void setRightCompoundDrawablePadding(int padding) {
        mRight.setCompoundDrawablePadding(padding);
    }

    //底部
    public void setBottomViewLayoutHeight(float bottomViewLayoutHeight) {
        mBottom.setLayoutHeight(bottomViewLayoutHeight);
    }

    public void setBottomViewBackGround(Drawable drawable) {
        mBottom.setBackGround(drawable);
    }

    public void setTitleBarBackground(Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(background);
        } else {
            setBackgroundDrawable(background);
        }
    }

    public void setLeftOnclickListener(OnClickListener leftListener) {
        mLeft.setOnclick(leftListener);
    }

    public void setRightOnclickListener(OnClickListener rightListener) {
        mRight.setOnclick(rightListener);
    }

    public void setTitleOnclickListener(OnClickListener titleListener) {
        mTitle.setOnclick(titleListener);
    }

    public View getTitleBarTitleView() {
        return mTitle.getView();
    }

    public View getTitleBarLeftView() {
        return mLeft.getView();
    }

    public View getTitleBarRightView() {
        return mRight.getView();
    }

    public View getTitleBarBottomView() {
        return mBottom.getView();
    }

    public void setTitleVisibility(int visibility) {
        mTitle.setVisibility(visibility);
    }

    public void setLeftVisibility(int visibility) {
        mLeft.setVisibility(visibility);
    }

    public void setRightVisibility(int visibility) {
        mRight.setVisibility(visibility);
    }

    public void setBottomVisibility(int visibility) {
        mBottom.setVisibility(visibility);
    }

    private static class TitleBarParams {
        private String mLeftText;
        private Drawable mLeftDrawable;
        private float mLeftTextSize;
        private ColorStateList mLeftTextColors;
        private int mLeftTextColor = -1;
        private int mLeftPadingLeft = -1;
        private int mLeftPadingRight = -1;
        private int mLeftPadingTop = -1;
        private int mLeftPadingBottom = -1;
        private int mLeftPading = -1;
        private boolean mLeftEventClickable = true;
        private int mLeftVisibility;
        private int mLeftDrawablePadding;

        private String mRightText;
        private Drawable mRightDrawable;
        private float mRightTextSize;
        private ColorStateList mRightTextColors;
        private int mRightTextColor = -1;
        private int mRightPadingLeft = -1;
        private int mRightPadingRight = -1;
        private int mRightPadingTop = -1;
        private int mRightPadingBottom = -1;
        private int mRightPading = -1;
        private int mRightVisibility;
        private int mRightDrawablePadding;


        private String mTitleText;
        private float mTitleTextSize;
        private ColorStateList mTitleTextColors;
        private int mTitleTextColor = -1;
        private int mTitlePadingLeft = -1;
        private int mTitlePadingRight = -1;
        private int mTitlePadingTop = -1;
        private int mTitlePadingBottom = -1;
        private int mTitlePading = -1;
        private int mTitleVisibility;
        private boolean mSingleLine;

        private float mBottomViewLayoutHeight;
        private Drawable mBottomViewBackGround;
        private int mBottomVisibility;


        private Drawable mTitleBarBackground;

        private void apply(UiLibTitleBar mUiLibTitleBar) {

            if (!TextUtils.isEmpty(mLeftText)) {
                mUiLibTitleBar.setLeftText(mLeftText);
            }
            if (mLeftDrawable != null) {
                mUiLibTitleBar.setLeftDrawable(mLeftDrawable);
            }
            if (mLeftTextSize != 0) {
                mUiLibTitleBar.setLeftTextSize(mLeftTextSize);
            }
            if (mLeftTextColors != null) {
                mUiLibTitleBar.setLeftTextColor(mLeftTextColors);
            }
            if (mLeftTextColor != -1) {
                mUiLibTitleBar.setLeftTextColor(mLeftTextColor);
            }
            if (mLeftPadingLeft != -1) {
                mUiLibTitleBar.setLeftPadingLeft(mLeftPadingLeft);
            }
            if (mLeftPadingRight != -1) {
                mUiLibTitleBar.setLeftPadingRight(mLeftPadingRight);
            }
            if (mLeftPadingTop != -1) {
                mUiLibTitleBar.setLeftPadingTop(mLeftPadingTop);
            }
            if (mLeftPadingBottom != -1) {
                mUiLibTitleBar.setLeftPadingBottom(mLeftPadingBottom);
            }
            if (mLeftPading != -1) {
                mUiLibTitleBar.setLeftPading(mLeftPading);
            }
            if (mLeftDrawablePadding != 0) {
                mUiLibTitleBar.setLeftCompoundDrawablePadding(mLeftDrawablePadding);
            }
            mUiLibTitleBar.setLeftEventClickBack(mLeftEventClickable);
            mUiLibTitleBar.setLeftVisibility(mLeftVisibility);


            if (!TextUtils.isEmpty(mRightText)) {
                mUiLibTitleBar.setRightText(mRightText);
            }
            if (mRightDrawable != null) {
                mUiLibTitleBar.setRightDrawable(mRightDrawable);
            }
            if (mRightTextSize != 0) {
                mUiLibTitleBar.setRightTextSize(mRightTextSize);
            }
            if (mRightTextColors != null) {
                mUiLibTitleBar.setRightTextColor(mRightTextColors);
            }
            if (mRightTextColor != -1) {
                mUiLibTitleBar.setRightTextColor(mRightTextColor);
            }
            if (mRightPadingLeft != 0) {
                mUiLibTitleBar.setRightPadingLeft(mRightPadingLeft);
            }
            if (mRightPadingRight != 0) {
                mUiLibTitleBar.setRightPadingRight(mRightPadingRight);
            }
            if (mRightPadingTop != 0) {
                mUiLibTitleBar.setRightPadingTop(mRightPadingTop);
            }
            if (mRightPadingBottom != 0) {
                mUiLibTitleBar.setRightPadingBottom(mRightPadingBottom);
            }
            if (mRightPading != -1) {
                mUiLibTitleBar.setRightPading(mRightPading);
            }
            if (mRightDrawablePadding != 0) {
                mUiLibTitleBar.setLeftCompoundDrawablePadding(mRightDrawablePadding);
            }
            mUiLibTitleBar.setRightVisibility(mRightVisibility);


            if (!TextUtils.isEmpty(mTitleText)) {
                mUiLibTitleBar.setTitleText(mTitleText);
            }
            if (mTitleTextSize != 0) {
                mUiLibTitleBar.setTitleTextSize(mTitleTextSize);
            }
            if (mTitleTextColors != null) {
                mUiLibTitleBar.setTitleTextColor(mTitleTextColors);
            }

            if (mTitleTextColor != -1) {
                mUiLibTitleBar.setTitleTextColor(mTitleTextColor);
            }

            if (mTitlePadingLeft != 0) {
                mUiLibTitleBar.setTitlePadingLeft(mTitlePadingLeft);
            }
            if (mTitlePadingRight != 0) {
                mUiLibTitleBar.setTitlePadingRight(mTitlePadingRight);
            }
            if (mTitlePadingTop != 0) {
                mUiLibTitleBar.setTitlePadingTop(mTitlePadingTop);
            }
            if (mTitlePadingBottom != 0) {
                mUiLibTitleBar.setTitlePadingBottom(mTitlePadingBottom);
            }
            if (mTitlePading != -1) {
                mUiLibTitleBar.setTitlePading(mTitlePading);
            }
            if (mSingleLine) {
                mUiLibTitleBar.setTitleSingleLine(mSingleLine);
            }
            mUiLibTitleBar.setTitleVisibility(mTitleVisibility);


            if (mBottomViewLayoutHeight != 0) {
                mUiLibTitleBar.setBottomViewLayoutHeight(mBottomViewLayoutHeight);
            }
            if (mBottomViewBackGround != null) {
                mUiLibTitleBar.setBottomViewBackGround(mBottomViewBackGround);
            }
            if (mTitleBarBackground != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mUiLibTitleBar.setBackground(mTitleBarBackground);
                } else {
                    mUiLibTitleBar.setBackgroundDrawable(mTitleBarBackground);
                }
            }
            mUiLibTitleBar.setBottomVisibility(mBottomVisibility);
        }
    }

    public static class Builder {
        private UiLibTitleBar mUiLibTitleBar;
        private TitleBarParams p;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
            p = new TitleBarParams();
        }


        /**
         * 快速创建，style使用normal
         */
        public UiLibTitleBar Create() {
            Create(LayoutParams.WRAP_CONTENT, Tools.dip2px(mContext, 48f),
                    RelativeLayout.ALIGN_TOP, R.style.uiLib_titlebar_normal);
            return mUiLibTitleBar;
        }

        /**
         * 快速创建,指定style
         */
        public UiLibTitleBar Create(int defStyleRes) {
            Create(LayoutParams.WRAP_CONTENT, Tools.dip2px(mContext, 48f),
                    RelativeLayout.ALIGN_TOP, defStyleRes);
            return mUiLibTitleBar;
        }

        /**
         * @param titleLayoutParamsW
         * @param titleLayoutParamsH
         * @param LayoutParamsVerb   a layout verb, such as {@link #ALIGN_PARENT_LEFT}
         * @param defStyleRes
         */
        public UiLibTitleBar Create(int titleLayoutParamsW, int titleLayoutParamsH, int LayoutParamsVerb, int defStyleRes) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mUiLibTitleBar = new UiLibTitleBar(mContext, null, 0, defStyleRes);
            } else {
                mUiLibTitleBar = new UiLibTitleBar(mContext);
            }
            p.apply(mUiLibTitleBar);
            return mUiLibTitleBar;
        }

        public Builder setTitleText(String text) {
            p.mTitleText = text;
            return this;
        }

        public Builder setTitleTextSize(float size) {
            p.mTitleTextSize = size;
            return this;
        }

        public Builder setTitleTextColor(ColorStateList colors) {
            p.mTitleTextColors = colors;
            return this;
        }

        public Builder setTitleTextColor(int color) {
            p.mTitleTextColor = color;
            return this;
        }

        public Builder setTitlePadingLeft(int padingLeft) {
            p.mTitlePadingLeft = padingLeft;
            return this;
        }

        public Builder setTitlePadingRight(int padingRight) {
            p.mTitlePadingRight = padingRight;
            return this;
        }

        public Builder setTitlePadingTop(int padingTop) {
            p.mTitlePadingTop = padingTop;
            return this;
        }

        public Builder setTitlePadingBottom(int padingBottom) {
            p.mTitlePadingBottom = padingBottom;
            return this;
        }

        public Builder setTitlePading(int pading) {
            p.mTitlePading = pading;
            return this;
        }

        public Builder setTitleVisibility(int visibility) {
            p.mTitleVisibility = visibility;
            return this;
        }

        public Builder setTitleSingleLine(boolean singleLine) {
            p.mSingleLine = singleLine;
            return this;
        }

        //左边
        public Builder setLeftText(String text) {
            p.mLeftText = text;
            return this;
        }

        public Builder setLeftTextSize(float size) {
            p.mLeftTextSize = size;
            return this;
        }

        public Builder setLeftTextColor(ColorStateList colors) {
            p.mLeftTextColors = colors;
            return this;
        }

        public Builder setLeftTextColor(int color) {
            p.mLeftTextColor = color;
            return this;
        }

        public Builder setLeftPadingLeft(int padingLeft) {
            p.mLeftPadingLeft = padingLeft;
            return this;
        }

        public Builder setLeftPadingRight(int padingRight) {
            p.mLeftPadingRight = padingRight;
            return this;
        }

        public Builder setLeftPadingTop(int padingTop) {
            p.mLeftPadingTop = padingTop;
            return this;
        }

        public Builder setLeftPadingBottom(int padingBottom) {
            p.mLeftPadingBottom = padingBottom;
            return this;
        }

        public Builder setLeftPading(int pading) {
            p.mLeftPading = pading;
            return this;
        }

        public Builder setLeftDrawable(Drawable drawable) {
            p.mLeftDrawable = drawable;
            return this;
        }

        public Builder setLeftEventClickBack(boolean eventClickable) {
            p.mLeftEventClickable = eventClickable;
            return this;
        }

        public Builder setLeftVisibility(int visibility) {
            p.mLeftVisibility = visibility;
            return this;
        }

        public Builder setLeftDrawablePadding(int padding) {
            p.mLeftDrawablePadding = padding;
            return this;
        }

        //右边
        public Builder setRightText(String text) {
            p.mRightText = text;
            return this;
        }

        public Builder setRightTextSize(float size) {
            p.mRightTextSize = size;
            return this;
        }

        public Builder setRightTextColor(ColorStateList colors) {
            p.mRightTextColors = colors;
            return this;
        }

        public Builder setRightTextColor(int color) {
            p.mRightTextColor = color;
            return this;
        }

        public Builder setRightPadingLeft(int padingLeft) {
            p.mRightPadingLeft = padingLeft;
            return this;
        }

        public Builder setRightPadingRight(int padingRight) {
            p.mRightPadingRight = padingRight;
            return this;
        }

        public Builder setRightPadingTop(int padingTop) {
            p.mRightPadingTop = padingTop;
            return this;
        }

        public Builder setRightPadingBottom(int padingBottom) {
            p.mRightPadingBottom = padingBottom;
            return this;
        }

        public Builder setRightPading(int pading) {
            p.mRightPading = pading;
            return this;
        }

        public Builder mLeftDrawable(Drawable drawable) {
            p.mRightDrawable = drawable;
            return this;
        }

        public Builder setRightVisibility(int visibility) {
            p.mRightVisibility = visibility;
            return this;
        }

        public Builder setRightDrawablePadding(int padding) {
            p.mRightDrawablePadding = padding;
            return this;
        }

        //底部
        public Builder setBottomViewLayoutHeight(float bottomViewLayoutHeight) {
            p.mBottomViewLayoutHeight = bottomViewLayoutHeight;
            return this;
        }

        public Builder setBottomViewBackGround(Drawable drawable) {
            p.mBottomViewBackGround = drawable;
            return this;
        }

        public Builder setTitleBarBackground(Drawable background) {
            p.mTitleBarBackground = background;
            return this;
        }

        public Builder setBottomVisibility(int visibility) {
            p.mBottomVisibility = visibility;
            return this;
        }

    }
}
