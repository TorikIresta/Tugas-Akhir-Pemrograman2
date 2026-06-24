/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PelangganDAO;
import dao.PelangganDAOImpl;
import model.Pelanggan;
import view.PelangganForm;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ID_PC
 */
public class PelangganController {
    private PelangganForm view;
    private PelangganDAO dao;

    public PelangganController(PelangganForm view) {
        this.view = view;
        this.dao = new PelangganDAOImpl();
    }

    public void setNextIdKeForm() {
    int nextId = dao.getNextId();
    view.getTxtId().setText(String.valueOf(nextId));
    }
    
    public void tampilkanData() {
        List<Pelanggan> list = dao.getAll();
        isiTabel(list);
    }

    public void cariData(String keyword) {
        List<Pelanggan> list = dao.search(keyword);
        isiTabel(list);
    }

    private void isiTabel(List<Pelanggan> list) {
        DefaultTableModel model = (DefaultTableModel) view.getTablePelanggan().getModel();
        model.setRowCount(0);
        for (Pelanggan p : list) {
            model.addRow(new Object[]{p.getId(), p.getNama(), p.getNomorPlat(), p.getKeluhan()});
        }
    }

    public void simpanData() {
    // Validasi Input Wajib Tidak Boleh Kosong
    if (view.getTxtNama().getText().trim().isEmpty() || view.getTxtPlat().getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Nama dan Nomor Plat wajib diisi!");
        return;
    }

    Pelanggan p = new Pelanggan();
    p.setNama(view.getTxtNama().getText());
    p.setNomorPlat(view.getTxtPlat().getText());
    p.setKeluhan(view.getCmbKeluhan().getSelectedItem().toString());

    dao.insert(p); // Langsung panggil insert tanpa IF-ELSE
    JOptionPane.showMessageDialog(view, "Data pelanggan berhasil ditambahkan!");
    
    tampilkanData();
    bersihkanForm();
    }
    
    public void ubahData() {
    // Validasi apakah user sudah memilih data dari tabel (ID tidak boleh kosong)
    if (view.getTxtId().getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Pilih data pelanggan yang ingin diubah dari tabel terlebih dahulu!");
        return;
    }
    
    if (view.getTxtNama().getText().trim().isEmpty() || view.getTxtPlat().getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Nama dan Nomor Plat tidak boleh kosong saat diubah!");
        return;
    }
    
    if (view.getCmbKeluhan().getSelectedIndex() == 0) {
    JOptionPane.showMessageDialog(view, "Silakan pilih keluhan sparepart terlebih dahulu!");
    return;
}

    Pelanggan p = new Pelanggan();
    p.setId(Integer.parseInt(view.getTxtId().getText())); // Ambil ID yang mau di-update
    p.setNama(view.getTxtNama().getText());
    p.setNomorPlat(view.getTxtPlat().getText());
    p.setKeluhan(view.getCmbKeluhan().getSelectedItem().toString());

    dao.update(p); // Langsung panggil update
    JOptionPane.showMessageDialog(view, "Data pelanggan berhasil diubah!");
    
    tampilkanData();
    bersihkanForm();
    }

    public void hapusData() {
        if (!view.getTxtId().getText().isEmpty()) {
            int id = Integer.parseInt(view.getTxtId().getText());
            int konfirmasi = JOptionPane.showConfirmDialog(view, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                dao.delete(id);
                JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                tampilkanData();
                bersihkanForm();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Pilih data yang ingin dihapus dari tabel terlebih dahulu!");
        }
    }
    
    public void muatDaftarSparepartKeCombo() {
    // Kita panggil SparepartDAOImpl untuk mengambil data sparepart
    dao.SparepartDAO sparepartDao = new dao.SparepartDAOImpl();
    List<model.Sparepart> listSparepart = sparepartDao.getAll();
    
    // Kosongkan isi combo box terlebih dahulu
    view.getCmbKeluhan().removeAllItems();
    
    // Beri pilihan default di baris pertama
    view.getCmbKeluhan().addItem("-- Pilih Kerusakan / Sparepart --");
    
    // Masukkan semua nama sparepart ke dalam combo box
    for (model.Sparepart s : listSparepart) {
        view.getCmbKeluhan().addItem(s.getNamaSparepart());
    }
}

    public void bersihkanForm() {
    view.getTxtId().setText(""); // kosongkan dulu
    view.getTxtNama().setText("");
    view.getTxtPlat().setText("");
    view.getCmbKeluhan().setSelectedIndex(0);
    
    setNextIdKeForm(); 
    }

    // Poin 9: Fitur Cetak Laporan Terformat
    public void cetakLaporan() {
        List<Pelanggan> list = dao.getAll();
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("          LAPORAN DATA PELANGGAN BENGKEL          \n");
        sb.append("==================================================\n");
        sb.append(String.format("%-5s %-20s %-15s\n", "ID", "Nama Pelanggan", "No. Plat"));
        sb.append("--------------------------------------------------\n");
        for (Pelanggan p : list) {
            sb.append(String.format("%-5d %-20s %-15s\n", p.getId(), p.getNama(), p.getNomorPlat()));
        }
        sb.append("--------------------------------------------------\n");
        sb.append("Total Antrean: " + list.size() + " Kendaraan\n");

        javax.swing.JTextArea textArea = new javax.swing.JTextArea(sb.toString());
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(view, new javax.swing.JScrollPane(textArea), "Cetak Laporan Antrean", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
