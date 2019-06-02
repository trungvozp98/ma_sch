/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.DangKyDayDAO;
import DAO.GiaoVienDAO;
import DAO.IGiaoVienDAO;
import DAO.ILopHocPhanDAO;
import DAO.IMonHocDAO;
import DAO.LopHocPhanDAO;
import DAO.MonHocDAO;
import Date.DayNow;
import Entities.DangKyDay;
import Entities.DangKyHoc;
import Entities.GiaoVien;
import Entities.LopHocPhan;
import Entities.MonHoc;
import static GUI.DangKyHocPhan.toObjectsData;
import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trung98
 */
public class DangKyDayHocPhan extends javax.swing.JFrame {

    /**
     * Creates new form DangKyDayHocPhan
     */
    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private ArrayList<LopHocPhan> listLHP;
    private ArrayList<DangKyDay> listDKD;

    public DangKyDayHocPhan() {
        initComponents();
        model1 = (DefaultTableModel) bangdkhp.getModel();
        model2 = (DefaultTableModel) bangtthp.getModel();
        loadDangkyhocphan();
        loadThongtinhocphan();
        // txthocky.setText(MainGV.hocky);
        txtnamhoc.setText(MainGV.namhoc);
        txthocky.setFont(new Font(MainGV.hocky, 1, 14));
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

    public void resetMH() {

        int countmh = new DangKyDayDAO().summh(Main.nameLogin2, MainGV.hocky, MainGV.namhoc);
        String summh = Integer.toString(countmh);
        lb_sumsm.setText(summh);
    }

    public void loadDangkyhocphan() {
        model1.setRowCount(0);
        try {

            listLHP = new LopHocPhanDAO().getAll(MainGV.hocky, MainGV.namhoc);
            ILopHocPhanDAO lopHocPhanDAO = (ILopHocPhanDAO) Class.forName("DAO.LopHocPhanDAO").newInstance();

            for (LopHocPhan lhp : listLHP) {
                model1.addRow(toObjectsData(lhp));
            }
            bangdkhp.setModel(model1);
            resetMH();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadThongtinhocphan() {
        model2.setRowCount(0);
        try {

            listDKD = new DangKyDayDAO().findByMaGV(Main.nameLogin2, MainGV.hocky, MainGV.namhoc);

            for (DangKyDay dkd : listDKD) {

                LopHocPhan lhp = new LopHocPhanDAO().findByMaLHP(dkd.getMalhp(), dkd.getHocky(), dkd.getNamhoc());
                model2.addRow(toObjectsData(lhp));
            }

            bangtthp.setModel(model2);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void timkiem() {
        String tenmh = txtfindmh.getText();
        model1.setRowCount(0);
        ArrayList<LopHocPhan> listLHP = new LopHocPhanDAO().findByTenMH(tenmh, MainGV.hocky, MainGV.namhoc);
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

    public DangKyDay takeDKD() {
        DangKyDay dkd = null;
        String malhp = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0).toString();
        String magv = Main.nameLogin2;
        String hocky = MainGV.hocky;
        String namhoc = MainGV.namhoc;
        String ngdangky = new DayNow().dayNow();
        dkd = new DangKyDay(magv, malhp, hocky, namhoc, ngdangky);
        return dkd;
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
        txtfindmh = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txthocky = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnamhoc = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btn_xemmh1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangdkhp = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_dangky = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bangtthp = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lb_sumsm = new javax.swing.JLabel();
        btn_huydangky = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng ký lớp học phần");

        jPanel8.setBackground(java.awt.SystemColor.textHighlight);
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("DANH SÁCH LỚP HỌC PHẦN ");

        txthocky.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txthocky.setText("HỌC KỲ 1");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("NĂM HỌC");

        txtnamhoc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtnamhoc.setText("2018-2019");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ba.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_xemmh1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_xemmh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btn_xemmh1.setText("Xem");
        btn_xemmh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xemmh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtfindmh, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_xemmh1)))
                .addGap(108, 108, 108)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthocky, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnamhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfindmh, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_xemmh1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthocky, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnamhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        bangdkhp.getColumnModel().getColumn(1).setPreferredWidth(150);
        bangdkhp.getColumnModel().getColumn(2).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(3).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(4).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(5).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(6).setPreferredWidth(100);
        bangdkhp.getColumnModel().getColumn(7).setPreferredWidth(150);
        bangdkhp.getColumnModel().getColumn(8).setPreferredWidth(50);

        jPanel1.setBackground(java.awt.SystemColor.textHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(505, 505, 505)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addGap(359, 359, 359)
                .addComponent(btn_dangky)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_dangky, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Tổng số môn đã đăng ký : ");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lb_sumsm.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        btn_huydangky.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_huydangky.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/unre.png"))); // NOI18N
        btn_huydangky.setText("Hủy đăng ký");
        btn_huydangky.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_huydangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huydangkyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_sumsm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_huydangky)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_sumsm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_huydangky, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1251, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfindmhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindmhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfindmhMouseClicked

    private void txtfindmhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindmhMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfindmhMouseEntered

    private void txtfindmhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindmhKeyPressed

    }//GEN-LAST:event_txtfindmhKeyPressed

    private void txtfindmhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindmhKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
            timkiem();
        }
    }//GEN-LAST:event_txtfindmhKeyReleased

    private void bangdkhpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangdkhpMouseClicked

    }//GEN-LAST:event_bangdkhpMouseClicked

    private void btn_dangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangkyActionPerformed
        String malhp = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 0).toString();
        String TenMH = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 1).toString();
        String SoTC = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 2).toString();
        String Thu = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 3).toString();
        String Tiet = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 4).toString();
        String Phong = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 5).toString();
        String gv = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 7).toString();
        String Siso = bangdkhp.getValueAt(bangdkhp.getSelectedRow(), 8).toString();
        String magv = Main.nameLogin2;
        String hocky = MainGV.hocky;
        String namhoc = MainGV.namhoc;
        String ngdangky = new DayNow().dayNow();
        DangKyDay dkd = new DangKyDay(magv, malhp, hocky, namhoc, ngdangky);
        boolean cmalhp = new DangKyDayDAO().checkMaLHP(dkd);

        if (malhp.equals(null)) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn mã lớp học phần ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } else if (!gv.equals("")) {
            JOptionPane.showMessageDialog(this, "Đã có giáo viên đăng ký lớp học phần này ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (!cmalhp) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký lớp học phần này ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DangKyDay insert = new DangKyDayDAO().dangkyday(dkd);
            JOptionPane.showMessageDialog(this, "Bạn đã đăng ký thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadDangkyhocphan();
            loadThongtinhocphan();
            resetMH();
        }
    }//GEN-LAST:event_btn_dangkyActionPerformed

    private void btn_huydangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huydangkyActionPerformed
        String malhp = bangtthp.getValueAt(bangtthp.getSelectedRow(), 0).toString();
        String magv = Main.nameLogin2;
        String hocky = MainGV.hocky;
        String namhoc = MainGV.namhoc;
        DangKyDay dkd = new DangKyDay(magv, malhp, hocky, namhoc, "");

        int check = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa học phần này ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            new DangKyDayDAO().huydangky(dkd);
            new LopHocPhanDAO().updatemagv(malhp, hocky, namhoc);
            JOptionPane.showMessageDialog(this, "Xóa lớp học phần thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadThongtinhocphan();
            resetMH();

        }

    }//GEN-LAST:event_btn_huydangkyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_xemmh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xemmh1ActionPerformed
        timkiem();
    }//GEN-LAST:event_btn_xemmh1ActionPerformed

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
            java.util.logging.Logger.getLogger(DangKyDayHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyDayHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyDayHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyDayHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKyDayHocPhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangdkhp;
    private javax.swing.JTable bangtthp;
    private javax.swing.JButton btn_dangky;
    private javax.swing.JButton btn_huydangky;
    private javax.swing.JButton btn_xemmh1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel lb_sumsm;
    private javax.swing.JTextField txtfindmh;
    private javax.swing.JLabel txthocky;
    private javax.swing.JLabel txtnamhoc;
    // End of variables declaration//GEN-END:variables
}
