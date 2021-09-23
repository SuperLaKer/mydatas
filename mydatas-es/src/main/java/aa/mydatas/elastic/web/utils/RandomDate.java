package aa.mydatas.elastic.web.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomDate {
    public static void main(String[] args) {
        String date = getDate();
        System.out.println(date);
    }

    public static String getDate() {

        String l = System.currentTimeMillis() + "";
        String substring = l.substring(l.length() - 2);
        long aLong = Long.parseLong(substring + substring + substring + substring + substring);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis() - aLong * 9);
        return format.format(date);
    }
}
