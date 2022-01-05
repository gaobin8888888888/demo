package com.learn.demo.math;


import java.util.Arrays;

/**
 * @author gaobin
 * @date 2021/9/17 9:55 下午
 * @desc 快速排序与选择查找
 */
public class QuickAndSelect {

    public static void main(String[] args) {
        int array[] = {3,8,1,2,4,7,3,4,6};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        System.out.println(select(array, 0, array.length - 1, 3));
    }

    public static int select(int[] array, int left, int right, int num) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > num) {
                right = right - 1;
            } else if (array[mid] < num) {
                left = left + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void quickSort(int[] array, int start, int end) {
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
                if(left < right){
                    array[right--] = array[left];
                }

                array[left] = look;
                quickSort(array, start, left - 1);
                quickSort(array, left + 1, end);
            }
        }
    }
}
