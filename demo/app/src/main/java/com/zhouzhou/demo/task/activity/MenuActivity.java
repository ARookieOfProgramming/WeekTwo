package com.zhouzhou.demo.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhouzhou.demo.R;
import com.zhouzhou.demo.task.activity.FirstActivity;
import com.zhouzhou.demo.task.broadcastReceiver.BroadcastActivity;
import com.zhouzhou.demo.task.service.ServiceMenuActivity;

//import java.time.LocalTime;

//import java.time.LocalTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends Activity {

    @BindView(R.id.tv_menu_hi)
    TextView tvMenuHi;
    @BindView(R.id.tv_menu_saying)
    TextView tvMenuSaying;
    @BindView(R.id.tv_menu_name)
    TextView tvMenuName;
    @BindView(R.id.rl_menu_word)
    RelativeLayout rlMenuWord;
    @BindView(R.id.btn_menu_activity)
    Button btnMenuActivity;
    @BindView(R.id.btn_menu_service)
    Button btnMenuService;
    @BindView(R.id.rl_menu_moudle1)
    RelativeLayout rlMenuMoudle1;
    @BindView(R.id.btn_menu_broadcast)
    Button btnMenuBroadcast;
    @BindView(R.id.btn_menu_component)
    Button btnMenuComponent;
    private Context mContext = MenuActivity.this;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        //initHelloWords();
    }

//    private void initHelloWords() {
//        //获取当前时间
//        LocalTime now = LocalTime.now();
//        int hour = now.getHour();
//        if (hour < 12) {
//            tvMenuHi.setText("早上好");
//        } else if (hour < 16) {
//            tvMenuHi.setText("下午好");
//        } else if (hour < 18) {
//            tvMenuHi.setText("傍晚好");
//        } else {
//            tvMenuHi.setText("晚上好");
//        }
//    }

    @OnClick({R.id.btn_menu_activity, R.id.btn_menu_service, R.id.btn_menu_broadcast, R.id.btn_menu_component})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_menu_activity:
                intent = new Intent(mContext, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu_service:
                intent = new Intent(this,ServiceMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu_broadcast:
                intent = new Intent(this, BroadcastActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu_component:
                break;
        }
    }
}