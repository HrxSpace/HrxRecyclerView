package com.example.recyclerview_master.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_master.R;
import com.example.recyclerview_master.model.AnimalBean;
import com.example.recyclerview_master.single.SingleDelegate;
import com.kaka.recyclerviewlib.baseadp.RecyclerAdp;
import com.kaka.recyclerviewlib.listener.OnItemClickListener;
import com.kaka.recyclerviewlib.mode.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrx on 2017/5/9.
 */

public class SingleRecyclerView extends BaseActivity implements OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerAdp<ItemData> recyclerAdp;
    private List<ItemData> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        init();
        initView();
    }

    private void init() {
        //整理数据
        datas = new ArrayList<>();
        datas.add(new ItemData(SingleDelegate.HEADER_TYPE, "动物"));
        for (int i = 0; i < mAnimalList.size(); i++) {
            datas.add(new ItemData(SingleDelegate.ANIMAL_TYPE, mAnimalList.get(i)));
        }
        datas.add(new ItemData(SingleDelegate.HEADER_TYPE, "植物"));
        for (int i = 0; i < mPlantList.size(); i++) {
            datas.add(new ItemData(SingleDelegate.PLANT_TYPE, mPlantList.get(i)));
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerAdp = new RecyclerAdp(datas, new SingleDelegate());
        recyclerAdp.setItemClickListener(this);
        mRecyclerView.setAdapter(recyclerAdp);
        recyclerAdp.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v, int position, Object data) {
        Log.d("MainActivity", "position: " + position);
        ItemData itemData = (ItemData) data;
        if (itemData.data instanceof AnimalBean) {
            AnimalBean animalBean = (AnimalBean) itemData.data;
            animalBean.setImageUrl("http://pic126.nipic.com/file/20170413/12398452_082941666000_2.jpg");
            recyclerAdp.notifyItemChanged(position);
        }
    }

    @Override
    public boolean onLongClick(View v, int position, Object data) {
        Log.d("MainActivity", "position: " + position);
        ItemData itemData = (ItemData) data;
        if (itemData.data instanceof AnimalBean) {
            AnimalBean animalBean = (AnimalBean) itemData.data;
            mAnimalList.remove(animalBean);
            datas.remove(position);
            Log.d("onLongClick", "mAnimalList---" + mAnimalList.size());
            recyclerAdp.notifyItemRemoved(position);
            recyclerAdp.notifyItemRangeChanged(position, recyclerAdp.getItemCount());
        }
        return true;
    }
}
