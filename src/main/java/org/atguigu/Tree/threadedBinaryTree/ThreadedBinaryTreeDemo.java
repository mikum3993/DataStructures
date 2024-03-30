
package org.atguigu.Tree.threadedBinaryTree;

import java.security.PrivateKey;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试 中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试:以10号节点
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱结点是：" + leftNode);
        System.out.println("10号节点的后继结点是：" + rightNode);

        //当线索化二叉树后，不能使用原来的遍历方法
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

//
//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化 ，需要创建要给指向当前节点的前驱结点的指针
    //pre在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法

    //遍历线索化二叉树
    public void threadedList() {
        //定义一个变量，临时存储当前遍历的结点,从root开始
        HeroNode node =root;
        while(node !=null){
            //循环的找到leftType == 1 的结点，第一个找到的就是8结点
            //后面随着遍历而变化，因为当leftType ==1 时，说明该结点是按照线索化
            //处理后的有效节点
            while (node.getLeftType()==0){
                node=node.getLeft();

            }
            //打印这个当前结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继节点，就一直输出
            while (node.getRightType()==1){
                //获取到当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node=node.getRight();
        }
    }

    /**
     * @param node 就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //如果当前节点为空，
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前节点
        //以 8结点来理解
        //8结点的.left=null    ,8结点的.leftType =1
        if (node.getLeft() == null) {
            //让当前节点的左指针，指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，让当前结点是下一个结点的前驱节点
        pre = node;

        //(三)再线索化右子树
        //处理当前节点的前驱节点
        threadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，这里立即判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树不能删除");
            return;
        }
    }
}


//创建HeroNode
//先创建HeroNode 结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //默认null
    private HeroNode right; //默认null

    //说明
    //1.如果lefyType ==0 表示指向的是左子树，如果是 1 则表示指向前驱节点
    //1.如果rightType ==0 表示指向的是右子树，如果是 1 则表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父结点
        //判断递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //判断递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    //编写后序序遍历的方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     * @param no 编号
     * @return 如果找到该Node, 没有则返回Null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        //比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        //1.判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到了结点，则返回；
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明左子树找到了
            return resNode;
        }
        //否则继续判断，当前的结点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        //没有找到，就和当前结点比较,如果是则返回当前结点，否则继续进行右递归的中序查找
        if (this.no == no) {
            return this;
        }
        //否则向右进行中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序序遍历查找
    public HeroNode postOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //否则向右进行中序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        System.out.println("进入后序查找");
        //左右子树都没没有找到，就和当前结点比较,如果是则返回当前结点，
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    // *  1)如果删除的节点是叶子节点(没有子节点)，，则删除该节点
    // *  2)如果删除的节点是非叶子节点，删除该子树
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树，递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

}