package com.zx.upm.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestUtil {

    private String UP_URL = null;

    public void doGet(Activity activity, final Handler handler, String uri) {
        if (UP_URL == null) {
            UP_URL = ProfileUtil.GetProfile(activity, ProfileUtil.KEY_UP_URL);

            if (UP_URL == null) {
                ProfileUtil.SetProfile(activity, ProfileUtil.KEY_UP_URL, "http://localhost:8080:UP");
            }
        }
        final String reqURL = UP_URL + uri;
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(reqURL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8888);
                    connection.setReadTimeout(8888);

                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = buffer.readLine()) != null) {
                        response.append(line);
                    }

                    Message message = new Message();
                    message.what = 1;
                    //将服务器返回的数据存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
