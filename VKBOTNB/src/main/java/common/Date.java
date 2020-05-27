package common;

import java.text.SimpleDateFormat;

public class Date {

    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return simpleDateFormat.format(new java.util.Date());
    }

    public static String getTimeNow(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(new java.util.Date());
    }
}
