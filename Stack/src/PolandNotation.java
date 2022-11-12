import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        /**
         * （3+4）x5-6 => 3 4 + 5 x 6 -
         *  为了方便，逆波兰表达式的数字和符号使用空格隔开
         */
        String suffixExpression = "3 4 + 5 * 6 -";
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的LIST" + parseSuffixExpressionList);
        System.out.printf("expression=%d\n", calculate(parseSuffixExpressionList));
        /**
         * 思路
         * 1.先将suffixExpression 放到ArrayList中
         * 2.将ArrayList 传递给一个方法，遍历 ArrayList 配合栈完成计算
         * 3.将得到的中缀表达式对应的List -> 后缀表达式的List
         */
        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);

        int res = calculate(listString);
        System.out.println(res);
    }

    /**
     * 中缀表达式转后缀表达式
     * 1. 1+( (2+3)x4)-5 -> 1 2 3 + 4 x + 5 -
     * 2. 因为直接对str进行操作，不方便，先将字符串转换为中缀的表达式对应的List
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //初始化栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明：s2这个栈，在整个转换的过程中，没有pop操作，而且后面我们需要逆序输出
        //因此比较麻烦，不用Stack，直接使用List<String>s2
        List<String> s2 = new ArrayList<>();//存储中间结果的s2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，就加入到s2
            if (item.matches("\\d+")) {//正则表达式
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号”）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到右括号位置，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将这个右括号pop，消除括号
            } else {
                //当item的优先级小于或者等于栈顶运算符，将s1栈顶的运算符弹出并压入到s2中，再次转到（4. 1）与s1中新的栈顶运算符想比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符一次弹出并压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是存放到一个list中的.因此按顺序输出就是对应的后缀表达式list
    }

    //将中缀表达式转换为对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> expression = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式
        String str;// 做对多位数的拼接
        char c;// 每遍历到一个字符就放入到c中
        do {
            //如果c是一个非数字,我们就需要加入到expression
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                expression.add(String.valueOf(c));
                i++;//i后移
            } else {//如果是一个数字，需要考虑多位数字
                str = "";//先将str清空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                expression.add(str);
            }
        } while (i < s.length());
        return expression;
    }


    //将一个逆波兰表达式，依次将数据和运算符 放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，在入栈
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
        return Integer.parseInt(stack.pop());
    }
}

//Operation 可以返回一个运算符对应的优先级
class Operation {
    /**
     * 加法
     */
    private static final int ADD = 1;
    /**
     * 减法
     */
    private static final int SUB = 1;
    /**
     * 乘法
     */
    private static final int MUL = 2;
    /**
     * 除法
     */
    private static final int DIV = 2;

    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}
