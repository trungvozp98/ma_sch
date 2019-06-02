/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Check.Check;
import DAO.GiaoVienDAO;
import DAO.KhoaDAO;
import DAO.LopDAO;
import DAO.SinhVienDAO;
import Entities.GiaoVien;
import Entities.Khoa;
import Entities.Lop;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trung98
 */
public class QLLop extends javax.swing.JFrame {

    /**
     * Creates new form QLLop
     */
    private ArrayList<Lop> listLop;
    private ArrayList<Khoa> listKhoa;
    private ArrayList<GiaoVien> listGV;
    private DefaultTableModel model;

    private DefaultComboBoxModel dcbmMakhoa1;
    private DefaultComboBoxModel dcbmMakhoa2;
    private DefaultComboBoxModel dcbmMaGV;
    private DefaultComboBoxModel dcbmTenGV;

    public QLLop() {
        initComponents();
        model = (DefaultTableModel) bangdsl.getModel();
        dcbmMakhoa1 = new DefaultComboBoxModel();
        dcbmMakhoa2 = new DefaultComboBoxModel();
        dcbmMaGV = new DefaultComboBoxModel();
        dcbmTenGV = new DefaultComboBoxModel();
        loadQLL();
        resetFormQll();
    }

    public void resetFormQll() {
        txtmalop.setText("");
        txttenlop.setText("");
        txtss.setText("");
        cbl2.setSelectedIndex(0);
        cbl3.setSelectedIndex(0);
        cbl4.setSelectedIndex(0);

        txtmalop.setEnabled(true);
        btnThemLop.setEnabled(true);
        btnSuaLop.setEnabled(false);
        btnXoaLop.setEnabled(false);

    }

    public void setTongso() {
        txtsum.setText(Integer.toString(bangdsl.getRowCount()));

    }

    public void loadAllLop() {
        model.setRowCount(0);
        // hiển thị dữ liệu ra Jtable
        listLop = new LopDAO().getAll();
        int i = 1;
        for (Lop l : listLop) {
            model.addRow(toObjectsDataLop(l, i));
            i++;
        }
        bangdsl.setModel(model);
        setTongso();
    }

    public void findLop() {
        listLop = new LopDAO().findByTenlop(txtfindtl.getText());
        int i = 1;
        model.setRowCount(0);
        for (Lop lop : listLop) {
            model.addRow(toObjectsDataLop(lop, i));
            i++;
        }
        bangdsl.setModel(model);
    }

    public Object[] toObjectsDataLop(Lop l, int i) {
        GiaoVien gv = new GiaoVienDAO().findByMaGV(l.getMagvcn());
        Khoa k = new KhoaDAO().findByMaKhoa(l.getMakhoa());
        Object[] ob = {i, l.getMalop(), l.getTenlop(), l.getSiso(), l.getMagvcn(), gv.getTenGV(), l.getMakhoa(), k.getTenkhoa()};
        return ob;
    }

    public void loadQLL() {

        loadAllLop();
        //hiển thị dữ liệu ra combobox khoa 
        listKhoa = new KhoaDAO().getAll();
        dcbmMakhoa1.addElement("-----Tất cả-----");
        for (Khoa k : listKhoa) {
            dcbmMakhoa1.addElement(k.getTenkhoa());
            dcbmMakhoa2.addElement(k.getMakhoa());
        }
        cbl1.setModel(dcbmMakhoa1);
        cbl4.setModel(dcbmMakhoa2);

        listGV = new GiaoVienDAO().getAll();
        for (GiaoVien gv : listGV) {
            dcbmMaGV.addElement(gv.getMaGV());
            dcbmTenGV.addElement(gv.getTenGV());
        }
        cbl2.setModel(dcbmMaGV);
        cbl3.setModel(dcbmTenGV);
    }

    public void loadLopByTenkhoa(String tenkhoa) {
        model.setRowCount(0);
        ArrayList<Lop> listLop = new LopDAO().findByTenKhoa(tenkhoa);
        int i = 1;
        for (Lop l : listLop) {
            model.addRow(toObjectsDataLop(l, i));
            i++;
        }
        bangdsl.setModel(model);
        setTongso();
    }

    public boolean checkinfoLop() {
        Check c = new Check();
        if (!c.checkMalop(txtmalop.getText())) {
            JOptionPane.showMessageDialog(this, "Mã lớp không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtmalop.setText("L");
            txtmalop.requestFocus();
            return false;
        } else if (!c.checkTenlop(txttenlop.getText())) {
            JOptionPane.showMessageDialog(this, "Tên lớp không hợp lệ.\nTên lớp bắt đầu bằng mã khoa với năm bắt đầu học của lớp đó", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txttenlop.setText("");
            txttenlop.requestFocus();
            return false;
        } else if (!new LopDAO().checkTenlop(txttenlop.getText())) {
            JOptionPane.showMessageDialog(this, "Tên lớp đã tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txttenlop.setText("");
            txttenlop.requestFocus();
            return false;
        } else if (!c.checkSiso(txtss.getText())) {
            JOptionPane.showMessageDialog(this, "Sĩ số không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtss.setText("");
            txtss.requestFocus();
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

        jPanel2 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        cbl1 = new javax.swing.JComboBox<>();
        txtfindtl = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnxemlhp = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        lbgvcn = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        btnThemLop = new javax.swing.JButton();
        btnSuaLop = new javax.swing.JButton();
        btnXoaLop = new javax.swing.JButton();
        btnLammoiLop = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        bangdsl = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        cbl4 = new javax.swing.JComboBox<>();
        txtmalop = new javax.swing.JTextField();
        txttenlop = new javax.swing.JTextField();
        txtss = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        cbl2 = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        cbl3 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtsum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý lớp");

        jPanel31.setBackground(java.awt.SystemColor.textHighlight);
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel57.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel57.setText("Khoa");

        cbl1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbl1ItemStateChanged(evt);
            }
        });
        cbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbl1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbl1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbl1MouseReleased(evt);
            }
        });

        txtfindtl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfindtlMouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel58.setText("* Tìm kiếm");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ba.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnxemlhp.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnxemlhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btnxemlhp.setText("Xem");
        btnxemlhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemlhpActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel59.setText("Tìm lớp ");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(411, 411, 411)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(txtfindtl, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnxemlhp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfindtl, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxemlhp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel100.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel100.setText("DANH SÁCH LỚP");

        jLabel101.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel101.setText("GVCN :");

        lbgvcn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbgvcn, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jLabel100)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lbgvcn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel42.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemLop.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnThemLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/insert_1.png"))); // NOI18N
        btnThemLop.setText("Insert");
        btnThemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLopActionPerformed(evt);
            }
        });

        btnSuaLop.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSuaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/update_1.png"))); // NOI18N
        btnSuaLop.setText("Update");
        btnSuaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLopActionPerformed(evt);
            }
        });

        btnXoaLop.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXoaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete_1.png"))); // NOI18N
        btnXoaLop.setText("Delete");
        btnXoaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLopActionPerformed(evt);
            }
        });

        btnLammoiLop.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLammoiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh-button (1).png"))); // NOI18N
        btnLammoiLop.setText("Refresh");
        btnLammoiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiLopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemLop, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLammoiLop)
                .addGap(55, 55, 55))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemLop, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bangdsl.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bangdsl.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã Lớp", "Tên lớp", "Sỉ số ", "Mã GVCN", "Tên GV", "Mã khoa", "Tên khoa"
            }
        ));
        bangdsl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangdslMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(bangdsl);
        bangdsl.getColumnModel().getColumn(0).setPreferredWidth(40);
        bangdsl.getColumnModel().getColumn(1).setPreferredWidth(50);
        bangdsl.getColumnModel().getColumn(2).setPreferredWidth(120);
        bangdsl.getColumnModel().getColumn(3).setPreferredWidth(50);
        bangdsl.getColumnModel().getColumn(4).setPreferredWidth(80);
        bangdsl.getColumnModel().getColumn(5).setPreferredWidth(150);
        bangdsl.getColumnModel().getColumn(6).setPreferredWidth(100);
        bangdsl.getColumnModel().getColumn(7).setPreferredWidth(180);

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel60.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel60.setText("Mã lớp ");

        jLabel61.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel61.setText("Tên lớp ");

        jLabel75.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel75.setText("Sỉ số");

        jLabel78.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel78.setText("Mã khoa");

        jLabel80.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel80.setText("* Thông tin chi tiết");

        jLabel82.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel82.setText("Mã GVCN");

        cbl2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbl2ItemStateChanged(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel83.setText("Tên GVCN");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttenlop)
                    .addComponent(txtmalop)
                    .addComponent(txtss, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbl4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbl3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmalop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtss, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Tổng số :");

        txtsum.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtsum.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsum, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtsum, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                            .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1385, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbl1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbl1ItemStateChanged
        if (cbl1.getSelectedIndex() == 0) {
            loadAllLop();
        } else {
            loadLopByTenkhoa(cbl1.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbl1ItemStateChanged

    private void cbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbl1MouseClicked

    private void cbl1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbl1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbl1MousePressed

    private void cbl1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbl1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbl1MouseReleased

    private void btnThemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLopActionPerformed
        if (!checkinfoLop()) {
            return;
        }
        String malop = txtmalop.getText();
        String tenlop = txttenlop.getText();
        String siso = txtss.getText();
        String magvcn = cbl2.getSelectedItem().toString();
        String makhoa = cbl4.getSelectedItem().toString();

        Lop l = new Lop(malop, tenlop, siso, magvcn, makhoa);
        boolean insert = new LopDAO().themLop(l);
        if (insert) {
            JOptionPane.showMessageDialog(this, "Thêm lớp thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            resetFormQll();
            loadAllLop();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThemLopActionPerformed

    private void btnSuaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLopActionPerformed

        String malop = txtmalop.getText();
        String tenlop = txttenlop.getText();
        String siso = txtss.getText();
        String magvcn = cbl2.getSelectedItem().toString();
        String makhoa = cbl4.getSelectedItem().toString();
        Lop l = new Lop(malop, tenlop, siso, magvcn, makhoa);
        boolean update = new LopDAO().suaLop(l);
        if (update) {
            JOptionPane.showMessageDialog(this, "Sửa lớp thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            resetFormQll();
            loadAllLop();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaLopActionPerformed

    private void btnXoaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLopActionPerformed
        String malop = txtmalop.getText();
        int c = JOptionPane.showConfirmDialog(this, "Nếu xóa lớp học này thì sinh viên của lớp học sẽ bị xóa\nBạn có chắc chắn muốn xóa lớp", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            new SinhVienDAO().xoaSVByMalop(malop);
            boolean delete = new LopDAO().xoaLop(malop);
            if (delete) {
                JOptionPane.showMessageDialog(this, "Xóa lớp thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                resetFormQll();
                loadAllLop();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnXoaLopActionPerformed

    private void btnLammoiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiLopActionPerformed
        resetFormQll();
    }//GEN-LAST:event_btnLammoiLopActionPerformed

    private void bangdslMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangdslMouseClicked
        txtmalop.setText((String) bangdsl.getValueAt(bangdsl.getSelectedRow(), 1));
        txttenlop.setText((String) bangdsl.getValueAt(bangdsl.getSelectedRow(), 2));
        txtss.setText((String) bangdsl.getValueAt(bangdsl.getSelectedRow(), 3));
        cbl2.setSelectedItem(bangdsl.getValueAt(bangdsl.getSelectedRow(), 4));
        cbl3.setSelectedItem(bangdsl.getValueAt(bangdsl.getSelectedRow(), 5));
        cbl4.setSelectedItem(bangdsl.getValueAt(bangdsl.getSelectedRow(), 6));

        txtmalop.setEnabled(false);
        btnThemLop.setEnabled(false);
        btnSuaLop.setEnabled(true);
        btnXoaLop.setEnabled(true);

        lbgvcn.setText((String) bangdsl.getValueAt(bangdsl.getSelectedRow(), 5));
    }//GEN-LAST:event_bangdslMouseClicked

    private void txtfindtlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindtlMouseClicked
        txtfindtl.setText("");
    }//GEN-LAST:event_txtfindtlMouseClicked

    private void btnxemlhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemlhpActionPerformed
        findLop();
    }//GEN-LAST:event_btnxemlhpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbl2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbl2ItemStateChanged
        GiaoVien gv = new GiaoVienDAO().findByMaGV(cbl2.getSelectedItem().toString());
        cbl3.setSelectedItem(gv.getTenGV());
    }//GEN-LAST:event_cbl2ItemStateChanged

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
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLLop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangdsl;
    private javax.swing.JButton btnLammoiLop;
    private javax.swing.JButton btnSuaLop;
    private javax.swing.JButton btnThemLop;
    private javax.swing.JButton btnXoaLop;
    private javax.swing.JButton btnxemlhp;
    private javax.swing.JComboBox<String> cbl1;
    private javax.swing.JComboBox<String> cbl2;
    private javax.swing.JComboBox<String> cbl3;
    private javax.swing.JComboBox<String> cbl4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbgvcn;
    private javax.swing.JTextField txtfindtl;
    private javax.swing.JTextField txtmalop;
    private javax.swing.JTextField txtss;
    private javax.swing.JLabel txtsum;
    private javax.swing.JTextField txttenlop;
    // End of variables declaration//GEN-END:variables
}
