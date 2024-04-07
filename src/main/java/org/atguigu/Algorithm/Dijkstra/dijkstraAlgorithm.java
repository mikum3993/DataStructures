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

        // 创建图 Graph
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        // 测试迪杰斯特拉
        System.out.println("--------------测试迪杰斯特拉------------------");
        graph.dsj(matrix.length - 1);
        graph.showDijkstra();

    }
}

// 创建图
class Graph {
    private final char[] vertex;// 顶点数组
    private final int[][] matrix;// 邻接矩阵
    private VisitedVertex vv;// 已经访问的顶点集合

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

    // 显示迪杰斯特拉的结果
    public void showDijkstra() {
        vv.show();
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index) {
        this.vv = new VisitedVertex(vertex.length, index);
        update(index);// 更新index顶点到周围顶点的距离和前驱顶点

        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();// 选择并返回新的访问顶点
            update(index);// 更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    // 更新index下标顶点到周围顶点你的距离和周围顶点的前驱节点,
    private void update(int index) {
        int len = 0;
        // 根据遍历我们的邻接矩阵，matrix[index]
        for (int i = 0; i < matrix[index].length; i++) {
            // len 含义是: 出发顶点到index顶点的距离 + 从index顶点到i顶点的距离 的和
            len = vv.getDis(index) + matrix[index][i];
            // 如果i顶点没有被访问过，并且len 小于出发顶点到i顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);// 更新i顶点的前驱为index顶点
                vv.updateDis(i, len);// 更新出发顶点到i顶点的距离
            }
        }
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
        this.already_arr[index] = 1;// 设置出发顶点被访问过 (1)
        this.dis[index] = 0;// 设置出发顶点的访问距离为0
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index
     * @return 如果访问过，返回true   否则false
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
        dis[index] = len;
    }

    /**
     * 功能:  更新顶点的前驱为index节点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * @param index
     * 功能: 返回出发节点到index的距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    // 继续选择并返回新的访问顶点，比如 G 访问完后，就是 A 作为新的访问顶点(注：不是出发点)
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        // 同时更新index 顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    // 显示最后结果
    // 即 将三个数组的情况输出
    public void show() {

        System.out.println("============================================");
        // 输出already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 输出前驱最后的顶点
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }

        System.out.println();
        // 输出dis[]
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 美化最后的最短距离
        char[] vetex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vetex[count] + "(" + i + "), ");
            } else {
                System.out.println("N");
            }
            count++;
        }
    }
}
