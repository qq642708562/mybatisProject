package test.utils;

import java.util.Arrays;

public class CompareAnswer {
    //如果答案顺序不同只要答案正确就为true
    public static boolean compareAnswer(String a,String b){
        int len1 = a.length();
        int len2 = b.length();
        String[] aa1 = new String[len1];
        String[] aa2 = new String[len2];
        for (int i = 0; i < len1; i++) {
            String temp = a.substring(i,i+1);
            aa1[i] = temp;
        }
        for (int i = 0; i < len2; i++) {
            String temp = b.substring(i,i+1);
            aa2[i] = temp;
        }
        //排序
        Arrays.sort(aa1);
        Arrays.sort(aa2);
        //比较
        return Arrays.equals(aa1,aa2);
    }
}
