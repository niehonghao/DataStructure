public class DoubleLinkedListMain {
    public static void main(String[] args) {

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
