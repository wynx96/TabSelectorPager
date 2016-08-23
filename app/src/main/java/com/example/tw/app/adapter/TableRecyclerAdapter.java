package com.example.tw.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.tw.app.R;
import com.example.tw.app.bean.Table;

import java.util.List;

/**
 * Created by 18348 on 2016/8/22.
 */
public class TableRecyclerAdapter extends BaseRecyclerAdapter<Table> {

    private AdapterView.OnItemClickListener onItemClickListener;
    private int maxCount = Integer.MAX_VALUE;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public int getItemCount() {
        return Math.min(super.getItemCount(), maxCount);
    }

    public TableRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.tab_item_layout, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TableViewHolder viewHolder = (TableViewHolder) holder;
        viewHolder.tabName.setText(get(holder.getLayoutPosition()).getName());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {

        public TextView tabName;
        public TextView tabDelete;

        public TableViewHolder(View view) {
            super(view);
            this.tabName = (TextView) view.findViewById(R.id.item_tab_name);
            this.tabDelete = (TextView) view.findViewById(R.id.item_tab_delete);
            this.tabDelete.setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), "iconfont.ttf"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(null, v, getLayoutPosition(), getLayoutPosition());
                    }
                }
            });
        }
    }
}
