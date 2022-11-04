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

        //测试修改结点代码
        System.out.println();
        System.out.println("修改后的链表");
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟--");
        singleLinkedList.update(newHeroNode);

        //删除一个结点
        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(4);
        singleLinkedList.deleteNode(2);
        singleLinkedList.deleteNode(3);

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

    /**
     * 修改结点的信息，根据no编号来修改，即no编号不能改
     * 1.根据newHeroNode的no来修改即可
     */
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的结点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该结点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的结点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {//没有找到这个结点
            System.out.printf("没有找到编号%d的结点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除结点
     */
    public void deleteNode(int no) {
        //定义辅助结点
        HeroNode temp = head;
        boolean flag = false;//标示是否找到待删除结点
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除结点前一个结点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //删除结点
            temp.next=temp.next.next;
        }else{
            System.out.printf("没有找到要删除的%d结点，结点不存在\n",no);
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
