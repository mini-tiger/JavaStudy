package basic.类;

/*
访问权限不能比父类中被重写的方法的访问权限更低。例如：如果父类的一个方法被声明为 public，那么在子类中重写该方法就不能声明为 protected。

        父类的成员方法只能被它的子类重写。

        声明为 final 的方法不能被重写。

        声明为 static 的方法不能被重写，但是能够被再次声明。

        子类和父类在同一个包中，那么子类可以重写父类所有方法，除了声明为 private 和 final 的方法。

        子类和父类不在同一个包中，那么子类只能够重写父类的声明为 public 和 protected 的非 final 方法。
*/

public class 重写 {
    public static void main(String args[]) {
        Animal1 a = new Animal1(); // Animal 对象
        Dog b = new Dog(); // Dog 对象

        a.move();// 执行 Animal 类的方法
        b.move();//执行 Dog 类的方法
        b.bark();
    }
}

class Animal1{
    public void move(){
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal1{
    @Override // 可加 可不加，代表重写
    public void move(){
        System.out.println("狗可以跑和走");
    }
    public void bark(){
        System.out.println("狗可以吠叫");
    }
}

