package com.tgs.topgamers.lib.uilib.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tgs.topgamers.lib.uilib.R;
import com.tgs.topgamers.lib.uilib.refresh.UiLibRefreshOnLoadMoreListener;
import com.tgs.topgamers.lib.uilib.refresh.UiLibRefreshOnRefreshListener;
import com.tgs.topgamers.lib.uilib.refresh.UiLibRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRefreshActivity extends Activity {
    UiLibRefreshLayout mRefresh;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uilib_activity_test_refresh);

        mRefresh = (UiLibRefreshLayout) findViewById(R.id.refresh);
        mListView = (ListView) findViewById(R.id.uilib_swipe_target);


        mRefresh.setOnRefreshListener(new UiLibRefreshOnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(false);
            }
        });

        mRefresh.setOnLoadMoreListener(new UiLibRefreshOnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRefresh.setLoadingMore(false);
            }
        });
//        mRefresh.setLoadingMore(true);

        initListView(new String[]{"item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item"});
    }

    private void initListView(String[] arrText) {

        List<Map<String, String>> items = new ArrayList<>();
        for (int i = 0; i < arrText.length; i++) {
            Map<String, String> item = new HashMap<>();
            item.put("text", arrText[i]);
            items.add(item);
        }

        SimpleAdapter mAdapter = new SimpleAdapter(this, items, R.layout.uilib_layout_dialog_item,
                new String[]{"text"}, new int[]{R.id.uilib_dialog_item_text});

        mListView.setAdapter(mAdapter);

    }

}
