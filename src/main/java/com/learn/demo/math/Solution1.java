package com.learn.demo.math;

import java.util.*;

/**
 * @author gaobin
 * @date 2021/8/2 8:58 下午
 * @desc
 */
public class Solution1 {

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode start;
        ListNode end = new ListNode(head.val);
        while (head.next != null) {
            head = head.next;
            start = end;
            end = new ListNode(head.val);
            end.next = start;
        }
        return end;
    }

    public int[] MySort (int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return arr;
        }
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left < right){
            int start = left;
            int end = right;
            int look = arr[start];
            while (start < end) {
                while (start < end && arr[end] >= look) {
                    end--;
                }
                if (start < end) {
                    arr[start++] = arr[end];
                }

                while (start < end && arr[start] < look) {
                    start++;
                }
                if (start < end) {
                    arr[end--] = arr[start];
                }
            }

            arr[start] = look;
            quickSort(arr, left, start - 1);
            quickSort(arr, start + 1, right);
        }
    }

    public int[][] threeOrders (TreeNode root) {
        List<Integer> list1 = new ArrayList<>();
        first(list1, root);
        Integer[] i1 = list1.toArray(new Integer[0]);
        int[] i11 = Arrays.stream(i1).mapToInt(Integer::intValue).toArray();

        List<Integer> list2 = new ArrayList<>();
        second(list2, root);
        Integer[] i2 = list2.toArray(new Integer[0]);
        int[] i22 = Arrays.stream(i2).mapToInt(Integer::intValue).toArray();


        List<Integer> list3 = new ArrayList<>();
        thirdy(list3, root);
        Integer[] i3 = list3.toArray(new Integer[0]);
        int[] i33 = Arrays.stream(i3).mapToInt(Integer::intValue).toArray();
        int[][] i = {i11, i22, i33};
        return i;
    }

    public void first(List<Integer> list, TreeNode root) {
        list.add(root.val);
        if (root.left != null){
            first(list, root.left);
        }
        if (root.right != null){
            first(list, root.right);
        }
    }

    public void second(List<Integer> list, TreeNode root) {
        if (root.left != null){
            second(list, root.left);
        }
        list.add(root.val);
        if (root.right != null){
            second(list, root.right);
        }
    }

    public void thirdy(List<Integer> list, TreeNode root) {
        if (root.left != null){
            thirdy(list, root.left);
        }
        if (root.right != null){
            thirdy(list, root.right);
        }
        list.add(root.val);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k == 0) {
            return new ArrayList<>();
        }
        if (input == null || input.length < k) {
            return new ArrayList<>();
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i = 0;
        while (i < input.length) {
            if (i < k) {
                if (map.containsKey(input[i])) {
                    map.put(input[i], map.get(input[i] + 1));
                } else {
                    map.put(input[i], 1);
                }
            } else {
                Map.Entry<Integer, Integer> entry = map.lastEntry();
                if (entry.getKey() > input[i]) {
                    if (entry.getValue() == 1){
                        map.pollLastEntry();
                        if (map.containsKey(input[i])) {
                            map.put(input[i], map.get(input[i] + 1));
                        } else {
                            map.put(input[i], 1);
                        }
                    } else {
                        map.put(entry.getKey(), entry.getValue() - 1);
                    }
                }
            }
            i++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            for (int n = 0; n < map.get(key); n++){
                list.add(key);
            }
        }
        return list;
    }
//
//    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
//        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        int i = 0;
//        while (i < k) {
//            queue.add(input[i]);
//            i++;
//        }
//
//    }

    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                arrayList.add(node.val);
            }
            list.add(arrayList);
        }
        return list;
    }

    public long maxWater (int[] arr) {
        long total = 0L;
        if (arr == null || arr.length <= 2) {
            return total;
        }
        int left = 0;
        int right = arr.length - 1;
        int min = Math.min(arr[left], arr[right]);
        while (left < right) {
            if (arr[left] < arr[right]) {
                left++;
                if (arr[left] < min) {
                    total = total + min - arr[left];
                } else {
                    min = Math.min(arr[left], arr[right]);
                }
            } else {
                right--;
                if (arr[right] < min) {
                    total = total + min - arr[right];
                } else {
                    min = Math.min(arr[left], arr[right]);
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        String s = "(((()";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses (String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int left = 0, right = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (left < right) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i++){
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }


}
