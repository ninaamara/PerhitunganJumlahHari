/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko.view;

import com.mysql.jdbc.Connection;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import toko.koneksi;

/**
 *
 * @author Rifqi
 */
public class Form_penjualan_kasir extends javax.swing.JInternalFrame {

    /**
     * Creates new form Form_penjualan_kasir
     */
   private DefaultTableModel model;
     public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    Connection kon = koneksi.koneksiDb();
    public Form_penjualan_kasir() {
        initComponents();
        tampil_buku();
        model = new DefaultTableModel();

        tabel.setModel(model);
        model.addColumn("Id detail");
        model.addColumn("Id buku");
        model.addColumn("Judul");
        model.addColumn("Kategori");
        model.addColumn("harga satuan");
        model.addColumn("jumlah beli");
        model.addColumn("harga");
        loadData();
        nofaktur();
    
    }
      public void FilterHuruf(KeyEvent a){
       if(Character.isDigit(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "masukan huruf saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "masukan angka saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    public final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            java.sql.Connection c = koneksi.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM penjualan_detail";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("id_detail");
                o[1] = r.getString("id_buku");
                o[2] = r.getString("judul");
                o[3] = r.getString("kategori");
                o[4] = r.getString("hsatuan");
                o[5] = r.getString("jumlah_beli");
                o[6] = r.getString("harga");
               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
   }
      private void nofaktur() {
        try {
            java.sql.Connection c = koneksi.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM penjualan ORDER by id_transaksi desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("id_transaksi").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

                faktur.setText("F" + Nol + AN);
            } else {
                faktur.setText("F0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     private void tampil_buku(){
     // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id buku");
        model.addColumn("No.Isbn");
        model.addColumn("Judul");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Keategori buku");
        model.addColumn("Stok");
        model.addColumn("Harga Pokok");
        model.addColumn("Harga Jual");
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from buku";
            java.sql.Statement stm=kon.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)
                               ,res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            tabelbuku.setModel(model);
            res.last();
            int jumlahdata = res.getRow();
            res.first();
        } catch (Exception e) {
        }
    }
     
     public void tabeltransaksi(){
       DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Buku");
        model.addColumn("Tanggal");
        model.addColumn("Judul");
        model.addColumn("Kategori");
        model.addColumn("Harga Stuan");
        model.addColumn("Jumlah Beli");
        model.addColumn("Sub Total");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from penjualan order by tanggal desc";
            java.sql.Statement stm=kon.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)
                               ,res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            tabeltransaksi.setModel(model);
            
        } catch (Exception e) {
        }
        
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Datatransaksi = new javax.swing.JDialog();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txnota = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeltransaksi = new javax.swing.JTable();
        btncetak = new javax.swing.JToggleButton();
        txcetak = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        srlpane = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        faktur = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tjumlah = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        hsatuan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tjudul = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelbuku = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tkategori = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        idbuku = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ttotal = new javax.swing.JTextField();
        btnhitung = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txkembalian = new javax.swing.JTextField();
        txbayar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        totalsemua = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        alltotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        tx2 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        Datatransaksi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Datatransaksi.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        Datatransaksi.setSize(new java.awt.Dimension(754, 300));

        jInternalFrame1.setTitle("TABEL TRANSAKSI");
        jInternalFrame1.setVisible(true);

        jPanel5.setBackground(new java.awt.Color(52, 73, 94));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cari Id Transaksi");

        txnota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txnotaKeyPressed(evt);
            }
        });

        tabeltransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabeltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabeltransaksi);

        btncetak.setText("Cetak Nota");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txnota, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txcetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txnota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncetak)
                    .addComponent(txcetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout DatatransaksiLayout = new javax.swing.GroupLayout(Datatransaksi.getContentPane());
        Datatransaksi.getContentPane().setLayout(DatatransaksiLayout);
        DatatransaksiLayout.setHorizontalGroup(
            DatatransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
            .addGroup(DatatransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DatatransaksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        DatatransaksiLayout.setVerticalGroup(
            DatatransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(DatatransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DatatransaksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kelola Data Penjualan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        srlpane.setViewportView(tabel);

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form Penjualan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("No nota");

        faktur.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Jumlah");

        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tjumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("Harga");

        hsatuan.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Judul");

        tjudul.setEditable(false);

        tabelbuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelbuku);

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        jLabel11.setText("Cari Buku");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Kategori");

        tkategori.setEditable(false);

        jLabel12.setText("Id Buku");

        idbuku.setEditable(false);

        jLabel5.setText("Total");

        ttotal.setEditable(false);

        btnhitung.setText("hitung");
        btnhitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhitungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hsatuan))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tkategori))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tjudul)
                            .addComponent(idbuku)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnhitung)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tjumlah)
                            .addComponent(ttotal))))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(idbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tjudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(hsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhitung))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Kembalian");

        txkembalian.setEditable(false);
        txkembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txkembalianKeyTyped(evt);
            }
        });

        txbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbayarActionPerformed(evt);
            }
        });
        txbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txbayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txbayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txbayarKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Bayar");

        totalsemua.setText("Total semuanya");
        totalsemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalsemuaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Rp.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(totalsemua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alltotal)
                .addGap(72, 72, 72)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(totalsemua)
                    .addComponent(jLabel13)
                    .addComponent(alltotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Daftar buku yang di beli");

        jumlah.setBackground(new java.awt.Color(52, 73, 94));
        jumlah.setForeground(new java.awt.Color(52, 73, 94));
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        btnadd.setText("Tambah ke daftar buku di beli");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        jButton3.setText("Data Transaksi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnsimpan.setText("Selesai transaksi");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        tx2.setEditable(false);
        tx2.setBackground(new java.awt.Color(52, 73, 94));
        tx2.setForeground(new java.awt.Color(52, 73, 94));
        tx2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx2ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(52, 73, 94));

        jButton1.setText("Cetak Nota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(srlpane, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnadd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tx2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnadd)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnsimpan)
                                .addComponent(jButton3)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srlpane, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int jawaban;
        if ((jawaban = JOptionPane.showConfirmDialog(null,"Yakin batal?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
        try{
        
        int i = tabel.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        
        st = kon.createStatement();
        st.executeUpdate("delete from penjualan_detail where id_detail = '"+id+ "'");
        
        nofaktur();
        loadData();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_tabelMouseClicked

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
       if(txbayar.getText().equals("") ||txkembalian.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Senja Abadi Books Store", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String a = txkembalian.getText();
            int ab = Integer.parseInt(String.valueOf(txkembalian.getText()));
              if(ab < 0){
                JOptionPane.showMessageDialog(null, "Uang anda kurang", "Senja Abadi Books Store", JOptionPane.INFORMATION_MESSAGE);
                txbayar.setText("");
            txkembalian.setText("");
              }else{
           try {
            
            Statement s = kon.createStatement();

            String sql = "SELECT * FROM penjualan_detail";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                String sqla = "INSERT INTO penjualan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
                PreparedStatement p = kon.prepareStatement(sqla);
                p.setString(1, faktur.getText());
                p.setString(2, r.getString("id_buku"));
                p.setString(3, tgl);
                p.setString(4, r.getString("judul"));
                p.setString(5, r.getString("kategori"));
                p.setString(6, r.getString("hsatuan"));
                p.setString(7, r.getString("jumlah_beli"));
                p.setString(8, r.getString("harga"));
                p.setString(9, txbayar.getText());
                p.setString(10, txkembalian.getText()); 
                
                p.executeUpdate();
                p.close();
                
                
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
         try {
            String sqla ="TRUNCATE `penjualan_detail";
            java.sql.Connection conn=(java.sql.Connection)koneksi.koneksiDb();
            java.sql.PreparedStatement pst=conn.prepareStatement(sqla);
            pst.execute();
            JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "Senja Abadi Books Store", JOptionPane.INFORMATION_MESSAGE);
            loadData();
            tx2.setText(faktur.getText());
            txbayar.setText("");
            txkembalian.setText("");
            alltotal.setText("");
            nofaktur();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
           }
              }
         }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void txbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txbayarActionPerformed

    private void tabelbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbukuMouseClicked
        // TODO add your handling code here:
        int row = tabelbuku.getSelectedRow();
        idbuku.setText(tabelbuku.getModel().getValueAt(row, 0).toString());
        tjudul.setText(tabelbuku.getModel().getValueAt(row, 2).toString());
        tkategori.setText(tabelbuku.getModel().getValueAt(row, 6).toString());
        hsatuan.setText(tabelbuku.getModel().getValueAt(row, 9).toString());
        
        try {
            java.sql.Connection c = koneksi.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT stok FROM buku";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                //idbuku.setText(r.getString("id_buku"));
                jumlah.setText(r.getString("stok"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabelbukuMouseClicked

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id buku");
        model.addColumn("No.Isbn");
        model.addColumn("Judul");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Keategori buku");
        model.addColumn("Stok");
        model.addColumn("Harga Pokok");
        model.addColumn("Harga Jual");
        try {
            String cari = txtcari.getText();
            String sql = "select * from buku where judul LIKE '%"+cari+"%' OR kategori LIKE '%"+cari+"%'";
            java.sql.Statement stm=kon.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)
                               ,res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            tabelbuku.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Datatransaksi.setLocationRelativeTo(null);
        tabeltransaksi();
        Datatransaksi.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
       
       if(faktur.getText().equals("") ||idbuku.getText().equals("") || tjudul.getText().equals("")|| tkategori.getText().equals("")|| hsatuan.getText().equals("")|| tjumlah.getText().equals("")||ttotal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elektronik berkah", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String idbukuu = idbuku.getText();
            String judull = tjudul.getText();
            String kategorii=tkategori.getText();
            String hsatuann = hsatuan.getText();
            String tjumlahh = tjumlah.getText();
            String totall = ttotal.getText();
           
            try {
                java.sql.Connection c = koneksi.koneksiDb();
                
                String sql = "INSERT INTO penjualan_detail VALUES (?, ?, ?, ?, ?, ?, ?)";
                
               
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, null);
                p.setString(2, idbukuu);
                p.setString(3, judull);
                p.setString(4, kategorii);
                p.setString(5, hsatuann);
                p.setString(6, tjumlahh);
                p.setString(7, totall);
                
                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                nofaktur();
                idbuku.setText("");
                tjudul.setText("");
                tkategori.setText("");
                hsatuan.setText("");
                tjumlah.setText("");
                ttotal.setText("");
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Senja Abadi Books Store", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                
                
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void txbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txbayarKeyPressed

    private void tabeltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltransaksiMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tabeltransaksiMouseClicked

    private void txbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyTyped
        // TODO add your handling code here:
            FilterAngka(evt);
    }//GEN-LAST:event_txbayarKeyTyped

    private void txkembalianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txkembalianKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter==KeyEvent.VK_BACK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txkembalianKeyTyped

    private void tjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyReleased
        
        
    }//GEN-LAST:event_tjumlahKeyReleased

    private void totalsemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalsemuaActionPerformed
        // TODO add your handling code here:
         
        try {
            java.sql.Connection c = koneksi.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT SUM(`harga`) AS total FROM penjualan_detail";
            ResultSet r = s.executeQuery(sql);
           
            while (r.next()) {
                alltotal.setText(r.getString(""+"total"));
                
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
        
        
        
    }//GEN-LAST:event_totalsemuaActionPerformed

    private void txbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyReleased
        // TODO add your handling code here:
            bayar = Integer.parseInt(String.valueOf(txbayar.getText()));
            total = Integer.parseInt(String.valueOf(alltotal.getText()));
            kembali = bayar - total;
            
            txkembalian.setText(Long.toString(kembali));
    }//GEN-LAST:event_txbayarKeyReleased

    private void tx2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx2ActionPerformed

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_tjumlahKeyTyped

    private void btnhitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhitungActionPerformed
        // TODO add your handling code here:
        if(faktur.getText().equals("") ||idbuku.getText().equals("") || tjudul.getText().equals("")|| tkategori.getText().equals("")|| hsatuan.getText().equals("")|| tjumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Senja Abadi Books Store", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
        String a = tjumlah.getText();
        int aa = Integer.parseInt(a);
        
        String b = jumlah.getText();
        int bb = Integer.parseInt(b);
        if(aa > bb){
             JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "Senja Abadi Books Store", JOptionPane.INFORMATION_MESSAGE);
             tjumlah.setText("");
        }else{
       
        if(tjumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
        }else{
        int jumlah, harga, total;
       
        jumlah = Integer.parseInt(tjumlah.getText().toString());
        harga = Integer.parseInt(hsatuan.getText().toString());
        total = jumlah * harga;
       
       
             ttotal.setText(Integer.toString(total));
        
        }
        }
        }  
    }//GEN-LAST:event_btnhitungActionPerformed

    private void txnotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txnotaKeyPressed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Buku");
        model.addColumn("Tanggal");
        model.addColumn("Judul");
        model.addColumn("Kategori");
        model.addColumn("Harga Stuan");
        model.addColumn("Jumlah Beli");
        model.addColumn("Harga");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");
        //menampilkan data database kedalam tabel
        try {
            String nota = txnota.getText();
            String sql = "select * from penjualan where id_transaksi like'%"+nota+"%'";
            java.sql.Statement stm=kon.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)
                               ,res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            tabeltransaksi.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
        }
    }//GEN-LAST:event_txnotaKeyPressed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
        java.sql.Connection con=null;
        try {
            String jdbcDriver ="com.mysql.jdbc.Driver";
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/dbtoko","root","");
            Statement stm = koneksi.createStatement();
            
            try{
            String report=("E:\\JAVA\\toko\\src\\report\\nota.jrxml");
            HashMap hash = new HashMap();
            hash.put("nota",txnota.getText());
            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, kon);
            JasperViewer.viewReport(JPrint, false);
            //Datatransaksi.dispose();
            }catch(Exception rptexcpt){
                System.out.println("maaf cetak gagal"+rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btncetakActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         java.sql.Connection con=null;
        try {
            String jdbcDriver ="com.mysql.jdbc.Driver";
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/dbtoko","root","");
            Statement stm = koneksi.createStatement();
            
            try{
            String report=("E:\\JAVA\\toko\\src\\report\\nota.jrxml");
            HashMap hash = new HashMap();
            hash.put("nota",txnota.getText());
            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, kon);
            JasperViewer.viewReport(JPrint, false);
            //Datatransaksi.dispose();
            }catch(Exception rptexcpt){
                System.out.println("maaf cetak gagal"+rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Datatransaksi;
    private javax.swing.JLabel alltotal;
    private javax.swing.JButton btnadd;
    private javax.swing.JToggleButton btncetak;
    private javax.swing.JButton btnhitung;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JTextField faktur;
    public javax.swing.JTextField hsatuan;
    private javax.swing.JTextField idbuku;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jumlah;
    private javax.swing.JScrollPane srlpane;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabelbuku;
    private javax.swing.JTable tabeltransaksi;
    public javax.swing.JTextField tjudul;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField tkategori;
    private javax.swing.JButton totalsemua;
    private javax.swing.JTextField ttotal;
    private javax.swing.JTextField tx2;
    private javax.swing.JTextField txbayar;
    private javax.swing.JTextField txcetak;
    private javax.swing.JTextField txkembalian;
    private javax.swing.JTextField txnota;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
