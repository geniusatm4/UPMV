package com.zx.upm.view;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import com.zx.upm.R;

import java.util.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayAdapter<String> adapter;
    private int index = 1;
    private List<String> list = new ArrayList<>();
    private ListView listview;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        listview = (ListView) view.findViewById(R.id.listview);

        initView(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        for (int i = 0; i < 20; i++) {
            list.add("你好");
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        //改变加载显示的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.RED);
        //设置背景颜色
        swipeRefreshLayout.setBackgroundColor(Color.YELLOW);
        //设置初始时的大小
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //设置监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置向下拉多少出现刷新
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置刷新出现的位置
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);
    }

    @Override
    public void onRefresh() {

        //设置每次刷新时需要更新的数据
        list.clear();
        index++;
        for (int i = 0; i < 20; i++) {
            list.add("我好,哈哈" + index);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //然刷新控件停留两秒后消失
                    Thread.sleep(2000);
                    handler.post(new Runnable() {//在主线程执行
                        @Override
                        public void run() {
                            //更新数据
                            adapter.notifyDataSetChanged();
                            //停止刷新
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
