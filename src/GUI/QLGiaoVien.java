/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Check.Check;
import DAO.DangKyDayDAO;
import DAO.GiaoVienDAO;
import DAO.KhoaDAO;
import Date.DayNow;
import Entities.GiaoVien;
import Entities.Khoa;
import Entities.Lop;
import Entities.SinhVien;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trung98
 */
public class QLGiaoVien extends javax.swing.JFrame {

    /**
     * Creates new form QLGiaoVien
     */
    private DefaultTableModel model;
    private DefaultComboBoxModel dcbmMakhoa1;
    private DefaultComboBoxModel dcbmMakhoa2;

    private ArrayList<SinhVien> listSV;
    private ArrayList<Khoa> listKhoa;
    private ArrayList<GiaoVien> listGV;

    public QLGiaoVien() {
        initComponents();
        model = (DefaultTableModel) bangdsgv.getModel();
        dcbmMakhoa1 = new DefaultComboBoxModel();
        dcbmMakhoa2 = new DefaultComboBoxModel();
        loadQLGV();
        resetFormQlgv();

    }

    public void setTongso() {
        txtcount.setText(Integer.toString(bangdsgv.getRowCount()));
    }

    public void loadQLGV() {

        loadDSGVAll();
        dcbmMakhoa1.addElement("-----Tất cả-----");
        ArrayList<Khoa> listK = new KhoaDAO().getAll();
        for (Khoa k : listK) {
            dcbmMakhoa1.addElement(k.getTenkhoa());
            dcbmMakhoa2.addElement(k.getMakhoa());
        }
        cbgv1.setModel(dcbmMakhoa1);
        cbgv3.setModel(dcbmMakhoa2);
        setTongso();

    }

    private GiaoVien takeGV() {
        GiaoVien gv = new GiaoVien();
        gv.setMaGV(txtmagv.getText());
        gv.setTenGV(txthotengv.getText());
        gv.setHocvi(cbgv2.getSelectedItem().toString());
        gv.setMucluong(txtluong.getText());
        gv.setMaKhoa((String) cbgv3.getSelectedItem());
        if (chbNam.isSelected() == true) {
            gv.setGioitinh("Nam");
        } else {
            gv.setGioitinh("Nữ");
        }
        DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        gv.setNgsinh(fm.format(dcngsinh.getDate()));
        gv.setNgvl(fm.format(dcngayvl.getDate()));
        return gv;
    }

    public boolean checkinfoGV() {
        Check c = new Check();

        if (!c.checkmaGV(txtmagv.getText())) {
            JOptionPane.showMessageDialog(this, "Mã giáo viên không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtmagv.setText("GV");
            txtmagv.requestFocus();
            return false;
        } /*else if (!c.checkHoten(txthotengv.getText())) {
            JOptionPane.showMessageDialog(this, "Họ tên giáo viên không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txthotengv.setText("");
            txthotengv.requestFocus();
            return false;
        } */ else if (((JTextField) dcngsinh.getDateEditor().getUiComponent()).getText().equals("") == true) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
            dcngsinh.requestFocus();
            return false;
        } else if (((JTextField) dcngayvl.getDateEditor().getUiComponent()).getText().equals("") == true) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm không được bỏ trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
            dcngayvl.requestFocus();
            return false;
        } else if (!c.checkDiffTwoDays(dcngsinh.getDate(), dcngayvl.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm phải lớn hơn ngày sinh", "Thông báo", JOptionPane.ERROR_MESSAGE);
            dcngayvl.requestFocus();
            return false;
        } else if (chbNam.isSelected() == false && chbNu.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Thông báo", JOptionPane.ERROR_MESSAGE);
            chbNam.requestFocus();
            return false;
        } else if (!c.checkLuong(txtluong.getText())) {
            JOptionPane.showMessageDialog(this, "Lương không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtluong.setText("");
            txtluong.requestFocus();
            return false;
        }
        return true;
    }

    public void loadDSGVAll() {
        model.setRowCount(0);
        listGV = new GiaoVienDAO().getAll();
        int i = 1;
        for (GiaoVien gv : listGV) {
            model.addRow(toObjectsDataGV(gv, i));
            i++;
        }
        bangdsgv.setModel(model);
        setTongso();

    }

    public Object[] toObjectsDataGV(GiaoVien gv, int i) {
        Object[] ob = {i, gv.getMaGV(), gv.getTenGV(), gv.getNgsinh(), gv.getGioitinh(), gv.getHocvi(), gv.getNgvl(), gv.getMucluong(), gv.getMaKhoa()};
        return ob;
    }

    public void loadDSGVByKhoa() {
        model.setRowCount(0);
        ArrayList<GiaoVien> listGV = new GiaoVienDAO().findByTenKhoa(cbgv1.getSelectedItem().toString());
        int i = 1;
        for (GiaoVien gv : listGV) {
            model.addRow(toObjectsDataGV(gv, i));
            i++;
        }
        bangdsgv.setModel(model);
        setTongso();
    }

    public void findGVByName() {
        listGV = new GiaoVienDAO().findSVByName(txtfindnamegv.getText());
        int i = 1;
        model.setRowCount(0);
        for (GiaoVien gv : listGV) {
            model.addRow(toObjectsDataGV(gv, i));
            i++;
        }
        bangdsgv.setModel(model);

    }

    public void resetFormQlgv() {
        txtmagv.setText("GV");
        txthotengv.setText("");
        cbgv2.setSelectedIndex(0);
        cbgv3.setSelectedIndex(0);
        dcngsinh.setDate(null);
        dcngayvl.setDate(null);
        chbNam.setSelected(true);
        txtluong.setText("");

        txtmagv.setEnabled(true);
        btnThemGV.setEnabled(true);
        btnSuaGV.setEnabled(false);
        btnXoaGV.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        bangdsgv = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        lbtrgkhoa = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        cbgv3 = new javax.swing.JComboBox<>();
        txtmagv = new javax.swing.JTextField();
        txthotengv = new javax.swing.JTextField();
        txtluong = new javax.swing.JTextField();
        chbNam = new javax.swing.JCheckBox();
        chbNu = new javax.swing.JCheckBox();
        dcngsinh = new com.toedter.calendar.JDateChooser();
        jLabel99 = new javax.swing.JLabel();
        dcngayvl = new com.toedter.calendar.JDateChooser();
        cbgv2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        btnThemGV = new javax.swing.JButton();
        btnSuaGV = new javax.swing.JButton();
        btnXoaGV = new javax.swing.JButton();
        btnLammoiGV = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        btnxemgv = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        cbgv1 = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        txtfindnamegv = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtcount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý giáo viên");

        bangdsgv.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã GV", "Họ Tên", "Ngày Sinh", "Giới tính ", "Học vị", "Ngày VL", "Lương", "Mã khoa"
            }
        ));
        bangdsgv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangdsgvMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(bangdsgv);
        bangdsgv.getColumnModel().getColumn(0).setPreferredWidth(50);
        bangdsgv.getColumnModel().getColumn(1).setPreferredWidth(80);
        bangdsgv.getColumnModel().getColumn(2).setPreferredWidth(200);
        bangdsgv.getColumnModel().getColumn(3).setPreferredWidth(120);
        bangdsgv.getColumnModel().getColumn(4).setPreferredWidth(80);
        bangdsgv.getColumnModel().getColumn(5).setPreferredWidth(100);
        bangdsgv.getColumnModel().getColumn(6).setPreferredWidth(120);
        bangdsgv.getColumnModel().getColumn(7).setPreferredWidth(150);
        bangdsgv.getColumnModel().getColumn(8).setPreferredWidth(80);

        jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel77.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel77.setText("DANH SÁCH GIÁO VIÊN");

        lbtrgkhoa.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addComponent(lbtrgkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel77)
            .addComponent(lbtrgkhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel38.setPreferredSize(new java.awt.Dimension(528, 346));

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel72.setText("Mã GV");

        jLabel79.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel79.setText("Họ tên");

        jLabel93.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel93.setText("Ngày sinh");

        jLabel94.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel94.setText("Giới tính");

        jLabel95.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel95.setText("Học vị");

        jLabel96.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel96.setText("Lương");

        jLabel97.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel97.setText("Mã khoa");

        jLabel98.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel98.setText("* Thông tin chi tiết");

        buttonGroup1.add(chbNam);
        chbNam.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        chbNam.setText("Nam");

        buttonGroup1.add(chbNu);
        chbNu.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        chbNu.setText("Nữ");

        dcngsinh.setDateFormatString("dd-MM-yyyy");

        jLabel99.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel99.setText("Ngày VL");

        dcngayvl.setDateFormatString("dd-MM-yyyy");

        cbgv2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbgv2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thạc sĩ", "Tiến sĩ", "P.GS", "GS" }));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("(VND)");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txthotengv, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbgv2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtmagv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbgv3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcngayvl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcngsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel38Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel38Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(txtluong, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcngsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmagv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthotengv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbgv2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbgv3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtluong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dcngayvl, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel39.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel39.setPreferredSize(new java.awt.Dimension(599, 98));

        btnThemGV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnThemGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/them (1).png"))); // NOI18N
        btnThemGV.setText("Insert");
        btnThemGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGVActionPerformed(evt);
            }
        });

        btnSuaGV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSuaGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/update_1.png"))); // NOI18N
        btnSuaGV.setText("Update");
        btnSuaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGVActionPerformed(evt);
            }
        });

        btnXoaGV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXoaGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete_1.png"))); // NOI18N
        btnXoaGV.setText("Delete");
        btnXoaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGVActionPerformed(evt);
            }
        });

        btnLammoiGV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLammoiGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh-button (1).png"))); // NOI18N
        btnLammoiGV.setText("Refresh ");
        btnLammoiGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiGVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(btnThemGV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaGV)
                .addGap(18, 18, 18)
                .addComponent(btnXoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLammoiGV)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemGV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 1336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel29.setBackground(java.awt.SystemColor.textHighlight);
        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnxemgv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnxemgv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btnxemgv.setText("Xem");
        btnxemgv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemgvActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel52.setText("Khoa");

        cbgv1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------Tất cả--------" }));
        cbgv1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbgv1ItemStateChanged(evt);
            }
        });
        cbgv1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbgv1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbgv1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbgv1MouseReleased(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel69.setText("* Tìm kiếm");

        txtfindnamegv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfindnamegvMouseClicked(evt);
            }
        });
        txtfindnamegv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfindnamegvKeyPressed(evt);
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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Tìm giáo viên");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbgv1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(411, 411, 411)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(txtfindnamegv, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnxemgv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbgv1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfindnamegv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxemgv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Tổng số :");

        txtcount.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtcount.setText("8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 674, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addComponent(txtcount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bangdsgvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangdsgvMouseClicked
        txtmagv.setText((String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 1));
        txthotengv.setText((String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 2));
        cbgv2.setSelectedItem((String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 5));
        cbgv3.setSelectedItem(bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 8));
        String sex = (String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 4);
        if (sex.equals("Nam")) {
            chbNam.setSelected(true);
        } else {
            chbNu.setSelected(true);
        }
        txtluong.setText((String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 7));

        String ngsinh = (String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 3);
        String ngvl = (String) bangdsgv.getValueAt(bangdsgv.getSelectedRow(), 6);

        dcngsinh.setDate(new DayNow().converttoDate(ngsinh));
        dcngayvl.setDate(new DayNow().converttoDate(ngvl));

        txtmagv.setEnabled(false);
        btnThemGV.setEnabled(false);
        btnSuaGV.setEnabled(true);
        btnXoaGV.setEnabled(true);
    }//GEN-LAST:event_bangdsgvMouseClicked

    private void btnThemGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGVActionPerformed
        if (!checkinfoGV()) {
            return;
        }
        boolean chmagv = new GiaoVienDAO().checkMaGV(txtmagv.getText());
        if (!chmagv) {
            JOptionPane.showMessageDialog(this, "Mã số này đã tồn tại\nHãy thử mã số khác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (new GiaoVienDAO().themGV(takeGV())) {
                JOptionPane.showMessageDialog(this, "Bạn đã thêm giáo viên thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDSGVAll();
                resetFormQlgv();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemGVActionPerformed

    private void btnSuaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGVActionPerformed
        boolean chmagv = new GiaoVienDAO().checkMaGV(txtmagv.getText());
        if (!checkinfoGV()) {
            return;
        } else {
            if (new GiaoVienDAO().suaGV(takeGV())) {
                JOptionPane.showMessageDialog(this, "Sửa giáo viên thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDSGVAll();
                resetFormQlgv();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSuaGVActionPerformed

    private void btnXoaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGVActionPerformed
        String magv = txtmagv.getText();
        int c = JOptionPane.showConfirmDialog(this, "Nếu xóa giáo viên này thì tất cả các thông tin liên quan đến giáo viên sẽ bị mất\nBạn có chắc chắn muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            GiaoVien gv = new GiaoVien();
            gv.setMaGV(magv);
            if (!new DangKyDayDAO().huyByMaGV(gv.getMaGV())) {
                JOptionPane.showMessageDialog(this, "Xóa lớp học phần thất bại");
            } else if (new GiaoVienDAO().xoaGV(gv)) {
                JOptionPane.showMessageDialog(this, "Bạn đã xóa giáo viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDSGVAll();
                resetFormQlgv();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaGVActionPerformed

    private void btnLammoiGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiGVActionPerformed
        resetFormQlgv();
    }//GEN-LAST:event_btnLammoiGVActionPerformed

    private void btnxemgvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemgvActionPerformed
        findGVByName();
    }//GEN-LAST:event_btnxemgvActionPerformed

    private void cbgv1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbgv1ItemStateChanged
        if (cbgv1.getSelectedIndex() == 0) {
            loadDSGVAll();
        } else {
            loadDSGVByKhoa();

        }
    }//GEN-LAST:event_cbgv1ItemStateChanged

    private void cbgv1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbgv1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbgv1MouseClicked

    private void cbgv1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbgv1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbgv1MousePressed

    private void cbgv1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbgv1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbgv1MouseReleased

    private void txtfindnamegvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindnamegvMouseClicked
        txtfindnamegv.setText("");
    }//GEN-LAST:event_txtfindnamegvMouseClicked

    private void txtfindnamegvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindnamegvKeyPressed
     
    }//GEN-LAST:event_txtfindnamegvKeyPressed

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
            java.util.logging.Logger.getLogger(QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLGiaoVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangdsgv;
    private javax.swing.JButton btnLammoiGV;
    private javax.swing.JButton btnSuaGV;
    private javax.swing.JButton btnThemGV;
    private javax.swing.JButton btnXoaGV;
    private javax.swing.JButton btnxemgv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbgv1;
    private javax.swing.JComboBox<String> cbgv2;
    private javax.swing.JComboBox<String> cbgv3;
    private javax.swing.JCheckBox chbNam;
    private javax.swing.JCheckBox chbNu;
    private com.toedter.calendar.JDateChooser dcngayvl;
    private com.toedter.calendar.JDateChooser dcngsinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbtrgkhoa;
    private javax.swing.JLabel txtcount;
    private javax.swing.JTextField txtfindnamegv;
    private javax.swing.JTextField txthotengv;
    private javax.swing.JTextField txtluong;
    private javax.swing.JTextField txtmagv;
    // End of variables declaration//GEN-END:variables
}
