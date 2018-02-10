package com.zx.upm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ProfileUtil {

    public static final String KEY_UP_URL = "http://localhost:8080/up";

    public static boolean SetProfile(Activity view, String key, String value) {
        //Use 0 or MODE_PRIVATE for the default operation
        SharedPreferences settings = view.getSharedPreferences("global", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);

        // 提交本次编辑
        return editor.commit();
    }

    public static String GetProfile(Activity view, String key) {
        //Use 0 or MODE_PRIVATE for the default operation
        SharedPreferences settings = view.getSharedPreferences("global", Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }

}
