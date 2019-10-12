package com.hrx.recyclerview.baseadp;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.hrx.recyclerview.base.BaseDelegate;
import com.hrx.recyclerview.base.BaseViewHolder;
import com.hrx.recyclerview.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by hrx on 2017/4/21.
 * 适配器基类
 */

public class RecyclerAdp<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private List<T> mDataList;//列表数据源

    private BaseDelegate<T> mDelegate;//ViewHolder分发器

    private OnItemClickListener<T> mListener;//Item监听接口

    public RecyclerAdp(List<T> dataList, BaseDelegate<T> delegate) {
        if (dataList == null) {
            dataList = Collections.emptyList();
        }
        this.mDataList = dataList;
        this.mDelegate = delegate;
    }

    /**
     * 转入ViewHolder分发器去生成相应的ViewHolder
     *
     * @param parent   父控件
     * @param viewType viewHolder类型
     * @return 生成的ViewHolder
     */
    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegate.onCreateViewHolder(parent, viewType);
    }

    /**
     * 给ViewHolder绑定数据
     *
     * @param holder   ViewHolder
     * @param position 在数据源中的位置
     */
    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, final int position) {
        if (holder != null) {
            holder.onBindViewHolder(mDataList.get(position));
            if (mListener != null && holder.clickAble()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onClick(v, position, mDataList.get(position));
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return mListener.onLongClick(v, position, mDataList.get(position));
                    }
                });
            }
        }
    }

    /**
     * 返回item的ViewType
     *
     * @param position 列表中的位置
     * @return 返回item的ViewType
     */
    @Override
    public int getItemViewType(int position) {
        return mDelegate.getItemViewType(mDataList.get(position));
    }

    /**
     * 总的数据长度
     */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 设置Item监听接口
     */
    public void setItemClickListener(OnItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;
    }
}
