package com.zhouzhou.demo.task.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

//本地服务
//步骤1：新建子类继承Service类
public class LocalService extends Service {

    private String mCreate = "";
    private String mCommand = "";
    private String mDestroy = "";
    public LocalService() {
    }

//步骤2:重写父类的onCreate()、onStartCommand()、onDestroy()和onBind()
    @Override
    public void onCreate() {
        super.onCreate();
        mCreate = "onCreate";
        Log.d("LocalService","执行了onCreate方法");
        Toast.makeText(this, "执行了" + " " + mCreate, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCommand = "onStartCommand";
        Toast.makeText(this, "执行了" + " " + mCommand, Toast.LENGTH_SHORT).show();
        Log.d("LocalService","执行了onStartCommand方法");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mDestroy = "onDestroy";
        super.onDestroy();
        Toast.makeText(this, "执行了" + mDestroy, Toast.LENGTH_SHORT).show();
        Log.d("LocalService","执行了onDestroy方法");

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
