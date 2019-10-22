package basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 深浅拷贝 {

    public static void main(String[] args) {
        System.out.println("======================this is MAP Object======================================");
        System.out.println("======================this is qian1copy======================================");
        qian1copy();
        System.out.println("======================this is qian2copy======================================");
        qian2copy();
        System.out.println("======================this is deepcopy======================================");
        deepcopy();
        System.out.println("======================this is Arrary Object======================================");
        ArrCopy();
    }

    public static void qian1copy(){
        Map<String,String> mapAA = new HashMap<>();
        mapAA.put("A", "A");
        mapAA.put("AA","AA");
        mapAA.put("AAA","AAA");
        System.out.println(mapAA);
        //用mapBB对象去引用mapAA
        Map<String,String> mapBB = mapAA;

        mapBB.put("B","B"); // 改变MAPBB ，MAPAA 随着一起改变，只是内存的引用

        System.out.println(mapBB);
        System.out.println(mapAA);
    }
    public static void qian2copy() {

        Map<String,String> mapAA = new HashMap<>();
        mapAA.put("A", "A");
        mapAA.put("AA","AA");
        mapAA.put("AAA","AAA");
        System.out.println(mapAA);

        Map<String,String> mapBB = new HashMap<>();
        //把mapAA的元素复制到mapBB中
        mapBB.putAll(mapAA); // Map中的putAll实现简单类型的复制
        mapBB.put("B","B");
        //打印出的mapAA应该不受影响
        System.out.println(mapAA);
        //打印出的mapBB应该多了元素B
        System.out.println(mapBB);
    }

    public static void deepcopy() {

        Map<String,String> mapAA = new HashMap<>();
        mapAA.put("A", "A");
        mapAA.put("AA","AA");
        mapAA.put("AAA","AAA");
        System.out.println(mapAA);

        HashMap<String,String> mapBB = new HashMap<>(); // 只有HashMap 使用putall 是深拷贝
        //把mapAA的元素复制到mapBB中
        mapBB.putAll(mapAA); // HashMap中的putAll，注意是 MAPBB.putall
        mapBB.put("B","B");
        //打印出的mapAA应该不受影响
        System.out.println(mapAA);
        //打印出的mapBB应该多了元素B
        System.out.println(mapBB);
    }
    public static void ArrCopy(){
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = Arrays.copyOfRange(a1, 0, 1);

        System.out.println(Arrays.toString(a1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(a2)); // [1]

/*_________________________________________________________________________*/
        int[] a3 = {1, 2, 3, 4, 5};
        int[] a4 = a1.clone();
        a4[0]=111;
        System.out.println(Arrays.toString(a3)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(a4)); // [1]

    }
}
