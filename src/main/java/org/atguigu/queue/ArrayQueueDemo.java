package org.atguigu.queue;

import java.util.Scanner;

/**
 * 数组模拟队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试队列
        //创建队列
        ArrayQueue queue = new ArrayQueue(30);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):查看队列头数据");
            key=scanner.next().charAt(0);//接受一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("队列头数据时%d\n",res);
                    }catch (Exception e){e.printStackTrace();}
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头数据时%d\n",res);
                    }catch (Exception e){e.printStackTrace();}
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}


//使用数组模拟队列  编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据

    //1.创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front=0;//指向队列头部,分析出front是指向队列头的前一个位置
        rear=0;//指向队列尾，指向队列尾的数据(即包含队列最后一个数据)

    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1) %maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到数列
    public void addQueue(int n){
        //1、判断是否满
        if (isFull()){
            System.out.println("队列满，不能计入数据");
            return;
        }
        rear++;//让rear后移
        arr[rear] = n;
    }

    //获取队列数据、出队列
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            //通过抛出异常
            throw new RuntimeException("该队列为空，先添加数据");
        }
        front++;//让front后移
        return arr[front];

    }
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("该队列为空");
            return;
        }

        for (int i = 0; i < (rear+maxSize-front)%maxSize; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列头数据、不是取数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("该队列为空");
        }
        return arr[front+1];
    }
}