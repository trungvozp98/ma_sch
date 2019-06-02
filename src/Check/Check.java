/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Check;

import Date.DayNow;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author trung98
 */
public class Check {

    public Check() {
    }

    public static boolean checkmaSV(String id) {
        if (id == null || id.length() != 5 || !id.substring(0, 2).equals("SV")) {
            return false;
        } else {
            String quytac = "^[A-Z]{2}+[0-9]{3}$";
            Pattern pt = Pattern.compile(quytac);
            Matcher ma = pt.matcher(id);
            return ma.matches();
        }
    }

    public static boolean checkHoten(String name) {
        if (name == null || name.length() == 0) {
            return false;
        } else {
            String pattern = "[a-zA-Z]{3,40}";
            return name.matches(pattern);
        }

    }

    public static boolean checkQuequan(String home) {
        if (home == null || home.length() == 0) {
            return false;
        } else {
            String quytac = "^[a-zA-Z]";
            Pattern pt = Pattern.compile(quytac);
            Matcher ma = pt.matcher(home);
            return ma.matches();
        }

    }

    public static boolean checkDiachi(String add) {
        if (add == null || add.length() == 0) {
            return false;
        } else {
            String quytac = "^[a-zA-Z0-9]";
            Pattern pt = Pattern.compile(quytac);
            Matcher ma = pt.matcher(add);
            return ma.matches();
        }
    }

    public static boolean checkmaGV(String id) {
        if (id == null || id.length() != 5 || !id.substring(0, 2).equals("GV")) {
            return false;
        } else {
            String quytac = "^GV+[0-9]{3}";
            return id.matches(quytac);
        }
    }

    public static boolean checkLuong(String sal) {
        if (sal == null) {
            return false;
        } else {
            String quytac = "[0-9]{7,10}";
            return sal.matches(quytac);
        }
    }

    public static boolean checksodt(String sdt) {
        if (sdt == null || sdt.length() == 0) {
            return false;
        } else {
            String quytac = "^0+[0-9]{9}";
            return sdt.matches(quytac);
        }

    }

    public static boolean checkThu(String thu) {
        if (thu == null || thu.equals("")) {
            return false;
        } else {
            String[] t = {"2", "3", "4", "5", "6", "7", "CN"};
            for (int i = 0; i < t.length; i++) {
                if (thu.equals(t[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkMalop(String malop) {
        if (malop == null || malop.equals("")) {
            return false;
        } else {
            String quytac = "^L+[0-9]{2}";
            return malop.matches(quytac);
        }
    }

    public static boolean checkTenlop(String tenlop) {
        if (tenlop == null || tenlop.equals("")) {
            return false;
        } else {
            String quytac = "[A-Z]{4,6}+[0-9]{4}";
            return tenlop.matches(quytac);
        }
    }

    public static boolean checkPhong(String phong) {
        if (phong == null || phong.equals("")) {
            return false;
        } else {
            String quytac = "[A-Z]{1}+[0-9]{3,5}";
            return phong.matches(quytac);
        }
    }

    public static boolean checkTiet(String tiet) {
        if (tiet == null || tiet.equals("")) {
            return false;
        } else {
            String quytac = "[0-9]{1,5}";
            return tiet.matches(quytac);
        }
    }

    public static boolean checkSiso(String siso) {
        if (siso == null || siso.equals("")) {
            return false;
        } else {
            String quytac = "[0-9]{1,3}";
            return siso.matches(quytac);
        }
    }

    public static boolean checkDiffTwoDays(Date begin, Date end) {
        if (begin == null || end == null) {
            return false;
        } else if (end.getTime() < begin.getTime()) {
            return false;
        }
        return true;

    }

    public static boolean checkMakhoa(String makhoa) {
        if (makhoa == null || makhoa.length() == 0) {
            return false;
        } else {
            String quytac = "[A-Z]{2,7}";
            return makhoa.matches(quytac);
        }
    }

    public static boolean checkTenkhoa(String makhoa) {
        if (makhoa == null || makhoa.length() == 0) {
            return false;
        } else {
            String quytac = "[a-zA-Z]{4,40}";
            return makhoa.matches(quytac);
        }
    }

    // Ngày thành lập phải nhỏ hơn hoặc bằng ngày hiện tại
    public static boolean checkNgthlap(Date ngtlap) {
        if (ngtlap == null) {
            return false;
        } else {
            Date now = new DayNow().dayNow2();
            boolean c = new DayNow().dateMax(ngtlap, now);
            if (!c) {
                return false;
            }
        }
        return true;
    }
}
