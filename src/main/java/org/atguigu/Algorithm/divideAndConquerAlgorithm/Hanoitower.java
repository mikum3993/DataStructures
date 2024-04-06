package org.atguigu.Algorithm.divideAndConquerAlgorithm;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    //汉罗塔的移动方案（代码）
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {//只有一个盘
            System.out.println("第一个盘从" + a + "->" + c);
        } else {//多个盘
            //如果 n>=2 可以看作是两个盘，
            //1.先把最上面的盘 A->B,移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2.把最下边的盘 A->C
            System.out.println("第"+num +"个盘从"+a+"->"+c);
            //3.把B塔的所有盘 从 B ->C,    移动过程使用到 a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}
