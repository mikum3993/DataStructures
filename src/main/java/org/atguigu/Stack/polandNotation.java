package org.atguigu.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//前缀、中缀、后缀
public class polandNotation {
    public static void main(String[] args) {
        //先创建一个逆波兰表达式
        //(3+4)*5-6 => 4 3 + 5 * 6 -
        //为了方便,逆波兰表达式的数字和符号 使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        //思路
        //1.先将 suffixExpression => 放到 ArrayList 中
        //2.将 ArrayList 传给一个方法，使用配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calulate(rpnList);
        System.out.println(res);

    }

    //将一个逆波兰表达式，一次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    /*
        1.从左到右扫描，将3和4压入堆栈
        2.遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算那出3+4 的值，得7，再将7入栈
        3.将5入栈
        接下来就是x运算符，因此弹出5和7，激素那7x5=35,将35入栈
        将6入栈
        最后运算符-，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calulate(List<String> ls) {
        //创建给栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String item : ls) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(String.valueOf(res));
            }

        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}
