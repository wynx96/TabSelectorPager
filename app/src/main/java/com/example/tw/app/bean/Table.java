package com.example.tw.app.bean;

import java.io.Serializable;

/**
 * Created by 18348 on 2016/8/18.
 */
public class Table implements Serializable{
    private String name;

    public Table(String name) {
        this.name = name;
    }

    public Table() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                '}';
    }
}
