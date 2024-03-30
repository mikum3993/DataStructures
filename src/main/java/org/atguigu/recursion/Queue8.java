package org.atguigu.recursion;

/**
 * 八皇后问题实现
 */
public class Queue8 {

    //定义一个max 表示 共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置位置的结果,比如 ARR = {0，4，7，5，2，6，1，3}
    int[] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法" , count);
    }

    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先将当前这个皇后n，放到该行的第1列
            array[n] = i;
            //判断当前放置第n个皇后到i列时，是否冲突
            if (judge(n)) {  //不冲突
                //接着放n+1个皇后
                check(n + 1);
            }

            //如果冲突，继续执行 array[n]=i; 即将第 n 个皇后，放置到本行的后一个位置
        }
    }


    //查看当我们放置第n个皇后时，就去检测 该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示放3第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明：
            // array[i]==array[n] 判断 第n个皇后是否和前面的n-1个皇后在同一列
            // Math.abs(n-i)==Math.abs(array[n]-array[i])   判断第n个皇后是否和第i个皇后是否在同一个斜线
            // n =1
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法 可以将皇后摆放的位置打印
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
