package org.atguigu.linkList;

import java.util.Stack;

import static org.atguigu.linkList.SingleLinkedList.*;

/**
 * 单向链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

//        按照编号的顺序插入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.list();

        System.out.println("----------修改后的链表---------------");
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "小尾巴~~~~");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();

        System.out.println("----------删除后的链表---------------");
        singleLinkedList.delete(1);

        //显示
        singleLinkedList.list();
        //测试：求单链表中有效节点的个数
        System.out.println("----------求单链表中有效节点的个数---------------");
        System.out.println("有效的节点个数： "+getLength(singleLinkedList.getHead()));

//        System.out.println("----------得到了倒数第k个节点---------------");
        //测试是否得到了倒数第k个节点
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
//        System.out.println("第k个节点:"+res);

//        System.out.println("----------测试单链表的反转---------------");
//        singleLinkedList.list();
//        ReverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

        singleLinkedList.list();
        System.out.println("----------测试单链表的逆序打印,没有改变链表结构---------------");
        reversePrint(singleLinkedList.getHead());
    }
}
//定义SingleLikedList管理
class SingleLinkedList{
    //先初始化,一个头节点    头节点不能动
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助变量 temp
        HeroNode temp = head;

        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if (temp.next ==null){
                break;
            }
            //如果没有找到最后，temp赋值到下一个节点
             temp = temp.next;
        }

        //当退出while循环时，temp就指向了链表的最后
        temp.next=heroNode;
    }


    //第二种方式在添加数据时，根据排名将数据添加到指定位置
    //(如果有这个数据，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此仍然需要一个辅助变量来帮助找到添加的位置
        //因为是单链表,找的temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag:标志添加的编号是否存在，默认false
        while(true){
            if (temp.next==null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){//位置找到，就在temp的后边插入
                break;
            }else if(temp.next.no == heroNode.no){//说明希望添加的herNode的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//一定要后移，遍历当前链表
        }
        //判断flag的值
        if (flag){//不能添加，编号已存在
            System.out.printf("插入的数据:%d、已存在，不能添加\n",heroNode.no);
             }else {
            //插入到链表中、temp 的后边
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


    //修改节点的信息,根据编号来修改，no编号不能改.
    //说明
//    1.根据 newHeroNode 的 no 来进行修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点(no)
        //先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否会遭到该节点
        while(true){
            if (temp==null){//到链表的最后一位，即已经遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no){
                //找到
                flag =true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name= newHeroNode.name;
            temp.nickname= newHeroNode.nickname;
        }else {//没有找到
            System.out.printf("没有找到编号 %d 的节点,不能修改",newHeroNode.no);
        }
    }

    //删除节点的数据
    //从单链表中删除一个节点
    //1、需要先找到要删除节点的前一个节点temp
    //2、temp.next = temp.next.next
    //比较是，是temp.next.no 和需要删除的节点的no比较
    //3、被删除的节点，不会有其他引用指向，会被jvm垃圾回收机制回收
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//标识是否找到要删除的节点
        while(true){
            if (temp.next==null){//已经到链表的最后
                break;
            }
            if (temp.next.no==no){
                //找到了待删除节点的前一个节点    temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){//找到
            //可以删除
            temp.next = temp.next.next;

        }else {
            System.out.printf("要删除的 %d 节点不存在 \n",no);
        }
    }

    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if (head.next ==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移,一定小心；
            temp = temp.next;

        }
    }

    //查找单链表中的倒数第k个节点
    //思路：
    //1.编写方法，接收head节点，同时接受一个index(倒数第index个节点)
    //2.先把链表从头到尾遍历，得到链表的总的长度 getlength
    //3.得到size后，从链表的第一个开始遍历(size-index)个
    //如果找到了返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断，链表是否为空
        if (head.next ==null){
            System.out.println("空");
            return null;
        }
        //第一次遍历得到链表节点的个数
        int size = getLength(head);
        //第二次遍历 (size-index) 位置，倒数第k个节点
        //先做一个数据(index)的校验
        if (index <= 0 || index>size){
            System.out.println("不在范围内");
            return null;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        //for循环定位到倒数的index
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;

    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需要不统计头节点)
    /*
    head 链表的头节点
    return 返回的有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next==null){//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量、这里没有统计头节点
        HeroNode cur = head.next;
        while(cur !=null){
            length++;
            cur= cur.next;
        }
        return length;
    }

    //单链表的反转
    public static void ReverseList(HeroNode head){
        if (head.next==null || head.next.next==null){
            return  ;
        }
        //先定义一个辅助指针，帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表、并完成链表的反转
        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的头部
            reverseHead.next=cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
        // 将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    //从尾到头打印单链表
    //方式1:先将单链表进行反转操作，在遍历；(这样做会破坏单链表的结构、不建议)
    //方式2:利用栈这个数据结构，将各个节点压入到栈中，利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点，压入栈中
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }
}



//定义HerNode,每个HerNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器
    public HeroNode(int hNo,String hName,String hNickName){
        this.no=hNo;
        this.name=hName;
        this.nickname=hNickName;
    }

    //为了显示方便，重写toSTring方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\''+
                '}';
    }
}