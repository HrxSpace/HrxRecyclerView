package com.kaka.recyclerviewlib.treeadp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.kaka.recyclerviewlib.base.BaseDelegate;
import com.kaka.recyclerviewlib.base.BaseViewHolder;
import com.kaka.recyclerviewlib.listener.OnTreeItemClickListener;
import com.kaka.recyclerviewlib.mode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * mod by hrx on 2017-5-10.
 */
public class TreeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private String TAG = "TreeRecyclerAdapter";
    /**
     * 存储所有可见的Node
     */
    protected List<TreeNode> mTreeNodes = new ArrayList<>();

    /**
     * 存储所有的Node
     */
    protected List<TreeNode> mAllTreeNodes = new ArrayList<>();

    /**
     * 0--默认不展开
     */
    private int defaultExpandLevel = 0;
    /**
     * 展开与关闭的图片
     */
    private int iconExpand = -1, iconNoExpand = -1;
    /**
     * ViewHolder分发器
     **/
    private BaseDelegate mDelegate;
    private RecyclerView mRecyclerView;
    private OnTreeItemClickListener mListener;//Item监听接口

    /**
     * 构造器
     *
     * @param datas              数据源
     * @param defaultExpandLevel 默认展开级别
     * @param iconExpand         展开图标
     * @param iconNoExpand       关闭图标
     * @param delegate           ViewHolder分发器
     */
    public TreeRecyclerAdapter(RecyclerView recyclerView, List<TreeNode> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand, BaseDelegate delegate) {
        this.mRecyclerView = recyclerView;
        this.defaultExpandLevel = defaultExpandLevel;
        this.iconExpand = iconExpand;
        this.iconNoExpand = iconNoExpand;
        this.mDelegate = delegate;

        for (TreeNode baseTreeNode : datas) {
            baseTreeNode.getChildren().clear();
            baseTreeNode.iconExpand = iconExpand;
            baseTreeNode.iconNoExpand = iconNoExpand;
        }
        /**
         * 对所有的Node进行排序
         */
        mAllTreeNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        /**
         * 过滤出可见的Node
         */
        mTreeNodes = TreeHelper.filterVisibleNode(mAllTreeNodes);
    }

    /**
     * 构造器
     *
     * @param datas              数据源
     * @param defaultExpandLevel 默认展开级别
     * @param delegate           ViewHolder分发器
     */
    public TreeRecyclerAdapter(RecyclerView recyclerView, List<TreeNode> datas, int defaultExpandLevel, BaseDelegate delegate) {
        this(recyclerView, datas, defaultExpandLevel, -1, -1, delegate);
    }

    /**
     * 转入ViewHolder分发器去生成相应的ViewHolder
     *
     * @param parent   父控件
     * @param viewType viewHolder类型
     * @return 生成的ViewHolder
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegate.onCreateViewHolder(parent, viewType);
    }



    /**
     * 给ViewHolder绑定数据
     *
     * @param holder   ViewHolder
     * @param position 在数据源中的位置
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final int pos = holder.getLayoutPosition();
        final TreeNode treeNode = mTreeNodes.get(position);
        holder.onBindViewHolder(treeNode);
        // 设置内边距
        holder.itemView.setPadding(treeNode.getLevel() * 30, 3, 3, 3);
        if (holder.clickAble()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 设置节点点击时，可以展开以及关闭
                     */
                    expandOrCollapse(pos);
                    /**
                     * 点击项移动到第一行
                     */
                    ScrollItemToFist(pos, treeNode);

                    if (mListener != null) {
                        mListener.onClick(v, pos, treeNode);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    if (mListener != null) {
//                        mListener.onLongClick(v, pos, treeNode);
//                    }
                    return true;
                }
            });
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
        return mDelegate.getItemViewType(mTreeNodes.get(position));
    }

    /**
     * 总的数据长度
     */
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mTreeNodes.size());
        return mTreeNodes.size();
    }

    /**
     * 清除掉之前数据并刷新  重新添加
     *
     * @param mlists
     * @param defaultExpandLevel 默认展开几级列表
     */
    public void addDataAll(List<TreeNode> mlists, int defaultExpandLevel) {
        mAllTreeNodes.clear();
        addData(-1, mlists, defaultExpandLevel);
    }

    /**
     * 在指定位置添加数据并刷新 可指定刷新后显示层级
     *
     * @param index
     * @param mlists
     * @param defaultExpandLevel 默认展开几级列表
     */
    public void addData(int index, List<TreeNode> mlists, int defaultExpandLevel) {
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(index, mlists);
    }

    /**
     * 在指定位置添加数据并刷新
     *
     * @param index
     * @param mlists
     */
    public void addData(int index, List<TreeNode> mlists) {
        notifyData(index, mlists);
    }

    /**
     * 添加数据并刷新
     *
     * @param mlists
     */
    public void addData(List<TreeNode> mlists) {
        addData(mlists, defaultExpandLevel);
    }

    /**
     * 添加数据并刷新 可指定刷新后显示层级
     *
     * @param mlists
     * @param defaultExpandLevel
     */
    public void addData(List<TreeNode> mlists, int defaultExpandLevel) {
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(-1, mlists);
    }

    /**
     * 添加数据并刷新
     *
     * @param baseTreeNode
     */
    public void addData(TreeNode baseTreeNode) {
        addData(baseTreeNode, defaultExpandLevel);
    }

    /**
     * 添加数据并刷新 可指定刷新后显示层级
     *
     * @param baseTreeNode
     * @param defaultExpandLevel
     */
    public void addData(TreeNode baseTreeNode, int defaultExpandLevel) {
        List<TreeNode> baseTreeNodes = new ArrayList<>();
        baseTreeNodes.add(baseTreeNode);
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(-1, baseTreeNodes);
    }

    /**
     * 刷新数据
     *
     * @param index
     * @param mListTreeNodes
     */
    private void notifyData(int index, List<TreeNode> mListTreeNodes) {
        for (int i = 0; i < mListTreeNodes.size(); i++) {
            TreeNode baseTreeNode = mListTreeNodes.get(i);
            baseTreeNode.getChildren().clear();
            baseTreeNode.iconExpand = iconExpand;
            baseTreeNode.iconNoExpand = iconNoExpand;
        }
        for (int i = 0; i < mAllTreeNodes.size(); i++) {
            TreeNode baseTreeNode = mAllTreeNodes.get(i);
            baseTreeNode.getChildren().clear();
            baseTreeNode.isNewAdd = false;
        }
        if (index != -1) {
            mAllTreeNodes.addAll(index, mListTreeNodes);
        } else {
            mAllTreeNodes.addAll(mListTreeNodes);
        }
        //对所有的Node进行排序
        mAllTreeNodes = TreeHelper.getSortedNodes(mAllTreeNodes, defaultExpandLevel);
        //过滤出可见的Node
        mTreeNodes = TreeHelper.filterVisibleNode(mAllTreeNodes);
        //刷新数据
        notifyDataSetChanged();
    }

    /**
     * 获取排序后所有节点
     */
    public List<TreeNode> getAllNodes() {
        if (mAllTreeNodes == null)
            mAllTreeNodes = new ArrayList<>();
        return mAllTreeNodes;
    }

    /**
     * 相应Item的点击事件 展开或关闭某节点
     *
     * @param position 点击的位置
     */
    public void expandOrCollapse(int position) {
        int preSize = mRecyclerView.getLayoutManager().getChildCount();
        TreeNode n = mTreeNodes.get(position);
        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                boolean isExpand = n.isExpand();
                n.setExpand(!isExpand);
                mTreeNodes = TreeHelper.filterVisibleNode(mAllTreeNodes);
                // 刷新视图
                int childSize = n.getChildren().size();
                if (!isExpand) {
                    notifyItemRangeInserted(position + 1, childSize);
                    notifyItemRangeChanged(position + 1, mTreeNodes.size() -1 - position);
                } else {
                    notifyItemRangeRemoved(position + 1, childSize);
                    notifyItemRangeChanged(position + 1, preSize -1 - position);
                }

            }
        }
    }

    /**
     * 响应Item点击事件，将点击的节点移动到第一个Item
     */
    public void ScrollItemToFist(int position,final TreeNode treeNode) {
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (treeNode.isRoot() || treeNode.getParent() != null) {
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                int firstItem = manager.findFirstVisibleItemPosition();
                int lastItem = manager.findLastVisibleItemPosition();
                if (position <= firstItem) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else if (position <= lastItem) {
                    int top = mRecyclerView.getChildAt(position - firstItem).getTop();
                    mRecyclerView.scrollBy(0, top);
                } else {
                    mRecyclerView.smoothScrollToPosition(position);
                }
            }
        }
    }

    /**
     * 设置多选
     *
     * @param baseTreeNode
     * @param checked
     */
    protected void setChecked(final TreeNode baseTreeNode, boolean checked) {
        baseTreeNode.setChecked(checked);
        setChildChecked(baseTreeNode, checked);
        if (baseTreeNode.getParent() != null)
            setNodeParentChecked(baseTreeNode.getParent(), checked);
        notifyDataSetChanged();
    }

    /**
     * 设置是否选中
     *
     * @param baseTreeNode
     * @param checked
     */
    public <T, B> void setChildChecked(TreeNode<T, B> baseTreeNode, boolean checked) {
        if (!baseTreeNode.isLeaf()) {
            baseTreeNode.setChecked(checked);
            for (TreeNode childrenTreeNode : baseTreeNode.getChildren()) {
                setChildChecked(childrenTreeNode, checked);
            }
        } else {
            baseTreeNode.setChecked(checked);
        }
    }

    private void setNodeParentChecked(TreeNode baseTreeNode, boolean checked) {
        if (checked) {
            baseTreeNode.setChecked(checked);
            if (baseTreeNode.getParent() != null)
                setNodeParentChecked(baseTreeNode.getParent(), checked);
        } else {
            List<TreeNode> childrens = baseTreeNode.getChildren();
            boolean isChecked = false;
            for (TreeNode children : childrens) {
                if (children.isChecked()) {
                    isChecked = true;
                }
            }
            //如果所有自节点都没有被选中 父节点也不选中
            if (!isChecked) {
                baseTreeNode.setChecked(checked);
            }
            if (baseTreeNode.getParent() != null)
                setNodeParentChecked(baseTreeNode.getParent(), checked);
        }
    }

    /**
     * 设置Item监听接口
     */
    public void setItemClickListener(OnTreeItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;
    }

}
