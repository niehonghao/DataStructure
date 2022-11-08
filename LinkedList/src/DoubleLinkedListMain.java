public class DoubleLinkedListMain {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表测试");
        //创建结点
        DoubleLinkedListNode node1 = new DoubleLinkedListNode(1, "宋江", "及时雨");
        DoubleLinkedListNode node2 = new DoubleLinkedListNode(2, "卢俊义", "玉麒麟");
        DoubleLinkedListNode node3 = new DoubleLinkedListNode(3, "吴用", "智多星");
        DoubleLinkedListNode node4 = new DoubleLinkedListNode(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        //显示
        doubleLinkedList.list();
        //修改
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newNode);
        System.out.println("修改后链表的情况");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.deleteNode(3);
        System.out.println("删除后链表的情况");
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //初始化一个头结点，头结点不要动，不存放具体的数据
    private DoubleLinkedListNode head = new DoubleLinkedListNode(0, "", "");

    //返回头结点
    public DoubleLinkedListNode getHead() {
        return head;
    }

    //遍历双向链表的方法【显示链表】
    public void list() {
        if (head.next == null) {//判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，需要一个辅助结点来遍历
        DoubleLinkedListNode temp = head.next;
        while (true) {
            if (temp == null) {//已经遍历完结点
                break;
            }
            System.out.println(temp);
            temp = temp.next;//temp后移
        }
    }

    //添加一个结点到双向链表的最后
    public void add(DoubleLinkedListNode node) {
        //辅助结点
        DoubleLinkedListNode temp = head;
        //遍历找到最后
        while (true) {
            if (temp.next == null) {//已经遍历完结点
                break;
            }
            temp = temp.next;//temp后移
        }
        //temp指向了链表的最后
        //形成一个双向链表
        temp.next = node;
        node.pre = temp;
    }

    //修改一个结点的内容
    public void update(DoubleLinkedListNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改结点的no，根据no编号
        //定义一个辅助变量
        DoubleLinkedListNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//已经遍历完结点
            }
            if (temp.no == node.no) {
                //找到修改的结点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到编号%d的结点，不能修改\n", node.no);
        }
    }

    //从双向链表中删除一个结点

    /**
     * 1.对于双向链表，可以直接找到要删除的这个结点
     * 2.找到后，自我删除
     */
    public void deleteNode(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        //辅助结点
        DoubleLinkedListNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;//已经遍历完结点
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个结点，就不需要执行这段话
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到要删除的%d结点，结点不存在\n", no);
        }
    }
}

class DoubleLinkedListNode {
    public int no;
    public String name;
    public String nickName;
    public DoubleLinkedListNode next;//指向下一个结点，默认为null
    public DoubleLinkedListNode pre;//指向前一个结点，默认为null

    public DoubleLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //toString
    @Override
    public String toString() {
        return "DoubleLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
