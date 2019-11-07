package com.zhouzhou.demo.task.service;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//普通本地服务
public class SerivceLocalActivity extends Activity {

    @BindView(R.id.btn_service_local_start)
    Button btnServiceLocalStart;
    @BindView(R.id.btn_service_local_stop)
    Button btnServiceLocalStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serivce_local);
        ButterKnife.bind(this);
    }

//步骤3：构建Intent对象，并调用startService()启动Service、stopService停止服务
    @OnClick({R.id.btn_service_local_start, R.id.btn_service_local_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_service_local_start:
                Intent startIntent = new Intent(this,LocalService.class);
                startService(startIntent);
                break;
            case R.id.btn_service_local_stop:
                Intent stopIntent = new Intent(this,LocalService.class);
                stopService(stopIntent);
                break;
        }
    }
}
