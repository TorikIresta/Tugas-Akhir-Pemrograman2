/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PelangganDAO;
import dao.PelangganDAOImpl;
import view.TransaksiForm;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author ID_PC
 */
public class TransaksiController {
    private TransaksiForm view;
    private PelangganDAO dao;

    public TransaksiController(TransaksiForm view) {
        this.view = view;
        this.dao = new PelangganDAOImpl();
    }

    public void tampilkanRiwayatTransaksi() {
        List<Map<String, Object>> list = dao.getAllTransaksi();
        isiTabel(list);
    }

    public void cariTransaksi(String keyword) {
        List<Map<String, Object>> list = dao.searchTransaksi(keyword);
        isiTabel(list);
    }

    private void isiTabel(List<Map<String, Object>> list) {
    DefaultTableModel model = (DefaultTableModel) view.getTablePelanggan().getModel();
    model.setRowCount(0);
    
    // Modifikasi tipis di dalam perulangan 'for' method isiTabel() Anda:
    for (Map<String, Object> m : list) {
        int hargaPart = (int) m.get("harga_part");
        int biayaServis = (int) m.get("biaya_servis");

        int totalTagihan = (int) m.get("total_tagihan");
        if (totalTagihan == 0) {
            totalTagihan = hargaPart + biayaServis; // Hitung manual jika data lama bernilai 0
        }

        int totalBayar = (int) m.get("total_bayar");

        int kembalian = (int) m.get("kembalian");
        if (kembalian == 0 && totalBayar > totalTagihan) {
            kembalian = totalBayar - totalTagihan; // Hitung manual jika data lama bernilai 0
        }

        model.addRow(new Object[]{
            m.get("id_pel"),
            m.get("nama"),
            m.get("plat"),
            m.get("sparepart"),
            hargaPart,
            biayaServis,
            totalTagihan,
            totalBayar,
            kembalian
        });
    }
}
    
    public void pilihBaris() {
    int row = view.getTablePelanggan().getSelectedRow();
    if (row != -1) {
        // Mengambil teks nama dari kolom indeks 1
        view.getTxtNama().setText(view.getTablePelanggan().getValueAt(row, 1).toString());
    }
}
    
    public void cetakStruk() {
    int row = view.getTablePelanggan().getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(view, "Silakan pilih baris transaksi di tabel yang ingin dicetak struknya!");
        return;
    }
        String idPelanggan = view.getTablePelanggan().getValueAt(row, 0).toString();
        String nama = view.getTablePelanggan().getValueAt(row, 1).toString();
        String plat = view.getTablePelanggan().getValueAt(row, 2).toString();
        String part = view.getTablePelanggan().getValueAt(row, 3).toString();
        String hargaPart = view.getTablePelanggan().getValueAt(row, 4).toString();
        String biayaServis = view.getTablePelanggan().getValueAt(row, 5).toString();
        String total = view.getTablePelanggan().getValueAt(row, 6).toString();
        String bayar = view.getTablePelanggan().getValueAt(row, 7).toString();
        String kembali = view.getTablePelanggan().getValueAt(row, 8).toString();
    // Menyusun format nota teks bengkel
    String strukTeks = "=========================================\n"
                     + "      NOTA BENGKEL MOTOR TORIK JAYA      \n"
                     + "=========================================\n"
                     + " Id Pelanggan  : " + idPelanggan + "\n"
                     + " Nama Pelanggan: " + nama + "\n"
                     + " No. Plat      : " + plat + "\n"
                     + "-----------------------------------------\n"
                     + " Part/Kerusakan: " + part + "\n"
                     + " Total Part    : Rp. " + hargaPart + "\n"
                     + " Biaya Jasa    : Rp. " + biayaServis + "\n"
                     + "-----------------------------------------\n"
                     + " TOTAL TAGIHAN : Rp. " + total + "\n"
                     + " TUNAI         : Rp. " + bayar + "\n"
                     + " KEMBALIAN     : Rp. " + kembali + "\n"
                     + "=========================================\n"
                     + "      Terima Kasih Atas Kunjungan Anda   \n"
                     + "=========================================\n";

    // Membuat komponen JTextArea khusus untuk preview visual struk
    javax.swing.JTextArea txtPreview = new javax.swing.JTextArea(strukTeks);
    txtPreview.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12)); // Font struk kasir monospaced
    txtPreview.setEditable(false);
    txtPreview.setBackground(new java.awt.Color(245, 245, 245));
    
    // Membungkus ke dalam JScrollPane agar rapi kalau teksnya panjang
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(txtPreview);
    scrollPane.setPreferredSize(new java.awt.Dimension(320, 380));

    // Membuat tombol kustom untuk jendela pop-up preview
    Object[] options = {"Print Sekarang", "Batal"};
    
    int pilihan = JOptionPane.showOptionDialog(
            view, 
            scrollPane, 
            "Preview Struk Kasir", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            options, 
            options[0]
    );

    // Jika kasir mengklik "Print Sekarang", baru dialog printer sistem operasi dimunculkan
    if (pilihan == JOptionPane.OK_OPTION) {
        try {
            boolean complete = txtPreview.print();
            if (complete) {
                JOptionPane.showMessageDialog(view, "Struk berhasil dicetak!");
            }
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(view, "Gagal melakukan cetak: " + e.getMessage());
        }
    }
}
    public void cetakLaporanHarian() {
    DefaultTableModel model = (DefaultTableModel) view.getTablePelanggan().getModel();
    int rowCount = model.getRowCount();
    
    if (rowCount == 0) {
        JOptionPane.showMessageDialog(view, "Tidak ada data transaksi hari ini untuk dilaporkan!");
        return;
    }

    // 1. Membuat Header Laporan Penjualan Harian
    StringBuilder laporanText = new StringBuilder();
    laporanText.append("=========================================================================================\n");
    laporanText.append("                     LAPORAN PENJUALAN HARIAN BENGKEL MOTOR TORIK JAYA                   \n");
    laporanText.append("=========================================================================================\n");
    laporanText.append(String.format("| %-5s | %-15s | %-11s | %-12s | %-12s | %-12s |\n", 
            "ID Tx", "Nama Pelanggan", "Plat Nomor", "Total Part", "Biaya Jasa", "Total Bayar"));
    laporanText.append("-----------------------------------------------------------------------------------------\n");

    int akumulasiPart = 0;
    int akumulasiServis = 0;
    int akumulasiPendapatan = 0;

    // 2. Mengambil data dari JTable untuk dimasukkan ke teks laporan & menghitung omzet
    for (int i = 0; i < rowCount; i++) {
        String idPel = model.getValueAt(i, 0).toString(); // Ambil kolom ID Pelanggan
        String nama = model.getValueAt(i, 1).toString();
        String plat = model.getValueAt(i, 2).toString();
        
        int hargaPart = Integer.parseInt(model.getValueAt(i, 4).toString());
        int biayaServis = Integer.parseInt(model.getValueAt(i, 5).toString());
        int totalTagihan = Integer.parseInt(model.getValueAt(i, 6).toString());

        // Akumulasi total keseluruhan di bagian bawah laporan
        akumulasiPart += hargaPart;
        akumulasiServis += biayaServis;
        akumulasiPendapatan += totalTagihan;

        // PERBAIKAN FORMAT: Memasukkan idPel agar pas menjadi 6 kolom laporan teks
        laporanText.append(String.format("| %-5s | %-15s | %-11s | Rp.%-9d | Rp.%-9d | Rp.%-9d |\n", 
               idPel, nama, plat, hargaPart, biayaServis, totalTagihan));
    }

    // 3. Membuat Footer / Ringkasan Total Keuangan Kasir Bengkel
    laporanText.append("-----------------------------------------------------------------------------------------\n");
    laporanText.append(String.format(" TOTAL PENDAPATAN SPAREPART : Rp. %d\n", akumulasiPart));
    laporanText.append(String.format(" TOTAL PENDAPATAN JASA/SERVIS: Rp. %d\n", akumulasiServis));
    laporanText.append("=========================================================================================\n");
    laporanText.append(String.format(" GRAND TOTAL OMZET HARIAN   : Rp. %d\n", akumulasiPendapatan));
    laporanText.append("=========================================================================================\n");

    // 4. Membuat JTextArea untuk memunculkan Jendela Pop-up Preview Laporan
    javax.swing.JTextArea txtPreview = new javax.swing.JTextArea(laporanText.toString());
    txtPreview.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12)); // Font lurus khusus tabel text
    txtPreview.setEditable(false);
    txtPreview.setBackground(new java.awt.Color(250, 250, 250));

    // Membungkus panel laporan dengan scrollbar karena datanya pasti lebar dan panjang
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(txtPreview);
    scrollPane.setPreferredSize(new java.awt.Dimension(720, 450));

    // Membuat tombol konfirmasi kustom pada pop-up
    Object[] options = {"Print Laporan (PDF)", "Batal"};
    
    int pilihan = JOptionPane.showOptionDialog(
            view, 
            scrollPane, 
            "Preview Rekap Laporan Penjualan Harian", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            options, 
            options[0]
    );

    // 5. Pemicu cetak fisik atau cetak ke PDF jika tombol ditekan
    if (pilihan == JOptionPane.OK_OPTION) {
        try {
            boolean complete = txtPreview.print();
            if (complete) {
                JOptionPane.showMessageDialog(view, "Laporan rekap harian berhasil dicetak!");
            }
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(view, "Gagal melakukan cetak laporan: " + e.getMessage());
        }
    }
    }
}