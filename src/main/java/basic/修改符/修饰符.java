package basic.修改符;
import java.util.*;
public class 修饰符 {
    private static int numInstances = 0;//静态变量

    protected static int getCount() {
        return numInstances;
    }

    private static void addInstance() {
        numInstances++;
    }

    // 构造方法
    修饰符() {
        修饰符.addInstance();
    }

    public static void pp() {
        System.out.println("Starting with " +
                修饰符.getCount() + " instances");

        for (int i = 0; i < 500; ++i) {
            new 修饰符();
        }

        System.out.println("创建过 " +
                修饰符.getCount() + " instances");

    }

    public static void main(String[] arguments) throws InterruptedException {
        pp();
        numInstances=0;

        // 可以不创建实例 ，直接 调用
        System.out.println("Starting with " +
                修饰符.getCount() + " instances");
        for (int i = 0; i < 500; ++i) {
            addInstance(); // 直接 调用
        }

        System.out.println("创建过 " +
                修饰符.getCount() + " instances");

        // todo 测试线程同步
//        threadpp();
    }
    public static void threadpp() throws InterruptedException {
        线程同步 r = new 线程同步();
        startThread(r);

        Thread.sleep(1000*3);   // 休眠3秒
        endThread(r);
    }

//    synchronized 关键字声明的方法同一时间只能被一个线程访问。synchronized 修饰符可以应用于四个访问修饰符
        public synchronized void showDetails(){

        }

//    todo volatile 修饰的成员变量在每次被线程访问时，都强制从共享内存中重新读取该成员变量的值。
//    而且，当成员变量发生变化时，会强制线程将变化值回写到共享内存。这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
        public static void startThread(线程同步 r){

            r.run();

        }

        public static void endThread(线程同步 r) {

            r.stop();
    }
}