package org.atguigu.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

        radixSort(arr);

    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //根据前面的推导过程，最终可以得到的基数排序代码

        //先得到数组中最大的数的位数
        int max = arr[0];//假设第一数就是最大数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();

        System.out.println(maxLength);

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小为arr.length
        //3.明确基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组，来记录各个桶的每次放入的数据个数
        //理解
        //比如：bucketElementConuts[0]，记录的就是 bucket[0] 桶的放入的数据个数
        int[] bucketElementConuts = new int[10];


        //使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //第一轮排序(针对每个元素的个位进行排序处理),第一次是个位，第二次是十位，第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位数
                int digitOfElement = arr[j] / n % 10;
                //放到对应的桶
                bucket[digitOfElement][bucketElementConuts[digitOfElement]] = arr[j];
                bucketElementConuts[digitOfElement]++;
            }
            //按照桶的顺序，放入原来的数组中
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到元素里
            for (int k = 0; k < bucketElementConuts.length; k++) {
                //如果桶中有数据，才放入到原数组中
                if (bucketElementConuts[k] != 0) {
                    //有数据
                    //循环该桶，第k个桶(即第k个一维数组)，放入
                    for (int l = 0; l < bucketElementConuts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementConuts[k] =0
                bucketElementConuts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮，的排序处理" + Arrays.toString(arr));

        }

//        //第一轮排序(针对每个元素的个位进行排序处理)
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的个位数
//            int digitOfElement = arr[i] % 10;
//            //放到对应的桶
//            bucket[digitOfElement][bucketElementConuts[digitOfElement]] = arr[i];
//            bucketElementConuts[digitOfElement]++;
//        }
//        //按照桶的顺序，放入原来的数组中
//        int index = 0;
//        //遍历每一个桶，并将桶中的数据放入到元素里
//        for (int k = 0; k < bucketElementConuts.length; k++) {
//            //如果桶中有数据，才放入到原数组中
//            if (bucketElementConuts[k] != 0) {
//                //有数据
//                //循环该桶，第k个桶(即第k个一维数组)，放入
//                for (int l = 0; l < bucketElementConuts[k]; l++) {
//                    //取出元素放入到arr中
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            //第一轮处理后，需要将每个bucketElementConuts[k] =0
//            bucketElementConuts[k] = 0;
//        }
//        System.out.println("第一轮，对个位的排序处理" + Arrays.toString(arr));
//
//        //=======================================================
//
//        //第2轮排序(针对每个元素的十位进行排序处理)
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的十位数
//            int digitOfElement = arr[i] / 10 % 10;
//            //放到对应的桶
//            bucket[digitOfElement][bucketElementConuts[digitOfElement]] = arr[i];
//            bucketElementConuts[digitOfElement]++;
//        }
//        //按照桶的顺序，放入原来的数组中
//        index = 0;
//        //遍历每一个桶，并将桶中的数据放入到元素里
//        for (int k = 0; k < bucketElementConuts.length; k++) {
//            //如果桶中有数据，才放入到原数组中
//            if (bucketElementConuts[k] != 0) {
//                //有数据
//                //循环该桶，第k个桶(即第k个一维数组)，放入
//                for (int l = 0; l < bucketElementConuts[k]; l++) {
//                    //取出元素放入到arr中
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            //第2轮处理后，需要将每个bucketElementConuts[k] =0
//            bucketElementConuts[k] = 0;
//        }
//        System.out.println("第2轮，对个位的排序处理" + Arrays.toString(arr));
//        //=======================================================
//
//        //第3轮排序(针对每个元素的百位进行排序处理)
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的百位数
//            int digitOfElement = arr[i] / 100 % 10;
//            //放到对应的桶
//            bucket[digitOfElement][bucketElementConuts[digitOfElement]] = arr[i];
//            bucketElementConuts[digitOfElement]++;
//        }
//        //按照桶的顺序，放入原来的数组中
//        index = 0;
//        //遍历每一个桶，并将桶中的数据放入到元素里
//        for (int k = 0; k < bucketElementConuts.length; k++) {
//            //如果桶中有数据，才放入到原数组中
//            if (bucketElementConuts[k] != 0) {
//                //有数据
//                //循环该桶，第k个桶(即第k个一维数组)，放入
//                for (int l = 0; l < bucketElementConuts[k]; l++) {
//                    //取出元素放入到arr中
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            //第2轮处理后，需要将每个bucketElementConuts[k] =0
//            bucketElementConuts[k] = 0;
//        }
//        System.out.println("第3轮，对个位的排序处理" + Arrays.toString(arr));
    }

}
