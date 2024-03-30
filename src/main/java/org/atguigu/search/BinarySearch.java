package org.atguigu.search;


import java.util.ArrayList;

//二分查找
//二分查找的前提，该数组是有序的

/**
 * 查找算法介绍
     * 顺序查找
     * 二分查找/折半查找
     * 插值查找
     * 斐波那契查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 13, 423, 423, 423,423, 12321};

//        int resIndex = binarySearch(arr,0,arr.length-1,1);
        ArrayList<Integer> resIndex = binarySearch2(arr, 0, arr.length - 1, 423);
        System.out.println("resIndexList" +resIndex);
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 查找的值
     * @return 没找到是返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当 left > right 时  说明递归整个数组，但没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {    //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    //课后思考题

    /**
     * 一个有序数组中，有多个相同的数值时，如何将所有的数值都查到
     * <p>
     * 思路
     * 1.找到mid 索引值， 不要马上返回
     * 2.向mid 索引值的左边扫描，将所有满足 条件 的元素的下表，加入到集合 ArrayList
     * 3.向 mid 索引值的右边扫面，将所有满足 条件 的元素的下表，加入到集合 ArrayList
     * 将 ArrayList 返回
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        System.out.println("hello");
        //当 left > right 时  说明递归整个数组，但没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {    //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            /**  1.找到mid 索引值， 不要马上返回
             * 2.向mid 索引值的左边扫描，将所有满足 条件 的元素的下表，加入到集合 ArrayList
             * 3.向 mid 索引值的右边扫面，将所有满足 条件 的元素的下表，加入到集合 ArrayList
             * 将 ArrayList 返回
             */
            ArrayList<Integer> resIndexlist = new ArrayList<>();
            //向mid 索引值的左边扫描，将所有满足 条件 的元素的下表，加入到集合 ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {   //退出
                    break;
                }
                //否则，就把temp 放到 集合中
                resIndexlist.add(temp);
                temp -= 1;//temp左移
            }
            resIndexlist.add(mid);
            //向 mid 索引值的右边扫面，将所有满足 条件 的元素的下表，加入到集合 ArrayList
             temp = mid + 1;
            while (true) {
                if (temp >arr.length-1 || arr[temp] != findVal) {   //退出
                    break;
                }
                //否则，就把temp 放到 集合中
                resIndexlist.add(temp);
                temp += 1;//temp右移
            }
            return resIndexlist;
        }
    }

}
