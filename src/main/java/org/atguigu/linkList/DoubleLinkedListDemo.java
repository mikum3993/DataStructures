package org.atguigu.linkList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建给链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        //输出
        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况/...........");
        doubleLinkedList.list();

    }
}

class DoubleLinkedList {
    //初始化
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {

        return head;
    }

    //添加节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此需要一个辅助变量 temp
        HeroNode2 temp = head;

        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，temp赋值到下一个节点
            temp = temp.next;
        }

        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点的内容，可以看到双向链表的节点内容修改和单向链表一样
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点(no)
        //先定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否会遭到该节点
        while (true) {
            if (temp == null) {//到链表的最后一位，即已经遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找到编号 %d 的节点,不能修改", newHeroNode.no);
        }
    }

    //从双向链表中删除一个节点
    //对于双向链表，可以直接找到要删除的这个节点
    //找到后自我删除即可
    public void delete(int no) {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到要删除的节点
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到了待删除节点的前一个节点    temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag) {//找到
            //可以删除
//            temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //这里代码有问题,
            //如果是最后一个节点，就不需要执行下一句话，否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在 \n", no);
        }
    }

    //遍历双向链表的方法
    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移,一定小心；
            temp = temp.next;

        }
    }
}


//定义HerNode,每个HerNode对象就是一个节点
//创建一个双向链表的类
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点,默认为null
    public HeroNode2 pre;//指向前一个节点,默认为null

    //构造器
    public HeroNode2(int no, String hName, String hNickName) {
        this.no = no;
        this.name = hName;
        this.nickname = hNickName;
    }

    //为了显示方便，重写toSTring方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}