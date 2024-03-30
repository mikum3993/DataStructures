package org.atguigu.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        ShellSort(arr);
        shellSort2(arr);
    }

    //希尔排序时，对有序序列在插入时采用交换法
    //希尔排序
    //逐步推导
//    public static void ShellSort(int[] arr) {
//        int temp = 0;
//        //希尔排序: 第一轮排序
//        //第一轮是将10个数，分成5组
//        for (int i = 5; i < arr.length; i++) {
//            //遍历各组中所有的元素(共有5组,每组有2个元素),步长 5
//            for (int j = i - 5; j >= 0; j -= 5) {
//                //如果当前元素大于加上步长后的元素，说明交换
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//
//        }
//        System.out.println("第一轮希尔排序" + Arrays.toString(arr));
//
//        //希尔排序: 第二轮排序
//        //第一轮是将10个数，分成5组
//        for (int i = 2; i < arr.length; i++) {
//            //遍历各组中所有的元素(共有5组,每组有2个元素),步长 5
//            for (int j = i - 2; j >= 0; j -= 2) {
//                //如果当前元素大于加上步长后的元素，说明交换
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("第二轮希尔排序" + Arrays.toString(arr));
//
//
//        //希尔排序: 第三轮排序
//        //第一轮是将10个数，分成5组
//        for (int i = 1; i < arr.length; i++) {
//            //遍历各组中所有的元素(共有5组,每组有2个元素),步长 5
//            for (int j = i - 1; j >= 0; j -= 1) {
//                //如果当前元素大于加上步长后的元素，说明交换
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("第三轮希尔排序" + Arrays.toString(arr));
//    }
    //
    public static void ShellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            //希尔排序: 第一轮排序
            //第一轮是将10个数，分成5组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共有5组,每组有2个元素),步长 5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "轮希尔排序:" + Arrays.toString(arr));
        }
    }

    //对交换式的希尔排序进行优化 ->移位法
    public static void shellSort2(int[] arr) {
        int count =0;
        //增量gap,并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
            System.out.println("第" + (++count) + "轮希尔排序:" + Arrays.toString(arr));
        }

    }

}
