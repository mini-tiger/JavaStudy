package basic.类;

public class super_Overload {
    public static void main(String args[]){
        Animal2 b = new Dog2(); // Dog 对象
        b.move(); //执行 Dog类的方法


        // 重载
        Overloading o = new Overloading();
        System.out.println(o.test());
        o.test(1);
        System.out.println(o.test(1,"test3"));
        System.out.println(o.test("test4",1));
    }
}


class Animal2{
    public void move(){
        System.out.println("动物可以移动");
    }
}

class Dog2 extends Animal2{
    public void move(){
        super.move(); // todo 应用super类的方法,调用父类方法
        System.out.println("狗可以跑和走");
    }
}


/*
// 重载
重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。

        每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。

        最常用的地方就是构造器的重载。
*/
class Overloading {
    public int test(){
        System.out.println("test1");
        return 1;
    }

    public void test(int a){ // todo 构造器重载
        System.out.println("test2");
    }

    //todo 以下两个参数类型顺序不同
    public String test(int a,String s){
        System.out.println("test3");
        return "returntest3";
    }

    public String test(String s,int a){
        System.out.println("test4");
        return "returntest4";
    }


}