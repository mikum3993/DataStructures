package org.atguigu.recursion;

/*
递归机制
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印测试，回顾递归调用机制
//        test(4);
        int res = factorial(4);
        System.out.println(res);

    }

    //打印问题
    public static void test(int n){
        if (n>2){
            test(n-1);
        }
        System.out.println(n);
    }
    //阶乘问题
    public static int factorial(int n ){
        if (n==1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
