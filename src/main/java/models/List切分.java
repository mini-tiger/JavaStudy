package models;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

//https://juejin.im/post/5b1b9b9a5188257d37762630
public class List切分 {
    public static void main(String[] args){

        // 定义一个 10W 数量的集合
        List<String> largeList = new ArrayList<>();
        for(int i=0;i<11;i++){
            largeList.add("a");
        }
        // 拆分成 10 个集合数量 1 的大小的小集合
        List<List<String>> smallLists = Lists.partition(largeList, 1);
        System.out.println(smallLists);
    }
}
