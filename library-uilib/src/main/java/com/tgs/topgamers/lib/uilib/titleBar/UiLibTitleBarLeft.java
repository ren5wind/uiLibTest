package com.tgs.topgamers.lib.uilib.titleBar;

import android.annotation.TargetApi;
import android.app.Activity;
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
public class UiLibTitleBarLeft extends UiLibCombinationBase<TextView> {
    protected final String TAG = "left";


    UiLibTitleBarLeft(Context context, ViewGroup rootView) {
        super(context, rootView);
    }

    @Override
    public void loadStyle(TypedArray typeArray) {
        mViewStyle = typeArray.getResourceId(R.styleable.UiLibTitleBar_left_style, 0);
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
        mView.setId(R.id.uilib_titlebar_left);
        mView.setGravity(Gravity.CENTER);
        mRootView.addView(mView);
        return mView;
    }

    @Override
    public void loadTypedArray(TypedArray typeArray) {
        int count = typeArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int typeArrayIndex = typeArray.getIndex(i);
            if (typeArrayIndex == R.styleable.UiLibTitleBar_left_text) {
                setText(typeArray.getString(R.styleable.UiLibTitleBar_left_text));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_textSize) {
                setTextSize(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_textSize, 15));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_textColor) {
                setTextColor(typeArray.getColorStateList(R.styleable.UiLibTitleBar_left_textColor));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_paddingLeft) {
                setPadingLeft(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_paddingLeft, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_paddingRight) {
                setPadingRight(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_paddingRight, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_paddingTop) {
                setPadingTop(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_paddingTop, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_paddingBottom) {
                setPadingBottom(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_paddingBottom, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_padding) {
                int padding = typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_right_padding, 0);
                setPading(padding);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_drawable) {
                setDrawable(typeArray.getDrawable(R.styleable.UiLibTitleBar_left_drawable), null, null, null);

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_visibility) {
                setVisibility(typeArray.getInt(R.styleable.UiLibTitleBar_left_visibility, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_drawablePadding) {
                setCompoundDrawablePadding(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_left_drawablePadding, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_left_background) {
                setBackground(typeArray.getDrawable(R.styleable.UiLibTitleBar_left_background));
            }

            setEventClickBack(typeArray.getBoolean(R.styleable.UiLibTitleBar_left_onclick_back, true));

        }
    }

    @Override
    public void setParams() {
        mParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mView.setLayoutParams(mParams);
    }

    @Override
    public void setOnclick(View.OnClickListener listener) {
        if (listener != null) {
            super.setOnclick(listener);
            return;
        }
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext == null) {
                    return;
                }
                if (mContext instanceof Activity) {
                    Activity act = (Activity) mContext;
                    if (!act.isFinishing()) {
                        act.finish();
                    }
                }
            }

        });
    }

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

    void setDrawable(Drawable drawable) {
        setDrawable(drawable, null, null, null);
    }

    private void setDrawable(@Nullable Drawable left,
                             @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        mView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
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

    void setCompoundDrawablePadding(int padding) {
        mView.setCompoundDrawablePadding(padding);
    }

    void setEventClickBack(boolean eventClickable) {
        if (eventClickable) {
            setOnclick(null);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void setBackground(@Nullable Drawable backGround) {
        mView.setBackground(backGround);
    }
}
