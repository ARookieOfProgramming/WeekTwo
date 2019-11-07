package com.zhouzhou.demo.task.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zhouzhou.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdActivity extends Activity {

    @BindView(R.id.btn_activity_third_back)
    Button btnActivityThirdBack;
    @BindView(R.id.et_activity_third_input)
    EditText etActivityThirdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.btn_activity_third_back)
    public void onViewClicked() {
        int resultCode = 2;
        setResult(resultCode,new Intent().putExtra("AAT", etActivityThirdInput.getText().toString()));
        finish();
    }
}
