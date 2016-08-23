package com.example.tw.app.util;

import com.example.tw.app.bean.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18348 on 2016/8/18.
 */
public class TableUtils {
    public static List<Table> getTables(List<String> tables) {
        List<Table> tableList = new ArrayList<>();
        for (String s : tables) {
            tableList.add(new Table(s));
        }
        return tableList;
    }
}
