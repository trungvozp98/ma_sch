/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Check.Check;
import DAO.GiaoVienDAO;
import DAO.LichThiDAO;
import DAO.LopHocPhanDAO;
import DAO.MonHocDAO;
import Date.DayNow;
import Entities.GiaoVien;
import Entities.LichThi;
import Entities.LopHocPhan;
import Entities.MonHoc;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author trung98
 */
public class QLLichThi extends javax.swing.JFrame {

    /**
     * Creates new form QLLichThi
     */
    private DefaultTableModel model;
    private ArrayList<LichThi> listLT;
    private ArrayList<LopHocPhan> listLHP;
    private LopHocPhan lhp;
    private ArrayList<GiaoVien> listGV;

    private DefaultComboBoxModel dcbmMaLHP;
    private DefaultComboBoxModel dcbmMaMH;
    private DefaultComboBoxModel dcbmTenMH;
    private DefaultComboBoxModel dcbmMaGV;

    public QLLichThi() {
        initComponents();
        model = (DefaultTableModel) bangdslt.getModel();
        dcbmMaLHP = new DefaultComboBoxModel();
        dcbmMaMH = new DefaultComboBoxModel();
        dcbmTenMH = new DefaultComboBoxModel();
        dcbmMaGV = new DefaultComboBoxModel();
    }

    public void loadLichthi() {
        model.setRowCount(0);
        listLT = new LichThiDAO().findByNamhoc(cbhockylt.getSelectedItem().toString(), cbnamhoclt.getSelectedItem().toString());
        int i = 0;
        for (LichThi lt : listLT) {
            i++;
            model.addRow(toObjectsDateLT(lt, i));

        }
        bangdslt.setModel(model);

        listLHP = new LopHocPhanDAO().getAll(cbhockylt.getSelectedItem().toString(), cbnamhoclt.getSelectedItem().toString());
        for (LopHocPhan lhp : listLHP) {
            dcbmMaLHP.addElement(lhp.getMaLHP());
            dcbmMaMH.addElement(lhp.getMaMH());
            MonHoc mh = new MonHocDAO().findByMaMH(lhp.getMaMH());
            dcbmTenMH.addElement(mh.getTenMH());
        }
        cbmalhp.setModel(dcbmMaLHP);
        cbmamh.setModel(dcbmMaMH);
        cbtemh.setModel(dcbmTenMH);

        listGV = new GiaoVienDAO().getAll();
        dcbmMaGV.addElement("");
        for (GiaoVien gv : listGV) {
            dcbmMaGV.addElement(gv.getMaGV());

        }
        cbmagv.setModel(dcbmMaGV);

    }

    public Object[] toObjectsDateLT(LichThi lt, int i) {

        lhp = new LopHocPhanDAO().findByMaLHP(lt.getMaLHP(), lt.getHocky(), lt.getNamhoc());

        MonHoc mh = new MonHocDAO().findByMaMH(lhp.getMaMH());

        Object[] ob = {i, lt.getMaLHP(), mh.getMaMH(), mh.getTenMH(), lt.getMagvct(), lt.getThu(), lt.getTiet(), lt.getPhong(), lt.getNgThi()};
        return ob;

    }

    public void findLTByTenMH() {
        listLT = new LichThiDAO().findByTenMH(txtfindmh.getText(), cbhockylt.getSelectedItem().toString(), cbnamhoclt.getSelectedItem().toString());
        int i = 1;
        model.setRowCount(0);
        for (LichThi lt : listLT) {
            model.addRow(toObjectsDateLT(lt, i));
            i++;
        }
        bangdslt.setModel(model);

    }

    private void resetFormQllt() {

        cbmagv.setSelectedIndex(0);
        txtthu.setText("");
        cbca.setSelectedIndex(0);
        txtphong.setText("");
        dcngthi.setDate(null);

        cbmalhp.setEnabled(true);
        btnThemLichthi.setEnabled(true);
        btnSuaLichThi.setEnabled(false);
        btnXoaLichthi.setEnabled(false);

    }

    public boolean checkinfoLT() {
        String malhp = cbmalhp.getSelectedItem().toString();
        String hocky = cbhockylt.getSelectedItem().toString();
        String namhoc = cbnamhoclt.getSelectedItem().toString();
        Check c1 = new Check();
        String ngkt = new LopHocPhanDAO().findNgktByMaLHP(malhp, hocky, namhoc);

        if (!c1.checkThu(txtthu.getText())) {
            JOptionPane.showMessageDialog(this, "Thứ không hợp lệ ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtthu.setText("");
            txtthu.requestFocus();
            return false;

        } else if (!c1.checkPhong(txtphong.getText())) {
            JOptionPane.showMessageDialog(this, "Phòng không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtphong.setText("");
            txtphong.requestFocus();
            return false;
        } else if (!c1.checkDiffTwoDays(new DayNow().converttoDate(ngkt), dcngthi.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày thi phải lớn hơn ngày kết thúc của lớp học phần này", "Thông báo", JOptionPane.ERROR_MESSAGE);
            dcngthi.requestFocus();
            return false;
        }

        return true;

    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);

        XSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle createStyleForBold(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);

        XSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle createStyleForData(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font);
        return style;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        cbnamhoclt = new javax.swing.JComboBox<>();
        btnXemmh = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        cbhockylt = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        txtfindmh = new javax.swing.JTextField();
        btnXemLichthi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        bangdslt = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        btnThemLichthi = new javax.swing.JButton();
        btnSuaLichThi = new javax.swing.JButton();
        btnXoaLichthi = new javax.swing.JButton();
        btnLammoiLichthi = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        cbmagv = new javax.swing.JComboBox<>();
        dcngthi = new com.toedter.calendar.JDateChooser();
        jLabel71 = new javax.swing.JLabel();
        txtthu = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txtphong = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        cbmalhp = new javax.swing.JComboBox<>();
        cbtemh = new javax.swing.JComboBox<>();
        cbmamh = new javax.swing.JComboBox<>();
        cbca = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý lịch thi");

        jPanel22.setBackground(java.awt.SystemColor.textHighlight);
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbnamhoclt.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbnamhoclt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016-2017", "2017-2018", "2018-2019" }));
        cbnamhoclt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbnamhocltItemStateChanged(evt);
            }
        });

        btnXemmh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXemmh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ssss.png"))); // NOI18N
        btnXemmh.setText("Tìm kiếm");
        btnXemmh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemmhActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel51.setText("Năm học");

        jLabel53.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel53.setText("Học kỳ");

        cbhockylt.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbhockylt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Học kỳ 1", "Học kỳ 2", "Học kỳ 3" }));
        cbhockylt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbhockyltItemStateChanged(evt);
            }
        });
        cbhockylt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbhockyltMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbhockyltMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbhockyltMouseReleased(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel54.setText("* Tìm kiếm");

        txtfindmh.setText("Nhập tên môn");
        txtfindmh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfindmhMouseClicked(evt);
            }
        });
        txtfindmh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfindmhActionPerformed(evt);
            }
        });
        txtfindmh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfindmhKeyPressed(evt);
            }
        });

        btnXemLichthi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXemLichthi.setText("Xem");
        btnXemLichthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemLichthiActionPerformed(evt);
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

        btnExport.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExport.setText("Xuất lịch thi");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbhockylt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(cbnamhoclt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXemLichthi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                                .addComponent(txtfindmh, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXemmh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))))
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbhockylt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbnamhoclt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXemLichthi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfindmh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXemmh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        bangdslt.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bangdslt.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã LHP", "Mã MH", "Tên MH", "GVCT", "Thứ", "Ca", "Phòng", "Ngày"
            }
        ));
        bangdslt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangdsltMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(bangdslt);
        bangdslt.getColumnModel().getColumn(0).setPreferredWidth(40);
        bangdslt.getColumnModel().getColumn(1).setPreferredWidth(80);
        bangdslt.getColumnModel().getColumn(2).setPreferredWidth(80);
        bangdslt.getColumnModel().getColumn(3).setPreferredWidth(180);
        bangdslt.getColumnModel().getColumn(4).setPreferredWidth(80);
        bangdslt.getColumnModel().getColumn(5).setPreferredWidth(50);
        bangdslt.getColumnModel().getColumn(6).setPreferredWidth(50);
        bangdslt.getColumnModel().getColumn(7).setPreferredWidth(50);
        bangdslt.getColumnModel().getColumn(8).setPreferredWidth(100);

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemLichthi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnThemLichthi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/insert_1.png"))); // NOI18N
        btnThemLichthi.setText("Insert");
        btnThemLichthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLichthiActionPerformed(evt);
            }
        });

        btnSuaLichThi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSuaLichThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/update_1.png"))); // NOI18N
        btnSuaLichThi.setText("Update");
        btnSuaLichThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLichThiActionPerformed(evt);
            }
        });

        btnXoaLichthi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXoaLichthi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete_1.png"))); // NOI18N
        btnXoaLichthi.setText("Delete");
        btnXoaLichthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLichthiActionPerformed(evt);
            }
        });

        btnLammoiLichthi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLammoiLichthi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh-button (1).png"))); // NOI18N
        btnLammoiLichthi.setText("Refresh");
        btnLammoiLichthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiLichthiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemLichthi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnSuaLichThi)
                .addGap(18, 18, 18)
                .addComponent(btnXoaLichthi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLammoiLichthi)
                .addGap(74, 74, 74))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemLichthi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLichthi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoiLichthi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel55.setText("Mã LHP");

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel56.setText("Mã MH");

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel59.setText("Ngày thi");

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel62.setText("Tên MH");

        jLabel66.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel66.setText("Mã GVCT");

        jLabel67.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel67.setText("* Thông tin chi tiết");

        dcngthi.setDateFormatString("dd-MM-yyyy");

        jLabel71.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel71.setText("Thứ");

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel74.setText("Ca");

        jLabel81.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel81.setText("Phòng");

        cbmalhp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmalhpItemStateChanged(evt);
            }
        });

        cbca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1", "2", "3", "4" }));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmalhp, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbtemh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbmamh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbmagv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtthu)
                            .addComponent(cbca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtphong)
                            .addComponent(dcngthi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(35, 35, 35))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtthu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmalhp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmamh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbtemh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcngthi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmagv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel70.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel70.setText("LỊCH THI");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 616, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("*Ghi chú : Ca 1 : 7h30    , Ca 2 : 9h30    , Ca 3 : 13h30    , Ca 4: 15h30");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(266, 266, 266)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1398, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbnamhocltItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbnamhocltItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnamhocltItemStateChanged

    private void btnXemmhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemmhActionPerformed
        findLTByTenMH();
    }//GEN-LAST:event_btnXemmhActionPerformed

    private void cbhockyltItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbhockyltItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbhockyltItemStateChanged

    private void cbhockyltMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbhockyltMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbhockyltMouseClicked

    private void cbhockyltMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbhockyltMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbhockyltMousePressed

    private void cbhockyltMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbhockyltMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbhockyltMouseReleased

    private void txtfindmhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindmhMouseClicked
        txtfindmh.setText("");
    }//GEN-LAST:event_txtfindmhMouseClicked

    private void txtfindmhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindmhKeyPressed

    }//GEN-LAST:event_txtfindmhKeyPressed

    private void btnXemLichthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemLichthiActionPerformed
        loadLichthi();
    }//GEN-LAST:event_btnXemLichthiActionPerformed

    private void bangdsltMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangdsltMouseClicked
        cbmalhp.setSelectedItem(bangdslt.getValueAt(bangdslt.getSelectedRow(), 1));
        cbmamh.setSelectedItem(bangdslt.getValueAt(bangdslt.getSelectedRow(), 2));
        cbtemh.setSelectedItem(bangdslt.getValueAt(bangdslt.getSelectedRow(), 3));
        txtthu.setText((String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 5));
        txtphong.setText((String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 7));

        String magv = (String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 4);
        String thu = (String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 5);
        String ca = (String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 6);
        String phong = (String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 7);
        String ngthi = (String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 8);
        if (ngthi != null) {
            dcngthi.setDate(new DayNow().converttoDate(ngthi));
        } else {
            dcngthi.setDate(null);
        }
        if (bangdslt.getValueAt(bangdslt.getSelectedRow(), 4) == null) {
            cbmagv.setSelectedIndex(0);
        } else {
            cbmagv.setSelectedItem(bangdslt.getValueAt(bangdslt.getSelectedRow(), 4));
        }

        if (bangdslt.getValueAt(bangdslt.getSelectedRow(), 6) == null) {
            cbca.setSelectedIndex(0);
        } else {
            cbca.setSelectedItem(bangdslt.getValueAt(bangdslt.getSelectedRow(), 6));
        }

        /*if (magv != null) {
            cbmagv.setSelectedItem(bangdslt.getValueAt(bangdslt.getSelectedRow(), 4));
        } else {
            cbmagv.setSelectedIndex(0);
        }

        if (thu == null || ca == null || phong == null) {
            txtthu.setText("");
            txtphong.setText("");
            cbca.setSelectedIndex(0);
        } else {
            txtthu.setText((String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 5));
            cbca.setSelectedItem((String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 6));
            txtphong.setText((String) bangdslt.getValueAt(bangdslt.getSelectedRow(), 7));
        }*/
        cbmalhp.setEnabled(false);
        btnThemLichthi.setEnabled(false);
        btnSuaLichThi.setEnabled(true);
        btnXoaLichthi.setEnabled(true);
    }//GEN-LAST:event_bangdsltMouseClicked

    private void btnSuaLichThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLichThiActionPerformed
        if (!checkinfoLT()) {
            return;
        }
        String malhp = cbmalhp.getSelectedItem().toString();
        String hocky = cbhockylt.getSelectedItem().toString();
        String namhoc = cbnamhoclt.getSelectedItem().toString();
        String magvct = cbmagv.getSelectedItem().toString();
        String thu = txtthu.getText();
        String ca = cbca.getSelectedItem().toString();
        String phong = txtphong.getText();
        DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String ngthi = new DayNow().converttoString(new DayNow().convertFromJAVADateToSQLDate(dcngthi.getDate()));

        LopHocPhan lhp = new LopHocPhanDAO().findByMaLHP(malhp, hocky, namhoc);
        LichThi lt = new LichThi(malhp, hocky, namhoc, thu, ca, phong, magvct, ngthi);

        boolean update = new LichThiDAO().suaLichthi(lt);
        if (update) {
            boolean update2 = new LichThiDAO().updateByMon(thu, ca, ngthi, lhp.getMaMH());
            if (update2) {
                JOptionPane.showMessageDialog(this, "Cập nhật lịch thi thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadLichthi();
                resetFormQllt();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaLichThiActionPerformed

    private void btnXoaLichthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLichthiActionPerformed
        String malhp = cbmalhp.getSelectedItem().toString();
        String hocky = cbhockylt.getSelectedItem().toString();
        String namhoc = cbnamhoclt.getSelectedItem().toString();

        LichThi lt = new LichThi(malhp, hocky, namhoc);
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa lịch thi này", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            boolean delete = new LichThiDAO().xoaLichthi(lt);
            if (delete) {
                JOptionPane.showMessageDialog(this, "Xóa lịch thi thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadLichthi();
                resetFormQllt();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaLichthiActionPerformed

    private void btnLammoiLichthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiLichthiActionPerformed
        resetFormQllt();
    }//GEN-LAST:event_btnLammoiLichthiActionPerformed

    private void cbmalhpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmalhpItemStateChanged
        listLHP = new LopHocPhanDAO().findByMaLHP(cbmalhp.getSelectedItem().toString());
        cbmamh.setSelectedItem(listLHP.get(0).getMaMH());
        MonHoc mh = new MonHocDAO().findByMaMH(listLHP.get(0).getMaMH());
        cbtemh.setSelectedItem(mh.getTenMH());
        cbmamh.setEnabled(false);
        cbtemh.setEnabled(false);
    }//GEN-LAST:event_cbmalhpItemStateChanged

    private void txtfindmhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfindmhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfindmhActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Result");
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 3000);

        int rownum = 2;
        Row row;
        Cell cell;
        XSSFCellStyle style = createStyleForTitle(wb);
        XSSFCellStyle styleBold = createStyleForBold(wb);
        XSSFCellStyle styleData = createStyleForData(wb);
        row = sheet.createRow(rownum);

        XSSFRow title = sheet.createRow(0);
        Cell titleCell = title.createCell(2);
        String hocky = cbhockylt.getSelectedItem().toString().toUpperCase();

        titleCell.setCellValue("LỊCH THI CUỐI KỲ " + hocky + " NĂM HỌC " + cbnamhoclt.getSelectedItem());
        titleCell.setCellStyle(style);

        // Số TT
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Số TT");
        cell.setCellStyle(styleBold);
        // Môn học
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã LHP");
        cell.setCellStyle(styleBold);
        // Điểm quá trình
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Mã MH");
        cell.setCellStyle(styleBold);
        // Điểm giữa kỳ
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Tên MH");
        cell.setCellStyle(styleBold);
        // Điểm cuối kỳ
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Mã GVCT");
        cell.setCellStyle(styleBold);
        // Điểm trung bình
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Thứ");
        cell.setCellStyle(styleBold);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Ca");
        cell.setCellStyle(styleBold);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Phòng");
        cell.setCellStyle(styleBold);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Ngày thi");
        cell.setCellStyle(styleBold);

        for (int i = 0; i < bangdslt.getRowCount(); i++) {
            rownum++;
            row = sheet.createRow(rownum);

            // Số TT
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(String.valueOf(bangdslt.getValueAt(i, 0)));
            cell.setCellStyle(styleData);
            // Mã LHP 
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue((String) bangdslt.getValueAt(i, 1));
            cell.setCellStyle(styleData);

            // Mã MH 
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 2)));
            cell.setCellStyle(styleData);
            // Tên MH 
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 3)));
            cell.setCellStyle(styleData);
            // Mã GVCT
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 4)));
            cell.setCellStyle(styleData);
            // Thứ
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 5)));
            cell.setCellStyle(styleData);
            // Ca
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 6)));
            cell.setCellStyle(styleData);
            // Phòng
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 7)));
            cell.setCellStyle(styleData);
            // Ngày thi
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue((String) (bangdslt.getValueAt(i, 8)));
            cell.setCellStyle(styleData);
        }
        XSSFRow sum = sheet.createRow(rownum + 2);
        Cell sumLabelCell = sum.createCell(0);
        sumLabelCell.setCellValue("Ghi chú : Ca 1 : 7h30 , Ca 2: 9h30, Ca 3 : 13h30, Ca4 : 15h30");
        sumLabelCell.setCellStyle(styleBold);
        File file = new File("E:/Learn Java/Slide UIT/" + "lichthi" + ".xlsx");
        file.getParentFile().mkdirs();

        try {
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KetQuaHocTap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KetQuaHocTap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnThemLichthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLichthiActionPerformed

    }//GEN-LAST:event_btnThemLichthiActionPerformed

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
            java.util.logging.Logger.getLogger(QLLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLLichThi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangdslt;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnLammoiLichthi;
    private javax.swing.JButton btnSuaLichThi;
    private javax.swing.JButton btnThemLichthi;
    private javax.swing.JButton btnXemLichthi;
    private javax.swing.JButton btnXemmh;
    private javax.swing.JButton btnXoaLichthi;
    private javax.swing.JComboBox<String> cbca;
    private javax.swing.JComboBox<String> cbhockylt;
    private javax.swing.JComboBox<String> cbmagv;
    private javax.swing.JComboBox<String> cbmalhp;
    private javax.swing.JComboBox<String> cbmamh;
    private javax.swing.JComboBox<String> cbnamhoclt;
    private javax.swing.JComboBox<String> cbtemh;
    private com.toedter.calendar.JDateChooser dcngthi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtfindmh;
    private javax.swing.JTextField txtphong;
    private javax.swing.JTextField txtthu;
    // End of variables declaration//GEN-END:variables
}
