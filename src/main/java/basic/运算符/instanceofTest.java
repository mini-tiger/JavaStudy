package basic.运算符;

class Vehicle {
}


public class instanceofTest extends Vehicle {
    public static void main(String[] args) {
        Vehicle a = new instanceofTest();
        boolean result = a instanceof instanceofTest;
        System.out.println(result);
    }
}

