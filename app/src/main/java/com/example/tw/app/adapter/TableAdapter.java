package com.example.tw.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tw.app.R;
import com.example.tw.app.bean.Table;

/**
 * Created by tanwei on 2016/8/17.
 */
public class TableAdapter extends ArrayAdapter<Table> {
    private LayoutInflater inflater;
    private Context context;
    private int maxCount = Integer.MAX_VALUE;

    public TableAdapter(Context context) {
        super(context, R.layout.tab_item_layout);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public TableAdapter(Context context, int maxCount) {
        this(context);
        this.maxCount = maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public int getCount() {
        return Math.min(super.getCount(), maxCount);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        boolean isNull = convertView == null;
        if (isNull) {
            convertView = inflater.inflate(R.layout.tab_item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Table table = getItem(position);
        holder.tabName.setText(table.getName());
        holder.tabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(table);
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        private View view;
        public TextView tabName;
        public TextView tabDelete;

        public ViewHolder(View view) {
            this.view = view;
            this.tabName = (TextView) view.findViewById(R.id.item_tab_name);
            this.tabDelete = (TextView) view.findViewById(R.id.item_tab_delete);
            this.tabDelete.setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), "iconfont.ttf"));
        }

        public View getView() {
            return view;
        }
    }

}
