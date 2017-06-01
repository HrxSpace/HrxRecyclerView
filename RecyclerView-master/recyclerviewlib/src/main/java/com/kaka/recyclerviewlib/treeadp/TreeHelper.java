package com.kaka.recyclerviewlib.treeadp;

import com.kaka.recyclerviewlib.mode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * mod by hrx on 2017-5-10.
 */
public class TreeHelper {

    /**
     * 传入node  返回排序后的Node
     *
     * @param treeNodes
     * @param defaultExpandLevel
     * @return
     */
    public static List<TreeNode> getSortedNodes(List<TreeNode> treeNodes, int defaultExpandLevel) {
        List<TreeNode> result = new ArrayList<>();
        // 设置Node间父子关系
        List<TreeNode> baseTreeNodes = convetData2Node(treeNodes);
        // 拿到根节点
        List<TreeNode> rootBaseTreeNodes = getRootNodes(baseTreeNodes);
        // 排序以及设置Node间关系
        for (TreeNode baseTreeNode : rootBaseTreeNodes) {
            addNode(result, baseTreeNode, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 过滤出所有可见的Node
     *
     * @param baseTreeNodes
     * @return
     */
    public static List<TreeNode> filterVisibleNode(List<TreeNode> baseTreeNodes) {
        List<TreeNode> result = new ArrayList<>();

        for (TreeNode baseTreeNode : baseTreeNodes) {
            // 如果为跟节点，或者上层目录为展开状态
            if (baseTreeNode.isRoot() || baseTreeNode.isParentExpand()) {
                setNodeIcon(baseTreeNode);
                result.add(baseTreeNode);
            }
        }
        return result;
    }

    /**
     * 设置Node间，父子关系;让每两个节点都比较一次，即可设置其中的关系
     */
    private static List<TreeNode> convetData2Node(List<TreeNode> treeNodes) {
        for (int i = 0; i < treeNodes.size(); i++) {
            TreeNode n = treeNodes.get(i);
            for (int j = i + 1; j < treeNodes.size(); j++) {
                TreeNode m = treeNodes.get(j);
                if (m.getpId() == n.getId()) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getpId()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }
        return treeNodes;
    }

    /**
     * 拿到根节点
     */
    private static List<TreeNode> getRootNodes(List<TreeNode> baseTreeNodes) {
        List<TreeNode> root = new ArrayList<>();
        for (TreeNode baseTreeNode : baseTreeNodes) {
            if (baseTreeNode.isRoot())//父节点为空则为根
                root.add(baseTreeNode);
        }
        return root;
    }

    /**
     * 把一个节点上的所有的内容都挂上去
     */
    private static <T, B> void addNode(List<TreeNode> baseTreeNodes, TreeNode<T, B> baseTreeNode, int defaultExpandLeval, int currentLevel) {
        baseTreeNodes.add(baseTreeNode);

        if (baseTreeNode.isNewAdd && defaultExpandLeval >= currentLevel) {
            baseTreeNode.setExpand(true);
        }
        //叶子界点
        if (baseTreeNode.isLeaf()) {
            return;
        }
        //递归子节点
        for (int i = 0; i < baseTreeNode.getChildren().size(); i++) {
            addNode(baseTreeNodes, baseTreeNode.getChildren().get(i), defaultExpandLeval, currentLevel + 1);
        }
    }

    /**
     * 设置节点的图标
     */
    private static void setNodeIcon(TreeNode baseTreeNode) {
        if (baseTreeNode.getChildren().size() > 0 && baseTreeNode.isExpand()) {
            baseTreeNode.setIcon(baseTreeNode.iconExpand);
        } else if (baseTreeNode.getChildren().size() > 0 && !baseTreeNode.isExpand()) {
            baseTreeNode.setIcon(baseTreeNode.iconNoExpand);
        } else {
            baseTreeNode.setIcon(-1);
        }
    }
}
