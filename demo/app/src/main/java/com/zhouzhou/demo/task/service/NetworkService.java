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
import com.zhouzhou.demo.task.MenuActivity;

//步骤1：在新建子类继承Service类，并新建一个子类继承自Binder类、写入与Activity关联需要的方法、创建binder子类实例
public class NetworkService extends Service {

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("NetworkService","执行了onCreate方法");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("aa","bb",NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this,ServiceNetworkActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        //新建Builer对象
        Notification builer = new NotificationCompat.Builder(this)
        .setContentTitle("前台服务通知的标题")//设置通知的标题
                .setChannelId("aa")
        .setContentText("前台服务通知的内容")//设置通知的内容
        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的图标
        .setContentIntent(pendingIntent)
        .build();//设置点击通知后的操作

        startForeground(1, builer);//让Service变成前台Service,并在系统的状态栏显示出来
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "服务已启动", Toast.LENGTH_SHORT).show();
        Log.d("NetworkService","执行了onStartCommand方法");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("NetworkService","执行了onDestroy方法");
        Toast.makeText(this, "服务已销毁", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.d("NetworkService","执行了onUnbind方法");
        Toast.makeText(this, "服务已解绑", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("NetworkService","执行了onBind方法");
        // TODO: Return the communication channel to the service.
        Toast.makeText(this, " 服务已绑定", Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    class MyBinder extends Binder{
        public void connectActivity(){
            Toast.makeText(NetworkService.this, "service关联了活动", Toast.LENGTH_SHORT).show();
        }
    }

}
