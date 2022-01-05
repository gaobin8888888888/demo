package com.learn.demo.math;

import java.util.*;

/**
 * @author gaobin
 * @date 2021/8/23 9:56 下午
 * @desc
 */
public class MaxNNumMain {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String put1 = map.put("1", "1");
        System.out.println(put1);
        String put = map.put("1", "2");
        System.out.println(put);
    }

    public static void main3(String[] args) {
        int[] array = {6,4,3,2,0,56,32,12};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        findKMax(array, 0);
    }

    private static void findKMax(int[] array, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        map.put(6, 1);
        map.put(1, 1);
        map.put(16, 1);
        System.out.println(map);
    }


    private static int select(int[] array, int num, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (num < array[mid]) {
                right = mid - 1;
            } else if (num > array[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int left = start;
            int right = end;
            int look = array[start];
            while (left < right) {
                while (left < right && array[right] >= look) {
                    right--;
                }
                if (left < right) {
                    array[left++] = array[right];
                }

                while (left < right && array[left] < look) {
                    left++;
                }
                if (left < right) {
                    array[right--] = array[left];
                }
                array[left] = look;
                quickSort(array, start, left - 1);
                quickSort(array, left + 1, end);
            }
        }
    }

}
