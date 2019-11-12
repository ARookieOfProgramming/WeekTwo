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

/**
 * //可通信服务
 * //步骤1：在新建子类继承Service类，并新建一个子类继承自Binder类、写入与Activity关联需要的方法、创建实例
 */
public class ServiceNetworkActivity extends Activity {

    @BindView(R.id.btn_service_network_start)
    Button btnServiceNetworkStart;
    @BindView(R.id.btn_service_network_stop)
    Button btnServiceNetworkStop;
    @BindView(R.id.btn_service_network_bind)
    Button btnServiceNetworkBind;
    @BindView(R.id.btn_service_network_unbind)
    Button btnServiceNetworkUnbind;


    private Intent mIntent;
    private NetworkService.MyBinder mMyBinder;
    private ServiceConnection connection = new ServiceConnection() {

        //关联时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //向下转型
            mMyBinder = (NetworkService.MyBinder) service;
            //调用Binder子类方法
            mMyBinder.connectActivity();
        }

        //解除关联时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_network);
        ButterKnife.bind(this);
        mIntent = new Intent(this, NetworkService.class);
    }

    @OnClick({R.id.btn_service_network_start, R.id.btn_service_network_stop, R.id.btn_service_network_bind, R.id.btn_service_network_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_service_network_start:
                startService(mIntent);
                break;
            case R.id.btn_service_network_stop:
                stopService(mIntent);
                break;
            case R.id.btn_service_network_bind:
                bindService(mIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_service_network_unbind:
                try {
                    unbindService(connection);
                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
