package com.example.recyclerview_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.recyclerview_master.model.AnimalBean;
import com.example.recyclerview_master.model.PlantBean;
import com.example.recyclerview_master.recyclerview.HolderDelegate;
import com.kaka.recyclerviewlib.base.RecyclerAdp;
import com.kaka.recyclerviewlib.listener.OnItemClickListener;
import com.kaka.recyclerviewlib.mode.ItemData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerAdp<ItemData> recyclerAdp;
    private List<ItemData> datas;
    private List<AnimalBean> mAnimalList;
    private List<PlantBean> mPlantList;

    private String[] mAnimalImageUrl = {"http://pic125.nipic.com/file/20170321/19639894_090246571000_2.jpg", "http://pic121.nipic.com/file/20170131/17961491_092412377000_2.jpg",
            "http://pic121.nipic.com/file/20170208/24130317_154536684000_2.jpg", "http://pic122.nipic.com/file/20170214/17961491_143320251000_2.jpg",
            "http://pic122.nipic.com/file/20170213/17961491_082427600000_2.jpg", "http://pic122.nipic.com/file/20170214/17961491_142400147000_2.jpg"};
    private String[] mAnimalImageName = {"老虎", "狮子", "狼", "兔子", "狐狸", "企鹅"};

    private String[] mPlantImageUrl = {"http://pic126.nipic.com/file/20170413/12398452_082941666000_2.jpg", "http://pic127.nipic.com/file/20170414/21064908_165820530035_2.jpg",
            "http://pic126.nipic.com/file/20170412/24465837_145437883718_2.jpg", "http://pic85.nipic.com/file/20151112/5179140_162134442000_2.jpg"};
    private String[] mPlantImageName = {"玫瑰花", "牡丹花", "杜鹃花", "栀子花"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }

    private void init() {
        //模仿服务器给了这两组集合
        mAnimalList = new ArrayList<>();
        for (int i = 0; i < mAnimalImageUrl.length; i++) {
            AnimalBean ib = new AnimalBean(mAnimalImageUrl[i], mAnimalImageName[i]);
            mAnimalList.add(ib);
        }
        mPlantList = new ArrayList<>();
        for (int i = 0; i < mPlantImageUrl.length; i++) {
            PlantBean ib = new PlantBean(mPlantImageUrl[i], mPlantImageName[i]);
            mPlantList.add(ib);
        }
        //整理数据
        datas = new ArrayList<>();
        datas.add(new ItemData(HolderDelegate.HEADER_TYPE, "动物"));
        for (int i = 0; i < mAnimalList.size(); i++) {
            datas.add(new ItemData(HolderDelegate.ANIMAL_TYPE, mAnimalList.get(i)));
        }
        datas.add(new ItemData(HolderDelegate.HEADER_TYPE, "植物"));
        for (int i = 0; i < mPlantList.size(); i++) {
            datas.add(new ItemData(HolderDelegate.PLANT_TYPE, mPlantList.get(i)));
        }
    }

    private void initView() {
        recyclerAdp = new RecyclerAdp<>(datas, new HolderDelegate());
        recyclerAdp.setItemClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
