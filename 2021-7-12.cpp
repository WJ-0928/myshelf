#include<stdio.h>

#include<iostream>
using namespace std;
#if 0
int a = 10;



//1、普通的命名空间
//{}表示一个范围（作用域）----命名空间实际就是一个有名字的只作用域
namespace N1//N1为命名空间的名称
{
    //命名空间中的内容，既可以定义变量，也可以定义函数
    int a=20;
    int b = 20;
    int Add(int left, int right)
    {
        return left + right;
    }
}

//2、命名空间可以嵌套----命名空间中可以定义命名空间
namespace N2//N2为命名空间的名称
{
    int a=30;
    int b=30;
    int Add(int left, int right)
    {
        return left + right;
    }
    namespace N3
    {
        int a=40;
        int b=40;
        int Sub(int left, int right)
        {
            return left - right;
        }
    }
}

int main()
{
    int a = 50;
    printf("%d\n", a);//main局部变量a
    printf("%d\n", ::a);//全局作用域中的a
    printf("%d\n", N1::a);//N1命名空间中的a
    printf("%d\n", N2::a);//N2命名空间中的a
    printf("%d\n", N2::N3::a);//N3命名空间中的a
    return 0;
}


namespace N1//N1为命名空间的名称
{
    //命名空间中的内容，既可以定义变量，也可以定义函数
    int a = 20;
    int b = 20;
    int Add(int left, int right)
    {
        return left + right;
    }
}

//在命名空间成员前增加N1：：（命名空间名字以及作用域限定符)
//using N1::成员名字；
using N1::b;//b相当于该文件的全局变量（实际上b是N1命名空间的成员）


//3、using namespace N1;
using namespace N1;
//优点：命名空间中的成员可以直接在当前文件中直接使用
//缺点：产生冲突的概率比较大
int main()
{
    printf("%d\n", N1::a);
    printf("%d\n", b);
    Add(a, b);
    return 0;

}


namespace N1//N1为命名空间的名称
{
    //命名空间中的内容，既可以定义变量，也可以定义函数
    int a = 20;
    int b = 20;
    int Add(int left, int right)
    {
        return left + right;
    }
}
namespace N1//N1为命名空间的名称
{
    //命名空间中的内容，既可以定义变量，也可以定义函数
    int c;
    int d;
    int Sub(int left, int right)
    {
        return left - right;
    }
}
#endif
#if 0
int main()
{
    int a;
    int b;
    int c;
    cin >> a >> b >> c;
    cout << 1 << " " << 1.23 << endl;
    cout << "hello world" << endl;
    return 0;
}


//函数原型：返回值类型  函数名字（参数列表）{}
//c语言编译器对函数原型检测要求不是非常严格
void Test1()
{
    printf("Test1()\n");
}
int main()
{
    Test1();
    return 0;
}


//能否给参数带上默认值
void Test1(int a=0)
{
    printf("%d\n",a);
}
int main()
{
    Test1();
    Test1(10);
    return 0;
}



//缺省参数分类：
//1、全缺省参数----所有参数都带有默认值

void Func(int a = 1, int b = 2, int c = 3)
{
    cout << a << " " << b << " " << c << endl;
}

//函数传参规则：从右往左一次来传递参数
//缺省参数匹配规则：从左到右来进行匹配
int main()
{
    Func();//打印1,2,3
    Func(10);//打印10,2,3
    Func(10, 20);//打印10,20,3
    Func(10, 20, 30);//打印10,20,30
}


void Func(int a , int b = 2, int c = 3)
{
    cout << a << " " << b << " " << c << endl;
}

//函数传参规则：从右往左一次来传递参数
//缺省参数匹配规则：从左到右来进行匹配
int main()
{
    //Func();//打印1,2,3
    //Func(10);//打印10,2,3
    //Func(10, 20);//打印10,20,3
    //Func(10, 20, 30);//打印10,20,30
    return 0;
}


//一不小心，声明和定义位置给出的默认值可能不一致，在调用的时候应该用哪个就会存在问题，在** 声明** 的位置给出比较好



//C语言中，函数名字必须唯一
int Add(int left, int right)
{
    return left + right;
}
double Add(double left, double right)
{
    return left + right;
}
int main()
{
    Add(1, 2);//int,int--->Add(int,int)
    Add(1.0, 2.0);//double,double--->ADD(double,double)
    Add('1', '2');//没有提供字符串类型的加法函数，char和int可以发生隐式类型的转化---->char,char-->隐式类型转化
    Add(1,(int) 2.0);//int double 转化：int int或者double  double，编译器不知道该如何让转化---直接显示类型转化
}


//C语言中，函数名字必须唯一
int Add(int left, int right)
{
    return left + right;
}
double Add(double left, double right)
{
    return left + right;
}
int main()
{
    Add(1, 2);//int,int--->Add(int,int)
    Add(1.0, 2.0);//double,double--->ADD(double,double)
    Add('1', '2');//没有提供字符串类型的加法函数，char和int可以发生隐式类型的转化---->char,char-->隐式类型转化
    Add(1, (int)2.0);//int double 转化：int int或者double  double，编译器不知道该如何让转化---直接显示类型转化
}



int main()
{
    int a = 10;
    int& ra = a;
    ra = 100;
    a = 200;
    return 0;
}


//
void Swap(int& left, int& right)
{

    cout << &left << endl;
    cout << &right << endl;
    int temp = left;
    left = right;
    right = temp;
}

int main()
{
    int a = 10;
    int b = 20;
    cout << &a << endl;
    cout << &b << endl;
    Swap(a, b);
    return 0;
}
#endif

int& Add(int left, int right)
{
    int temp = left + right;
    return temp;
}

int main()
{
    int& ret = Add(1, 2);
    Add(3, 4);
    return 0;
}