package com.zhouzhou.demo.task.contentProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zhouzhou.demo.R;
import com.zhouzhou.demo.task.adapter.ContactsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentSystemActivity extends Activity {

    @BindView(R.id.lv_content_show)
    ListView lvContentShow;
    @BindView(R.id.btn_content_system_get)
    Button btnContentGet;


    private Context mContext = ContentSystemActivity.this;
    private ArrayList<ContactsInfo> contactsInfoArrayList = new ArrayList<>();
    private ContactsInfo contactsInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_sysytem);
        ButterKnife.bind(this);
        lvContentShow.addHeaderView(View.inflate(this, R.layout.content_listview_show_head, null));
        lvContentShow.addFooterView(View.inflate(this, R.layout.content_listview_show_foot, null));
    }

    @OnClick(R.id.btn_content_system_get)
    public void onViewClicked() {

        ContactsAdapter contactsAdapter = new ContactsAdapter(contactsInfoArrayList, this);
        lvContentShow.setAdapter(contactsAdapter);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS )
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{
            readContacts();
        }
    }

    private void readContacts() {
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);
            if (cursor != null){
                contactsInfoArrayList = new ArrayList<>();
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsInfoArrayList.add(new ContactsInfo(name,number));
                }
            }
            ContactsAdapter contactsAdapter = new ContactsAdapter(contactsInfoArrayList, this);
            lvContentShow.setAdapter(contactsAdapter);
        }catch (Exception e){
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            cursor.close();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }
                else{
                    Toast.makeText(mContext, "未授予权限，无法读取联系人", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}


