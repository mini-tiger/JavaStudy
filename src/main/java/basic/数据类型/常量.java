package basic.数据类型;


public class 常量 {


    public static void main(String[] args) {
        final double PI = 3.1415927; // final 与golang const一样
        final int decimal = 100;
        final int octal = 0144;
        final int hexa = 0x64;
        System.out.println("PI:" + PI);
        System.out.println(decimal);
        System.out.println(octal);// 8进制
        System.out.println(hexa);// 16进制
    }

}
