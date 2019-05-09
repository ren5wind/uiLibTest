package com.tgs.topgamers.lib.uilib.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description :
 */
public abstract class UiLibCombinationBase<T extends View> implements IUiLibCombination {

    protected ViewGroup mRootView;

    protected T mView;

    protected RelativeLayout.LayoutParams mParams;

    protected int mViewStyle;

    protected Context mContext;

    protected int mPaddingLeft, mPaddingRight, mPaddingTop, mPaddingBottom;


    protected UiLibCombinationBase(Context context, ViewGroup rootView) {
        mRootView = rootView;
        mContext = context;
    }

    public View getView(){
        return mView;
    }

    public void setVisibility(int visibility){
        mView.setVisibility(visibility);
    }

    @Override
    public void setOnclick(View.OnClickListener listener) {
        mView.setOnClickListener(listener);
    }

    @Override
    public void onDestroy() {

    }

}
