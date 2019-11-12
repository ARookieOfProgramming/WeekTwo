package com.zhouzhou.demo.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhouzhou.demo.R;
import com.zhouzhou.demo.task.contentProvider.ContactsInfo;

import java.util.ArrayList;

/**
 * author : ZhouZhou
 * e-mail : zhou.zhou@sim.com
 * date   : 19-11-11下午2:14
 * desc   :
 * version: 1.0
 */
public class ContactsAdapter extends BaseAdapter {
    private ArrayList<ContactsInfo> contactsList;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private Context context;

    public ContactsAdapter(ArrayList<ContactsInfo> contactsList, Context mContext) {
        this.contactsList = contactsList;
        this.context = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactsViewHolder contactsViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.content_item_listview_show,null);
            contactsViewHolder = new ContactsViewHolder();
            contactsViewHolder.iv_content_provider_image = (ImageView) convertView.findViewById(R.id.iv_content_provider_image);
            contactsViewHolder.et_content_provider_name =(EditText) convertView.findViewById(R.id.et_content_provider_name);
            contactsViewHolder.et_content_provider_number = (EditText) convertView.findViewById(R.id.et_content_provider_number);
            convertView.setTag(contactsViewHolder);
        } else {
            contactsViewHolder = (ContactsViewHolder) convertView.getTag();
        }

        contactsViewHolder.et_content_provider_name.setText(contactsList.get(position).getName());
        contactsViewHolder.et_content_provider_number.setText(contactsList.get(position).getNumber());
        
        contactsViewHolder.et_content_provider_number.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "长按修改手机号码", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return convertView;
        
    }

    static class ContactsViewHolder {
        public ImageView iv_content_provider_image;
        public EditText et_content_provider_name;
        public EditText et_content_provider_number;
    }
}
