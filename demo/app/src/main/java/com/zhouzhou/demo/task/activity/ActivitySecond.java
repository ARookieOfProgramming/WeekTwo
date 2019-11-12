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

public class ActivitySecond extends Activity {

    @BindView(R.id.et_activity_input)
    EditText etActivityInput;
    @BindView(R.id.btn_activity_back)
    Button btnActivityBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_second);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        etActivityInput.setText(intent.getStringExtra("AA"));
    }

    @OnClick(R.id.btn_activity_back)
    public void onViewClicked() {

        finish();

    }
}
