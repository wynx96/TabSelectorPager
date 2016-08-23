package com.example.tw.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tw.app.R;
import com.example.tw.app.bean.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanwei on 2016/8/17.
 */
public class ChooseGridAdapter extends ArrayAdapter<Table> {
    private LayoutInflater inflater;
    private Context context;

    public ChooseGridAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tab_item_layout2, null);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Table table = getItem(position);
        holder.tabName.setText(table.getName());

        return convertView;
    }

    public static class ViewHolder {
        private View view;
        public TextView tabName;

        public ViewHolder(View view) {
            this.view = view;
            this.view.setTag(this);
            tabName = (TextView) view.findViewById(R.id.item_tab_name);
        }

        public View getView() {
            return view;
        }
    }

}
