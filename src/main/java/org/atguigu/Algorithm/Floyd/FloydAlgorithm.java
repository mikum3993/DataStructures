package org.atguigu.Algorithm.Floyd;

import java.util.Arrays;

/**
 * @program: DataStructures
 * @description: 弗洛伊德算法应用-最短路径
 * @author: ${}
 * @create: 2024/4/7 14:29
 */
public class FloydAlgorithm {
    private static final int N = 65535;// 表示不可链接

    public static void main(String[] args) {
        // 测试图
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};/*A*/
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};/*B*/
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};/*C*/
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};/*D*/
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};/*E*/
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};/*F*/
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};/*G*/

        // 创建Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.show();
    }
}

// 创建图
class Graph {
    private char[] vertex;// 存放顶点的数组
    private int[][] dis;// 保存，从各个顶点出发到其他顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre;//  保存，到达目标顶点的前驱节点

    // 构造器

    /**
     * @param lenght 长度/大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int lenght, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[lenght][lenght];
        // 对pre数组初始化，注意存放的是前驱顶点的下标
        for (int i = 0; i < lenght; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre[]和dis[]
    public void show() {
        // 为了便于阅读    优化输出
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        for (int k = 0; k < dis.length; k++) {
            // 先将pre数组输出
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + ", ");
            }
            System.out.println();
            // System.out.println("输出dis数组");
            // 先将dis数组输出
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径是: " + dis[k][i] + "),");
                // System.out.print("<" + vertex[k] + "→" + vertex[i] + ": " + dis[k][i] + ">, ");
            }
            System.out.println();
            System.out.println();
        }
    }

    // 弗洛伊德算法，比较容易，且容易实现
    public void floyd() {
        int len = 0;// 变量保存距离
        // 从中间顶点的遍历,i 就是中间顶点的下标
        for (int i = 0; i < dis.length; i++) {
            // 从j顶点出发
            for (int j = 0; j < dis.length; j++) {
                for (int k = 0; k < dis.length; k++) {
                    len = (dis[i][k] + dis[k][j]);// =>求出从 j顶点出发 经过 i的中间顶点，到达k顶点的距离
                    if (len < dis[i][j]) {
                        dis[i][j] = len;// 更新距离
                        pre[i][j] = pre[k][j];// 更新前驱顶点
                    }
                }
            }
        }
    }
}