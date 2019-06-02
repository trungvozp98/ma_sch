package Entities;

/**
 *
 * @author trung98
 */
public class LopHocPhan {

    private String maLHP;
    private String maMH;
    private String thu;
    private String tiet;
    private String phonghoc;
    private String maGV;
    private String siso;
    private String hocky;
    private String namhoc;
    private String ngayBD;
    private String ngayKT;

    public LopHocPhan() {
    }

    public LopHocPhan(String maLHP, String maMH, String thu, String tiet, String phonghoc, String siso, String maGV, String ngayBD, String ngayKT, String hocky, String namhoc) {
        this.maLHP = maLHP;
        this.maMH = maMH;
        this.thu = thu;
        this.tiet = tiet;
        this.phonghoc = phonghoc;
        this.siso = siso;
        this.maGV = maGV;
        this.hocky = hocky;
        this.namhoc = namhoc;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public LopHocPhan(String maLHP, String hocky, String namhoc) {
        this.maLHP = maLHP;
        this.hocky = hocky;
        this.namhoc = namhoc;
    }

    public String getMaGV() {
        return maGV;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public String getPhonghoc() {
        return phonghoc;
    }

    public String getSiso() {
        return siso;
    }

    public String getThu() {
        return thu;
    }

    public String getTiet() {
        return tiet;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public void setPhonghoc(String phonghoc) {
        this.phonghoc = phonghoc;
    }

    public void setSiso(String siso) {
        this.siso = siso;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public void setTiet(String tiet) {
        this.tiet = tiet;
    }

    public String getHocky() {
        return hocky;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

}
