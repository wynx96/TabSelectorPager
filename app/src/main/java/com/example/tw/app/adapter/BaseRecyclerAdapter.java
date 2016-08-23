package com.example.tw.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 18348 on 2016/8/22.
 */
public abstract class BaseRecyclerAdapter<T> extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> implements List<T> {
    private List<T> mList;
    private Context context;
    private LayoutInflater layoutInflater;

    public BaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.mList = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

        if (this.mList == null) {
            this.mList = new ArrayList<>();
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public Context getContext() {
        return context;
    }


    public void add(int location, T object) {
        mList.add(location, object);
    }

    public boolean add(T object) {
        return mList.add(object);
    }

    public boolean addAll(int location, Collection<? extends T> collection) {
        return mList.addAll(location, collection);
    }

    public boolean addAll(Collection<? extends T> collection) {
        return mList.addAll(collection);
    }

    public void clear() {
        mList.clear();
    }

    public boolean contains(Object object) {
        return mList.contains(object);
    }

    public boolean containsAll(Collection<?> collection) {
        return mList.containsAll(collection);
    }

    @Override
    public boolean equals(Object object) {
        return mList.equals(object);
    }

    public T get(int location) {
        return mList.get(location);
    }

    @Override
    public int hashCode() {
        return mList.hashCode();
    }

    public int indexOf(Object object) {
        return mList.indexOf(object);
    }

    public boolean isEmpty() {
        return mList.isEmpty();
    }

    public Iterator<T> iterator() {
        return mList.iterator();
    }

    public int lastIndexOf(Object object) {
        return mList.lastIndexOf(object);
    }

    public ListIterator<T> listIterator() {
        return mList.listIterator();
    }

    public ListIterator<T> listIterator(int location) {
        return mList.listIterator(location);
    }

    public T remove(int location) {
        return mList.remove(location);
    }

    public boolean remove(Object object) {
        return mList.remove(object);
    }

    public boolean removeAll(Collection<?> collection) {
        return mList.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return mList.retainAll(collection);
    }

    public T set(int location, T object) {
        return mList.set(location, object);
    }

    public int size() {
        return mList.size();
    }

    public List<T> subList(int start, int end) {
        return mList.subList(start, end);
    }

    public Object[] toArray() {
        return mList.toArray();
    }

    public <T1> T1[] toArray(T1[] array) {
        return mList.toArray(array);
    }
}
