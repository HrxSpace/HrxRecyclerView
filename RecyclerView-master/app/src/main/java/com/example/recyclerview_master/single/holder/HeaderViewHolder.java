package com.example.recyclerview_master.single.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerview_master.R;
import com.kaka.recyclerviewlib.base.BaseViewHolder;
import com.kaka.recyclerviewlib.mode.ItemData;

/**
 * Created by hrx on 2017/4/22.
 * 头部的ViewHolder
 */

public class HeaderViewHolder extends BaseViewHolder<ItemData> {

    private TextView title;

    public HeaderViewHolder(ViewGroup parent, View itemView) {
        super(parent, itemView);
    }

    @Override
    public void findViews() {
        title = (TextView) itemView.findViewById(R.id.item_header_title);
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        title.setText(data.itemTitle);
    }

    /**
     * 设置该类的ViewHolder是否可点击
     * @return false--不可点击，true--可点击
     */
    @Override
    public boolean clickAble() {
        return false;
    }
}
