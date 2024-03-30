package org.atguigu.recursion;


import java.util.Map;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二位数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1作为墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板,1表示
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + "  ");
            }
            System.out.println();
        }

        //使用递归回溯 给小球找路
//        setWay(map, 1, 1);
        //使用递归回溯 给小球找路(修改策略)
        setWay2(map, 1, 1);

        //输出新的地图，小球走过并标识过的递归
        //输出地图
//        System.out.println("小球走过的地图情况");
        System.out.println("小球走过的地图情况(修改策略版)");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + "  ");
            }
            System.out.println();
        }

    }

    //使用递归回溯来给小球找路
    //1.MAP 表示地图
    //2.i,j 表示地图开始位置出发(1,1)
    //3.如果小球到达map[6][5]的位置，则说明路找到
    //4.约定:当 map[i][j]为0时，该点没有走过,当为1 表示墙，2.表示道路可以走,3 表示已经走过但是走不通
    //5.走迷宫时，需要确定一个策略(方法)， 下->右->上->左 ，如果该店走不通，在回溯

    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到了，返回true,否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到ok
            return true;
        } else {
            if (map[i][j] == 0) {//如果这个点还没有走过
                //按照策略 下->右->上->左 走
                map[i][j] = 2;//假定该点可以走通。
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j]!=0,map可能是1,2
                return false;
            }
        }
    }

    //修改找路的策略   上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到ok
            return true;
        } else {
            if (map[i][j] == 0) {//如果这个点还没有走过
                //按照策略 下->右->上->左 走
                map[i][j] = 2;//假定该点可以走通。
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j]!=0,map可能是1,2
                return false;
            }
        }
    }
}
