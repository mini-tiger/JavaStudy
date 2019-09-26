package basic.类;
import java.io.*;

public class TjClass {  // 这里名字必须要和 文件名一样
    String name;
    int age;
    String designation;
    double salary;
    // Employee 类的构造器
    public TjClass(String name){
        this.name = name;
    }
    public TjClass(){ // todo 可以有多个 构造器， 参数不同， 不像python 只能__init__一个
        this.age = 123;
    }
    // 设置age的值
    public void empAge(int empAge){
        age =  empAge;
    }
    /* 设置designation的值*/
    public void empDesignation(String empDesig){
        designation = empDesig;
    }
    /* 设置salary的值*/
    public void empSalary(double empSalary){
        salary = empSalary;
    }
    /* 打印信息 */
    public void printEmployee(){
        System.out.println("------------------");
        System.out.println("名字:"+ name );
        System.out.println("年龄:" + age );
        System.out.println("职位:" + designation );
        System.out.println("薪水:" + salary);
    }
}