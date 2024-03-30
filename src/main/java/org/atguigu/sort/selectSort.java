package org.atguigu.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class selectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
    }

    //选择排序
    public static void selectSort(int[] arr) {
        //使用逐步推导的方式讲解
        //第一轮
        //原始的数组:        101,34,119,1
        //第一轮排序：        1，34，119，101
        //算法--》先简单，再复杂

        //在推到的过程中，发现可以使用for循环来解决
        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的最小值，不是最小的
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }

            //将最小值， 放在arr[0],即交换
            arr[minIndex] = arr[i];
            arr[i] = min;

            System.out.println("第" + i + "轮后");
            System.out.println(Arrays.toString(arr));
        }
//        int minIndex = 0;
//        int min = arr[0];
//        for (int j = 0+1; j < arr.length ; j++) {
//            if (min > arr[j]){//说明假定的最小值，不是最小的
//                min = arr[j];//重置min
//                minIndex=j;//重置minIndex
//            }
//        }
//
//        //将最小值， 放在arr[0],即交换
//        arr[minIndex] = arr[0];
//        arr[0]=min;
//
//        System.out.println("第一轮后");
//
//        //第2轮
//
//        minIndex = 1;
//        min = arr[1];
//        for (int j = 1+1; j < arr.length ; j++) {
//            if (min > arr[j]){//说明假定的最小值，不是最小的
//                min = arr[j];//重置min
//                minIndex=j;//重置minIndex
//            }
//        }
//
//        //将最小值， 放在arr[0],即交换
//        arr[minIndex] = arr[1];
//        arr[1]=min;
//
//        System.out.println("第2轮后");
//        System.out.println(Arrays.toString(arr));
//        //第2轮
//
//        minIndex = 2;
//        min = arr[2];
//        for (int j = 2+1; j < arr.length ; j++) {
//            if (min > arr[j]){//说明假定的最小值，不是最小的
//                min = arr[j];//重置min
//                minIndex=j;//重置minIndex
//            }
//        }
//
//        //将最小值， 放在arr[0],即交换
//        arr[minIndex] = arr[2];
//        arr[2]=min;
//
//        System.out.println("第3轮后");
//        System.out.println(Arrays.toString(arr));

    }
}
