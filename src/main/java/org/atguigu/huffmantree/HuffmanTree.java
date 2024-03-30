package org.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);

        preOrder(huffmanTree);

    }

    //编写前序遍历的方法
    public static void preOrder(Node node) {
        if (node != null) {
            node.preOrder();
        } else {
            System.out.println("空树不能遍历");
        }
    }

    //创建赫夫曼树的方法

    /**
     * @param arr 需要船舰成功的赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root结点
     */
    public static Node createHuffmanTree(int[] arr) {
        //1.为了操作方便，
        //  1.遍历arr 数组
        //  2.将 arr 的每个元素构造成一个Node
        //  3.将 Node 放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            System.out.println(nodes);

            //取出根节点权值最小的二叉树
            //(1).取出权值最小的结点
            Node leftNode = nodes.get(0);
            //(2).取出第二小的结点
            Node rightNode = nodes.get(1);
            //(3).构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //(4).从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5).将parent加入到nodes
            nodes.add(parent);
        }
        //返回最后的结点(赫夫曼树的结点)
//        Collections.sort(nodes);
//        System.out.println("第一次处理后:" + nodes);
        return nodes.get(0);
    }
}

//创建结点类
//为了让 Node 对象 持续排序 Collections 集合排序
//让Node 实现Comparable
class Node implements Comparable<Node> {
    int value; //结点权值
    Node left;  //指向左子节点
    Node right; //指向右子节点

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
