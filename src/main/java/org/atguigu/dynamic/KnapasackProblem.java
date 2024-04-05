package org.atguigu.dynamic;

public class KnapasackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};   //物品的价值   val[i] =v[i]
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组，
        //v[i][j]   表示在前 i 个物品能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，定一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为零
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//第一行设置为0
        }

        //根据公式来动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行(默认为0)
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                //公式 w[i]>j 公式中的以第一个开始
                if (w[i - 1] > j) {     //
                    v[i][j] = v[i - 1][j];
                } else { //j>w[i-1]
                    //说明，
                    //i 以1开始的，因此公式要调整成
                    //v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);

                    //为了记录 商品存放到背包的情况，不能直接的使用上面的公式，需要使用if-else替代
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的记录记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出v 
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + ", ");
            }
            System.out.println();
        }

        //输出最后的放入的那些商品
        //遍历path， 只需要最后的放入情况
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d个商品放入到背包, \n", i);
//
//                }
//            }
//        }

        //
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {//从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包, \n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
