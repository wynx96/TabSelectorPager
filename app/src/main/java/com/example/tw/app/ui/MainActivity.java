package com.example.tw.app.ui;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tw.app.R;
import com.example.tw.app.adapter.MyPagerAdapter;
import com.example.tw.app.adapter.TableAdapter;
import com.example.tw.app.bean.Table;
import com.example.tw.app.util.TableUtils;
import com.example.tw.app.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final int MAX_TAB_COUNT = 8;

    private GridView mGridView;
    private ViewPager mViewPager;
    private List<Fragment> grid_list;
    private EditText input_table;
    private Button add_table;
    private ViewPagerIndicator viewPagerIndicator;
    private TableAdapter tableAdapter;
    private TextView count_num;
    private MyPagerAdapter myPagerAdapter;
    private TextView tabHint;


    public TableAdapter getTableAdapter() {
        return tableAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.show_table_grid);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        input_table = (EditText) findViewById(R.id.input_table);
        add_table = (Button) findViewById(R.id.add_table);
        count_num = (TextView) findViewById(R.id.count_num);
        viewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.viewPager_Indicator);
        tabHint = (TextView) findViewById(R.id.tab_hint);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Table table = tableAdapter.getItem(position);
                tableAdapter.remove(table);
            }
        });
        initData();

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(800);
        LayoutAnimationController layoutAnimationController = new GridLayoutAnimationController(alphaAnimation);
        layoutAnimationController.setInterpolator(new AccelerateInterpolator());
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimationController.setDelay(30);
        mGridView.setLayoutAnimation(layoutAnimationController);
    }

    private DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
//            count_num.setText(new StringBuilder("(").append(tableAdapter.getCount())
//                    .append("/").append(tableAdapter.getMaxCount()).append(")"));
            count_num.setText(String.format("(%d/%d)", tableAdapter.getCount(), tableAdapter.getMaxCount()));
        }
    };

    private void initData() {

        grid_list = new ArrayList<>();

        tableAdapter = new TableAdapter(this, MAX_TAB_COUNT);
        tableAdapter.registerDataSetObserver(dataSetObserver);
        mGridView.setAdapter(tableAdapter);
        //设置提示
        tabHint.setText(String.format(getString(R.string.tab_hint), tableAdapter.getMaxCount()));

        //加载默认数据
        String[] default_tables = getResources().getStringArray(R.array.default_table);
        List<Table> tables = TableUtils.getTables(Arrays.asList(default_tables));
        tableAdapter.addAll(tables);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ChooseFragment.KEY_CHOOSE_LIST, new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.character))));
        bundle.putString(ChooseFragment.KEY_CHOOSE_TABLE, "个性");
        ChooseFragment chooseFragment = new ChooseFragment();
        chooseFragment.setArguments(bundle);

        bundle = new Bundle();
        bundle.putStringArrayList(ChooseFragment.KEY_CHOOSE_LIST, new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.interest))));
        bundle.putString(ChooseFragment.KEY_CHOOSE_TABLE, "爱好");
        ChooseFragment chooseFragment2 = new ChooseFragment();
        chooseFragment2.setArguments(bundle);


        bundle = new Bundle();
        bundle.putStringArrayList(ChooseFragment.KEY_CHOOSE_LIST, new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.character))));
        bundle.putString(ChooseFragment.KEY_CHOOSE_TABLE, "个性");
        ChooseFragment chooseFragment3 = new ChooseFragment();
        chooseFragment3.setArguments(bundle);

        grid_list.add(chooseFragment);
        grid_list.add(chooseFragment2);
        grid_list.add(chooseFragment3);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), grid_list);
        mViewPager.setAdapter(myPagerAdapter);

        viewPagerIndicator.setupViewPager(mViewPager);
        add_table.setOnClickListener(addButtonClickListener);

    }

    private View.OnClickListener addButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tableStr = input_table.getText().toString();
            if (!TextUtils.isEmpty(tableStr)) {
                if (tableAdapter.getCount() >= tableAdapter.getMaxCount()) {
                    Toast.makeText(MainActivity.this, "最多" + tableAdapter.getMaxCount() + "个哦", Toast.LENGTH_SHORT).show();
                } else {
                    String table = String.valueOf(input_table.getText());
                    refreshData(table);
                }
            }
        }
    };

    public void refreshData(String table) {
        tableAdapter.add(new Table(table));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (tableAdapter != null) {
            tableAdapter.unregisterDataSetObserver(dataSetObserver);
        }
        viewPagerIndicator.unset();
    }
}
