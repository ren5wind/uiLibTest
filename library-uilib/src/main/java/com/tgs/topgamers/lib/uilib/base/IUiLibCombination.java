package com.tgs.topgamers.lib.uilib.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description :
 */
public interface IUiLibCombination {
    /**
     * 加载style
     * @param typeArray
     */
    void loadStyle(TypedArray typeArray);

    /**
     * 初始化view
     * @param context
     * @param attrs
     * @return
     */
    View initView(Context context, AttributeSet attrs);

    /**
     * 加载typeArray数据
     * @param typeArray
     */
    void loadTypedArray(TypedArray typeArray);

    /**
     * 调整布局
     */
    void setParams();

    void setOnclick(View.OnClickListener listener);

    void onDestroy();
}
