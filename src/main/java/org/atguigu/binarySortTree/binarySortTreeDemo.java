package org.atguigu.binarySortTree;

public class binarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

//        //删除叶子节点
        binarySortTree.delNode(3);
//        System.out.println("删除节点后");
//        binarySortTree.infixOrder();

        //删除有两颗子树的结点
        System.out.println("删除有两颗子树的节点");
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法

    /**
     * @param node 传入结点(当作新的二叉排序的根节点)
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这是 target 就指向最小节点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需求先去找到要删除的结点    targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果发现当前这颗二叉排序树只有最后一个节点     targetNode 没有父结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //找到targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子节点，
            if (targetNode.left == null && targetNode.right == null) {//该targetNode为叶子节点
                //判断targetNode 是父节点的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value) {//左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两棵子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一棵子树的结点
                //如果要删除的结点只有左节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode 是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode 是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//要删除的结点有右子节点
                    if (parent != null) {
                        //如果targetNode 是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//targetNode 是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;//如果root为空,直接让root指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序为空，不能遍历");
        }
    }

    //删除叶子节点


    //删除只有一棵子树的节点

    //删除有两棵子树的节点

}

//创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的结点

    /**
     * @param value 希望删除的节点的值
     * @return 如果找到了返回该结点，否则返回空
     */
    public Node search(int value) {
        if (value == this.value) {//找到该结点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前结点,向左子树递归查找
            //如果左子节点为空，
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前结点,向左子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父结点

    /**
     * @param value 要查找的结点的值
     * @return 返回的是要删除节点的父结点，如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前节点的左子节点不为空，
            //向左递归
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {//没有找到父结点
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点
    //递归的形式添加结点，需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断传入的节点的值，和当前子树根结点的值的关系
        if (node.value < this.value) {
            //如果当前结点的左子节点为空，直接添加
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {//添加的结点的值大于当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}