package com.tgs.topgamers.uilibtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tgs.topgamers.lib.uilib.dialog.DialogTemplate;
import com.tgs.topgamers.lib.uilib.dialog.UiLibDialog;
import com.tgs.topgamers.lib.uilib.dialog.UiLibDialogInterface;

public class TestDialogActivity extends Activity implements View.OnClickListener {
    Button mBtnNormal;
    Button mBtnList;
    Button mBtnListIos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uilib_activity_test_dialog);

        mBtnNormal = (Button) findViewById(R.id.btn_normal);
        mBtnList = (Button) findViewById(R.id.btn_list);
        mBtnListIos = (Button) findViewById(R.id.btn_list_ios);


        mBtnNormal.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnListIos.setOnClickListener(this);
    }

    private void dialogNormal() {
        UiLibDialog.Builder builder = new UiLibDialog.Builder(this, DialogTemplate.TEMPLATE_NORMAL, R.style.uiLib_dialog_anim_2);

        UiLibDialog uiLibDialog = builder.setTitle("标题")
                .setMessage("内容").setLeftButton("按钮1")
                .setTips("这里是tips这里是tips这里是tips这里是tips这里是tips这里是tips这里是tips")
                .setMidButton("按钮2", new UiLibDialogInterface.NormalOnClickListener() {
                    @Override
                    public void onClick(View v, boolean isChecked, String editText) {
                        System.out.println("按钮2" + "isChecked = " + isChecked + ",editText = " + editText);
                    }
                }, false).setMidButtonTextColor(R.color.uilib_btn_text_color_red)
                .setCheckBox("checkboxcheckboxcheck")
                .setIcon(R.drawable.loading01)
                .setEditTextHit("写点东西吧")
                .setCanceledOnTouchOutside(false)
                .create();

        uiLibDialog.show();
    }

    private void dialogList() {
        UiLibDialog.Builder builder = new UiLibDialog.Builder(this, DialogTemplate.TEMPLATE_LIST);

        UiLibDialog uiLibDialog = builder.setTitle("标题")
                .setMessage("内容").setLeftButton("按钮1")//string 字符串变化
                .setItems(new String[]{"项目1", "项目2", "项目3", "项目4"}, new UiLibDialogInterface.ListOnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position, String itemText) {
                        System.out.println("position = " + position + "," + ((TextView) v).getText());
                    }
                })
                .setIcon(R.drawable.loading01)
                .create();

        uiLibDialog.show();
    }
    private void dialogListIos() {
        UiLibDialog.Builder builder = new UiLibDialog.Builder(this, DialogTemplate.TEMPLATE_LIST_IOS);

        UiLibDialog uiLibDialog = builder.setTitle("标题")
                .setMessage("内容").setLeftButton("按钮1")//string 字符串变化
                .setItems(new String[]{"项目1", "项目2", "项目3", "项目4"}, new UiLibDialogInterface.ListOnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position, String itemText) {
                        System.out.println("position = " + position + "," + ((TextView) v).getText());
                    }
                })
                .setIcon(R.drawable.loading01)
                .create();

        uiLibDialog.show();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_normal) {
            dialogNormal();
        } else if (id == R.id.btn_list) {
            dialogList();
        } else if (id == R.id.btn_list_ios) {
            dialogListIos();
        }
    }
}
