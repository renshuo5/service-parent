package com.renshuo.cloud.util;

import lombok.Data;

/**
 * Created by Lenovo on 2023/12/1.
 */
@Data
public class TreeNode {

    /**
     * 二叉树：每个节点包含自身值，左节点引用，右节点引用
     */
    int val;

    TreeNode leftNode;
    TreeNode rightNode;

    TreeNode(int val){
        this.val = val;
    }
}
