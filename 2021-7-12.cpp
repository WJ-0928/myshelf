#include<stdio.h>

#include<iostream>
using namespace std;
#if 0
int a = 10;



//1����ͨ�������ռ�
//{}��ʾһ����Χ��������----�����ռ�ʵ�ʾ���һ�������ֵ�ֻ������
namespace N1//N1Ϊ�����ռ������
{
    //�����ռ��е����ݣ��ȿ��Զ��������Ҳ���Զ��庯��
    int a=20;
    int b = 20;
    int Add(int left, int right)
    {
        return left + right;
    }
}

//2�������ռ����Ƕ��----�����ռ��п��Զ��������ռ�
namespace N2//N2Ϊ�����ռ������
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
    printf("%d\n", a);//main�ֲ�����a
    printf("%d\n", ::a);//ȫ���������е�a
    printf("%d\n", N1::a);//N1�����ռ��е�a
    printf("%d\n", N2::a);//N2�����ռ��е�a
    printf("%d\n", N2::N3::a);//N3�����ռ��е�a
    return 0;
}


namespace N1//N1Ϊ�����ռ������
{
    //�����ռ��е����ݣ��ȿ��Զ��������Ҳ���Զ��庯��
    int a = 20;
    int b = 20;
    int Add(int left, int right)
    {
        return left + right;
    }
}

//�������ռ��Աǰ����N1�����������ռ������Լ��������޶���)
//using N1::��Ա���֣�
using N1::b;//b�൱�ڸ��ļ���ȫ�ֱ�����ʵ����b��N1�����ռ�ĳ�Ա��


//3��using namespace N1;
using namespace N1;
//�ŵ㣺�����ռ��еĳ�Ա����ֱ���ڵ�ǰ�ļ���ֱ��ʹ��
//ȱ�㣺������ͻ�ĸ��ʱȽϴ�
int main()
{
    printf("%d\n", N1::a);
    printf("%d\n", b);
    Add(a, b);
    return 0;

}


namespace N1//N1Ϊ�����ռ������
{
    //�����ռ��е����ݣ��ȿ��Զ��������Ҳ���Զ��庯��
    int a = 20;
    int b = 20;
    int Add(int left, int right)
    {
        return left + right;
    }
}
namespace N1//N1Ϊ�����ռ������
{
    //�����ռ��е����ݣ��ȿ��Զ��������Ҳ���Զ��庯��
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


//����ԭ�ͣ�����ֵ����  �������֣������б�{}
//c���Ա������Ժ���ԭ�ͼ��Ҫ���Ƿǳ��ϸ�
void Test1()
{
    printf("Test1()\n");
}
int main()
{
    Test1();
    return 0;
}


//�ܷ����������Ĭ��ֵ
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



//ȱʡ�������ࣺ
//1��ȫȱʡ����----���в���������Ĭ��ֵ

void Func(int a = 1, int b = 2, int c = 3)
{
    cout << a << " " << b << " " << c << endl;
}

//�������ι��򣺴�������һ�������ݲ���
//ȱʡ����ƥ����򣺴�����������ƥ��
int main()
{
    Func();//��ӡ1,2,3
    Func(10);//��ӡ10,2,3
    Func(10, 20);//��ӡ10,20,3
    Func(10, 20, 30);//��ӡ10,20,30
}


void Func(int a , int b = 2, int c = 3)
{
    cout << a << " " << b << " " << c << endl;
}

//�������ι��򣺴�������һ�������ݲ���
//ȱʡ����ƥ����򣺴�����������ƥ��
int main()
{
    //Func();//��ӡ1,2,3
    //Func(10);//��ӡ10,2,3
    //Func(10, 20);//��ӡ10,20,3
    //Func(10, 20, 30);//��ӡ10,20,30
    return 0;
}


//һ��С�ģ������Ͷ���λ�ø�����Ĭ��ֵ���ܲ�һ�£��ڵ��õ�ʱ��Ӧ�����ĸ��ͻ�������⣬��** ����** ��λ�ø����ȽϺ�



//C�����У��������ֱ���Ψһ
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
    Add('1', '2');//û���ṩ�ַ������͵ļӷ�������char��int���Է�����ʽ���͵�ת��---->char,char-->��ʽ����ת��
    Add(1,(int) 2.0);//int double ת����int int����double  double����������֪���������ת��---ֱ����ʾ����ת��
}


//C�����У��������ֱ���Ψһ
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
    Add('1', '2');//û���ṩ�ַ������͵ļӷ�������char��int���Է�����ʽ���͵�ת��---->char,char-->��ʽ����ת��
    Add(1, (int)2.0);//int double ת����int int����double  double����������֪���������ת��---ֱ����ʾ����ת��
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