package com.example.recyclerview_master.single;

import android.view.ViewGroup;

import com.example.recyclerview_master.R;
import com.example.recyclerview_master.single.holder.AnimalViewHolder;
import com.example.recyclerview_master.single.holder.HeaderViewHolder;
import com.example.recyclerview_master.single.holder.PlantViewHolder;
import com.hrx.recyclerview.base.BaseDelegate;
import com.hrx.recyclerview.base.BaseViewHolder;
import com.hrx.recyclerview.mode.ItemData;

/**
 * Created by hrx on 2017/4/22.
 * 根据需要定制的Holder分发器
 */

public class SingleDelegate extends BaseDelegate<ItemData> {

    public static final int HEADER_TYPE = 0;//头部
    public static final int ANIMAL_TYPE = 1;//动物类
    public static final int PLANT_TYPE = 2;//植物类

    @Override
    public BaseViewHolder<ItemData> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return new HeaderViewHolder(parent, getItemView(parent, viewType));
            case ANIMAL_TYPE:
                return new AnimalViewHolder(parent, getItemView(parent, viewType));
            case PLANT_TYPE:
                return new PlantViewHolder(parent, getItemView(parent, viewType));
        }
        return null;
    }

    /**
     * 获取类型
     * @param data 该Item的数据
     * @return 该Item的类型
     */
    @Override
    public int getItemViewType(ItemData data) {
        return data.holderType;
    }

    /**
     * 获取相应类型的布局
     * @param viewType ViewHolder的类型
     * @return 该Item的布局
     */
    @Override
    public int getLayoutId(int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return R.layout.item_header;
            case ANIMAL_TYPE:
                return R.layout.item_animal;
            case PLANT_TYPE:
                return R.layout.item_plant;
        }
        return 0;
    }
}
