package com.hrx.recyclerview.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hrx on 2017/4/22.
 * ViewHolder分发器
 */

public abstract class BaseDelegate<T> {

    /**
     * 根据viewType分发生成对应的ViewHolder
     *
     * @param parent   父控件
     * @param viewType viewHolder类型
     * @return 生成的ViewHolder
     */
    public abstract BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 根据数据获取ViewHolder的类型
     *
     * @param data 该Item的数据
     * @return ViewHolder的类型
     */
    public abstract int getItemViewType(T data);

    /**
     * 根据ViewHolder的类型获取布局Id
     *
     * @param viewType ViewHolder的类型
     * @return 布局Id
     */
    public abstract int getLayoutId(int viewType);

    /**
     * 根据ViewHolder的类型获取布局
     *
     * @param parent   父控件
     * @param viewType ViewHolder的类型
     * @return 布局
     */
    protected View getItemView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
    }
}
