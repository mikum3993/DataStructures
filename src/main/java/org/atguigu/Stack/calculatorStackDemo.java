package org.atguigu.Stack;

/**
 * 使用栈 完成表达式的计算器速录
 * 1.通过一个index值(索引) 来遍历我们的表达式
 * 2.如果发现是一个数字，直接如数栈
 * 3.如果扫描到是一个符号，分如下情况
 * 如果当前的符号栈为空，直接入栈
 * 如果当前符号栈不为空，进行比较，
 * 如果当前的操作符的优先级小于或者等于栈中的操作符
 * 就需要从数栈中pop出两个数，再从符号栈中pop出一个符号
 * ，进行运算，将得到结果，入数栈，然后将当前的操作符，如符号栈
 * 如果当前的操作符的优先级大于栈中的操作符，就直接如符号栈
 * 4.当表达式扫描完毕后，就顺序的从数栈和符号栈中pop出镶银的数和符号，并允许
 * 5.最后在数栈只有一个数字，就是表达式的结果
 */
public class calculatorStackDemo {
    public static void main(String[] args) {
        //根据前面的思路，完成表达式的运算
        String expression = "70+2*6-4";
        ///创建2个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始while循环扫描exoression
        while (true) {
            //依次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch 然后做相应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //处理
//                    如果当前符号栈不为空，进行比较，

                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
//                   *          如果当前的操作符的优先级小于或者等于栈中的操作符
//                   *          就需要从数栈中pop出两个数，再从符号栈中pop出一个符号
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果，入数栈
                        numStack.push(res);
                        //然后将当前的操作符，如符号栈
                        operStack.push(ch);
                    } else {
//                   *          如果当前的操作符的优先级大于栈中的操作符，就直接如符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            } else {//如果是数，直接入数栈
//                numStack.push(ch - 48);
                //1.当处理多位数式，不能发现是一个数就立即入栈，可能是多位数
                //2.在处理数，需要向expression的表达式的index 后再看一位，如果是数就进行扫描，如果是符号就入栈
                //3.因此，需要定义一个字符变量，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else
                    //判断下一个字符是不是数字，如果是数字，进行扫描，如果是符号，则入数栈
                    //注意看后面一位，不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位式运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要！！
                        //清空keepNum
                        keepNum = "";
                    }

            }
            //让index +1 判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //表达式扫描完毕后，就顺序的从数栈和符号栈中pop出相应的数和符号，进行运算
        while (true) {
            //如果符号栈为空，则计算结束,数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);//入栈
        }
        //将数栈的最后数，pop
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);

    }
}

//定义 ArrayStack 表示栈,需要扩展功能，符号及其优先级的潘顿啊
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈，数据就放到该数组
    private int top = -1;//top 表示栈顶，初始化-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回当前栈顶的值，不是真正的pop
    public int peek() {
        return stack[top];
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
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级使用数字表示
    //数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' | oper == '/') {
            return 2;
        } else if (oper == '+' || oper == '-') {
            return 1;
        } else {
            return 0;//假定当前的表达式只有 + - * /
        }
    }

    //判断是否是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}