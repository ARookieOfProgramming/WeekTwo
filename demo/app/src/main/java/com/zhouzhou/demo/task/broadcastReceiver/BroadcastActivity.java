package com.zhouzhou.demo.task.broadcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//动态注册方式及发送怒痛类型广播
public class BroadcastActivity extends Activity {

    @BindView(R.id.btn_broadcast_send_one)
    Button btnBroadcastSendOne;
    @BindView(R.id.btn_broadcast_send_two)
    Button btnBroadcastSendTwo;
    @BindView(R.id.btn_broadcast_send_three)
    Button btnBroadcastSendThree;
    @BindView(R.id.btn_broadcast_send_four)
    Button btnBroadcastSendFour;

    private IntentFilter mFilter;
    private MyBroadCastReceivver mReceivver;
    private Intent intent;
    private int num;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        //广播接收器注册
        //接收系统广播
        super.onResume();
//        mReceivver = new MyBroadCastReceivver();
//        mFilter = new IntentFilter();
//        mFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        //mFilter.setPriority(100);//接收有序广播时设置
//        registerReceiver(mReceivver, mFilter);
    }

    @Override
    protected void onPause() {
        //广播接收器取消注册
        super.onPause();
        if (count == 1){
            unregisterReceiver(mReceivver);
        }
    }

    @OnClick({R.id.btn_broadcast_send_one, R.id.btn_broadcast_send_two, R.id.btn_broadcast_send_three,R.id.btn_broadcast_send_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_broadcast_send_one:
                //发送标准广播
                intent = new Intent();
                intent.putExtra("name","普通广播");
                intent.setAction("BROADCAST_ACTION");
                intent.setPackage("com.zhouzhou.demo");
                sendBroadcast(intent);
                break;
            case R.id.btn_broadcast_send_two:
                //发送有序广播
                intent = new Intent();
                intent.setAction("BROADCAST_ACTION");
                intent.putExtra("name","有序广播");
                intent.setPackage("com.zhouzhou.demo");
                sendOrderedBroadcast(intent,null);
                break;
            case R.id.btn_broadcast_send_three:
                //发送本地广播
                startActivity(new Intent(this,LocalBroadcastActivity.class));
                break;
            case R.id.btn_broadcast_send_four:
                count++;
                // 接收系统广播
                if (count == 1){
                    mReceivver = new MyBroadCastReceivver();
                    mFilter = new IntentFilter();
                    //mFilter.setPriority(889);接收有序广播时设置优先级
                    mFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    registerReceiver(mReceivver,mFilter);
                }
                break;
        }
    }


    private class MyBroadCastReceivver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (num > 0){
                    Toast.makeText(context, "网络现已可用", Toast.LENGTH_SHORT).show();
                }
            }else{
                num++;
                Toast.makeText(context, "网络已断开", Toast.LENGTH_SHORT).show();
            }
            }

    }
}
