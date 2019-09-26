package basic.类;

public class TjClassTest{ // 这里名字必须要和 文件名一样

    public static void main(String[] args){ // 可以执行的类 ，必须有main方法   void无返回
        /* 使用构造器创建两个对象 */
        TjClass empOne = new TjClass("RUNOOB1");
        TjClass empTwo = new TjClass("RUNOOB2");
        TjClass empThree  = new TjClass(); // 调用不传参数的构造器

        empThree.printEmployee();

        // 调用这两个对象的成员方法
        empOne.empAge(26);
        empOne.empDesignation("高级程序员");
        empOne.empSalary(1000);
        empOne.printEmployee();

        empTwo.empAge(21);
        empTwo.empDesignation("菜鸟程序员");
        empTwo.empSalary(500);
        empTwo.printEmployee();
    }
}