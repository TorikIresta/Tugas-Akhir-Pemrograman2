/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PelangganDAO;
import dao.PelangganDAOImpl;
import view.PembayaranForm;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ID_PC
 */
public class PembayaranController {
    private PembayaranForm view;
    private PelangganDAO pelangganDao;
    private int selectedPelangganId = -1;
    private int hargaSparepart = 0;
    
    
    public PembayaranController(PembayaranForm view) {
        this.view = view;
        this.pelangganDao = new PelangganDAOImpl();
    }

    public void tampilkanAntrean() {
        List<model.Pelanggan> list = pelangganDao.getAll();
        DefaultTableModel model = (DefaultTableModel) view.getTablePelanggan().getModel();
        model.setRowCount(0);
        for (model.Pelanggan p : list) {
            model.addRow(new Object[]{p.getId(), p.getNama(), p.getNomorPlat(), p.getKeluhan()});
        }
    }

    public void cariPelanggan(String keyword) {
        List<model.Pelanggan> list = pelangganDao.search(keyword);
        DefaultTableModel model = (DefaultTableModel) view.getTablePelanggan().getModel();
        model.setRowCount(0);
        for (model.Pelanggan p : list) {
            model.addRow(new Object[]{p.getId(), p.getNama(), p.getNomorPlat(), p.getKeluhan()});
        }
    }

    public void pilihPelanggan() {
    int row = view.getTablePelanggan().getSelectedRow();
    if (row != -1) {
        selectedPelangganId = Integer.parseInt(view.getTablePelanggan().getValueAt(row, 0).toString());
        Map<String, String> detail = pelangganDao.getDetailPembayaran(selectedPelangganId);
        
        if (!detail.isEmpty()) {
            view.getTxtNama().setText(detail.get("nama"));
            view.getTxtPlat().setText(detail.get("nomor_plat"));
            view.getTxtNamaSparepart().setText(detail.get("sparepart"));
            
            // 1. Tampilkan Harga Part Asli di txtHargaPart
            String hargaStr = detail.get("harga_part");
            hargaSparepart = (hargaStr != null) ? Integer.parseInt(hargaStr) : 0;
            view.getTxtHargaPart().setText(String.valueOf(hargaSparepart));
            
            // 2. Set awal Biaya Servis ke 0 jika baru diklik
            view.getTxtBiayaServis().setText("0");
            
            // 3. Tampilkan Kalkulasi Awal di txtTotalBayar (Harga Part + 0)
            view.getTxtTotalTagihan().setText(String.valueOf(hargaSparepart));
        }
    }
}

    public void hitungTotal() {
        try {
            // 1. Hitung total tagihan dari harga part + biaya servis
            int biayaServis = view.getTxtBiayaServis().getText().trim().isEmpty() ? 0 : Integer.parseInt(view.getTxtBiayaServis().getText().trim());
            int totalTagihan = hargaSparepart + biayaServis;
            view.getTxtTotalTagihan().setText(String.valueOf(totalTagihan));
            
            // 2. Hitung kembalian otomatis jika uang bayar sudah diinput
            String uangBayarStr = view.getTxtTotalBayar().getText().trim();
            if (!uangBayarStr.isEmpty()) {
                int uangBayar = Integer.parseInt(uangBayarStr);
                int kembalian = uangBayar - totalTagihan;
                view.getTxtKembalian().setText(String.valueOf(kembalian));
            } else {
                view.getTxtKembalian().setText("0");
            }
        } catch (NumberFormatException e) {
            // Abaikan eror kosmetik saat kasir sedang menghapus teks ketikan
        }
    }

    public void prosesBayar() {
        if (selectedPelangganId == -1) {
            JOptionPane.showMessageDialog(view, "Pilih pelanggan terlebih dahulu!");
            return;
        }

        // 1. Validasi awal (Deklarasi pertama totalTagihan)
        int totalTagihan = Integer.parseInt(view.getTxtTotalTagihan().getText());
        int uangBayar = view.getTxtTotalBayar().getText().trim().isEmpty() ? 0 : Integer.parseInt(view.getTxtTotalBayar().getText().trim());

        if (uangBayar < totalTagihan) {
            JOptionPane.showMessageDialog(view, "Transaksi Gagal: Uang yang dibayarkan kurang!");
            return;
        }

        hitungTotal(); // Memastikan kalkulasi realtime sinkron

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Proses pembayaran sebesar Rp. " + totalTagihan + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                // Ambil data teks dari komponen Form Pembayaran
                String nama = view.getTxtNama().getText();
                String plat = view.getTxtPlat().getText();
                String sparepart = view.getTxtNamaSparepart().getText();
                
                int totalPart = Integer.parseInt(view.getTxtHargaPart().getText());
                int biayaServis = view.getTxtBiayaServis().getText().trim().isEmpty() ? 0 : Integer.parseInt(view.getTxtBiayaServis().getText().trim());
                
                // CATATAN: Menggunakan variabel totalTagihan yang sudah dideklarasikan di atas (tidak pakai 'int' lagi)
                int totalBayar = uangBayar; 
                int kembalian = Integer.parseInt(view.getTxtKembalian().getText());

                // 2. Panggil method database yang sudah ditambah kolom barunya
                pelangganDao.simpanTransaksi(nama, plat, sparepart, totalPart, biayaServis, totalTagihan, totalBayar, kembalian);

                // 3. Hapus dari daftar antrean servis utama
                pelangganDao.delete(selectedPelangganId);

                JOptionPane.showMessageDialog(view, "Pembayaran Berhasil! Data telah disimpan ke riwayat transaksi.");

                tampilkanAntrean();
                bersihkanForm();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Gagal memproses transaksi: " + e.getMessage());
            }
        }
    }

    private void bersihkanForm() {
        selectedPelangganId = -1;
        hargaSparepart = 0;
        view.getTxtNama().setText("");
        view.getTxtPlat().setText("");
        view.getTxtNamaSparepart().setText("");
        view.getTxtHargaPart().setText("");
        view.getTxtBiayaServis().setText("");
        view.getTxtTotalTagihan().setText("");
        view.getTxtTotalBayar().setText(""); // Tambahkan ini
        view.getTxtKembalian().setText("");  // Tambahkan ini
    }
}
