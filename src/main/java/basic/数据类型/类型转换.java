package basic.数据类型;

public class 类型转换 {
    public static void main(String[] args) {
        char c1 = 'a';//定义一个char类型
        int i1 = c1;//char自动类型转换为int
        System.out.println("char自动类型转换为int后的值等于" + i1);
        char c2 = 'A';//定义一个char类型
        int i2 = c2 + 1;//char 类型和 int 类型计算
        System.out.println("char类型和int计算后的值等于" + i2);
        // 强制类型转换
        int i3 = 123;
        byte b = (byte) i3;//强制类型转换为byte
        System.out.println("int强制类型转换为byte后的值等于" + b);

//        整型、实型（常量）、字符型数据可以混合运算。运算中，不同类型的数据先转化为同一类型，然后进行运算
//        低  ------------------------------------>  高
//        byte,short,char—> int —> long—> float —> double

        byte a1=123;
        int a2=2222;
        char a3='人';
        System.out.println("混合运算" + a1+a2+a3);   // 1232222人
        System.out.println((int)-45.89f == -45); //强制类型　直接舍弃小数位，没有四舍五入，ｔｒｕｅ

    }
}