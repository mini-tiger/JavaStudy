package basic.数据结构;

public class 数组 {
    public static void main(String[] args) {
        // 数组大小
        int size = 10;
        // 定义数组
        double[] myList = new double[size];
        myList[0] = 5.6;
        myList[1] = 4.5;
        myList[2] = 3.3;
        myList[3] = 13.2;
        myList[4] = 4.0;
        myList[5] = 34.33;
        myList[6] = 34.0;
        myList[7] = 45.45;
        myList[8] = 99.993;
        myList[9] = 11123;
        // 计算所有元素的总和
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += myList[i];
        }
        System.out.println("总和为： " + total);

        // todo 多维数组
        String s[][] = new String[2][];
        s[0] = new String[2];
        s[1] = new String[3];
        s[0][0] = new String("Good");
        s[0][1] = new String("Luck");
        s[1][0] = new String("to");
        s[1][1] = new String("you");
        s[1][2] = new String("!");

        int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        for(int a=0;a<arr.length;a++){//控制每个一维数组
            for(int i=0;i<arr[a].length;i++){//控制每个一维数组中的元素
                System.out.print(arr[a][i]+" ");//输出每个元素的值
            }
            System.out.println();//每执行完一个一维数组换行
        }
    }
}