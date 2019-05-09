package com.tgs.topgamers.lib.uilib.titleBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tgs.topgamers.lib.uilib.R;
import com.tgs.topgamers.lib.uilib.base.UiLibCombinationBase;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description :
 */
public class UiLibTitleBarBottom extends UiLibCombinationBase {

    protected final String TAG = "buttom";

    UiLibTitleBarBottom(Context context, ViewGroup rootView) {
        super(context, rootView);
    }

    @Override
    public void loadStyle(TypedArray typeArray) {
        mViewStyle = typeArray.getResourceId(R.styleable.UiLibTitleBar_bottom_style, 0);
    }

    @Override
    public View initView(Context context, AttributeSet attrs) {
        if (mView != null) {
            return mView;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mView = new View(context, attrs, 0, mViewStyle);
        } else {
            mView = new View(context, attrs, 0);
        }
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mView.setLayoutParams(bottomParams);
        mView.setId(R.id.uilib_titlebar_Bottom);
        mRootView.addView(mView);
        return mView;
    }

    @Override
    public void loadTypedArray(TypedArray typeArray) {
        int count = typeArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int typeArrayIndex = typeArray.getIndex(i);
            if (typeArrayIndex == R.styleable.UiLibTitleBar_bottom_layout_height) {
                setLayoutHeight(typeArray.getDimensionPixelSize(R.styleable.UiLibTitleBar_bottom_layout_height, 0));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_bottom_background) {
                setBackGround(typeArray.getDrawable(R.styleable.UiLibTitleBar_bottom_background));

            } else if (typeArrayIndex == R.styleable.UiLibTitleBar_bottom_visibility) {
                setVisibility(typeArray.getInt(R.styleable.UiLibTitleBar_bottom_visibility,View.VISIBLE));

            }
        }
    }

    @Override
    public void setParams() {

    }

    void setLayoutHeight(float layoutHeight) {
        mView.getLayoutParams().height = (int) layoutHeight;
    }

    void setBackGround(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mView.setBackground(drawable);
        }else{
            mView.setBackgroundDrawable(drawable);

        }
    }
}
