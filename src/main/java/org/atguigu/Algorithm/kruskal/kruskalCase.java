package org.atguigu.Algorithm.kruskal;

import java.util.Arrays;

//克鲁斯卡尔算法
public class kruskalCase {

    private int edgeNum;    //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0},
        };

        //创建克鲁苏卡尔Case
        kruskalCase kruskalCase = new kruskalCase(vertexs, matrix);
        //输出构建的图
        kruskalCase.print();
        kruskalCase.kruskal();

//        EData[] edges = kruskalCase.getEdges();
//        System.out.println("未排序" + Arrays.toString(edges));//未排序
//        kruskalCase.sortEdges(edges);
//        System.out.println("排序后" + Arrays.toString(edges));//排序后

    }

    //构造器
    public kruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        // 初始化顶点 vertexs
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边,使用赋值拷贝的方式
        this.matrix = new int[vlen][vlen];

        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //克鲁苏卡尔算法
    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存“已有生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组,保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合, 一共有12条边
        EData[] edges = getEdges();
        System.out.println("获取图的边的集合" + Arrays.toString(edges) + "共 " + edges.length + " 条边");//12

        //按照边的权值大小进行排序(从小到大)
        sortEdges(edges);

        //遍历edges 数组，将边添加到最小生成树中时，判断是准备加入的边是否形成了回路，如果没有，就加入rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i 条边的第2个顶点(起点)
            int p1 = getPosition(edges[i].start);
            //获取到第i 条边的第2个顶点(终点)
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点，在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2这个顶点，在已有的最小生成树中的终点
            int n = getEnd(ends, p2);
            //p1\p2 判断是否构成回路
            if (m != n) {//不构成回路
                ends[m] = n;//设置m 在“已有生成树”中的终点
                rets[index++] = edges[i];//有一条边加入到rets数组
            }
        }

        //统计并打印""最小生成树，输出rets
        System.out.println("最小生成树为 ");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);

        }

    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-10d\t", matrix[i][j]);
            }
            System.out.println();//换行处理
        }
    }

    /**
     * 功能:对边进行排序处理，冒泡排序
     *
     * @param edges
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {//交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 传入的顶点值，例'A','B'
     * @return 返回ch对应的下标，没有则返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {//找到
                return i;
            }
        }
        //找不到
        return -1;
    }

    /**
     * 功能：获取图中的边，放到EData数组中,需要遍历该数组
     * 通过matrix 邻接矩阵来获取
     * EData[] 形式 [['A','B',12],[...]]
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能:  获取下标为i的顶点的终点
     *
     * @param ends 数组就是记录了各个顶点对应的终点是哪个,ends数组是在遍历过程中，逐步形成
     * @param i    表示传入的顶点对应的下标
     * @return 返回的是下表为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

//创建一个类 EData,他的对象实例就表示一条边
class EData {
    char start;//边的起点
    char end;//边的终点
    int weight;

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                "> = " + weight +
                '}';
    }
}