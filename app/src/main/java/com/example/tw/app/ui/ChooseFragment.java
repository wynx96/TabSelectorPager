package com.example.tw.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tw.app.adapter.ChooseGridAdapter;
import com.example.tw.app.R;
import com.example.tw.app.adapter.TableAdapter;
import com.example.tw.app.bean.Table;
import com.example.tw.app.util.TableUtils;

import java.util.ArrayList;


public class ChooseFragment extends Fragment {
    public static final String KEY_CHOOSE_LIST = "chooseList";
    public static final String KEY_CHOOSE_TABLE = "chooseTable";

    private GridView choose_grid;
    private TextView table_name;
    private ChooseGridAdapter chooseGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose, container, false);
        choose_grid = (GridView) view.findViewById(R.id.choose_grivdView);
        table_name = (TextView) view.findViewById(R.id.choose_table_name);
        iniData();
        return view;
    }

    private void iniData() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        ArrayList<String> chooseList = bundle.getStringArrayList(KEY_CHOOSE_LIST);
        table_name.setText(bundle.getString(KEY_CHOOSE_TABLE) + ":");
        chooseGridAdapter = new ChooseGridAdapter(getActivity());
        chooseGridAdapter.addAll(TableUtils.getTables(chooseList));
        choose_grid.setAdapter(chooseGridAdapter);
        choose_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TableAdapter tableAdapter = ((MainActivity) getActivity()).getTableAdapter();
                if (tableAdapter != null && tableAdapter.getCount() < tableAdapter.getMaxCount()) {
                    Table table = chooseGridAdapter.getItem(position);
                    tableAdapter.add(table);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
