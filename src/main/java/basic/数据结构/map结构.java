package basic.数据结构;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class map结构 {

    public static void main(String[] args) {
        // 初始化，10W次赋值
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < 100000; i++)
            map.put(i, String.valueOf(i));

        /** 增强for循环，keySet迭代 */
        long start = System.currentTimeMillis();
        for (Integer key : map.keySet()) {
            map.get(key);
        }
        long end = System.currentTimeMillis();
        System.out.println("增强for循环，keySet迭代 -> " + (end - start) + " ms");

        /** 增强for循环，entrySet迭代 todo 占用资源最少*/
        start = System.currentTimeMillis();
        for (Entry<Integer, String> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("增强for循环，entrySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，keySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Integer> iterator = map.keySet().iterator();
        Integer key;
        while (iterator.hasNext()) {
            key = iterator.next();
            map.get(key);
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器，keySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，entrySet迭代 todo 速度最快*/
        start = System.currentTimeMillis();
        Iterator<Map.Entry<Integer,  String>> iterator1 = map.entrySet().iterator();
//        Map.Entry
//        描述在一个Map中的一个元素（键/值对）。是一个Map的内部类。
        Map.Entry<Integer, String> entry;
        while (iterator1.hasNext()) {
            entry = iterator1.next();
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();

        System.out.println("迭代器，entrySet迭代 -> " + (end - start) + " ms");
    }
}