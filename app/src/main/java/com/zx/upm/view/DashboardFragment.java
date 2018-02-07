package com.zx.upm.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import com.zx.upm.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    public static DashboardFragment newInstance(String info) {
        Bundle args = new Bundle();
        DashboardFragment fragment = new DashboardFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.text1);
        Button btn = (Button) view.findViewById(R.id.button);
        tvInfo.setText(getArguments().getString("info"));
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Don't click me.please!.", Snackbar.LENGTH_SHORT).show();
            }
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SystemPhotoUtil  photoUtil = new SystemPhotoUtil();
//                //设置你要截取的图片的大小,默认是250*250的
//                photoUtil.setCropPhotoSize(250,250);
//                //拍摄照片
//                photoUtil.takePhoto(getActivity());
//                //选择一张照片
//                photoUtil.pickPhoto(getActivity());
//                //你可以在 onActivityResult方法中获取到截取的bitmap对象,并使用它
//
//            }
//        });
        return view;
    }

}
