package com.example.recyclerview_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclerview_master.model.AnimalBean;
import com.example.recyclerview_master.model.PlantBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrx on 2017/5/9.
 */

public class BaseActivity extends AppCompatActivity {

    protected String[] mAnimalImageUrl = {"http://pic125.nipic.com/file/20170321/19639894_090246571000_2.jpg", "http://pic121.nipic.com/file/20170131/17961491_092412377000_2.jpg",
            "http://pic121.nipic.com/file/20170208/24130317_154536684000_2.jpg", "http://pic122.nipic.com/file/20170214/17961491_143320251000_2.jpg",
            "http://pic122.nipic.com/file/20170213/17961491_082427600000_2.jpg", "http://pic122.nipic.com/file/20170214/17961491_142400147000_2.jpg"};
    protected String[] mAnimalImageName = {"老虎", "狮子", "狼", "兔子", "狐狸", "企鹅"};

    protected String[] mPlantImageUrl = {"http://pic126.nipic.com/file/20170413/12398452_082941666000_2.jpg", "http://pic127.nipic.com/file/20170414/21064908_165820530035_2.jpg",
            "http://pic126.nipic.com/file/20170412/24465837_145437883718_2.jpg", "http://pic112.nipic.com/file/20161018/24120469_203647377000_2.jpg"};
    protected String[] mPlantImageName = {"玫瑰花", "牡丹花", "杜鹃花", "栀子花"};

    protected List<AnimalBean> mAnimalList;
    protected List<PlantBean> mPlantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
