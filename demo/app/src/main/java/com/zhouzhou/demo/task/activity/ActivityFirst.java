package com.zhouzhou.demo.task.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityFirst extends Activity {

    @BindView(R.id.et_activity_display)
    TextView etActivityDisplay;
    @BindView(R.id.ck_activity_have)
    CheckBox ckActivityHave;
    @BindView(R.id.ck_activity_no)
    CheckBox ckActivityNo;
    @BindView(R.id.btn_activity_start)
    Button btnActivityStart;
    @BindView(R.id.ck_activity_intent)
    CheckBox ckActivityIntent;

    private int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_first);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            etActivityDisplay.setText(data.getStringExtra("AAT"));
        }
    }

    @OnClick({R.id.et_activity_display, R.id.ck_activity_have, R.id.ck_activity_no,
            R.id.ck_activity_intent, R.id.btn_activity_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_activity_display:
                break;
            case R.id.ck_activity_have:

                ckActivityNo.setChecked(false);
                ckActivityIntent.setChecked(false);
                break;
            case R.id.ck_activity_no:
                ckActivityHave.setChecked(false);
                ckActivityIntent.setChecked(false);
                break;
            case R.id.ck_activity_intent:
                ckActivityNo.setChecked(false);
                ckActivityHave.setChecked(false);
                break;
            case R.id.btn_activity_start:

                Intent intent;
                if (ckActivityHave.isChecked()) {
                    ////带回调的启动
                    intent = new Intent(this,ActivityThird.class);
                    startActivityForResult(intent, requestCode);

                } else if (ckActivityNo.isChecked()) {
                    //一般启动
                    //显式intent启动活动
                    //方式一：使用构造函数
                    intent = new Intent(this, ActivitySecond.class);
                    intent.putExtra("AA", etActivityDisplay.getText().toString());

//                    //方式二1：使用setClassName传入包名+全限定类名
//                    intent.setClassName(com.zhouzhou.demo.activity.activity,
//                            com.zhouzhou.demo.activity.activity.ActivityThird);

//                    //方式二2：使用setClassName传入包名+类名/context上下文+类名
//                    intent.setClassName(this,"com.zhouzhou.demo.activity" +
//                            ".activity.ActivitySecond");

//                    //方式三：3. 通过ComponentName（）传入 包名 & 类全名
//                    ComponentName componentName = new ComponentName("com.zhouzhou.demo.activity.activity",
//                            com.zhouzhou.demo.activity.activity.ActivitySecond);
//                    intent.setComponent(componentName);

                    startActivity(intent);
                } else if (ckActivityIntent.isChecked()) {
                    try {
                        //隐式intent启动
                        intent = new Intent("android.intent.action.MY_ACTIVITY");
                        intent.addCategory("eff");
                        startActivityForResult(intent, requestCode);
                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请选择启动方式", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
