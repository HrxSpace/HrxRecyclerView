package com.example.recyclerview_master.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_master.R;
import com.example.recyclerview_master.model.AnimalBean;
import com.example.recyclerview_master.treelevel.TreeDelegate;
import com.kaka.recyclerviewlib.mode.TreeNode;
import com.kaka.recyclerviewlib.treeadp.TreeRecyclerAdapter;
import com.kaka.recyclerviewlib.listener.OnTreeItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrx on 2017/5/9.
 * 多级RecyclerView实现
 */

public class TreeRecyclerView extends BaseActivity implements OnTreeItemClickListener {

    private RecyclerView mRecyclerView;
    private TreeRecyclerAdapter recyclerAdp;//多级RecyclerView适配器
    private List<TreeNode> datas;//数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        init();
        initView();
    }

    private void init() {
        //整理数据
        datas = new ArrayList<>();
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 1, 0, "动物"));
        for (int i = 0; i < mAnimalList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.ANIMAL_TYPE, "1" + i, 1, mAnimalList.get(i).getImageName(), mAnimalList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 2, 0, "植物"));
        for (int i = 0; i < mPlantList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.PLANT_TYPE, "2" + i, 2, mPlantList.get(i).getImageName(), mPlantList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 3, 0, "动物"));
        for (int i = 0; i < mAnimalList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.ANIMAL_TYPE, "3" + i, 3, mAnimalList.get(i).getImageName(), mAnimalList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 4, 0, "植物"));
        for (int i = 0; i < mPlantList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.PLANT_TYPE, "4" + i, 4, mPlantList.get(i).getImageName(), mPlantList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 5, 0, "动物"));
        for (int i = 0; i < mAnimalList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.ANIMAL_TYPE, "5" + i, 5, mAnimalList.get(i).getImageName(), mAnimalList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 6, 0, "植物"));
        for (int i = 0; i < mPlantList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.PLANT_TYPE, "06" + i, 6, mPlantList.get(i).getImageName(), mPlantList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 7, 0, "动物"));
        for (int i = 0; i < mAnimalList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.ANIMAL_TYPE, "7" + i, 7, mAnimalList.get(i).getImageName(), mAnimalList.get(i)));
        }
        datas.add(new TreeNode(TreeDelegate.HEADER_TYPE, 8, 0, "植物"));
        for (int i = 0; i < mPlantList.size(); i++) {
            datas.add(new TreeNode(TreeDelegate.PLANT_TYPE, "8" + i, 8, mPlantList.get(i).getImageName(), mPlantList.get(i)));
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.n_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerAdp = new TreeRecyclerAdapter(mRecyclerView,datas, 0, R.mipmap.tree_ex, R.mipmap.tree_ec, new TreeDelegate());
//        recyclerAdp.setHasStableIds(true);
        recyclerAdp.setItemClickListener(this);
        mRecyclerView.setAdapter(recyclerAdp);
        recyclerAdp.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v, int itemPos, TreeNode data) {
        Toast.makeText(TreeRecyclerView.this, data.getName(), Toast.LENGTH_SHORT).show();
        if (data.bean instanceof AnimalBean) {
            AnimalBean animalBean = (AnimalBean) data.bean;
            animalBean.setImageUrl("http://pic126.nipic.com/file/20170413/12398452_082941666000_2.jpg");
            recyclerAdp.notifyItemChanged(itemPos);
        }
    }

    @Override
    public boolean onLongClick(View v, int itemPos, TreeNode data) {
        return false;
    }
}
