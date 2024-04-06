package org.atguigu.Algorithm.prim;

import java.util.Arrays;

//普利姆算法
public class primAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示
        int[][] weight = new int[][]{
                //A     B  C  D     E       F     G
                {10000, 5, 7, 10000, 10000, 10000, 2},//A
                {5, 10000, 10000, 9, 10000, 10000, 3},//B
                {7, 10000, 10000, 10000, 8, 10000, 10000},//C
                {7, 10000, 10000, 10000, 10000, 4, 10000},//D
                {10000, 9, 10000, 10000, 10000, 5, 4},//E
                {10000, 10000, 10000, 4, 5, 10000, 6},//F
                {2, 3, 10000, 10000, 4, 6, 10000}//G
        };

        //创建 MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);

        //测试普利姆算法
        minTree.prim(graph, 1);
    }
}

//创建最小生成树->村庄的图
class MinTree {
    //创建图的邻接矩阵

    /**
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {//顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，得到最小生成树

    /**
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {

        //visited[] 标记节点(顶点)是否被访问过
        int visited[] = new int[graph.verxs];
        //visited[] 默认元素值都是0
//        for (int i = 0; i < graph.verxs; i++) {
//            visited[i]=0;
//        }

        //把当前节点标记为已访问
        visited[v] = 1;
        //用h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight 初始成一个大数，后面再遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) {

            //确定每一次生成的子图
            for (int i = 0; i < graph.verxs; i++) {//i  结点表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) { //j 结点表示未被访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的结点和为访问过的几点间的权值最小边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //退出for循环时，找到一条最小边
            System.out.println("边< " + graph.data[h1] + " , " + graph.data[h2] + " >, 权值是：" + minWeight);
            //将当前h1 和h2 标记为已访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值 10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;//表示图的节点个数
    char[] data;//  保存节点数据
    int[][] weight;//存放边,邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }

}