package org.atguigu.Algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @program: DataStructures
 * @description: 骑士周游回溯算法代码实现
 * @author: {}
 * @create: 2024/4/7 16:09
 */
public class HorseChessBoard {
    private static int X;// 棋盘的列数
    private static int Y;// 棋盘的行数

    // 创建数组,标记棋盘的各个位置是否被访问过
    private static boolean visited[];

    // 使用一个属性，标记棋盘的所有位置是否都被访问过
    private static boolean finished;// 如果是true,表成功

    public static void main(String[] args) {
        System.out.println("骑士周游算法");
        // 测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1;  // 初始位置的行
        int column = 1;    // 初始列
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];// 初始值为false
        // 测试耗时
        long start = System.currentTimeMillis();

        traversalChessBoard(chessboard, row - 1, column - 1, 1);

        long end = System.currentTimeMillis();

        System.out.println("总耗时: " + (end - start) + "ms");

        // 输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 完成骑士周游问题的算法
     * 初始位置，(0,0)
     *
     * @param chessboard 棋盘
     * @param row        马儿当前位置的行
     * @param column     马儿当前位置的列
     * @param step       当前步数
     */
    public static void traversalChessBoard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        // row
        visited[row * X + column] = true;
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        // 对ps进行排序，排序的规则就是对ps所有的Point对象的下一步位置的数目进行非递减排序
        sort(ps);
        // 遍历 ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);// 取出下一个可以走的位置
            // 判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessboard, p.y, p.x, step + 1);
            }
        }
        // 判断是否完成了任务。使用step和应该走的步数比较
        // 如果没有达到数量，则表示没有完成任务,将整个棋盘归零
        // 1.棋盘到目前位置，仍然没有走完
        // 2.棋盘处于一个回溯过程
        if (step < X * Y && !finished) {// 说明还没有完成
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else { // step > X * Y && finished
            finished = true;
        }
    }

    /**
     * 功能：根据当前的位置(Point)，计算马儿还能走哪些位置，并放入到一个集合中(ArrayList),最多8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        // 创建一个集合
        ArrayList<Point> ps = new ArrayList<>();
        // 创建一个Point
        Point p1 = new Point();
        // 表示马儿可以走5 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走6 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    // 根据当前这一步的所有下一步的位置选择，进行非递减排序,减少回溯的次数
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
