package org.atguigu.Algorithm.KMP;

//暴力匹配
public class VioLenceMatch {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1="123213123434123 234213 2 3";
        String str2="3 2342";

        int index = violenceMath(str1, str2);
        System.out.println(index);

    }

    //暴力匹配算法实现
    public static int violenceMath(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Lenght = s1.length;
        int s2Lenght = s2.length;

        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2
        while (i < s1.length && j < s2.length) {//保证匹配时，不越界
            if (s1[i] == s2[j]) {//匹配成功
                i++;
                j++;
            } else {//没有匹配成功
                //如果失败(str1[i] !=str2[j])，令i=i-(j-1),j=0
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if (j == s2Lenght) {
            return i - j;
        }else {
            return -1;
        }
    }
}
