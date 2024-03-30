package org.atguigu.search;

/**
 * 线性查找
 * 可以有序，也可以无序
 * 如果找到了 返回下标值
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, -1, -3, 41, 12};
        int index = seqSearch(arr,41);
        if (index==-1){
            System.out.println("没找到");
        }else {
            System.out.println(index);
        }

    }

    /**
     * 实现的线性查找  是找到一个满足条件的值就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //线性查找是逐一比对，发现相同的值时，返回下表
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
