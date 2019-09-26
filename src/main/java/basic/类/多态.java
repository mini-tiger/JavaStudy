package basic.类;

public class 多态 {
    public static void main(String[] args) {
        show(new Cat3());  // 以 Cat 对象调用 show 方法
        show(new Dog3());  // 以 Dog 对象调用 show 方法

        Animal3 a = new Cat3();  // 向上转型
        a.eat();               // 调用的是 Cat 的 eat
        Cat3 c = (Cat3)a;        // 向下转型
        c.work();        // 调用的是 Cat 的 work
    }

    public static void show(Animal3 a)  {
        a.eat();
        // 类型判断
        if (a instanceof Cat3)  {  // 猫做的事情
            Cat3 c = (Cat3)a;
            c.work();
        } else if (a instanceof Dog3) { // 狗做的事情
            Dog3 c = (Dog3)a;
            c.work();
        }
    }
}

abstract class Animal3 {
    abstract void eat();
}

class Cat3 extends Animal3 {
    public void eat() {
        System.out.println("吃鱼");
    }
    public void work() {
        System.out.println("抓老鼠");
    }
}

class Dog3 extends Animal3 {
    public void eat() {
        System.out.println("吃骨头");
    }
    public void work() {
        System.out.println("看家");
    }
}