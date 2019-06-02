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
import DAO.KhoaDAO;
import DAO.LopDAO;
import DAO.PhuHuynhDAO;
import DAO.SinhVienDAO;
import Date.DayNow;
import Entities.GiaoVien;
import Entities.Khoa;
import Entities.Lop;
import Entities.SinhVien;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Võ Đại Nhật Trung
 */
public class QLSinhVien extends javax.swing.JFrame {

    /**
     * Creates new form QLSinhVien
     */
    private DefaultTableModel model;
    private DefaultComboBoxModel dcbmKhoa;
    private DefaultComboBoxModel dcbmTenlop;
    private DefaultComboBoxModel dcbmMalop;
    private ArrayList<SinhVien> listSV;
    private ArrayList<Khoa> listKhoa;
    private ArrayList<Lop> listLop;

    public QLSinhVien() {
        initComponents();

        model = (DefaultTableModel) bangdssv.getModel();
        dcbmKhoa = new DefaultComboBoxModel();
        dcbmTenlop = new DefaultComboBoxModel();
        dcbmMalop = new DefaultComboBoxModel();
        loadDataQLSV();
        resetFormQlsv();
    }

    public void loadDataQLSV() {
        loadAllSV();
        listKhoa = new KhoaDAO().getAll();
        dcbmKhoa.addElement("-----Tất cả-----");
        for (Khoa k : listKhoa) {
            dcbmKhoa.addElement(k.getTenkhoa());
        }
        cbkhoa.setModel(dcbmKhoa);

        // Đổ dữ liệu vào cbTenLop and cbMalop
        listLop = new LopDAO().getAll();
        dcbmTenlop.addElement("-----Tất cả-----");
        for (Lop l : listLop) {
            dcbmTenlop.addElement(l.getTenlop());
            dcbmMalop.addElement(l.getMalop());

        }
        cbtenlop.setModel(dcbmTenlop);
        cbmalop.setModel(dcbmMalop);
    }

    private SinhVien takeSV() {

        SinhVien sv = new SinhVien();

        sv.setMaSV(txtmasv.getText());
        sv.setHoTen(txthoten.getText());

        sv.setNgSinh(new DayNow().converttoString(new DayNow().convertFromJAVADateToSQLDate(dcngsinh.getDate())));

        if (chbNam.isSelected() == true) {
            sv.setGioitinh("Nam");
        } else {
            sv.setGioitinh("Nữ");
        }
        sv.setNoisinh(txtquequan.getText());
        sv.setMaLop(cbmalop.getSelectedItem().toString());
        sv.setSdt(txtsdt.getText());
        return sv;

    }

    public Object[] toObjectsDataSV(SinhVien sv, int i) {
        Object[] ob = {i, sv.getMaSV(), sv.getHoTen(), sv.getNgSinh(), sv.getGioitinh(), sv.getNoisinh(), sv.getSdt(), sv.getMaLop()};
        return ob;
    }

    public void loadDSSVByKhoa() {
        model.setRowCount(0);
        if (cbkhoa.getSelectedIndex() == 0) {
            loadAllSV();
            /* dcbmTenlop.removeAllElements();
            ArrayList<Lop> listLop = new LopDAO().getAll();
            for (Lop lop : listLop) {
                dcbmTenlop.addElement(lop.getTenlop());
            }
            cbtenlop.setModel(dcbmTenlop);*/
            txtsum.setText(Integer.toString(bangdssv.getRowCount()));
        } else {
            listSV = new SinhVienDAO().findByTenKhoa(cbkhoa.getSelectedItem().toString());
            int i = 1;
            for (SinhVien sv : listSV) {
                model.addRow(toObjectsDataSV(sv, i));
                i++;
            }
            bangdssv.setModel(model);
            /*dcbmTenlop.removeAllElements();
            ArrayList<Lop> listLop = new LopDAO().findByTenKhoa(cbkhoa.toString());
            for (Lop lop : listLop) {
                dcbmTenlop.addElement(lop.getTenlop());
            }
            cbtenlop.setModel(dcbmTenlop);*/
            txtsum.setText(Integer.toString(bangdssv.getRowCount()));
        }

    }

    public void setTongso() {
        txtsum.setText(Integer.toString(bangdssv.getRowCount()));
    }

    public void loadLopByKhoa(String khoa) {

        if (cbkhoa.getSelectedIndex() == 0) {
            loadAllSV();
        } else {
            listSV = new SinhVienDAO().findByTenKhoa(cbkhoa.getSelectedItem().toString());
            int i = 1;
            for (SinhVien sv : listSV) {
                model.addRow(toObjectsDataSV(sv, i));
                i++;
            }
            bangdssv.setModel(model);
        }

    }

    public void loadAllSV() {
        model.setRowCount(0);
        listSV = new SinhVienDAO().getAll();
        int i = 1;
        for (SinhVien sv : listSV) {
            model.addRow(toObjectsDataSV(sv, i));
            i++;
        }
        bangdssv.setModel(model);
        setTongso();

    }

    public void findSVByName() {
        listSV = new SinhVienDAO().findSVByName(txtfindhoten.getText());
        int i = 1;
        model.setRowCount(0);
        for (SinhVien sv : listSV) {
            model.addRow(toObjectsDataSV(sv, i));
            i++;
        }
        bangdssv.setModel(model);

    }

    public boolean checkinfoSV() {
        Check ch = new Check();
        long age = new DayNow().calAge(new DayNow().convertFromJAVADateToSQLDate(dcngsinh.getDate()));

        if (!ch.checkmaSV(txtmasv.getText())) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtmasv.setText("SVxxx");
            txtmasv.requestFocus();
            return false;
        } else if (!ch.checkHoten(txthoten.getText())) {
            JOptionPane.showMessageDialog(this, "Họ tên không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txthoten.setText("");
            txthoten.requestFocus();
            return false;
        } else if (!ch.checkQuequan(txtquequan.getText())) {
            JOptionPane.showMessageDialog(this, "Quê quán không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtquequan.setText("");
            txtquequan.requestFocus();
            return false;
        } else if (((JTextField) dcngsinh.getDateEditor().getUiComponent()).getText().equals("") == true) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
            dcngsinh.requestFocus();
            return false;
        } else if (age < 18) {
            JOptionPane.showMessageDialog(this, "Tuổi của sinh viên phải lớn hơn 18 tuổi", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (chbNam.isSelected() == false && chbNu.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Thông báo", JOptionPane.ERROR_MESSAGE);
            chbNam.requestFocus();
            return false;
        } else if (!ch.checksodt(txtsdt.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtsdt.setText("");
            txtsdt.requestFocus();
            return false;
        }
        return true;
    }

    public void resetFormQlsv() {
        txtmasv.setText("SV");
        txthoten.setText("");
        txtquequan.setText("");
        dcngsinh.setDate(null);
        txtsdt.setText("");
        cbmalop.setSelectedIndex(0);
        chbNam.setSelected(true);
        txtmasv.setEnabled(true);
        btnThemSV.setEnabled(true);
        btnSuaSV.setEnabled(false);
        btnXoaSV.setEnabled(false);
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
        jPanel4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bangdssv = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        cbtenlop = new javax.swing.JComboBox<>();
        btnxemsv = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        cbkhoa = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txtfindhoten = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        cbmalop = new javax.swing.JComboBox<>();
        txtmasv = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        txtquequan = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        chbNam = new javax.swing.JCheckBox();
        chbNu = new javax.swing.JCheckBox();
        dcngsinh = new com.toedter.calendar.JDateChooser();
        jPanel19 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        lbgv = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        btnThemSV = new javax.swing.JButton();
        btnSuaSV = new javax.swing.JButton();
        btnXoaSV = new javax.swing.JButton();
        btnLammoiSV = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtsum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý sinh viên");

        bangdssv.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bangdssv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SV", "Họ Tên", "Ngày Sinh", "Giới tính ", "Quê quán", "Số điện thoại", "Mã lớp"
            }
        ));
        bangdssv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangdssvMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(bangdssv);
        bangdssv.getColumnModel().getColumn(0).setPreferredWidth(50);
        bangdssv.getColumnModel().getColumn(1).setPreferredWidth(100);
        bangdssv.getColumnModel().getColumn(2).setPreferredWidth(200);
        bangdssv.getColumnModel().getColumn(3).setPreferredWidth(120);
        bangdssv.getColumnModel().getColumn(4).setPreferredWidth(80);
        bangdssv.getColumnModel().getColumn(5).setPreferredWidth(150);
        bangdssv.getColumnModel().getColumn(6).setPreferredWidth(150);
        bangdssv.getColumnModel().getColumn(7).setPreferredWidth(100);

        jPanel17.setBackground(java.awt.SystemColor.textHighlight);
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbtenlop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtenlopItemStateChanged(evt);
            }
        });

        btnxemsv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnxemsv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btnxemsv.setText("Xem");
        btnxemsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemsvActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel39.setText("Lớp ");

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel40.setText("Khoa");

        cbkhoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbkhoaItemStateChanged(evt);
            }
        });
        cbkhoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbkhoaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbkhoaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbkhoaMouseReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel41.setText("* Tìm kiếm");

        txtfindhoten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfindhotenMouseClicked(evt);
            }
        });
        txtfindhoten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfindhotenKeyPressed(evt);
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
        jLabel1.setText("Tìm sinh viên");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbtenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtfindhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnxemsv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(224, 224, 224))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnxemsv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtfindhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbtenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel42.setText("Mã SV");

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel43.setText("Họ tên");

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel44.setText("Ngày sinh");

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel45.setText("Giới tính");

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel46.setText("Quê quán");

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel47.setText("Số ĐT");

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel48.setText("Mã lớp");

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel49.setText("* Thông tin chi tiết");

        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });

        buttonGroup1.add(chbNam);
        chbNam.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        chbNam.setText("Nam");

        buttonGroup1.add(chbNu);
        chbNu.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        chbNu.setText("Nữ");

        dcngsinh.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(414, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtmasv, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txthoten, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtquequan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbmalop, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtsdt))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dcngsinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcngsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmasv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtquequan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmalop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel50.setText("DANH SÁCH SINH VIÊN");

        jLabel64.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel64.setText("GVCN :");

        lbgv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbgv, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbgv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addComponent(jLabel50))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemSV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnThemSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/them (1).png"))); // NOI18N
        btnThemSV.setText("Insert");
        btnThemSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSVActionPerformed(evt);
            }
        });

        btnSuaSV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSuaSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/update_1.png"))); // NOI18N
        btnSuaSV.setText("Update");
        btnSuaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSVActionPerformed(evt);
            }
        });

        btnXoaSV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXoaSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete_1.png"))); // NOI18N
        btnXoaSV.setText("Delete");
        btnXoaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSVActionPerformed(evt);
            }
        });

        btnLammoiSV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLammoiSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh-button (1).png"))); // NOI18N
        btnLammoiSV.setText("Refresh");
        btnLammoiSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemSV, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLammoiSV)
                .addGap(20, 20, 20))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoiSV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Tổng số : ");

        txtsum.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsum, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(544, 544, 544)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtsum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1376, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bangdssvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangdssvMouseClicked
        DayNow d = new DayNow();
        txtmasv.setText((String) bangdssv.getValueAt(bangdssv.getSelectedRow(), 1));
        txthoten.setText((String) bangdssv.getValueAt(bangdssv.getSelectedRow(), 2));
        txtquequan.setText((String) bangdssv.getValueAt(bangdssv.getSelectedRow(), 5));
        txtsdt.setText((String) bangdssv.getValueAt(bangdssv.getSelectedRow(), 6));
        cbmalop.setSelectedItem(bangdssv.getValueAt(bangdssv.getSelectedRow(), 7));

        String sex = (String) bangdssv.getValueAt(bangdssv.getSelectedRow(), 4);
        if (sex.equals("Nam")) {
            chbNam.setSelected(true);
        } else {
            chbNu.setSelected(true);
        }

        String ngsinh = (String) bangdssv.getValueAt(bangdssv.getSelectedRow(), 3);
        dcngsinh.setDate(new DayNow().converttoDate(ngsinh));

        txtmasv.setEnabled(false);
        btnThemSV.setEnabled(false);
        btnSuaSV.setEnabled(true);
        btnXoaSV.setEnabled(true);
    }//GEN-LAST:event_bangdssvMouseClicked

    private void cbtenlopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtenlopItemStateChanged
        model.setRowCount(0);
        if (cbtenlop.getSelectedIndex() == 0) {
            lbgv.setText("");
            loadDSSVByKhoa();
            setTongso();
        } else {
            listSV = new SinhVienDAO().findByTenLop(cbtenlop.getSelectedItem().toString());
            int i = 1;
            for (SinhVien sv : listSV) {
                model.addRow(toObjectsDataSV(sv, i));
                i++;
            }
            bangdssv.setModel(model);
            GiaoVien gv = new GiaoVienDAO().findByMalop(cbtenlop.getSelectedItem().toString());
            lbgv.setText(gv.getTenGV());
            setTongso();
        }

    }//GEN-LAST:event_cbtenlopItemStateChanged

    private void btnxemsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemsvActionPerformed
        findSVByName();
    }//GEN-LAST:event_btnxemsvActionPerformed

    private void cbkhoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbkhoaItemStateChanged
        loadDSSVByKhoa();

    }//GEN-LAST:event_cbkhoaItemStateChanged

    private void cbkhoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbkhoaMouseClicked

    }//GEN-LAST:event_cbkhoaMouseClicked

    private void cbkhoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbkhoaMousePressed

    }//GEN-LAST:event_cbkhoaMousePressed

    private void cbkhoaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbkhoaMouseReleased

    }//GEN-LAST:event_cbkhoaMouseReleased

    private void txtfindhotenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindhotenMouseClicked
        txtfindhoten.setText("");
    }//GEN-LAST:event_txtfindhotenMouseClicked

    private void txtfindhotenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindhotenKeyPressed
       
    }//GEN-LAST:event_txtfindhotenKeyPressed

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed

    private void btnThemSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSVActionPerformed

        int siso = new LopDAO().takeSisoByLop(cbmalop.getSelectedItem().toString());
        int countSV = new SinhVienDAO().countSVbyMalop(cbmalop.getSelectedItem().toString());

        boolean chmasv = new SinhVienDAO().checkMaSV(txtmasv.getText());  // Kiểm tra mã sinh viên có bị trùng hay không
        if (!checkinfoSV()) {
            return;
        }

        if (countSV >= siso) {
            JOptionPane.showMessageDialog(this, "Sĩ số đã full ,không thể thêm mới sinh viên\nMuốn thêm sinh viên thì hãy cập nhật sỉ số của lớp học này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (!chmasv) {
            JOptionPane.showMessageDialog(this, "Mã số sinh viên đã tồn tại \nHãy thử mã sinh viên khác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            boolean insert = new SinhVienDAO().themSV(takeSV());
            if (insert) {
                JOptionPane.showMessageDialog(this, "Bạn đã thêm sinh viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadAllSV();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemSVActionPerformed

    private void btnSuaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSVActionPerformed
        if (!checkinfoSV()) {
            return;
        }
        boolean update = new SinhVienDAO().suaSV(takeSV());
        if (update) {
            JOptionPane.showMessageDialog(this, "Bạn đã cập nhật sinh viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadAllSV();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.resetFormQlsv();
        }
    }//GEN-LAST:event_btnSuaSVActionPerformed

    private void btnXoaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSVActionPerformed
        String masv = txtmasv.getText();
        int c = JOptionPane.showConfirmDialog(this, "Nếu xóa sinh viên này thì tất cả các thông tin liên quan đến sinh viên sẽ bị mất\nBạn có chắc chắn muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            SinhVien sv = new SinhVien();
            sv.setMaSV(masv);
            if (!new DangKyHocDAO().huyByMaSV(sv.getMaSV())) {
                JOptionPane.showMessageDialog(this, "Xóa lớp học phần thất bại");
            } else if (!new DiemDAO().huyByMaSV(sv.getMaSV())) {
                JOptionPane.showMessageDialog(this, "Xóa điểm thất bại");
            } else if (!new PhuHuynhDAO().huyByMaSV(sv.getMaSV())) {
                JOptionPane.showMessageDialog(this, "Xóa phụ huynh thất bại");
            } else if (new SinhVienDAO().xoaSV(sv)) {
                JOptionPane.showMessageDialog(this, "Bạn đã xóa sinh viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadAllSV();
                this.resetFormQlsv();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaSVActionPerformed

    private void btnLammoiSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiSVActionPerformed
        resetFormQlsv();
    }//GEN-LAST:event_btnLammoiSVActionPerformed

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
            java.util.logging.Logger.getLogger(QLSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangdssv;
    private javax.swing.JButton btnLammoiSV;
    private javax.swing.JButton btnSuaSV;
    private javax.swing.JButton btnThemSV;
    private javax.swing.JButton btnXoaSV;
    private javax.swing.JButton btnxemsv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbkhoa;
    private javax.swing.JComboBox<String> cbmalop;
    private javax.swing.JComboBox<String> cbtenlop;
    private javax.swing.JCheckBox chbNam;
    private javax.swing.JCheckBox chbNu;
    private com.toedter.calendar.JDateChooser dcngsinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbgv;
    private javax.swing.JTextField txtfindhoten;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtmasv;
    private javax.swing.JTextField txtquequan;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JLabel txtsum;
    // End of variables declaration//GEN-END:variables
}
