package com.tgs.topgamers.lib.uilib.titleBar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
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
public class UiLibTitleBarRight extends UiLibCombinationBase<TextView> {

    protected final String TAG = "right";

    UiLibTitleBarRight(Context context, ViewGroup rootView) {
        super(context, rootView);
    }

    @Override
    public void loadStyle(TypedArray typeArray) {
        mViewStyle = typeArray.getResourceId(R.styleable.UiLibTitleBar_right_style, 0);
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
        mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mView.setId(R.id.uilib_titlebar_right);
        mView.setGravity(Gravity.CENTER);
        mRootView.addView(mView);
        return mView;
    }

    @Override
    public void loadTypedArray(TypedArray typeArray) {
        int count = typeArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int typeArrayIndex = typeArray.getIndex(i);
            if (typeArrayIndex == R.styleable.UiLibTitleBar_right_text) {
                setText(typeArray.getString(R.styleable.UiLibTitleBar_right_text));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_textSize) {
                setTextSize(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_textSize, 15));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_textColor) {
                setTextColor(typeArray.getColorStateList(R.styleable.UiLibTitleBar_right_textColor));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_paddingLeft) {
                setPadingLeft(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_paddingLeft, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_paddingRight) {
                setPadingRight(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_paddingRight, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_paddingTop) {
                setPadingTop(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_paddingTop, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_paddingBottom) {
                setPadingBottom(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_paddingBottom, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_padding) {
                int padding = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_padding, 0);
                setPading(padding);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_drawable) {
                setDrawable(null, null, typeArray.getDrawable(R.styleable.UiLibTitleBar_right_drawable), null);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_visibility) {
                setVisibility(typeArray.getInt(R.styleable.UiLibTitleBar_right_visibility, View.VISIBLE));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_drawablePadding) {
                setCompoundDrawablePadding(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_drawablePadding, 0));
            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_right_background) {
                setBackground(typeArray.getDrawable(R.styleable.UiLibTitleBar_right_background));
            }
        }
    }

    @Override
    public void setParams() {
        mParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mView.setLayoutParams(mParams);
    }

    //右边
    void setText(String text) {
        mView.setText(text);
    }

    void setTextSize(float size) {
        mView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    void setTextColor(ColorStateList color) {
        mView.setTextColor(color);
    }

    void setTextColor(int color) {
        mView.setTextColor(color);
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

    void setDrawable(Drawable drawable) {
        setDrawable(null, null, drawable, null);
    }

    void setTopDrawable(Drawable drawable) {
        setDrawable(null, drawable, null, null);
    }

    private void setDrawable(@Nullable Drawable left,
                             @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        mView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    void setCompoundDrawablePadding(int padding) {
        mView.setCompoundDrawablePadding(padding);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void setBackground(@Nullable Drawable backGround) {
        mView.setBackground(backGround);
    }
}
