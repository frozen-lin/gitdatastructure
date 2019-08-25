package com.frozen.stack.application;

import com.frozen.stack.Stack;
import com.frozen.stack.impl.LinkedStack;

/**
 * @program: datastructure
 * @description: 中缀表达式计算器(计算整数的加减乘除)
 * @author: lw
 * @create: 2019-08-18 20:30
 **/
public class InfixCalculator {
    /**
     * 符号栈
     */
    private Stack<Character> operaStack;
    /**
     * 数字栈
     */
    private Stack<Integer> integerStack;

    public InfixCalculator() {
        operaStack = new LinkedStack<>();
        integerStack = new LinkedStack<>();
    }

    /**
     * @return 返回计算结果
     */
    public Integer calculate(String expression) {
        if (expression == null || expression == "") {
            return 0;
        }
        int length = expression.length();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = expression.charAt(i);
            //字符是数字
            if (Character.isDigit(ch)) {
                //若是字符串最后一位或下一位不是数字,压入栈中
                if (i + 1 == length || !Character.isDigit(expression.charAt(i + 1))) {
                    num.append(ch);
                    integerStack.push(Integer.parseInt(num.toString()));
                    //清空字符串
                    num.setLength(0);
                } else {
                    num.append(ch);
                }
            } else {
                //若操作符栈为空,直接压入栈
                if (operaStack.isEmpty()) {
                    operaStack.push(ch);
                } else {
                    int priority2 = getPriority(ch);
                    int priority1 = getPriority(operaStack.peek());
                    /*遍历的操作符优先级比上一次操作符高,直接入栈*/
                    if (priority2 >= priority1) {
                        operaStack.push(ch);
                    } else {
                        //优先级低,取出数字栈中的两个数字和操作符栈的一个操作符进行计算
                        //将计算后的结果压入数字栈中
                        singleCalculate();
                        //i值保持不变
                        i--;
                    }
                }
            }
        }
        return finishCalculate();
    }

    private Integer finishCalculate() {
        if(operaStack.isEmpty()){
            return integerStack.pop();
        }
        singleCalculate();
        return finishCalculate();
    }
    /**
     * @Description : 优先级低,取出数字栈中的两个数字和操作符栈的一个操作符进行计算
     *                将计算后的结果压入数字栈中
     * @return : void
     * @Author : lw
     * @DateTime : 2019-08-18 22:04
     */
    private void singleCalculate() {
        int num2 = integerStack.pop();
        int num1 = integerStack.pop();
        char operation = operaStack.pop();
        int result;
        switch (operation) {
            case '+':
                result =  num1 + num2;
                break;
            case '-':
                result =  num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result =  num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("操作符异常!");
        }
        integerStack.push(result);
    }

    /**
     * @param operation 操作符
     * @return : int
     * @Description :  得到操作符优先级 数值大代表优先级高
     * @Author : lw
     * @DateTime : 2019-08-18 20:40
     */
    private int getPriority(char operation) {
        switch (operation) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                throw new IllegalArgumentException("操作符异常!");
        }
    }
}
