## Java数据结构和算法

## 目录

## 线性结构和非线性结构

### 线性结构

1. 线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系
2. 线性结构有两种不同的存储结构，即顺序存储结构（数组）和链式存储结构（链表）。顺序存储的线性表称为顺序表，顺序表中的存储元素是连续的。
3. 链式存储的线性表称为链表，链表中的存储元素不一定是连续的，元素节点中存放数据元素以及相邻元素的地址信息
4. 线性结构常见的有：数组、队列、链表和栈。

### 非线性结构

非线性结构包括：二维数组，多维数组，广义表，树结构，图结构。

## 稀疏数组和队列

### 稀疏数组

#### 介绍

当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。

稀疏数组的处理方法是：

1. 记录数组一共有几行几列，有多少个不同的值
2. 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模

### 队列

#### 介绍 

- 队列是一个有序列表，可以用数组或者链表来实现。
- 遵循**先入先出**的原则。即：先存入队列的数据，要先去除。后存入的要后取出

![队列](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E9%98%9F%E5%88%97.png)

#### 数组模拟队列

当我们将数据存入队列时称为“addQueue”，addQueue的处理需要两个步骤

1. 将尾指针往后移：rear+1，当foront==rear【空】
2. 若尾指针rear小于队列的最大下标maxSize-1，则将数据存入rear所指的数组元素中，否则无法存入数据。rear==maxSize-1【队列满】

因为队列的输入、输出是分别从前后端来处理，因此需要两个变量forint及rear分别记录队列前后端的下标，front会随着数据输出而改变，而rear则是随着数据输入而改变。



问题分析并优化

1. 目前数组使用一次就不能复用，没有达到复用的效果。
2. 将这个数组使用算法，改进成一个环形的队列。

#### 循环队列中的公式推导

**规定：**

front指向队列第一个元素

rear指向队列最后一个元素的下一个位置

假设maxSize=10



**因为循环，需要保证front和rear的值始终在0-9之间循环，由此想到取余即%**

a%b 当 a<b时 结果为 a，a>=0，b>0，于是有了下方：

rear=rear%maxSize

front=front%maxSize



**以下所有操作都是rear和rear%maxSize，front和front%maxSize相互之间的替换**



![循环队列公式推导](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E5%BE%AA%E7%8E%AF%E9%98%9F%E5%88%97%E5%85%AC%E5%BC%8F%E6%8E%A8%E5%AF%BC.png)

当空间为空时 front追上rear：front%maxSize=rear%maxSize 即 front=rear

当空间为满时 front在rear的下一位，即rear+1=front

rear%maxSize+1=front%maxSize

当front=0，rear=9时，10=0，出了点问题

所以 (rear%maxSize+1)%maxSize=front%maxSize

化简  (rear+1)%maxSize=front



**判断队列中元素的个数**

（rear-front）%maxSize

因为是循环队列不确定rear和front的大小，而我们不想让rear-front是负数，因为我们做的是取余操作，多加几个maxSize并不会对结果有什么影响，所以做出修改：

（rear-front+maxSize）%maxSize

#### 数组模拟环形队列

思路分析

1. front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素，front的初始值=0。
2. rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置。因为希望空出一个空间做为约定，来区别队列为空或者队列为满。rear的初始值=0。
3. 当队列满时，条件是（rear+1）%maxSize=front【满】
4. 队列为空的条件，rear==front【空】
5. 当我们这样分析，队列中有效的数据的个数 (rear-front+maxSize)%maxSize
6. 我们就可以在原来的队列上修改得到一个环形队列

## 链表

### 介绍

链表是有序连续，但是它在内存中是存储如下

![链表](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E9%93%BE%E8%A1%A8.png)

1. 链表是以节点的方式来存储
2. 每个节点包含data域，next域：指向下一个节点
3. 链表的各个节点不一定是连续存储
4. 链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定

### 单链表 

单链表（带头结点）逻辑结构

![带头结点的单链表](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E5%B8%A6%E5%A4%B4%E7%BB%93%E7%82%B9%E7%9A%84%E5%8D%95%E9%93%BE%E8%A1%A8.png)

#### 单链表的应用实例

使用带head头的单向链表实现水浒英雄排行榜管理完成对英雄任务的增删改查操作。

1. 第一种方法在添加英雄时，直接添加到链表的尾部

   1. 先创建一个head头结点，作用就是表示单链表的头

   2. 后面我们每添加一个结点，就直接加入到链表的最后

      （通过一个辅助变量遍历，帮助遍历整个链表）

2. 第二种方法在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）

   1. 首先找到新添加的结点的位置，是通过辅助变量（指针）遍历 `heroNode.no<temp.next.no`
   2. 新的结点.next=temp.next `heroNode.next=temp.next`
   3. temp.next=新的结点 `temp.next=heroNode`

3. 修改结点功能

   1. 先找到该结点，通过遍历； `temp.no=heroNode.no`
   2. 修改结点信息

4. 删除结点功能

   1. 先找到需要删除的这个结点的前一个结点temp
   2. `temp.next=temp.next.next`
   3. 被删除的结点，将不会有其他引用指向，会被垃圾回收机制回收

#### 单链表面试题

##### 获取单链表中有效结点的个数

获取单链表中有效结点的个数，如果是带头结点的链表，要求不统计头结点

1. 判断是不是空链表
2. 定义辅助变量，并不要包含头结点 `HeroNode cur=head.next`
3. 遍历统计有效结点的个数

##### 查找单链表中的倒数第k个结点

**【新浪面试题】**

思路

1. 编写一个方法 `findLastIndexNode`  ，来接收head这个结点，同时接受一个index
2. index表示的是倒数第index个结点
3. 先把链表从头到尾遍历一遍，得到链表的长度getLength
4. 得到size之后，我们从链表的第一个开始遍历（size-index）个
5. index校验
6. 找到则返回，没有找到返回null

##### 单链表的反转

**【腾讯面试题】**

思路

1. 先定义一个结点reverseHead
2. 从头到尾遍历原来的链表，将其取出，并放在新的链表reverseHead的最前端
   1. 取出来结点的next更新，将头结点的next赋值给取出来的新结点
   2. reverseHead的next更新，reverseHead的next指向取出来的结点
3. 原来的链表的head.next=reverseHead.next

##### 从尾到头打印单链表

**【百度面试题】**

方式1：反向遍历

方式2：Stack栈

思路

1. 要求就是逆序打印单链表
2. 方式1：先将单链表进行反转操作，然后再遍历即可，这样做的问题是会破坏原来的单链表的结构，不建议
3. 方式2：利用栈这个数据结构，将各个结点压入到栈中，利用栈的先进后出的特点，就实现了逆序打印的效果

### 双链表

单项链表的缺点分析：

1. 单向链表，查找的方向只能是一个方向，而双向链表可以向前或者向后查找。
2. 单向链表不能自我删除，需要靠辅助结点，而双向链表，则可以自我删除，所以前面我们单链表删除结点，总是找到temp，temp是待删除结点的前一个结点

#### 双链表的应用实例

1. 遍历

   同单链表，可以向前遍历，也可以向后遍历

2. 添加（默认添加到双向链表的最后）

   1. 找到双向链表最后的结点
   2. `temp.next=node`
   3. `node.pre=temp`

3. 修改

   同单项链表

4. 删除

   1. 双向链表，可以实现自我删除
   2. 直接找到要删除的这个结点，比如temp
   3. `temp.pre.next=temp.next`
   4. `temp.next.pre=temp.pre` 注意判断是不是最后一个结点，则temp.next==null

#### 环形链表介绍和约瑟夫问题

![约瑟夫问题](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E7%BA%A6%E7%91%9F%E5%A4%AB%E9%97%AE%E9%A2%98.png)

**Joseph 问题**

设编号为1，2，...n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。

提示 

用一个不带头结点的循环链表来处理Josephu问题：先构成一个有n个结点的单循环链表，然后由k结点起从1开始计数，计到m时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从1开始计数，直到最后一个结点从链表中删除，算法结束。

思路

1. 创建一个辅助指针（变量）helper，事先应该指向环形链表的最后这个结点
2. 确定编号k开始报数时，让first和helper指针同时移动k-1次
3. 当小孩报数时，让first和helper指针同时的移动m-1次
4. 这时就可以将first指向的小孩结点出圈
   1. `first=first.next`
   2. `helper.next=first`
   3. 原来first指向的结点就没有任何引用，就会被回收

**构建一个单向的环形链表的思路**

1. 先创建第一个结点，让first指向该结点，并形成环形
2. 后面当我们每创建一个新的结点，就把该结点，加入到已有的环形链表中即可

**遍历循环链表**

1. 先让一个辅助指针（变量），指向first结点
2. 然后通过一个while循环遍历该环形链表即可

## 栈

### 介绍

栈是一个先入后出（FILO-First In Last Out）的有序列表。

栈（stack）是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。允许插入和删除的一端，为变化的一端，称为栈顶（Top），另一端为固定的一端，称为栈底（Bottom）。

根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元素最先删除，最先放入的元素最后删除。

出栈和入栈的概念

![出栈和入栈](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E5%87%BA%E6%A0%88%E5%92%8C%E5%85%A5%E6%A0%88.png)

### 栈的应用场景

1. 子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以回到原来的程序中。
2. 处理递归调用：和子程序的调用类型，只是除了存储下一个指令的地址外，也将参数、区域变量等数据存储堆栈中。
3. 表达式的转换与求值（实际解决）。
4. 二叉树的遍历。
5. 图形的深度优先（depth-first）搜索法。



实现栈的思路分析

1. 使用数组来模拟栈
2. 定义一个top来表示栈顶，初始化为-1
3. 入栈的操作，当有数据加入到栈时，`top++，stack[top]=data;`
4. 出栈的操作，`int value=stack[top]，top--，return value;`

### 栈的一个实际需求

栈实现综合计算器

思路

1. 通过一个index值（索引），来遍历我们的表达式 
2. 如果我们发现是一个数字，就直接入数栈
3. 如果发现扫描到的是一个符号，就分如下情况解决
   1. 如果发现当前的符号栈为空，就直接入栈
   2. 如果符号栈有操作符，就进行比较，如果**当前的操作符的优先级小于或者等于栈中的操作符**，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈。如果**当前的操作符的优先级大于或者等于栈中的操作符**，就直接入符号栈。
4.  当表达式扫描完成，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行。
5. 最后在数栈只有一个数字，就是这个表达式的结果。

### 前缀、中缀、后缀表达式

#### 前缀表达式

前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前

**前缀表达式的计算机求值**

从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算(栈顶元素和次顶元素)，并将结果入栈;重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果

#### 中缀表达式

中缀表达式就是常见的运算表达式

中缀表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作(前面我们讲的案例就能看的这个问题)，因此，在计算结果时，往往会将中缀表达式转成其它表达式来操作(一般转成后缀表达式.)

#### 后缀表达式

后缀表达式又称逆波兰表达式，与前缀表达式相似，只是运算符位于操作数之后

**后缀表达式的计算机求值**

从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈项的两个数，用运算符对它们做相应的计算(次项元素和栈项元素)，并将结果入栈;重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果

##### 逆波兰计算器

1. 输入一个逆波兰表达式，使用栈（stack），计算器结果
2. 支持小括号和多位数整数
3. 思路分析
4. 代码完成

##### 中缀表达式转换为后缀表达式

大家看到，后缀表达式适合计算式进行运算，但是人却不太容易写出来，尤其是表达式很长的情况下，因此在开发中，我们需要将中缀表达式转成后缀表达式。

具体步骤

1. 初始化两个栈:运算符栈s1和储存中间结果的栈s2；
2. 从左至右扫描中缀表达式；
3. 遇到操作数时，将其压s2；
4. 遇到运算符时，比较其与s1栈顶运算符的优先级：
   1. 如果s1为空，或栈顶运算符为左括号”（“，则直接将此运算符入栈；
   2. 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
   3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到（4. 1）与s1中新的栈顶运算符想比较；
5. 遇到括号时：
   1. 如果是左括号”（“，则直接压入s1
   2. 如果是右括号”）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到右括号位置，此时将这一对括号丢弃
6. 重复步骤2至5，直到表达式的最右边
7. 将s1中剩余的运算符一次弹出并压入s2
8. 一次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式



将中缀表达式`1+( (2+3)x4)-5`转换为后缀表达式

结果为 `1 2 3 + 4 x + 5 -`

![中缀表达式转后缀表达式](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E4%B8%AD%E7%BC%80%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%BD%AC%E5%90%8E%E7%BC%80%E8%A1%A8%E8%BE%BE%E5%BC%8F.png)

## 递归

### 介绍

简单的说:递归就是方法自己调用自己,每次调用时传入不同的变量.递归有助于编程者解决复杂的问题,同时可以让代码变得简洁。

### 递归调用机制

**递归调用规则**

1. 当程序执行到一个方法时，就会开辟一个独立的空间（栈）
2. 每个空间的数据（局部变量），是独立的

![递归调用机制](https://typora-nhh.oss-cn-chengdu.aliyuncs.com/%E7%AE%97%E6%B3%95/%E9%80%92%E5%BD%92%E8%B0%83%E7%94%A8%E6%9C%BA%E5%88%B6.png)

#### 打印问题

```java
public static void test(int n) {
    if (n > 2) {
        test(n - 1);
    }
    System.out.println("n=" + n);
}
```

#### 阶乘问题

```java
public static int factorial(int n) {
    if (n == 1) {
        return 1;
    } else {
        return factorial(n - 1) * n;
    }
}
```

### 递归需要遵守的重要规则

1. 执行一个方法时，就创建一个新的受保护的独立空间（栈空间）
2. 方法的局部变量是独立的，不会相互影响
3. 如果方法中使用的是引用类型变量（比如数组），就会共享该引用类型的数据
4. 递归必须向退出递归的条件逼近，否则就是无线递归了，StackOverFlowError栈溢出
5. 当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕

### 迷宫问题

讨论

1. 小球得到的路径，和程序员设置的找路策略有关：找路的上下左右的顺序相关
2. 在得到小球路径时，可以先使用（下右上左），再改成（上右下左），看路是否有变化
3. 回溯线性
4. 最短路径

### 八皇后问题

回溯算法

#### 介绍

八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯.贝瑟尔于1848年提出:在8X8格的国际象棋上摆放八个皇后，使其不能互相攻击，即:任意两个皇后都不能处于同一行、同一列或同-斜线上，问有多少种摆法(92)。

#### 思路分析

1. 第一个皇后先放第一行第一列
2. 第二个皇后放在第二行第一列、然后判断是否ok，如果不ok继续放在第二列、第三列、依次把所有咧都放完，找到一个合适的
3. 继续第三个皇后，还是第一列、第二列...直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
4. 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第- 个皇后，放到第一列的所有 正确解，全部得到
5. 然后回头继续第一个皇后放第二列，后面继续循环执行1，2，3，4的步骤

#### 说明

理论上应该创建一一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] ={0,4, 7,5,2,6, 1,3}，对应arr下标表示第几行，即第几个皇后，ar[i]=val, val 表示第i+1个皇后，放在第i+1行的第val+1列。
