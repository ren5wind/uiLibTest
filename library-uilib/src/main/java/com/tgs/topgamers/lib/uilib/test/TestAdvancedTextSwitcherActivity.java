package com.tgs.topgamers.lib.uilib.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tgs.topgamers.lib.uilib.advancedTextSwitcher.AdvTextSwitcher;
import com.tgs.topgamers.lib.uilib.advancedTextSwitcher.Switcher;
import com.tgs.topgamers.lib.uilib.R;

/**
 * Author      : renxiaoming
 * Date        : 2017/7/21
 * Description :
 */
public class TestAdvancedTextSwitcherActivity extends Activity {

    private AdvTextSwitcher advTextSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uilib_activity_test_edittext);
        advTextSwitcher = (AdvTextSwitcher) findViewById(R.id.advTextSwitcher);
        //For example, the String array below contains four recent reviews.
        String[] texts = {"Anne: Great!", "Cathy: I do not think so.", "Jimmy: Cloning your repo...", "Aoi: This bug disappeared!"};
        advTextSwitcher = (AdvTextSwitcher) findViewById(R.id.advTextSwitcher);
        //Give them to AdvTextSwitcher
        advTextSwitcher.setTexts(texts);
        //Manually switch to the next text in the String array.
        advTextSwitcher.next();
        //Switch to the previous one.
        advTextSwitcher.previous();

        //Auto switch between texts every 5000ms.
        Switcher switcher = new Switcher(advTextSwitcher, 5000);
        switcher.start();
        //Pause
        switcher.pause();
        //Or use switcher in only one line...
        new Switcher().attach(advTextSwitcher).setDuration(5000).start();

        //Want to know which text is clicked?
        advTextSwitcher.setCallback(new AdvTextSwitcher.Callback() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(TestAdvancedTextSwitcherActivity.this, "ITEM@" + position + " Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
