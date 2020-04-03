package org.mxframework.contentflow.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mx
 */
public class DateUtil {

    public static String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(new Date());
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.format(new Date());
        System.out.println(df.format(new Date()));
    }
}
