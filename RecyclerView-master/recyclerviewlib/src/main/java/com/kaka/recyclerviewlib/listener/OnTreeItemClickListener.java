package com.kaka.recyclerviewlib.listener;

import android.view.View;

import com.kaka.recyclerviewlib.mode.TreeNode;

/**
 * Created by hrx on 2017/4/22.
 * Item点击监听接口
 */

public interface OnTreeItemClickListener {
    /**
     * 点击事件
     * @param v ItemView
     * @param itemPos 列表中的位置
     * @param data 该Item的数据
     */
    void onClick(View v, int itemPos, TreeNode data);

    /**
     * 长按点击事件
     * @param v ItemView
     * @param itemPos 列表中的位置
     * @param data 该Item的数据
     * @return true-过滤单击
     */
    boolean onLongClick(View v, int itemPos, TreeNode data);
}
