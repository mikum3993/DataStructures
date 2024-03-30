package org.atguigu.sort;



import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//        //为了容量理解，
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 8000);//生成【0，8000000）数
        }
//        System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间="+date1Str);
        //测试冒泡排序封装
        bubbleSort(arr);

        //输出排序后
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间="+date2Str);
        //第二趟排序
//        for (int j = 0; j < arr.length - 1 - 1; j++) {
//            //如果千米那的数比后面的大，则交换
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        System.out.println("第二趟排序后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第三趟排序
//        for (int j = 0; j < arr.length - 1 - 2; j++) {
//            //如果前面的数比后面的大，则交换
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        System.out.println("第三趟排序后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第四趟排序
//        for (int j = 0; j < arr.length - 1 - 3; j++) {
//            //如果前面的数比后面的大，则交换
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        System.out.println("第四趟排序后的数组");
//        System.out.println(Arrays.toString(arr));

    }

    //将前面的冒泡排序，封装成一个方法
    public static void bubbleSort(int[] arr) {
        int temp = 0;//临时变量
        boolean flag = false;//表示变量，表示是否进行过交换
        //第一趟排序：将最大的数排到最后
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果千米那的数比后面的大，则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

}
