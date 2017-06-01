package com.example.recyclerview_master.treelevel.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerview_master.R;
import com.example.recyclerview_master.model.AnimalBean;
import com.kaka.recyclerviewlib.base.BaseViewHolder;
import com.kaka.recyclerviewlib.mode.TreeNode;

/**
 * Created by hrx on 2017/4/22.
 * 动物的ViewHolder
 */

public class TreeAnimalViewHolder extends BaseViewHolder<TreeNode> {

    private ImageView image;
    private TextView name;

    public TreeAnimalViewHolder(ViewGroup parent, View itemView) {
        super(parent, itemView);

    }

    @Override
    public void findViews() {
        image = (ImageView) itemView.findViewById(R.id.item_animal_image);
        name = (TextView) itemView.findViewById(R.id.item_animal_name);
    }

    @Override
    public void onBindViewHolder(TreeNode data) {
        if (data.bean instanceof AnimalBean) {
            AnimalBean pb = (AnimalBean) data.bean;
            Glide.with(mParent.getContext()).load(pb.getImageUrl()).into(image);
            name.setText(pb.getImageName());
        }
    }

    @Override
    public boolean clickAble() {
        return true;
    }
}
