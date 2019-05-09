package com.tgs.topgamers.lib.uilib.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Author      : renxiaoming
 * Date        : 2017-07-12
 * Description :
 */
public class Tools {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    static AttributeSet getAttributeSet(Context context, int layoutId) {
        Resources res = context.getResources();
        XmlResourceParser parser = res.getLayout(layoutId);
        AttributeSet attrs = Xml.asAttributeSet(parser);
        int type;
        try {
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attrs;
    }

    public static ViewGroup getActivityRootView(Context context)
    {
        if(!(context instanceof Activity)){
            return null;
        }
        return (ViewGroup) ((ViewGroup) (((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content))).getChildAt(0);
    }

}
