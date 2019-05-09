package com.tgs.topgamers.lib.uilib.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgs.topgamers.lib.uilib.R;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description :
 */
public class UiLibDialogNormal extends UiLibDialogImp {

    private boolean mIsChecked;
    private EditText mEt;
    private MyCountDownTimer mCountDownTimer;
    private int mCountDown;
    private int mButtonCountdownDirection;
    static final int BUTTON_COUNTDOWN_DIRECTION_LEFT = 1;
    static final int BUTTON_COUNTDOWN_DIRECTION_MID = 2;
    static final int BUTTON_COUNTDOWN_DIRECTION_RIGHT = 3;
    private String mLeftButtonText, mMidButtonText, mRightButtonText;
    private UiLibDialogInterface.NormalOnCountdownListener mNormalCountdownListener;

    protected UiLibDialogNormal(@NonNull Context context, int themeResId, int layoutResID) {
        super(context, themeResId, layoutResID);
    }

    private void setNormalTitle(@Nullable CharSequence title) {
        setTextViewText(R.id.uilib_dialog_tv_title, title);
        getViewById(R.id.uilib_dialog_tv_title).setVisibility(View.VISIBLE);

    }

    private void setNormalMessage(@Nullable CharSequence message) {
        setTextViewText(R.id.uilib_dialog_tv_message, message);
        getViewById(R.id.uilib_dialog_tv_message).setVisibility(View.VISIBLE);
    }

    private void setNormalIcon(int resId) {
        getViewById(R.id.uilib_dialog_iv_icon).setVisibility(View.VISIBLE);
        setImageViewSrc(R.id.uilib_dialog_iv_icon, resId);
    }

    private void setNormalIcon(Drawable drawable) {
        getViewById(R.id.uilib_dialog_iv_icon).setVisibility(View.VISIBLE);
        setImageViewSrc(R.id.uilib_dialog_iv_icon, drawable);
    }

    private void setNormalTips(@Nullable CharSequence tips) {
        setTextViewText(R.id.uilib_dialog_tv_tips, tips);
        getViewById(R.id.uilib_dialog_tv_tips).setVisibility(View.VISIBLE);
    }

    private void setNormalCheckBox(CharSequence text, Drawable drawable) {
        setCheckBox(R.id.uilib_dialog_checkbox, text, drawable);
        getViewById(R.id.uilib_dialog_checkbox).setVisibility(View.VISIBLE);
    }

    private void setNormalCheckBoxListener() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.uilib_dialog_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsChecked = isChecked;
            }
        });
    }

    private void setTextColor(int resId, int colorsResId) {
        ColorStateList csl = (ColorStateList) mContext.getResources().getColorStateList(colorsResId);
        if (csl != null) {
            ((TextView) findViewById(resId)).setTextColor(csl);
        }
    }


    @SuppressLint("WrongConstant")
    private void setNormalEditText(CharSequence hint) {
        mEt = (EditText) findViewById(R.id.uilib_dialog_et_name);
        View view = findViewById(R.id.uilib_dialog_edit);

        if (hint != null) {
            view.setVisibility(View.VISIBLE);
            mEt.setHint(hint);
            setNormalEditTextClear();
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void setNormalEditTextClear() {
        ImageView view = (ImageView) findViewById(R.id.uilib_dialog_btn_clear);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt.setText("");
            }
        });
    }

    private void setNormalOnclickListener(int viewId, final UiLibDialogInterface.NormalOnClickListener listener,
                                          final boolean aotuDismiss) {
        View view = findViewById(viewId);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v, mIsChecked, (mEt == null) ? null : mEt.getText().toString().trim());
                }
                if (aotuDismiss) {
                    dismiss();
                }
                mNormalCountdownListener = null;
            }
        });

    }

    private void setNormalCheckBox(CharSequence text) {
        setCheckBox(R.id.uilib_dialog_checkbox, text);
    }

    @SuppressLint("WrongConstant")
    public void setNormalButton(CharSequence leftButtonText, CharSequence midButtonText, CharSequence rightButtonText,
                                UiLibDialogInterface.NormalOnClickListener mLeftButtonListener,
                                UiLibDialogInterface.NormalOnClickListener mMidButtonListener,
                                UiLibDialogInterface.NormalOnClickListener mRightButtonListener,
                                boolean leftButtonAotuDismiss, boolean midButtonAotuDismiss,
                                boolean rightButtonAotuDismiss, int buttonCountdownTime, int buttonCountdownDirection) {
        //计算button与分割线view的显示
        int buttonIndex = 0;
        if (!TextUtils.isEmpty(leftButtonText)) {
            mLeftButtonText = leftButtonText.toString();
            if (buttonCountdownTime > 0 && buttonCountdownDirection == BUTTON_COUNTDOWN_DIRECTION_LEFT) {
                mCountDown = buttonCountdownTime;
                mButtonCountdownDirection = buttonCountdownDirection;
                setTextViewText(R.id.uilib_dialog_btn_left, leftButtonText + "" + buttonCountdownTime);
            } else {
                setTextViewText(R.id.uilib_dialog_btn_left, leftButtonText);
            }
            buttonIndex = buttonIndex | 4;
        } else {
            findViewById(R.id.uilib_dialog_btn_left).setVisibility(View.GONE);
        }

        setNormalOnclickListener(R.id.uilib_dialog_btn_left, mLeftButtonListener, leftButtonAotuDismiss);
        setNormalOnclickListener(R.id.uilib_dialog_btn_right, mRightButtonListener, rightButtonAotuDismiss);
        setNormalOnclickListener(R.id.uilib_dialog_btn_mid, mMidButtonListener, midButtonAotuDismiss);

        if (!TextUtils.isEmpty(midButtonText)) {
            mMidButtonText = midButtonText.toString();
            if (buttonCountdownTime > 0 && buttonCountdownDirection == BUTTON_COUNTDOWN_DIRECTION_MID) {
                mCountDown = buttonCountdownTime;
                mButtonCountdownDirection = buttonCountdownDirection;
                setTextViewText(R.id.uilib_dialog_btn_mid, midButtonText + "" + buttonCountdownTime);
            } else {
                setTextViewText(R.id.uilib_dialog_btn_mid, midButtonText);
            }
            buttonIndex = buttonIndex | 2;
        } else {
            findViewById(R.id.uilib_dialog_btn_mid).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(rightButtonText)) {
            mRightButtonText = rightButtonText.toString();
            if (buttonCountdownTime > 0 && buttonCountdownDirection == BUTTON_COUNTDOWN_DIRECTION_RIGHT) {
                mCountDown = buttonCountdownTime;
                mButtonCountdownDirection = buttonCountdownDirection;
                setTextViewText(R.id.uilib_dialog_btn_right, rightButtonText + "" + buttonCountdownTime);
            } else {
                setTextViewText(R.id.uilib_dialog_btn_right, rightButtonText);
            }
            buttonIndex = buttonIndex | 1;
        } else {
            findViewById(R.id.uilib_dialog_btn_right).setVisibility(View.GONE);
        }

        switch (buttonIndex & 7) {
            case 0:
            case 1:
            case 2:
            case 4:
                findViewById(R.id.uilib_dialog_view_1).setVisibility(View.GONE);
                findViewById(R.id.uilib_dialog_view_2).setVisibility(View.GONE);
                break;
            case 3:
            case 5:
                findViewById(R.id.uilib_dialog_view_1).setVisibility(View.GONE);
                break;
            case 6:
                findViewById(R.id.uilib_dialog_view_2).setVisibility(View.GONE);
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void applyDialog(UiLibDialogParams params) {
        if (!TextUtils.isEmpty(params.mTitle)) {
            setNormalTitle(params.mTitle);
        }
        if (!TextUtils.isEmpty(params.mMessage)) {
            setNormalMessage(params.mMessage);
        }
        if (!TextUtils.isEmpty(params.mTips)) {
            setNormalTips(params.mTips);
        }
        if (params.mIconId != -1) {
            setNormalIcon(params.mIconId);
        }
        if (params.mIconDrawable != null) {
            setNormalIcon(params.mIconDrawable);
        }
        if (params.mCheckBoxText != null) {
            setNormalCheckBox(params.mCheckBoxText);
        }

        if (params.mCheckBoxDrawable != null) {
            setNormalCheckBox(params.mCheckBoxText, params.mCheckBoxDrawable);
        }

        if (params.mCheckBoxVisible) {
            findViewById(R.id.uilib_dialog_checkbox).setVisibility(View.VISIBLE);
            setNormalCheckBoxListener();
        } else {
            findViewById(R.id.uilib_dialog_checkbox).setVisibility(View.GONE);
        }

        if (params.mLeftButtonTextColorResId != -1) {
            setTextColor(R.id.uilib_dialog_btn_left, params.mLeftButtonTextColorResId);
        }
        if (params.mRightButtonTextColorResId != -1) {
            setTextColor(R.id.uilib_dialog_btn_right, params.mRightButtonTextColorResId);
        }
        if (params.mMidButtonTextColorResId != -1) {
            setTextColor(R.id.uilib_dialog_btn_mid, params.mMidButtonTextColorResId);
        }

        if (params.mNormalCountdownListener != null) {
            mNormalCountdownListener = params.mNormalCountdownListener;
        }

        setNormalEditText(params.mEditTextHint);

        setNormalButton(params.mLeftButtonText, params.mMidButtonText, params.mRightButtonText,
                params.mNormalLeftButtonListener, params.mNormalMidButtonListener, params.mNormalRightButtonListener,
                params.mLeftButtonAotuDismiss, params.mMidButtonAotuDismiss, params.mRightButtonAotuDismiss,
                params.mButtonCountdownTime, params.mButtonCountdownDirection);
    }

    public void show() {
        super.show();
        if (mNormalCountdownListener != null) {
            mCountDownTimer = null;
            mCountDownTimer = new MyCountDownTimer(mCountDown * 1000, 1000);
            mCountDownTimer.start();
        }
    }


    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            switch (mButtonCountdownDirection) {
                case BUTTON_COUNTDOWN_DIRECTION_LEFT:
                    setTextViewText(R.id.uilib_dialog_btn_left, mLeftButtonText + "（" + l / 1000 + "）");
                    break;
                case BUTTON_COUNTDOWN_DIRECTION_MID:
                    setTextViewText(R.id.uilib_dialog_btn_mid, mMidButtonText + "（" + l / 1000 + "）");
                    break;
                case BUTTON_COUNTDOWN_DIRECTION_RIGHT:
                    setTextViewText(R.id.uilib_dialog_btn_right, mRightButtonText + "（" + l / 1000 + "）");
                    break;
            }
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            dismiss();
            if (mNormalCountdownListener != null) {
                mNormalCountdownListener.onCountdownFinish();
            }
        }
    }
}
