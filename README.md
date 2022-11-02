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

#### 基本介绍

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
