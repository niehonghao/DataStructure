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
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //按照编号添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

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

    /**
     * 第二种添加方式
     * 在添加英雄的时，根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败。并给出提示
     */
    public void addByOrder(HeroNode heroNode) {
        /**
         * 因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
         * 因为是单链表。因此我们找的temp 是位于 添加位置的前一个结点，否则插入不了
         */
        HeroNode temp = head;
        boolean flag = false;// 标示添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置就找到了，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//添加的hero的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前的链表
        }
        //判断flag
        if (flag) {//不能添加，编号存在
            System.out.printf("英雄编号%d已经存在，不能添加。\n", heroNode.no);
        } else {
            //插入链表
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


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
