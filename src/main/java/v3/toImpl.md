## toImpl

### 语法

1. 标识符（由数字、字母和下划线组成的串，但必须以字母开头、且不能以下划线结尾的串）

2. ElseStmt
    现只支持：（1）纯if（2）if-else

3. DeclStmt -> Type VarList ;
    VarList 现只支持一个变量

4. 数组：多维数组

### 中间代码

1 常见的三地址指令：  
  
    (1) [5] x = y op z
    (2) [4] x = op y
    (3) [3] x = y
    (4) [2] goto L
    (5) [4] if x goto L
        [5] if false x goto L
    (6) [6] if x rel y goto L
    (7) 过程调用与返回
    (8) [3] x = y[i]
        [3] x[i] = y
    (9) [3] x = &y, x = *y, *x = y

四元式指令集：  
  
(1) op  y   z   x
(2) op  y   _   x
(3) =   y   _   x
(4) Jump    _   _   L
(5) ???????
(6) ???????
(8) Access  y   i   x
    SetElem i   y   x

2. do语句的行号：从起始算起

3. 符号表：存在bug（v2的case2通不过） && if语句的跳转有问题


### 代码生成 & 虚拟机运行

1. 思考与数组有关的
    （1）多维数组
    （2）声明：类型，容量（ok）
    （3）访问：是否越界；是否为不合理的值
    （4）赋值：访问 + 值是否符合声明的基本类型
    