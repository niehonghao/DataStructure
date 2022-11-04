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

#### 获取单链表中有效结点的个数

获取单链表中有效结点的个数，如果是带头结点的链表，要求不统计头结点

1. 判断是不是空链表
2. 定义辅助变量，并不要包含头结点 `HeroNode cur=head.next`
3. 遍历统计有效结点的个数

#### 查找单链表中的倒数第k个结点

**【新浪面试题】**

思路

1. 编写一个方法 `findLastIndexNode`  ，来接收head这个结点，同时接受一个index
2. index表示的是倒数第index个结点
3. 先把链表从头到尾遍历一遍，得到链表的长度getLength
4. 得到size之后，我们从链表的第一个开始遍历（size-index）个
5. index校验
6. 找到则返回，没有找到返回null

#### 单链表的反转

**【腾讯面试题】**

思路

1. 先定义一个结点reverseHead
2. 从头到尾遍历原来的链表，将其取出，并放在新的链表reverseHead的最前端
   1. 取出来结点的next更新，将头结点的next赋值给取出来的新结点
   2. reverseHead的next更新，reverseHead的next指向取出来的结点
3. 原来的链表的head.next=reverseHead.next
