package com.zhouzhou.demo.task.contentProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouzhou.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentCustomActivity extends Activity {


    @BindView(R.id.tv_content_custom_show_data)
    TextView tvContentCustomShowData;
    @BindView(R.id.et_content_custom_id)
    EditText etContentCustomId;
    @BindView(R.id.et_content_custom_name)
    EditText etContentCustomName;
    @BindView(R.id.et_content_custom_insert)
    Button etContentCustomInsert;
    @BindView(R.id.et_content_custom_delete)
    Button etContentCustomDelete;
    @BindView(R.id.et_content_custom_update)
    Button etContentCustomUpdate;
    @BindView(R.id.et_content_custom_query)
    Button etContentCustomQuery;

    private Uri uri = null;
    private ContentResolver contentResolver = null;
    private List<String> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_custom);
        ButterKnife.bind(this);
        contentResolver = getContentResolver();
        uri = Uri.parse("content://com.zhouzhou.demo.task.provider/employees");
        ContentValues values = new ContentValues();
        dataUpdate();
    }

    @OnClick({R.id.et_content_custom_insert, R.id.et_content_custom_delete, R.id.et_content_custom_update, R.id.et_content_custom_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_content_custom_insert:
                if (!findId() && etContentCustomId.getText().length() > 0) {
                    ContentValues values = new ContentValues();
                    values.put("id", etContentCustomId.getText().toString());
                    values.put("name", etContentCustomName.getText().toString());
                    contentResolver.insert(uri, values);
                } else {
                    if (etContentCustomId.getText().length() == 0) {
                        Toast.makeText(this, "请输入id", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "该id已存在", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.et_content_custom_delete:
                if (etContentCustomId.getText().length() > 0 && etContentCustomName.getText().length() > 0){
                    if (findId() && findName()) {
                        contentResolver.delete(uri, "id = ? and name = ?", new String[]{etContentCustomId.
                                getText().toString(), etContentCustomName.getText().toString()});
                    } else{
                        Toast.makeText(this, "无此条数据", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(this, "请输入id或name", Toast.LENGTH_SHORT).show();
                }else if(etContentCustomId.getText().length() == 0 && etContentCustomName.getText().length() == 0 ){
                    Toast.makeText(this, "请输入id或name", Toast.LENGTH_SHORT).show();
                }else if (etContentCustomId.getText().length() > 0 && etContentCustomName.getText().length() == 0 ){
                    if (findId()){
                        contentResolver.delete(uri,"id = ?",new String[]{etContentCustomId.getText().toString()});
                    }else{
                        Toast.makeText(this, "无此条数据", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (findName()){
                        contentResolver.delete(uri,"name = ?",new String[]{etContentCustomName.getText().toString()});
                    }else{
                        Toast.makeText(this, "无此条数据", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.et_content_custom_update:
                ContentValues values1 = new ContentValues();
                values1.put("name", etContentCustomName.getText().toString());
                if (findId()){
                    contentResolver.update(uri, values1, "id = ?", new String[]{etContentCustomId.getText().toString()});
                }else{
                    if (etContentCustomId.getText().length() == 0 && etContentCustomName.getText().length() == 0){
                        Toast.makeText(this, "请输入id或name", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "无此条数据", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.et_content_custom_query:
                dataUpdate();
                break;
        }
        dataUpdate();
    }

    private boolean findName() {
        Cursor cursor = contentResolver.query(uri, null, "name = ?", new String[]{etContentCustomName.
                getText().toString()}, null);
        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }

    }

    private boolean findId() {
        Cursor cursor = contentResolver.query(uri, null, "id = ?", new String[]{etContentCustomId.getText().toString()}, null);
        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }

    }

    private void dataUpdate() {
        list = new ArrayList<>();
        Cursor cursor = contentResolver.query(uri, new String[]{"id", "name"}, null, null, null);
        try {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex("id")) + " = " + cursor.getString(cursor.getColumnIndex("name")));
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            cursor.close();
        }
        tvContentCustomShowData.setText(list.toString());
    }


}
