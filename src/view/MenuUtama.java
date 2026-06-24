
package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableRowSorter;

public class MenuUtama extends javax.swing.JFrame {
    
    private DefaultTableModel model;
    Timer timer;
    int DELAY = 100;       
    private TableRowSorter<TableModel> rowSorter;
    private static final Border TOP_LEFT_RIGHT_BORDER = BorderFactory.createMatteBorder(1, 3, 0, 0, Color.RED);
    private static final Border BOTTOM_BORDER = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
      
    public MenuUtama() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Maksimalkan JFrame
       
        model = new DefaultTableModel();
        
        // Menambahkan event listener ke setiap JPanel
        menuPelanggan.addMouseListener(hoverEffect);
        menuSparepart.addMouseListener(hoverEffect);
        menuPembayaran.addMouseListener(hoverEffect);
        menuTransaksi.addMouseListener(hoverEffect1);
        menuPelanggan.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE));
        menuSparepart.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE));
        menuPembayaran.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE));
        menuTransaksi.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE));
    }
    
        // Default border settings
        Border defaultBorder1 = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE);
        Border defaultBorder2 = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE);
        Border defaultBorder3 = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE);
        Border defaultBorder4 = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE);
    
            // Event listener
            MouseAdapter hoverEffect = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                panel.setBackground(new Color(172,195,55)); // Warna saat hover
                panel.setBorder(BorderFactory.createMatteBorder(1, 3, 0, 0, Color.WHITE)); // Border saat hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();

                // Mengembalikan border default berdasarkan panel yang dihover
                if (panel == menuPelanggan) {
                    panel.setBorder(defaultBorder1);
                }
                if (panel == menuSparepart) {
                    panel.setBorder(defaultBorder2);
                }
                if (panel == menuPembayaran) {
                    panel.setBorder(defaultBorder3);
                }
                else if (panel == menuTransaksi) {
                    panel.setBorder(defaultBorder4);
                } 
                panel.setBackground(null); // Mengembalikan warna background ke default
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                panel.setBackground(new Color(172,195,55)); // Warna saat hover
                panel.setBorder(BorderFactory.createMatteBorder(1, 3, 0, 0, Color.RED)); // Border saat di klik
            }
        };
        
            MouseAdapter hoverEffect1 = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {                
                JPanel panel1 = (JPanel) e.getSource();
                panel1.setBackground(new Color(172,195,55)); // Warna saat hover
                panel1.setBorder(BorderFactory.createMatteBorder(1, 3, 1, 0, Color.WHITE));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JPanel panel1 = (JPanel) e.getSource();

                // Mengembalikan border default berdasarkan panel yang dihover
                if (panel1 == menuTransaksi) {
                    panel1.setBorder(defaultBorder4);
                }
                panel1.setBackground(null); // Mengembalikan warna background ke default
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel p = (JPanel) e.getSource();
                // mengambil nilai Border Custom
                p.setBorder(new CompoundBorder(
                    BOTTOM_BORDER,
                    TOP_LEFT_RIGHT_BORDER
                ));
                p.setBackground(new Color(172,195,55)); //mengambil warna hover ketika di klik
            }
        };
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        menuPelanggan = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        menuSparepart = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        menuTransaksi = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuPembayaran = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelUtama = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(2, 3, 129));

        menuPelanggan.setBackground(new java.awt.Color(2, 3, 129));
        menuPelanggan.setPreferredSize(new java.awt.Dimension(55, 50));
        menuPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPelangganMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Pelanggan");
        jLabel14.setIconTextGap(10);

        javax.swing.GroupLayout menuPelangganLayout = new javax.swing.GroupLayout(menuPelanggan);
        menuPelanggan.setLayout(menuPelangganLayout);
        menuPelangganLayout.setHorizontalGroup(
            menuPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPelangganLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuPelangganLayout.setVerticalGroup(
            menuPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuSparepart.setBackground(new java.awt.Color(2, 3, 129));
        menuSparepart.setPreferredSize(new java.awt.Dimension(55, 50));
        menuSparepart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSparepartMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Sparepart");
        jLabel15.setIconTextGap(10);

        javax.swing.GroupLayout menuSparepartLayout = new javax.swing.GroupLayout(menuSparepart);
        menuSparepart.setLayout(menuSparepartLayout);
        menuSparepartLayout.setHorizontalGroup(
            menuSparepartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuSparepartLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuSparepartLayout.setVerticalGroup(
            menuSparepartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuSparepartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuTransaksi.setBackground(new java.awt.Color(2, 3, 129));
        menuTransaksi.setPreferredSize(new java.awt.Dimension(55, 50));
        menuTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTransaksiMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Transaksi");
        jLabel17.setIconTextGap(10);

        javax.swing.GroupLayout menuTransaksiLayout = new javax.swing.GroupLayout(menuTransaksi);
        menuTransaksi.setLayout(menuTransaksiLayout);
        menuTransaksiLayout.setHorizontalGroup(
            menuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuTransaksiLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuTransaksiLayout.setVerticalGroup(
            menuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LogoTor.png"))); // NOI18N

        menuPembayaran.setBackground(new java.awt.Color(2, 3, 129));
        menuPembayaran.setPreferredSize(new java.awt.Dimension(55, 50));
        menuPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPembayaranMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Pembayaran");
        jLabel16.setIconTextGap(10);

        javax.swing.GroupLayout menuPembayaranLayout = new javax.swing.GroupLayout(menuPembayaran);
        menuPembayaran.setLayout(menuPembayaranLayout);
        menuPembayaranLayout.setHorizontalGroup(
            menuPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPembayaranLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuPembayaranLayout.setVerticalGroup(
            menuPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(menuTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuSparepart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPembayaran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuSparepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(2, 3, 129));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BENGKEL TORIK JAYA");
        jLabel1.setIconTextGap(150);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        panelUtama.setBackground(new java.awt.Color(204, 204, 255));
        panelUtama.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void menuPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPelangganMouseClicked
        // TODO add your handling code here:
        // Bersihkan panelUtama sebelum menambahkan komponen baru
        panelUtama.removeAll();

        // Buat instance dari InfoPart dan tambahkan ke panelUtama
        PelangganForm PanelPelangganForm = new PelangganForm();
        panelUtama.add(PanelPelangganForm);

        // Refresh tampilan jPanel4
        panelUtama.revalidate();
        panelUtama.repaint();
    }//GEN-LAST:event_menuPelangganMouseClicked

    private void menuSparepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSparepartMouseClicked
        // TODO add your handling code here:   
        panelUtama.removeAll();

        SparepartForm PanelSparepartForm = new SparepartForm();
        panelUtama.add(PanelSparepartForm);

        // Refresh tampilan jPanel4
        panelUtama.revalidate();
        panelUtama.repaint();
    }//GEN-LAST:event_menuSparepartMouseClicked

    private void menuPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPembayaranMouseClicked
        // TODO add your handling code here:
        panelUtama.removeAll();

        PembayaranForm PanelPembayaranForm = new PembayaranForm();
        panelUtama.add(PanelPembayaranForm);

        // Refresh tampilan jPanel4
        panelUtama.revalidate();
        panelUtama.repaint();
    }//GEN-LAST:event_menuPembayaranMouseClicked

    private void menuTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransaksiMouseClicked
        // TODO add your handling code here:
        panelUtama.removeAll();

        TransaksiForm PanelTransaksiForm = new TransaksiForm();
        panelUtama.add(PanelTransaksiForm);

        // Refresh tampilan jPanel4
        panelUtama.revalidate();
        panelUtama.repaint();
    }//GEN-LAST:event_menuTransaksiMouseClicked
             
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
                               
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel menuPelanggan;
    private javax.swing.JPanel menuPembayaran;
    private javax.swing.JPanel menuSparepart;
    private javax.swing.JPanel menuTransaksi;
    private javax.swing.JPanel panelUtama;
    // End of variables declaration//GEN-END:variables
}
