package com.learn.demo.math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author gaobin
 * @date 2021/9/23 4:48 下午
 * @desc
 */
public class TreeMathTest {

    public static void main(String[] args) {

    }

    private static List<List<Integer>> four(TreeNode treeNode) {
        List<List<Integer>> list = new ArrayList<>();
        if (treeNode == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            List<Integer> integerList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                integerList.add(node.val);
            }
            list.add(integerList);
        }
        return list;
    }
}
