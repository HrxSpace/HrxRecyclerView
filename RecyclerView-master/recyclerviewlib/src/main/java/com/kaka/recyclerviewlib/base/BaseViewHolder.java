package com.kaka.recyclerviewlib.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hrx on 2017/4/19.
 * ViewHolder基类
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected ViewGroup mParent;

    public BaseViewHolder(ViewGroup parent, View itemView) {
        super(itemView);
        this.mParent = parent;
        findViews();
    }

    /**
     * 对item中的view实例化
     */
    public abstract void findViews();

    /**
     * 绑定ViewHolder数据
     *
     * @param data 该Item的数据
     */
    public abstract void onBindViewHolder(T data);

    /**
     * 该Item是否可点击
     *
     * @return true--是，false--否
     */
    public boolean clickAble() {
        return true;
    }

}
