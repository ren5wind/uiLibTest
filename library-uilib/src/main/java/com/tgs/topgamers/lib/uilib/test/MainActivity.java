package com.tgs.topgamers.lib.uilib.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tgs.topgamers.lib.uilib.R;


public class MainActivity extends Activity implements View.OnClickListener {
    Button mBtnDialog;
    Button mBtnTitle;
    Button mBtnRefresh;
    Button mBtnEditText;
    Button mBtnAdvTextSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uilib_activity_main);

        mBtnDialog = (Button) findViewById(R.id.btn_dialog);
        mBtnTitle = (Button) findViewById(R.id.btn_titlebar);
        mBtnRefresh = (Button) findViewById(R.id.btn_refresh);
        mBtnEditText = (Button) findViewById(R.id.btn_editText);
        mBtnAdvTextSwitcher = (Button) findViewById(R.id.btn_advTextSwitcher);

        mBtnDialog.setOnClickListener(this);
        mBtnTitle.setOnClickListener(this);
        mBtnRefresh.setOnClickListener(this);
        mBtnEditText.setOnClickListener(this);
        mBtnAdvTextSwitcher.setOnClickListener(this);
    }

    private void startActivity(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog) {
            startActivity(TestDialogActivity.class);
        } else if (id == R.id.btn_titlebar) {
            startActivity(TestTitlebarActivity.class);
        } else if (id == R.id.btn_refresh) {
            startActivity(TestRefreshActivity.class);
        } else if (id == R.id.btn_editText) {
            startActivity(TestEditTextActivity.class);
        } else if (id == R.id.btn_advTextSwitcher) {
            startActivity(TestAdvancedTextSwitcherActivity.class);
        }
    }
}
