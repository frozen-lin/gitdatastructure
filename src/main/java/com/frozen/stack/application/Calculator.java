package com.frozen.stack.application;

import com.frozen.stack.Stack;
import com.frozen.stack.impl.LinkedStack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: datastructure
 * @description: 简单4则运算计算器
 * @author: lw
 * @create: 2019-08-25 20:44
 **/
public class Calculator {

    /**
     * @param infixExpression 中缀表达式
     * @return : java.lang.Integer
     * @Description :  中缀表达计算器
     * @Author : lw
     * @DateTime : 2019-08-25 20:49
     */
    public BigDecimal calculate(String infixExpression) {
        List<String> suffixExpression = infixToSuffix(infixExpression);
        return suffixCalculate(suffixExpression);
    }

    /**
     * @Description :
     * @param infixExpression 中缀转后缀
     * @return : java.util.List<java.lang.String>
     * @Author : lw
     * @DateTime : 2019-08-25 21:52
     */
    public List<String> infixToSuffix(String infixExpression) {
        //后缀表达式结果
        List<String> suffixExpreList = new ArrayList<>();
        //操作符栈
        Stack<String> operationStack = new LinkedStack<>();
        char[] chars = infixExpression.toCharArray();
        //计算数字
        StringBuilder numString = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            //若字符是数字或小数点.
            if (isOperateNum(ch)){
                numString.append(ch);
                //若是字符数组最后一位或下一位不是操作数
                if(i+1==chars.length||!isOperateNum(chars[i+1])){
                    //添加至后缀表达式List
                    suffixExpreList.add(numString.toString());
                    //清空numString
                    numString.setLength(0);
                }
            }else{
                String operation = ch + "";
                //若是右括号
                if(operation.equals(")")){
                    while(true){
                        int priority = OperationPriority.getPriority(operationStack.peek());
                        if (priority!=99){
                            //不是左括号将操作符栈栈顶弹出并加入后缀表达式List
                            suffixExpreList.add(operationStack.pop());
                        }else{
                            //消除左括号
                            operationStack.pop();
                            break;
                        }
                    }
                }else{
                    while(true){

                        int priority2 = OperationPriority.getPriority(operation);
                        //若操作符栈为空,压入操作符栈
                        if(operationStack.isEmpty()){
                            operationStack.push(operation);
                            break;
                        }else{
                            //操作符栈栈顶为"("或操作符>=操作符栈栈顶的优先级,压入操作符栈
                            int priority1 = OperationPriority.getPriority(operationStack.peek());
                            if(priority1==99||priority2>=priority1){
                                operationStack.push(operation);
                                break;
                            }else{
                                //否则将操作符栈栈顶弹出并加入后缀表达式List
                                suffixExpreList.add(operationStack.pop());
                            }
                        }
                    }
                }
            }
        }
        //将操作符栈栈顶弹出并压入后缀表达式List
        while(!operationStack.isEmpty()){
            suffixExpreList.add(operationStack.pop());
        }
        return suffixExpreList;
    }

    /**
     * @Description :  后缀表达式计算器(逆波兰计算)
     * @param suffixExpression 后缀表达式
     * @return : BigDecimal
     * @Author : lw
     * @DateTime : 2019-08-25 21:56
     */
    public BigDecimal suffixCalculate(List<String> suffixExpression) {
        Stack<BigDecimal> calStack = new LinkedStack<>();
        for (String str:suffixExpression){
            //若是操作数
            if(str.length()>1||Character.isDigit(str.charAt(0))){
                calStack.push(new BigDecimal(str));
            }else{
                BigDecimal num2 = calStack.pop();
                BigDecimal num1 = calStack.pop();
                calStack.push(singleCalculate(str, num1, num2));
            }
        }
        return calStack.pop();
    }

    /**
     * @Description : 计算
     * @return : BigDecimal
     * @Author : lw
     * @DateTime : 2019-08-18 22:04
     */
    private BigDecimal singleCalculate(String operation,BigDecimal num1,BigDecimal num2) {
        switch (operation) {
            case "+":
                return  num1.add(num2);
            case "-":
                return num1.subtract(num2);
            case "*":
                return num1.multiply(num2);
            case "/":
                return num1.divide(num2);
            default:
                throw new IllegalArgumentException("操作符异常!");
        }
    }
    /**
     * @Description :判断字符是否是操作数(数字或小数点)
     * @param ch
     * @return : boolean
     * @Author : lw
     * @DateTime : 2019-08-25 21:23
     */
    private boolean isOperateNum(char ch){
        return Character.isDigit(ch)||ch=='.';
    }
}

class OperationPriority {
    /**
     * @param operation 操作符
     * @return : int
     * @Description :  得到操作符优先级 数值大代表优先级高
     * @Author : lw
     * @DateTime : 2019-08-18 20:40
     */
    public static int getPriority(String operation) {
        switch (operation) {
            case "(":
                return 99;
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                throw new IllegalArgumentException("操作符异常!");
        }
    }
}
