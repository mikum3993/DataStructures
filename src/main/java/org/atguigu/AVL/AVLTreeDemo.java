package org.atguigu.AVL;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建AVLTree
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历 中序
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("没有做平衡处理前~~~~");
        System.out.println("树的高度" + avlTree.getRoot().height());//4
        System.out.println("左子树的高度" + avlTree.getRoot().leftHeight());//1
        System.out.println("右子树的高度" + avlTree.getRoot().rightHeight());//3


    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
}

//创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    //返回当前结点的高度,以该节点为根节点的树的高度
    public int height() {

        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    private void leftRotate() {
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(this.value);
        //把新的节点的左子树，设置成当前节点的左子树
        newNode.left = this.left;
        //把新的节点的右子树，设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前结点的值，替换成右子结点的值
        value = this.right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = this.right.right;
        //把当前节点的左子节点，设置成新的节点
        left = newNode;

    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        value = this.left.value;
        left = this.left.left;
        right = newNode;
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

        //当添加完一个节点后，如果右子树的高度-左子树的高度 > 1
        if (rightHeight() - leftHeight() > 1) {
            //右子树的左子树的高度大于右子树的右子树的高度
            if (right != null && right.rightHeight() < right.leftHeight()) {
                //先对右子树进行旋转
                right.rightRotate();
                leftRotate();//左旋转
            } else {
                leftRotate();//左旋转
            }
            return;
        }
        //当添加完一个节点后，如左子树的高度-右子树的高度 > 1
        if (leftHeight() - rightHeight() > 1) {
            //如果左子树的右子树的高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左节点(左子树)->左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
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