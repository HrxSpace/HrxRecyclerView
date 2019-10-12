package com.example.recyclerview_master.single.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerview_master.R;
import com.example.recyclerview_master.model.AnimalBean;
import com.hrx.recyclerview.base.BaseViewHolder;
import com.hrx.recyclerview.mode.ItemData;

/**
 * Created by hrx on 2017/4/22.
 * 动物的ViewHolder
 */

public class AnimalViewHolder extends BaseViewHolder<ItemData> {

    private ImageView image;
    private TextView name;

    public AnimalViewHolder(ViewGroup parent, View itemView) {
        super(parent, itemView);

    }

    @Override
    public void findViews() {
        image = itemView.findViewById(R.id.item_animal_image);
        name = itemView.findViewById(R.id.item_animal_name);
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        if (data.data instanceof AnimalBean) {
            AnimalBean pb = (AnimalBean) data.data;
            Glide.with(mParent.getContext()).load(pb.getImageUrl()).into(image);
            name.setText(pb.getImageName());
        }
    }

    @Override
    public boolean clickAble() {
        return true;
    }
}
