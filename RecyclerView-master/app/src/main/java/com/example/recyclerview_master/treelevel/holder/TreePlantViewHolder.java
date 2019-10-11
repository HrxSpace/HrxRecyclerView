package com.example.recyclerview_master.treelevel.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerview_master.R;
import com.example.recyclerview_master.model.PlantBean;
import com.hrx.recyclerview.base.BaseViewHolder;
import com.hrx.recyclerview.mode.TreeNode;

/**
 * Created by hrx on 2017/4/22.
 * 植物的ViewHolder
 */

public class TreePlantViewHolder extends BaseViewHolder<TreeNode> {

    private ImageView image;
    private TextView name;

    public TreePlantViewHolder(ViewGroup parent, View itemView) {
        super(parent, itemView);

    }

    @Override
    public void findViews() {
        image = (ImageView) itemView.findViewById(R.id.item_plant_image);
        name = (TextView) itemView.findViewById(R.id.item_plant_name);
    }

    @Override
    public void onBindViewHolder(TreeNode data) {
        if (data.bean instanceof PlantBean) {
            PlantBean pb = (PlantBean) data.bean;
            Glide.with(mParent.getContext()).load(pb.getImageUrl()).into(image);
            name.setText(pb.getImageName());
        }
    }

    @Override
    public boolean clickAble() {
        return true;
    }
}
