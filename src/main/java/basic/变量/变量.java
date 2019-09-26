package basic.变量;

public class 变量 {
    static int allClicks=0;    // 类变量

    String str="hello world";  // 实例变量

    public int method(){

        int i =0;  // 局部变量
        return i;
    }
    public static void main(String[] args) {
        变量 a = new 变量();
        System.out.println(a.str);
        System.out.println(allClicks);
        System.out.println(a.method());
    }
}
