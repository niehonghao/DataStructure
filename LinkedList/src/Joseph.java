public class Joseph {
    public static void main(String[] args) {

    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first结点，当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩结点
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;//赋值指针，帮助构建环形链表
        //使用for循环来创建循环链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩结点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }
}

//创建一个boy类，表示一个结点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个结点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
