package org.atguigu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};

        insertSort(arr);

    }

    //插入排序
    public static void insertSort(int[] arr) {
        //使用逐步推导的方式
        //第一轮
        for (int i = 1; i < arr.length; i++) {

            //定义待插入的数
            int insertValue = arr[i];
            int insertIndex = i - 1;

            //给insertVal 找到插入的位置
            //说明
//        1.insertIndex>= 0 保证给insertVal找插入位置，不越界
//        2.insertValue < arr[insertIndex],待插入的数，还没有找到适当的位置
//        3.就需要，将 arr[insertIndex]往后移动
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//
                insertIndex--;
            }
            //当退出循环后,说明插入的位置找到，insertIndex +1
            arr[insertIndex + 1] = insertValue;
            System.out.println("第"+i+"轮插入");
            System.out.println(Arrays.toString(arr));
        }
    }
}
