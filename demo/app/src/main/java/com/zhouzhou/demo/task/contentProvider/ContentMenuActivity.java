package com.zhouzhou.demo.task.contentProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentMenuActivity extends Activity {

    @BindView(R.id.btn_content_system)
    Button btnContentSystem;
    @BindView(R.id.btn_content_custom)
    Button btnContentCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_content_system, R.id.btn_content_custom})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_content_system:
                intent = new Intent(this,ContentSystemActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_content_custom:
                intent = new Intent(this,ContentCustomActivity.class);
                startActivity(intent);
                break;
        }
    }
}
