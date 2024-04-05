package org.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储对应的邻接矩阵
    private int numOfEdges;//表示边的数目

    //定义给数组boolean[] ,记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试 图 是否创建
        int n = 8;
//        String Vertexs[] = {"A", "B", "C", "D", "E"};
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String value : Vertexs) {
            graph.insertVertex(value);
        }
        //添加边
//        graph.insertEdges(0, 1, 1);
//        graph.insertEdges(0, 2, 1);
//        graph.insertEdges(1, 2, 1);
//        graph.insertEdges(1, 3, 1);
//        graph.insertEdges(1, 4, 1);

        //更新边的关系
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);

        //显示邻接矩阵
        graph.showGraph();

        //测试dfs
        System.out.println("深度遍历");
//        graph.dfs();

        System.out.println();
        System.out.println("广度优先!");
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //图 中常用的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到第一个邻接结点的下标  w

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeigh(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        //访问该结点
        System.out.print(getValueByIndex(i)+ "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点 i 的第一个邻接结点 w
        int w = getFirstNeigh(i);
        while (w != -1) {//说明有邻接结点
            //判断是否被访问
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs 进行重载，遍历所有的结点，并进行dfs
    public void dfs() {
        //遍历所有的结点，进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法，
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的一个头结点对应的下标
        int w;//邻接结点 w
        //队列 记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将这个节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标
            w = getFirstNeigh(u);
            while (w != -1) {//找到
                //是否访问过
                if (!isVisited[w]) {
                    //若节点w尚未被访问，则访问结点w 并标记为已访问
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u 为起始点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w);//体现出广度优先
            }
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //根据前一个临界节点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点[i](下标)对应的值(数据) 0->'A'
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     点的下标即是第几个顶点
     * @param v2     第二个顶点对应的下标
     * @param weight
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
        isVisited = new boolean[vertexList.size()];
    }
}
