package org.atguigu.search;

import java.util.Arrays;

/**
 * 斐波那契(黄金二分法)查找
 */
public class fibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1563};

    }

    //因为后面 mid = low+F(k-1)-1，需要使用到斐波那契数列,因此需要先获取到一个斐波那契数列
    //非递归方式得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 0; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式编写

    /**
     * @param a   数组
     * @param key 需要查找的关键码(值)
     * @returnf 返回对应的下标，没有则-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;   //表示斐波那契分割数值的下标
        int mid = 0;    //存放mid 值
        int f[] = fib();  //获取到斐波那契数列
        //获取到斐波那契数值的下标
        while (high > f[k - 1]) {
            k++;
        }
        //因为 f[k]值，可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a
        //不足的部分，会使用 0 填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需求使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //使用while 循环处理，找到我们的数Key
        while (low <= high) {//只要满足这个条件，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]){//应该向数组的前面查找
                high =mid-1;
                //f[k]=f[k-1]+f[k-2]
                //f[k-1]=f[k-2]+f[k-3]
                //k--
                //mid = f[k-1-1]-1
                k--;
            }else if(low>= temp[mid]){//向右查找
                low = mid+1;
                //f[k]=f[k-1]+f[k-2]
                //在 f[k-2] 的前面进行查找 k-=2
                //mid = f[k-1-2]-1
                k-=2;
            }else {
                //需要确定返回的是那个下标
                if (mid <=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
