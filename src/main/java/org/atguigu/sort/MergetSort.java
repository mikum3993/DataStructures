package org.atguigu.sort;

import java.util.Arrays;

public class MergetSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2,0,234};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);

        System.out.println("归并排序后="+ Arrays.toString(arr));

    }

    //分+合 方法
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间的索引
            //向左递归进行分解
            mergeSort(arr, left, mid - 1, temp);
            //享有递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //到合并，
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println("xxxxx");
        int i = left; //初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始j,右边有序序列的初始索引
        int t = 0;//指向temp 数组的当前索引

        //1.
        //先把左右两边(有序)的数据，按规则拷贝到temp数组
        //直到左右两边数据，有一边处理完毕
        while (i <= mid && j <= right) {//继续
            if (arr[i] <= arr[j]) {
                //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
                //即将左边的当前元素，拷贝到temp数组
                //然后t++，i++
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之，将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;

            }
        }

        //2.
        //有剩余数据的一方的数据，依次全部填充到temp中
        while (i <= mid) {//左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {//左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //3.
        //将temp数组的元素拷贝到arr
        //注意：并不是每次都拷贝所有
        t = 0;
        int tempLeft = left; //tempLeft
        while (tempLeft <= right) {//第一次合并时，tempLeft=0;right=1;
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
