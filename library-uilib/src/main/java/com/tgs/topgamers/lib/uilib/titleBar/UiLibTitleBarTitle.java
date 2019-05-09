package com.tgs.topgamers.lib.uilib.titleBar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tgs.topgamers.lib.uilib.R;
import com.tgs.topgamers.lib.uilib.base.UiLibCombinationBase;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description :
 */
public class UiLibTitleBarTitle extends UiLibCombinationBase<TextView> {

    protected final String TAG = "title";

    UiLibTitleBarTitle(Context context, ViewGroup rootView) {
        super(context, rootView);
    }

    @Override
    public void loadStyle(TypedArray typeArray) {
        mViewStyle = typeArray.getResourceId(R.styleable.UiLibTitleBar_title_style, 0);
    }

    @Override
    public View initView(Context context, AttributeSet attrs) {
        if (mView != null) {
            return mView;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mView = new TextView(context, attrs, 0, mViewStyle);
        } else {
            mView = new TextView(context, attrs, 0);
        }
        mView.setEllipsize(TextUtils.TruncateAt.END);
        mView.setGravity(Gravity.CENTER);
        mView.setId(R.id.uilib_titlebar_title);
        mRootView.addView(mView);
        mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        return mView;
    }


    @Override
    public void loadTypedArray(TypedArray typeArray) {
        int count = typeArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int typeArrayIndex = typeArray.getIndex(i);
            if (typeArrayIndex == R.styleable.UiLibTitleBar_title_text) {
                setText(typeArray.getString(R.styleable.UiLibTitleBar_title_text));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_textSize) {
                setTextSize(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_title_textSize, 15));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_textColor) {
                ColorStateList textColor = typeArray.getColorStateList(R.styleable.UiLibTitleBar_title_textColor);
                setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFF000000));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_singleLine) {
                setSingleLine(typeArray.getBoolean(R.styleable.UiLibTitleBar_title_singleLine, true));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_paddingLeft) {
                mPaddingLeft = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_title_paddingLeft, 0);
                setPadingLeft(mPaddingLeft);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_paddingRight) {
                mPaddingRight = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_title_paddingRight, 0);
                setPadingRight(mPaddingRight);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_paddingTop) {
                mPaddingTop = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_title_paddingTop, 0);
                setPadingTop(mPaddingTop);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_paddingBottom) {
                mPaddingBottom = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_title_paddingBottom, 0);
                setPadingBottom(mPaddingBottom);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_padding) {
                int padding = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_padding, 0);
                setPading(padding);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_title_visibility) {
                setVisibility(typeArray.getInt(R.styleable.UiLibTitleBar_title_visibility, View.VISIBLE));

            }
        }
    }

    @Override
    public void setParams() {
        mParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        mParams.addRule(RelativeLayout.LEFT_OF,R.id.uilib_titlebar_right);
//        mParams.addRule(RelativeLayout.RIGHT_OF,R.id.uilib_titlebar_left);
        mView.setLayoutParams(mParams);

    }

    void setMaxWidth(int maxpixels){
        mView.setMaxWidth(maxpixels);
    }

    void setText(String text) {
        mView.setText(text);
    }

    void setTextSize(float size) {
        mView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    void setTextColor(ColorStateList colors) {
        mView.setTextColor(colors);
    }

    void setTextColor(int color) {
        mView.setTextColor(color);
    }

    void setSingleLine(boolean singleLine) {
        mView.setSingleLine(singleLine);
    }

    void setPadingLeft(int padingLeft) {
        mPaddingLeft = padingLeft;
        setPading(padingLeft, 0, 0, 0);
    }

    void setPadingRight(int padingRight) {
        mPaddingRight = padingRight;
        setPading(0, 0, padingRight, 0);
    }

    void setPadingTop(int padingTop) {
        mPaddingTop = padingTop;
        setPading(0, padingTop, 0, 0);
    }

    void setPadingBottom(int padingBottom) {
        mPaddingBottom = padingBottom;
        setPading(0, 0, 0, padingBottom);
    }

    void setPading(int pading) {
        setPading(pading, pading, pading, pading);
    }

    private void setPading(int left, int top, int right, int bottom) {
        mView.setPadding(mPaddingLeft > 0 ? mPaddingLeft : left, mPaddingTop > 0 ? mPaddingTop : top,
                mPaddingRight > 0 ? mPaddingRight : right, mPaddingBottom > 0 ? mPaddingBottom : bottom);
    }
}
