package test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IsValidDate {

    //判断时间格式是否合法
    public static boolean isValidDate(String time) {
        boolean result=true;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            format.setLenient(false);
            format.parse(time);
            //判断输入的HH和mm是否为0-9的数字
            String[] strings = time.split(":");
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < strings.length; i++){
                sb. append(strings[i]);
            }
            String s = sb.toString();
            char[] chars = s.toCharArray();
            for(char c:chars){
                int cc = c-'0';
                result = false;
                for(int i=0;i<=9;i++){
                    if (cc == i)
                        result = true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }
}
