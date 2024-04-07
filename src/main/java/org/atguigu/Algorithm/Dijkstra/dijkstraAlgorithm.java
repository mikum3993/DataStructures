package org.atguigu.Algorithm.Dijkstra;

import java.util.Arrays;

// 迪杰斯特拉算法最佳应用-最短路径
public class dijkstraAlgorithm {
    private static final int N = 65535;// 表示不可链接

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};/*A*/
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};/*B*/
        matrix[2] = new int[]{7, N, N, N, 8, N, N};/*C*/
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};/*D*/
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};/*E*/
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};/*F*/
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};/*G*/

        // 创建图 Grap
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
    }
}

class VisitedVertex {
    // 记录各个顶点是否访问过 1，已访问:0，未访问，会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点的下标，会自动更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会自动更新，求的最小距离就会存放到dis
    public int[] dis;

    // 构造器

    /**
     * @param length 表示顶点的个数
     * @param index  出发顶点对应的下标,例 G顶点，下标为6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 初始化dis数组
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index
     * @return 如果访问过，返回true   否则falue
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能：更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {


    }
}

// 创建图
class Graph {
    private char[] vertex;// 顶点数组
    private int[][] matrix;// 邻接矩阵

    // 构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 显示图方法
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "<" + Arrays.toString(vertex) +
                ", " + Arrays.toString(matrix) +
                "> ";
    }
}