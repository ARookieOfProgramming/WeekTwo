package com.zhouzhou.demo.task.broadcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//本地广播
public class LocalBroadcastActivity extends Activity {


    @BindView(R.id.btn_broadcast_send_three)
    Button btnBroadcastSendThree;

    private LocaBroadcastReceiver mLocaBroadcastReceiver;
    private IntentFilter mFilter;
    private LocalBroadcastManager mLocalBroadcastManager;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loca_broadcast);
        ButterKnife.bind(this);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocaBroadcastReceiver = new LocaBroadcastReceiver();
        mFilter = new IntentFilter();
        mFilter.addAction("LOCAL_BROADCAST_ACTION");
        mLocalBroadcastManager.registerReceiver(mLocaBroadcastReceiver,mFilter);
    }

    @OnClick(R.id.btn_broadcast_send_three)
    public void onViewClicked() {
        mIntent = new Intent("LOCAL_BROADCAST_ACTION");
        mIntent.putExtra("name","本地广播");
        mLocalBroadcastManager.sendBroadcast(mIntent);
    }

    private class LocaBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到了" + intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
        }
    }
}
