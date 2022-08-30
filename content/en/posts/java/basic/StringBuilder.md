---
title: "凤凰架构-架构师的视角-透明多级分流系统"
date: 2022-08-29T14:38:38+08:00
lastmod: 2018-03-03T16:01:23+08:00
draft: false
tags: ["java", "basic"]
categories: ["Notes"]
authors:
- Lhy

---



## 概述
字符串一旦创建不在更改
String的值不可改变，但可以共享
字符串效果上相当于是字符数组char[]，但底层原理是字节数组byte[]
## String构造方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020031611151161.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3MDc2OTQw,size_16,color_FFFFFF,t_70)
## String对象的特点
1.new出来的String对象每次都会申请一个内存空间 即使内容相同 地址也不相同
2.以“”方式给出的字符串，只要字符序列相同(顺序和大小写)，无论在程序代码中出现几次JVM都只会建立一个String对象，并在字符串池中维护
## 字符串的比较
**==的特点
基本类型：比较的是是数据值是否相同
引用类型：比较的是地址是否相同**

**比较字符串内容是否相同使用equals()方法**
## 常用方法
### length（）方法
s.length()返回字符串长度
### charAt(int index)方法
返回索引所在得值
### endwith()
测试字符串是否以规定得字符结尾

###  String[] split(String s);
以s分割整个字符串返回字符串数组
## 案例


```java
package String.base;
//需求：键盘录入字符串，统计该字符串中大写字母字符，小写字母字符，数字字符出现的次数
import java.util.*;
public class StringTest01 {
        public static  void main(String[] args){
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入一个字符串");
            String line = sc.nextLine();
            int bigCount=0,smallCount=0,numberCount=0;
            for(int i=0;i<line.length();i++){
                if(line.charAt(i)>='a' && line.charAt(i)<='z'){
                    smallCount++;
                }else if(line.charAt(i)>='A' && line.charAt(i)<='Z'){
                    bigCount++;
                }else if(line.charAt(i)>='0' && line.charAt(i)<='9'){
                    numberCount++;
                }
            }
            System.out.println("大写字母:"+bigCount);
            System.out.println("小写字母:"+smallCount);
            System.out.println("数字:"+numberCount);
        }
}

```

## StringBuilder(内容可变)
### 构造方法![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316144342853.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3MDc2OTQw,size_16,color_FFFFFF,t_70)
## StringBuilder的添加和反转方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316144715163.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3MDc2OTQw,size_16,color_FFFFFF,t_70)
sb.append("hello").append("100").append("qwe"); 因为返回对象本身 可以链式编程
## deleteCharAt(int index)方法
*  删除指定索引的字符
## delete(int index1 ,int index 2)方法
* 删除从索引1到索引2之间的字符

## insert(int index,String s)方法
* 在指定索引之前加上字符串s
## indexOf(String s,int index )方法
* 在字符串中查找指定字符串的位置返回首字符的index 如果没有找到就返回-1
* 第二个参数写上后可以从指定位置向后查找
## lastIndexOf(String s,int index)方法
* 用法与indexOf相同只是从后向前查找
## StringBuilder和String相互转换

1.SB转换为S  可以通过toString()方法

```java
 String s = sb.toString();
```

2.S转换为SB  可以通过SB得构造方法实现

```java
 StringBuilder sb = new StringBuilder(s);
```
## 案例

```java
package String.StringBuilder;
//需求  把一个数组int [] = {8,9,5,4}以[8,9,5,4]的形式输出
public class StringBuiderTest01 {
    public static void main(String[] args){
        int [] a= {1,8,9,7};
        String s = arrayToString(a);
        System.out.println(s);
    }
    public static String arrayToString(int[] a){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i=0;i<a.length;i++){
            if(a.length-1==i){
                sb.append(a[i]);
            }   else{
                sb.append(a[i]);
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
```
## StringBuffer是线程安全的StringBulider但是效率较低