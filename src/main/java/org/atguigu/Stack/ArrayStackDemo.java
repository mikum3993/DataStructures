package org.atguigu.Stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {

        //测试
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出数据");
            System.out.println("push：表示添加数据到栈");
            System.out.println("pop：表示从栈取出数据");
            System.out.println("输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是 :" + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//定义 ArrayStack 表示栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈，数据就放到该数组
    private int top = -1;//top 表示栈顶，初始化-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈满
        if (isFull()) {
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            return 0;
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈(遍历)
    public void list() {
        //遍历时需要从栈顶开始显示数据
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}