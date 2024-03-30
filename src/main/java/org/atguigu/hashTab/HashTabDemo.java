package org.atguigu.hashTab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加用户");
            System.out.println("list:显示用户");
            System.out.println("find:查找用户");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//next 默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建hashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //不要分解,初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工id    得到该员工应当添加到那条链表
        int EmpLinkedListNo = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[EmpLinkedListNo].add(emp);
    }

    //遍历所有的链表，遍历hash表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表
        int EmpLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[EmpLinkedListNo].findEmpById(id);
        if (emp != null) {//找到了
            System.out.printf("在第x条链表中找到雇员 id= %id\n", EmpLinkedListNo + 1);
        } else {
            System.out.println("在哈希表中没有该股元");
        }
    }

    //编写一个散列函数,使用一个简单的取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//创建一个 EmpLinkedList,表示链表
class EmpLinkedList {
    //头指针,指向第一个Emp,因此，这个链表的head 是直接指向第一个雇员的
    private Emp head;//默认null

    //添加员工到链表
    //说明
    //1.假定，当添加雇员时，id是自增长，id的分配总是从小到大
    //  将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果添加第一个雇员，
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时，直接将emp 加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {//链表为空
            System.out.println("第" + no + " 链表为空");
            return;
        }
        System.out.print("第" + no + " 链表信息为:");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.printf("=>id=%id name =%s \t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明curEmp已经是最后节点
                curEmp=null;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到 返回emp ;反之返回Null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;
            }
            //退出
            if (curEmp.next == null) {//说明遍历当前链表没有找到
                curEmp = null;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}