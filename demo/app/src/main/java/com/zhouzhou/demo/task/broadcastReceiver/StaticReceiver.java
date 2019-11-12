package com.zhouzhou.demo.task.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "优先值：888", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "接收到了" + intent.getStringExtra("name"), Toast.LENGTH_LONG).show();
    }
}
