package org.atguigu.sort;

import java.util.Arrays;

public class quickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 6518};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int temp = 0;//临时变量,作为交换时使用
        int l = left;//左下标
        int r = right;//右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        //while循环的目的  是让比pivot 值小的 放到左边，
        //比pivot 值大的放到右边
        while (l < r) {
            //在pivot 的左边一直找，找到一个大于等于pivot的值，再推出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot 的右边一直找，找到一个小于等于pivot的值，再退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果 l > r 成立 说明pivot 的左右两边的值，已经按照左边
            // 全部是小于等于pivot的值
            //右边全部都是大于等于pivot的值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换后，发现这个arr[l] == pivot 值相等--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换后，发现这个arr[r] == pivot 值相等++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        //如果 l=r , 必须l++,r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (l < right) {
            quickSort(arr, l, right);
        }

    }
}
