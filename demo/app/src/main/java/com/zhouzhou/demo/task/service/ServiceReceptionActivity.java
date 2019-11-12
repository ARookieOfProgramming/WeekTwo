package com.zhouzhou.demo.task.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceReceptionActivity extends Activity {


    @BindView(R.id.btn_service_reception_start)
    Button btnServiceReceptionStart;
    @BindView(R.id.btn_service_reception_stop)
    Button btnServiceReceptionStop;
    @BindView(R.id.btn_service_reception_bind)
    Button btnServiceReceptionBind;
    @BindView(R.id.btn_service_reception_unbind)
    Button btnServiceReceptionUnbind;

    private Intent intent;
    private ReceptionService.MBinder mBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ReceptionService.MBinder) service;
            mBinder.connectionActivity();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reception);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_service_reception_start, R.id.btn_service_reception_stop, R.id.btn_service_reception_bind, R.id.btn_service_reception_unbind})
    public void onViewClicked(View view) {
         intent = new Intent(this,ReceptionService.class);
        switch (view.getId()) {
            case R.id.btn_service_reception_start:
                startService(intent);
                break;
            case R.id.btn_service_reception_stop:
                stopService(intent);
                break;
            case R.id.btn_service_reception_bind:
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_service_reception_unbind:
                try {
                    unbindService(serviceConnection);
                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
