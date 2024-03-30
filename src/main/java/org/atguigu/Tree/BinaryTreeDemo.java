package org.atguigu.Tree;

/**
 * 使用前序、中序、后续的方式来查询指定的节点
 * <p>
 * 前序查找思路：
 * 1.先判断当前结点的 no 是否等于要查找的
 * 2.如果相等，则返回当前结点
 * 3.如果不等，则判断当前的左子节点是否为空，如果不为空，则递归前序查找
 * 4.如果左递归前序查找，找到了结点，则返回；否则继续判断，当前的结点的右子节点是否为空，如果不为空，则继续向右递归前序查找
 * <p>
 * 中序查找思路：
 * 1.判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
 * 2.如果找到了，则返回当前结点，没有找到，就和当前结点比较,如果是则返回当前结点，否则继续进行右递归的中序查找
 * 3.如果右递归中序查找，找到就返回，否则返回Null
 * <p>
 * 后序查找思路：
 * 1.判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
 * 2.如果找到，就返回，如果没找到，就判断当前节点的右子节点是否为空，如果不为空，则右递归进行后续查找，如果找到，就返回
 * 3.就和当前节点进行，比如，如果是则返回，否则返回null
 */

/**
 * 删除节点
 * 1)如果删除的节点是叶子节点(没有子节点)，，则删除该节点
 * 2)如果删除的节点是非叶子节点，删除该子树
 * 3)测试，删除掉5好叶子节点和 3号子树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //现需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明： 先手动创建该二叉树,后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
//        System.out.println("前序遍历");//root,2,3,4
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");//2,1,3,4
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");//2,4,3,1
//        binaryTree.postOrder();

        //前序遍历
//        System.out.println("前序遍历方式");
//        HeroNode resnode = binaryTree.preOrderSearch(3);
//        if (resnode != null) {
//            System.out.printf("找到了 ，信息为 no=%d name = %s", resnode.getNo(), resnode.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的英雄",3);
//        }
        //中序遍历
//        System.out.println("前序遍历方式");
//        HeroNode resnode = binaryTree.infixOrderSearch(4);
//            System.out.printf("找到了 ，信息为 no=%d name = %s", resnode.getNo(), resnode.getName());
//        if (resnode != null) {
//        }else {
//            System.out.printf("没有找到 no=%d 的英雄",4);
//        }
        //后序遍历
//        System.out.println("后序遍历方式");
//        HeroNode resnode = binaryTree.postOrderSearch(5);
//        if (resnode != null) {
//            System.out.printf("找到了 ，信息为 no=%d name = %s", resnode.getNo(), resnode.getName());
//        } else {
//            System.out.printf("没有找到 no=%d 的英雄", 5);
//        }
        //删除测试
        System.out.println("删除前：前序遍历");
        binaryTree.preOrder();//1,2,3,5,4
//        binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后:前序遍历");
        binaryTree.preOrder();//1,2,3,4

    }
}

//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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


//先创建HeroNode 结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //默认null
    private HeroNode right; //默认null

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