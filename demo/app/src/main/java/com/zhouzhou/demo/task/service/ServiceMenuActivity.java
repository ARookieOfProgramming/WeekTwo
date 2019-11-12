package com.zhouzhou.demo.task.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceMenuActivity extends Activity {

    @BindView(R.id.btn_service_local)
    Button btnServiceLocal;
    @BindView(R.id.btn_service_remote)
    Button btnServiceRemote;
    @BindView(R.id.btn_service_network)
    Button btnServiceNetwork;
    @BindView(R.id.btn_service_reception)
    Button btnServiceReception;

    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_menu);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_service_local)
    public void onBtnServiceLocalClicked() {
        mIntent = new Intent(this,SerivceLocalActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_service_remote)
    public void onBtnServiceRemoteClicked() {
        mIntent = new Intent(this,ServiceRemoteActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_service_network)
    public void onBtnServiceNetworkClicked() {
        mIntent = new Intent(this,ServiceNetworkActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_service_reception)
    public void onBtnServiceReceptionClicked() {
        mIntent = new Intent(this,ServiceReceptionActivity.class);
        startActivity(mIntent);
    }
}
