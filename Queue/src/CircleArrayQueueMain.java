import java.util.Scanner;

public class CircleArrayQueueMain {
    public static void main(String[] args) {
        //创建一个循环队列
        CircleArray circleArray = new CircleArray(4);//设置4，队列有效数据最大是3
        char key = ' ';//接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接受一个字符
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    private int maxSize;//表示数组最大的容量
    private int front;//队列头，指向队列的第一个元素，初始值=0
    private int rear;//队列尾，指向队列最后一个元素的后一个位置，初始值=0
    private int[] arr;//该数据用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据
    public void addQueue(int data) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = data;
        //将rear后移
        rear = (rear + 1) % maxSize;
    }

    //取数据
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        /**
         * 这里需要分析front是指向队列的第一个元素
         * 1.先把front对应的值保留到一个临时变量
         * 2.将front后移
         * 3.将临时保存的变量返回
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        /**
         * 思路：从front开始遍历，遍历多少个元素
         */
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列的有效个数
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    //显示头元素，注意不是取出元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}