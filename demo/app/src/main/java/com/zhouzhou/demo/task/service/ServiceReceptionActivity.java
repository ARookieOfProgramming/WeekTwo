package com.zhouzhou.demo.task.service;

import android.app.Activity;
import android.os.Bundle;

import com.zhouzhou.demo.R;
//可通信服务
//步骤1：在新建子类继承Service类，并新建一个子类继承自Binder类、写入与Activity关联需要的方法、创建实例
public class ServiceReceptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reception);

    }
}
