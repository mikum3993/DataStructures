package org.atguigu.Stack;

/**
 * 栈(Stack)
 * 是一个先入后厨(FILO-FIRST In Last Out) 的有序列表
 * 栈时限制线性表中元素的插入和删除只能在线性表的同一段进行的一种特殊线性表。
 *  允许插入和删除的一段，为变化的一段，称为 栈顶(Top)
 *  另一端为固定的一端 称为栈底(Bottom)
 *
 * 栈的思路分析
 * 使用数组来模拟栈
 * 定义一个top来表示栈顶，初始化为-1
 * 入栈操作，当有数据加入到栈时，top++;stack[top]=data;
 * 出栈操作，int value = stack[top];top--,return value;
 *
 */
public class StackDemo {
}
