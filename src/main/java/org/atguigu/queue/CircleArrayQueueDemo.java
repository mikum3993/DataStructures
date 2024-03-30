package org.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("模拟数组环形队列的案例///////////");
        //测试队列
        //创建-环形队列
        CircleArray queue = new CircleArray(4); //设置4   队列有效数据最大是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);//接受一个字符
            switch (key) {
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
                        System.out.printf("队列头数据时%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头数据时%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
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

//使用数组模拟队列  编写一个ArrayQueue类
class CircleArray{
    private int maxSize;//数组最大容量
    private int front;//队列头:front指向队列的第一个元素 arr[front]
                        //初始值：0
    private int rear;//队列尾
    //指向队列最后一个元素的后一个位置
    //初始:0
    private int[] arr;//该数组用于存放数据

    //1.创建队列的构造器
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
//        front=0;//指向队列头部,分析出front是指向队列头的前一个位置
//        rear=0;//指向队列尾，指向队列尾的数据(即包含队列最后一个数据)

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
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，必须考虑取模
        rear = (rear + 1 ) % maxSize;

    }
    //获取队列数据、出队列
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            //通过抛出异常
            throw new RuntimeException("该队列为空，先添加数据");
        }
        //这里需要分析出，front指向队列的第一个元素
        //1.先把front 对应的值保存到一个临时变量
        //2.将front后移,考虑取模
        //将临时保存的变量返回
        int value = arr[front];

        front = (front +1 )%maxSize;
        return value;
    }

    //显示队列数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("该队列为空");
            return;
        }
        //从front开始遍历，遍历多少个元素
        //
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }
    //显示当前队列的有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头数据、不是取数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("该队列为空");
        }
        return arr[front];
    }
}