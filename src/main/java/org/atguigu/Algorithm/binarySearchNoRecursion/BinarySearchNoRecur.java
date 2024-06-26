package org.atguigu.Algorithm.binarySearchNoRecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 100);
        System.out.println("index = "+index +"数据为"+arr[index]);
    }

    //二分查找的非递归实现

    /**
     * @param arr    待查找的数组 arr是升序排序
     * @param target 需要查找的数
     * @return 返回对应下标，没有则返回-1
     */
    public static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {//可以继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;//需要向左边查找
            } else {
                left = mid + 1;//向右边查找
            }
        }
        return -1;
    }
}
