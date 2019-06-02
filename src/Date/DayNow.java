/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Date;

import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DayNow {

    public String dayNow() {
        java.util.Date now = new java.util.Date();
        Locale local = new Locale("vi", "VI");
        SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd hh:mm ", local);
        String NgayDangKy = sfm.format(now);
        return NgayDangKy;
    }

    public Date dayNow2() {
        java.util.Date now = new java.util.Date();

        return convertFromJAVADateToSQLDate(now);
    }

    public Date converttoDate(String str) {
        Date date = null;
        String pattern = "yyyy-MM-dd";
        try {
            date = convertFromJAVADateToSQLDate(new SimpleDateFormat(pattern).parse(str));
        } catch (ParseException ex) {
            Logger.getLogger(DayNow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public String converttoString(Date date) {
        return new SimpleDateFormat().format(date);
    }

    public long calAge(Date date) {

        java.util.Date now = new java.util.Date();
        Date date1 = convertFromJAVADateToSQLDate(date);
        Date date2 = convertFromJAVADateToSQLDate(now);
        long getDiff = date2.getTime() - date1.getTime();
        long getDaysDiff = (getDiff / (24 * 60 * 60 * 1000)) / 365;

        return getDaysDiff;

    }

    public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }

    public static boolean dateMax(java.util.Date begin, java.util.Date end) {
        if (end.getTime() < begin.getTime()) {
            return false;
        }
        return true;
    }


}
