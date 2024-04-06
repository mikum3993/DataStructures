package org.atguigu.Algorithm.KMP;

import java.util.Arrays;

//
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABDE";
//        String str2 = "BBC";

//        int[] next = kmpNext("AAAB");
//        int[] next = kmpNext("ABCDABD");
        int[] next = kmpNext(str2);
        System.out.println("next=>" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    //写出kmp搜索算法

    /**
     * @param str1 原字符串
     * @param str2 字串
     * @param next 部分匹配表   字串对应的部分匹配表
     * @return 如果是-1 没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        //遍历 str1
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //需要处理 str1.charAt(i)!=str2.charAt(j),  去调整j的大小
            //KMP算法核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {//找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串(字串)的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个数组，保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串的长度为 1 ,部分匹配值为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当 dest.charAt(i)!=dest.charAt(j) 时，需要从next[j-1]获取新的j
            //直到发现有 dest.charAt(i)==dest.charAt(j) 满足时，退出
            //kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当 dest.charAt(i)==dest.charAt(j) 满足时，部分匹配值就是要+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
