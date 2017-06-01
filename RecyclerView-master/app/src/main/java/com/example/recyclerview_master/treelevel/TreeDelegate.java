package com.example.recyclerview_master.treelevel;

import android.view.ViewGroup;

import com.example.recyclerview_master.R;
import com.example.recyclerview_master.treelevel.holder.TreeAnimalViewHolder;
import com.example.recyclerview_master.treelevel.holder.TreeHeaderViewHolder;
import com.example.recyclerview_master.treelevel.holder.TreePlantViewHolder;
import com.kaka.recyclerviewlib.base.BaseDelegate;
import com.kaka.recyclerviewlib.base.BaseViewHolder;
import com.kaka.recyclerviewlib.mode.TreeNode;

/**
 * Created by hrx on 2017/4/22.
 * 根据需要定制的Holder分发器
 */

public class TreeDelegate extends BaseDelegate<TreeNode> {

    public static final int HEADER_TYPE = 0;//头部
    public static final int ANIMAL_TYPE = 1;//动物类
    public static final int PLANT_TYPE = 2;//植物类

    @Override
    public BaseViewHolder<TreeNode> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return new TreeHeaderViewHolder(parent, getItemView(parent, viewType));
            case ANIMAL_TYPE:
                return new TreeAnimalViewHolder(parent, getItemView(parent, viewType));
            case PLANT_TYPE:
                return new TreePlantViewHolder(parent, getItemView(parent, viewType));
        }
        return null;
    }

    /**
     * 获取类型
     *
     * @param data 该Item的数据
     * @return 该Item的类型
     */
    @Override
    public int getItemViewType(TreeNode data) {
        return data.holderType;
    }

    /**
     * 获取相应类型的布局
     *
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
