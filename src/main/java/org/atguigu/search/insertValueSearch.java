package org.atguigu.search;

//插值查找算法

import java.util.Arrays;

/**
 * int mid = left+(right-left)*(key-arr[left])/(arr[right]-arr[left])
 *
 * 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
 */
public class insertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Arrays.toString(arr));

        int index = inserValueSearch(arr, 0, arr.length - 1, 0);
        System.out.println(index);
    }

    //编写差值查找
    //说明：插值查找算法，也要求数组是有序的

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int inserValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("hello");

        //注意    findVal < arr[0] || findVal > arr[arr.length - 1]   必须需要的条件
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//说明应该向右递归
            return inserValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return inserValueSearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }

    }
}
