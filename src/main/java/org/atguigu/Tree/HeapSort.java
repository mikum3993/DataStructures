package org.atguigu.Tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序");
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));
//
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));

        //完成最终代码
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        System.out.println("数组" + Arrays.toString(arr));

        /**
         * 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
         * 重新调整结构，使其满足对定义，然后继续交换堆顶元素与末尾元素，反复执行调整+交换步骤，知道整个序列有序
         */
        int count =0;
        for (int j = arr.length - 1; j > 0; j--) {
            //交换堆顶元素与末尾元素
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
            count++;
        System.out.println("第"+count+"次遍历:" + Arrays.toString(arr));
        }
        System.out.println("数组:" + Arrays.toString(arr));
    }
    //将一个数组(二叉树)，调整成一个大顶堆
    /**
     * 功能 完成 将以 i 对应的非叶子结点的树调整成大顶堆
     * 例:    arr = {4, 6, 8, 5, 9}; =>i =1 =>adjustHeap() => 得到{4,9,8,5,6}
     * 如果再次调用adjustHeap 传入的是i =0=>得到{4.9.8.5.6}=>{9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中的索引
     * @param lenght 表示对多少个元素继续进行调整
     */
    public static void adjustHeap(int arr[], int i, int lenght) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            if (k+1<lenght && arr[k] < arr[k + 1]) {//说明 左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];//把较大的值 赋给当前结点
                i = k;//!!!!  让i 指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for 循环结束后，已经将以i 为父节点的树的最大值，放到了最顶(局部)
        arr[i] = temp;//将 temp值放到调整后的位置
    }
}
