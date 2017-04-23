package com.kaka.recyclerviewlib.mode;

/**
 * Created by hrx on 2017/4/21.
 * 默认Item数据封装类，可另外自定义
 */
public class ItemData {

    public int holderType;//viewHolder类型

    public String itemTitle;//标题

    public Object data;//数据

    public ItemData(int holderType) {
        this.holderType = holderType;
    }

    public ItemData(int holderType, String itemTitle) {
        this.holderType = holderType;
        this.itemTitle = itemTitle;
    }

    public ItemData(int holderType, Object data) {
        this.holderType = holderType;
        this.data = data;
    }
}
