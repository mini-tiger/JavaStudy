package models.正则;

import java.util.regex.*;

class first{
    public static void main(String args[]){
        String content = "?*150+190";

        Pattern pattern1 = Pattern.compile("^[A-Za-z]*$"); // 是否包含英文
        Matcher m1 = pattern1.matcher(content);
        System.out.println(m1.find());

        String regEx="[\\?].*";// 是否包括?号
        Pattern   p   =   Pattern.compile(regEx);
        Matcher   m   =   p.matcher(content);
        System.out.println(m.find());


        String regEx3="[+|\\-|*|/|].*";// 是否包括+ - * / 任意一个
        Pattern   p3   =   Pattern.compile(regEx3);
        Matcher   m3   =   p3.matcher(content);
        System.out.println(m3.find());


        String regEx2="[`~!@#$%^&=|{}':;',\\[\\].<>/~！@#￥%……&（）——|{}【】‘；：”“’。，、？].*";
//        String reg="[A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern   p2   =   Pattern.compile(regEx2);
        Matcher   m2  =   p2.matcher(content);
        System.out.println(m2.find());

    }
}
