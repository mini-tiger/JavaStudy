package basic.类;

import java.lang.*;

interface AnimalInterface { // 接口定义 类似golang ,要实现接口中所有方法
    public void eat();

}

interface AnimalInterface1 extends AnimalInterface{ // 接口继承
    public void travel();
}



public class 接口 implements AnimalInterface1{ // 实现接口

    public void eat(){
        System.out.println("Mammal eats");
    }

    public void travel(){
        System.out.println("Mammal travels");
    }

    public int noOfLegs(){
        return 0;
    }

    public static void main(String args[]){
        接口 m = new  接口();
        m.eat();
        m.travel();
    }
}