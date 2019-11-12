package com.zhouzhou.demo.task.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.zhouzhou.demo.R;

public class ReceptionService extends Service {
    private MBinder mBinder = new MBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ReceptionService", "执行了onCreate方法");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("abc", "def", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
            Intent intent = new Intent(this, ServiceReceptionActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            Notification build = new NotificationCompat.Builder(this)
                    .setContentTitle("demo的前台服务通知")
                    .setChannelId("abc")
                    .setContentText("这是一个前台服务")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            startForeground(1, build);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "服务已启动", Toast.LENGTH_SHORT).show();
        Log.d("ReceptionService", "执行了onStartCommand方法");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("ReceptionService", "执行了onDestroy方法");
        Toast.makeText(this, "服务已销毁", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.d("ReceptionService", "执行了onUnbind方法");
        Toast.makeText(this, "服务已解绑", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ReceptionService", "执行了onBind方法");
        // TODO: Return the communication channel to the service.
        Toast.makeText(this, " 服务已绑定", Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    class MBinder extends Binder {

        public void connectionActivity() {
            Toast.makeText(ReceptionService.this, "前台服务", Toast.LENGTH_SHORT).show();
        }
    }

}
