/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Check.Check;
import DAO.DangKyHocDAO;
import DAO.DiemDAO;
import DAO.GiaoVienDAO;
import DAO.IGiaoVienDAO;
import DAO.ILopHocPhanDAO;
import DAO.IMonHocDAO;
import DAO.LopHocPhanDAO;
import DAO.MonHocDAO;
import DB_Connect.DBConnect;
import Date.DayNow;
import Entities.DangKyHoc;
import Entities.Diem;
import Entities.GiaoVien;
import Entities.LopHocPhan;
import Entities.MonHoc;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trung98
 */
public class DangKyHocPhan extends javax.swing.JFrame {

    /**
     * Creates new form DangKyHoc
     */
    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private ArrayList<LopHocPhan> listLHP;
    private ArrayList<DangKyHoc> listDKH;
    
    public DangKyHocPhan() {
        initComponents();
        model1 = (DefaultTableModel) bangdkhp.getModel();
        model2 = (DefaultTableModel) bangtthp.getModel();
    }
    
    public void loadThongtinhocphan() {
        model2.setRowCount(0);
        try {
            // listDKH = new DangKyHocDAO().getAll();

            ArrayList<Entities.DangKyHoc> listDKH = new DangKyHocDAO().findByMaSV(Main.nameLogin, cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
            // Mảng động ArrayList list đã có đối tượng(LopHocPhan)
            for (Entities.DangKyHoc dkh : listDKH) {
                LopHocPhan lhp = new LopHocPhanDAO().findByMaLHP(dkh.getMaLHP(), dkh.getHocKy(), dkh.getNamHoc());
                model2.addRow(toObjectsData(lhp));
            }
            //ILopHocPhanDAO lopHocPhanDAO = (ILopHocPhanDAO) Class.forName("DAO.LopHocPhanDAO").newInstance();

            bangtthp.setModel(model2);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void loadDangkyhocphan() {
        model1.setRowCount(0);
        try {
            
            listLHP = new LopHocPhanDAO().getAll(cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
            // Mảng động ArrayList list đã có đối tượng(LopHocPhan)
            ILopHocPhanDAO lopHocPhanDAO = (ILopHocPhanDAO) Class.forName("DAO.LopHocPhanDAO").newInstance();
            
            for (LopHocPhan lhp : listLHP) {
                model1.addRow(toObjectsData(lhp));
            }
            bangdkhp.setModel(model1);
            changeTC_Mon();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int tongsoTC() {
        listDKH = new DangKyHocDAO().findByMaSV(Main.nameLogin, cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
        int sum = 0;
        for (DangKyHoc d : listDKH) {
            LopHocPhan lhp = new LopHocPhanDAO().findByMaLHP(d.getMaLHP(), cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
            MonHoc mh = new MonHocDAO().findByMaMH(listLHP.get(0).getMaMH());
            sum += Integer.parseInt(mh.getSoTC());
        }
        return sum;
    }
    
    public int sumSoMon() {
        int i = new DangKyHocDAO().tongSoMon(Main.nameLogin, cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
        return i;
    }
    
    public void changeTC_Mon() {
        String str = Integer.toString(tongsoTC());
        lb_sumsotc.setText(str);
        String s = Integer.toString(sumSoMon()); // function sumSoMon() have problems
        lb_sumsm.setText(s);
    }
    
    public static Object[] toObjectsData(LopHocPhan lhp) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        IMonHocDAO monHocDAO = (IMonHocDAO) Class.forName("DAO.MonHocDAO").newInstance();
        MonHoc mh = new MonHocDAO().findByMaMH(lhp.getMaMH());
        IGiaoVienDAO giaovienDAO = (IGiaoVienDAO) Class.forName("DAO.GiaoVienDAO").newInstance();
        String tengv;
        if (lhp.getMaGV() == null) {
            tengv = "";
        } else {
            GiaoVien gv = new GiaoVienDAO().findByMaGV(lhp.getMaGV());
            tengv = gv.getTenGV();
        }
        
        Object[] objectsData = {lhp.getMaLHP(), mh.getTenMH(), mh.getSoTC(), mh.getMaKhoa(), lhp.getThu(), lhp.getTiet(), lhp.getPhonghoc(), tengv, lhp.getSiso()};
        return objectsData;
    }
    
    public void timkiem() {
        String tenmh = txtfindmh.getText();
        model1.setRowCount(0);
        ArrayList<LopHocPhan> listLHP = new LopHocPhanDAO().findByTenMH(tenmh, cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
        for (LopHocPhan lopHocPhan : listLHP) {
            try {
                model1.addRow(toObjectsData(lopHocPhan));
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bangdkhp.setModel(model1);
        
    }
    
    public boolean checkInfoDKHP() {
        String MaLHP = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0).toString();
        if (MaLHP.equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn học phần");
            return false;
        }
        return true;
    }
    
    public DangKyHoc takeinfo() {
        String masv = Main.nameLogin;
        String malhp = (String) bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0);
        String hocky = cb_hockylhp.getSelectedItem().toString();
        String namhoc = cb_namhoclhp.getSelectedItem().toString();
        String ngdangky = new DayNow().dayNow();
        DangKyHoc dkh = new DangKyHoc(masv, malhp, hocky, namhoc, ngdangky);
        
        return dkh;
    }
    
    public boolean checkinfoLHP() {
        
        String malhp = (String) bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0);
        String hocky = cb_hockylhp.getSelectedItem().toString();
        String namhoc = cb_namhoclhp.getSelectedItem().toString();
        
        LopHocPhan lhp = new LopHocPhanDAO().findByMaLHP(malhp, hocky, namhoc);
        
        boolean cmalhp = new DangKyHocDAO().checkMaLHP_DKD(takeinfo());
        boolean cmamh = new DangKyHocDAO().checkMH(takeinfo(), lhp.getMaMH());
        if (!cmalhp) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký lớp học phần này", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!cmamh) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký môn học này", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cb_hockylhp = new javax.swing.JComboBox<>();
        cb_namhoclhp = new javax.swing.JComboBox<>();
        btn_xemhp = new javax.swing.JButton();
        txtfindmh = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_xemmh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangdkhp = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_dangky = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        lb_siso = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bangtthp = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lb_sumsotc = new javax.swing.JLabel();
        lb_sumsm = new javax.swing.JLabel();
        btn_huydangky = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng ký học phần");

        jPanel8.setBackground(java.awt.SystemColor.textHighlight);
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Học kỳ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Năm học");

        cb_hockylhp.setMaximumRowCount(3);
        cb_hockylhp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Học kỳ 1", "Học kỳ 2", "Học kỳ 3", " " }));
        cb_hockylhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_hockylhpActionPerformed(evt);
            }
        });

        cb_namhoclhp.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cb_namhoclhp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016-2017", "2017-2018", "2018-2019" }));
        cb_namhoclhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namhoclhpActionPerformed(evt);
            }
        });

        btn_xemhp.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_xemhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btn_xemhp.setText("Xem");
        btn_xemhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xemhpActionPerformed(evt);
            }
        });

        txtfindmh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfindmhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtfindmhMouseEntered(evt);
            }
        });
        txtfindmh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfindmhKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfindmhKeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel28.setText("Tìm môn học");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("DANH SÁCH LỚP HỌC PHẦN ");

        btn_xemmh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_xemmh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btn_xemmh.setText("Search");
        btn_xemmh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xemmhActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ba.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_hockylhp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cb_namhoclhp, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xemhp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(309, 309, 309)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtfindmh, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_xemmh, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(28, 28, 28))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_hockylhp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_namhoclhp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xemhp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfindmh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xemmh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bangdkhp.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bangdkhp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MãLHP", "Tên môn học", "Số TC", "Mã Khoa", "Thứ", "Tiết", "Phòng", "Giảng viên", "Sỉ số"
            }
        ));
        bangdkhp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangdkhpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bangdkhp);
        bangdkhp.getColumnModel().getColumn(0).setPreferredWidth(50);
        bangdkhp.getColumnModel().getColumn(1).setPreferredWidth(160);
        bangdkhp.getColumnModel().getColumn(2).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(3).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(4).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(5).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(6).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(7).setPreferredWidth(150);
        bangdkhp.getColumnModel().getColumn(8).setPreferredWidth(40);

        jPanel1.setBackground(java.awt.SystemColor.textHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THÔNG TIN ĐĂNG KÝ HỌC PHẦN ");

        btn_dangky.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_dangky.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/regi.png"))); // NOI18N
        btn_dangky.setText("Đăng ký");
        btn_dangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangkyActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel27.setText("Sĩ số hiện tại :");

        lb_siso.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addGap(117, 117, 117)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_siso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btn_dangky)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(lb_siso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_dangky, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        bangtthp.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bangtthp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MãLHP", "Tên MH", "Số TC", "Mã Khoa", "Thứ ", "Tiết", "Phòng ", "Giảng viên", "Sỉ số"
            }
        ));
        jScrollPane2.setViewportView(bangtthp);
        bangtthp.getColumnModel().getColumn(0).setPreferredWidth(50);
        bangtthp.getColumnModel().getColumn(1).setPreferredWidth(150);
        bangtthp.getColumnModel().getColumn(2).setPreferredWidth(100);
        bangtthp.getColumnModel().getColumn(3).setPreferredWidth(100);
        bangtthp.getColumnModel().getColumn(4).setPreferredWidth(100);
        bangtthp.getColumnModel().getColumn(5).setPreferredWidth(100);
        bangtthp.getColumnModel().getColumn(6).setPreferredWidth(150);
        bangtthp.getColumnModel().getColumn(7).setPreferredWidth(100);

        jPanel14.setBackground(java.awt.SystemColor.textHighlight);
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("- Tổng số tín chỉ đã đăng ký  :");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel24.setText("- Tổng số môn đã đăng ký    :");

        lb_sumsotc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        lb_sumsm.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        btn_huydangky.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_huydangky.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/unre.png"))); // NOI18N
        btn_huydangky.setText("Hủy đăng ký");
        btn_huydangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huydangkyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_sumsm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_sumsotc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_huydangky)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lb_sumsotc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lb_sumsm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_huydangky)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_hockylhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_hockylhpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_hockylhpActionPerformed

    private void cb_namhoclhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namhoclhpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_namhoclhpActionPerformed

    private void btn_xemhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xemhpActionPerformed
        loadDangkyhocphan();
        loadThongtinhocphan();
    }//GEN-LAST:event_btn_xemhpActionPerformed

    private void txtfindmhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindmhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfindmhMouseClicked

    private void txtfindmhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindmhMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfindmhMouseEntered

    private void txtfindmhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindmhKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
            timkiem();
        }
    }//GEN-LAST:event_txtfindmhKeyReleased

    private void bangdkhpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangdkhpMouseClicked
        String maLHP = (String) bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0);
        listDKH = new DangKyHocDAO().checkMaLHP(maLHP, cb_hockylhp.getSelectedItem().toString(), cb_namhoclhp.getSelectedItem().toString());
        lb_siso.setText(Integer.toString(listDKH.size()));
    }//GEN-LAST:event_bangdkhpMouseClicked

    private void btn_dangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangkyActionPerformed
        String malhp = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0).toString();
        String TenMH = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 1).toString();
        String SoTC = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 2).toString();
        String Thu = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 3).toString();
        String Tiet = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 4).toString();
        String Phong = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 5).toString();
        String Giangvien = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 6).toString();
        String Siso = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 8).toString();
        String masv = Main.nameLogin;
        String hocky = cb_hockylhp.getSelectedItem().toString();
        String namhoc = cb_namhoclhp.getSelectedItem().toString();
        String ngdangky = new DayNow().dayNow();
        
        listDKH = new DangKyHocDAO().checkMaLHP(malhp, hocky, namhoc);
        LopHocPhan lhp = new LopHocPhanDAO().findByMaLHP(malhp, hocky, namhoc);
        
        boolean cmalhp = new DangKyHocDAO().checkMaLHP_DKD(takeinfo());
        boolean cmamh = new DangKyHocDAO().checkMH(takeinfo(), lhp.getMaMH());

        //Lấy danh sách DangKyHoc của sinh viên x 
        ArrayList<DangKyHoc> listSV = new DangKyHocDAO().findByMaSV(Main.nameLogin, hocky, namhoc);
        
        DangKyHoc dkh = new DangKyHoc(masv, malhp, hocky, namhoc, ngdangky);
        
        boolean check = new DangKyHocDAO().checkMaMH_TRUOC(lhp.getMaMH(), masv, hocky, namhoc);
        
        if (!check) {
            JOptionPane.showMessageDialog(this, "Môn học này có môn học trước mà bạn chưa học ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (!cmalhp) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký lớp học phần này ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (!cmamh) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký môn học này ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (tongsoTC() + Integer.parseInt(SoTC) > 30) {
            JOptionPane.showMessageDialog(this, "Trong một học kỳ bạn chỉ đăng ký tối ta 30 tín chỉ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (listDKH.size() >= Integer.parseInt(Siso)) {
            JOptionPane.showMessageDialog(this, "Lớp học phần này đã đầy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            boolean insert = new DangKyHocDAO().dangkyhoc(dkh);
            Diem d = new Diem(masv, malhp, hocky, namhoc, 0, 0, 0);
            boolean insertDiem= new DiemDAO().insertDiem(d);
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadThongtinhocphan();
            changeTC_Mon();
        }
    }//GEN-LAST:event_btn_dangkyActionPerformed

    private void btn_huydangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huydangkyActionPerformed
        String maLHP = bangtthp.getValueAt(bangtthp.getSelectedRow(), 0).toString();
        String masv = Main.nameLogin;
        String hocky = cb_hockylhp.getSelectedItem().toString();
        String namhoc = cb_namhoclhp.getSelectedItem().toString();
        String ngHuy = new DayNow().dayNow();
        DangKyHoc dkh = new DangKyHoc(masv, maLHP, hocky, namhoc, ngHuy);
        
        int check = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa học phần này ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            new DiemDAO().huyByMaSV(dkh);
            new DangKyHocDAO().huydangky(dkh);
            JOptionPane.showMessageDialog(this, "Bạn đã xóa lớp học phần thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadThongtinhocphan();
            changeTC_Mon();
            
        }
    }//GEN-LAST:event_btn_huydangkyActionPerformed

    private void txtfindmhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindmhKeyPressed

    }//GEN-LAST:event_txtfindmhKeyPressed

    private void btn_xemmhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xemmhActionPerformed
        timkiem();
    }//GEN-LAST:event_btn_xemmhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangKyHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKyHocPhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangdkhp;
    private javax.swing.JTable bangtthp;
    private javax.swing.JButton btn_dangky;
    private javax.swing.JButton btn_huydangky;
    private javax.swing.JButton btn_xemhp;
    private javax.swing.JButton btn_xemmh;
    private javax.swing.JComboBox<String> cb_hockylhp;
    private javax.swing.JComboBox<String> cb_namhoclhp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_siso;
    private javax.swing.JLabel lb_sumsm;
    private javax.swing.JLabel lb_sumsotc;
    private javax.swing.JTextField txtfindmh;
    // End of variables declaration//GEN-END:variables
}
