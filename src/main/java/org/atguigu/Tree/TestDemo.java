package org.atguigu.Tree;

import java.util.ArrayList;

public class TestDemo {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        //以ArrayList为例  看看底层的数组扩容
        //ArrayList 维护了数组transient Object[] elementData;/
        /**
         * ArrayList 底层仍然是进行数组扩容
         * private Object[] grow(int minCapacity) {
         *         int oldCapacity = elementData.length;
         *         if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
         *             int newCapacity = ArraysSupport.newLength(oldCapacity,
         *                     minCapacity - oldCapacity, /* minimum growth
         *oldCapacity >> 1           /* preferred growth );
         *return elementData = Arrays.copyOf(elementData, newCapacity);
         *} else
    {
         *return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
         *}
         *
}
         */
//        ArrayList<Object> arrayList = new ArrayList<>();
        String strByte="10101000";
        System.out.println((byte)Integer.parseInt(strByte,2));
    }
}
