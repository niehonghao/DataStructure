public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描到的char保存到ch
        String keepNum = "";//用于拼接
        //开始用while循环扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    /**
                     * 不为空
                     * 1.当前的操作符的优先级小于或者等于栈中的操作符：从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算
                     */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //2.当前的操作符的优先级大于或者等于栈中的操作符，就直接入符号栈。
                        operStack.push(ch);
                    }
                } else {
                    //为空，直接入符号栈
                    operStack.push(ch);
                }
            } else {//如果是数字，入数栈
                /**
                 * 1.当处理多位数的时，不能发现是一个数就立即入栈，因为他可能是多位数
                 * 2.在处理数，需要向expression的表达式的index后再看一位，如果是数就继续扫描，如果是符号就入栈
                 * 3.因此我们需要定义一个变量字符串，用于拼接
                 */
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，则继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要：keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完成，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行。
        while (true) {
            //如果符号栈为空，则计算结束，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);//入栈
        }
        //将数栈最后的数pop出来，即为结果
        System.out.printf("表达式%s=%d", expression, numStack.pop());
    }
}

//先创建一个栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组中
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int data) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = data;
    }

    //出栈，将栈顶的数据返回
    public int pop() {
        //先判断是否栈空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int data = stack[top];
        top--;
        return data;
    }

    //返回栈顶的值
    public int peek() {
        return stack[top];
    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示
     * 数字越大，优先级越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有+，-，*，/
        }
    }

    //判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res用于存放计算的结果
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
            default:
                break;
        }
        return res;
    }
}