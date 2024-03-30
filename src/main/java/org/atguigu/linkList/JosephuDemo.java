package org.atguigu.linkList;

/**
 * 约瑟夫问题
 * 使用单向环形链表完成
 * <p>
 * 思路：
 * 1.先创建第一个节点，让first指向该节点，并形成环形
 * 2、后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可
 * <p>
 * 遍历环形链表
 * 1，先让一个辅助指针(变量)curBoy：指向first节点
 * 2，然后通过while循环遍历该环形链表即可 curBoy.next == first 结束
 */
public class JosephuDemo {
    public static void main(String[] args) {
        //测试
        CircleSingleLinedList circleSingleLinedList = new CircleSingleLinedList();
        circleSingleLinedList.addBoy(5);
        circleSingleLinedList.showBoy();

        //测试小孩出圈
        circleSingleLinedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinedList {
    //创建一个first节点，目前先不赋值
    private BoyNode first = new BoyNode(-1);

    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 2) {
            System.out.println("nums的值不正确");
            return;
        }
//        使用for循环创建环形链表
        BoyNode curBoy = first;//辅助指针，帮助构建环形链表
        for (int i = 0; i <= nums; i++) {
            //根据编号创建小孩节点
            BoyNode boy = new BoyNode(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
        //根据用户的输入

    }

    //遍历当前环形链表
    public void showBoy() {
        if (first.next == null) {
            System.out.println("链表为空");
            return;
        }
        //first不能动，仍然使用一个辅助指针完成遍历
        BoyNode curBoy = first;
        while (true) {
            System.out.printf("小孩编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //说明遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//让curboy后移
        }
    }

    //根据用户的输入计算出出圈的顺序

    /**
     * @param startNo  从第几个开始
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || countNum > nums) {
            System.out.println("参数输入有误");
            return;
        }
//        创建辅助指针，帮助完成小孩出圈
        BoyNode helper = first;
        //需求创建一个辅助指针(变量)helper,实现应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //当小孩报数前，让first和helper指针同时的移动 k -1 次
        for (int i = 0; i < startNo -1; i++) {
            first =first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动 m -1 次,然后出圈
        //这里是循环操作，直到圈中只有一个节点
        while (true){
            if (first.next==first){
                System.out.println("圈中只有一人");
                break;
            }
            //让first 和helper 移动 countNum - 1次，然后出圈
            for (int i = 0; i < countNum -1; i++) {
                first =first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点，是要出圈的节点
            System.out.printf("小孩 %d 出圈 \n",first.getNo());
            //这时将first指向的小孩(节点)出圈
            first = first.next;
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的编号 %d",first.getNo());
    }

}


//创建一个Boy类。，表示一个节点
class BoyNode {
    public int no;//编号
    public BoyNode next;//指向下一个节点,默认为null

    //构造器
    public BoyNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public BoyNode getNext() {
        return next;
    }

    public void setNext(BoyNode next) {
        this.next = next;
    }
}
