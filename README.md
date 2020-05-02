#### 根据IP查询地址
方案2：
MySQL，id是主键。ip是二级索引
用redis做缓存。

为了实现高效率的地址查找以及比对，将ip地址转化为long型存入redis进行处理



方案1：

1、把ip转换成long

原理（代码看test里面）：
举个简单例子先，比如有个ip地址是169.254.85.85，对应的二进制是10101001.11111110.1010101.1010101，
将小数点去掉后变为101010011111111010101011010101，可以看到除了最低的位置的整数位置没有发生变化，
其他三个位置的整数分别相对原来的位置左移了8位，16位，24位，而在二进制中每左移一位，就相当于*2，
也就是说对应的每段数字分别乘2的8次方，16次方以及24次方，
总体来说思路就是去掉ip地址的小数点后重新计算，上面这个ip地址转化为long型整数是2852017493。


原文链接：https://blog.csdn.net/a532672728/java/article/details/78197767


2、读取csv文件，存到treeMap里面,key是ipStrStartNum,value是ip地区
参考：https://www.cnblogs.com/leihupqrst/p/3508410.html   

3、把treeMap的key保存到ArrayList中，并且排序，用来二分查找
参考：https://www.cnblogs.com/yydcdut/p/3825721.html

4、二分法查找list得到key.key是ipStrStartNum
  
    
5、再用key去treeMap里面查找ip的地址。