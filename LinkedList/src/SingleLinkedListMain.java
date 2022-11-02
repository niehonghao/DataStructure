import javax.xml.soap.Node;

public class SingleLinkedListMain {
    public static void main(String[] args) {
        //先创建结点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //显示
        singleLinkedList.list();
    }
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动
    private HeroNode head = new HeroNode(0, "", "");

    //添加结点到单向链表

    /**
     * 思路，当不考虑编号的顺序时
     * 1.找到当前链表的最后结点
     * 2.将最后这个结点的next指向新的结点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //因为head结点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        /**
         *当退出while循环时，temp就指向了链表的最后
         *将最后这个结点的next指向新的结点
         */
        temp.next = heroNode;
    }

    //第二种添加方式

    //显示链表（遍历）
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表的最后
            if (temp == null) {
                break;
            }
            //输出结点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}


//定义HeroNode，每个HeroNode对象就是一个结点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个结点

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

}
