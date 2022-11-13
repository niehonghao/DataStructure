public class Queen8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果
    int[] array = new int[max];
    //统计次数
    static int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(count);
    }

    //编写一个方法，放置第n个皇后
    //注意：check是每一次递归时，进入到check中都有for(int i=0;i<max;i++)，因此有回溯
    private void check(int n) {
        if (n == max) {//n==8,其实8个皇后就已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {//不冲突
                //开始放n+1个皇后，开始递归
                check(n + 1);
            }
            //冲突，将第n个皇后后移到该行的下一列，又开始判断是否冲突
        }
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * Math.abs 返回绝对值
     * 判断同一在斜线：横坐标之差绝对值=纵坐标之差绝对值
     * 判断是否在同一行：没有必要，n每次都在递增
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
