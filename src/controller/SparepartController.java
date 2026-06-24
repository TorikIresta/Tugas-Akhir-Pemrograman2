/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SparepartDAO;
import dao.SparepartDAOImpl;
import model.Sparepart;
import view.SparepartForm;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ID_PC
 */
public class SparepartController {
    private SparepartForm view;
    private SparepartDAO dao;

    public SparepartController(SparepartForm view) {
        this.view = view;
        this.dao = new SparepartDAOImpl();
    }
    
    public void setNextIdKeForm() {
    int nextId = dao.getNextId();
    view.getTxtId().setText(String.valueOf(nextId));
    }

    public void tampilkanData() {
        List<Sparepart> list = dao.getAll();
        isiTabel(list);
    }

    public void cariData(String keyword) {
        List<Sparepart> list = dao.search(keyword);
        isiTabel(list);
    }

    private void isiTabel(List<Sparepart> list) {
        DefaultTableModel model = (DefaultTableModel) view.getTableSparepart().getModel();
        model.setRowCount(0);
        for (Sparepart s : list) {
            model.addRow(new Object[]{s.getId(), s.getNamaSparepart(), s.getHarga(), s.getStok()});
        }
    }

    public void simpanData() {
        if (view.getTxtNama().getText().trim().isEmpty() || view.getTxtHarga().getText().trim().isEmpty() || view.getTxtStok().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua data sparepart wajib diisi!");
            return;
        }

        int harga, stok;
        try {
            harga = Integer.parseInt(view.getTxtHarga().getText());
            stok = Integer.parseInt(view.getTxtStok().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga dan Stok harus berupa angka!");
            return;
        }

        Sparepart s = new Sparepart();
        s.setNamaSparepart(view.getTxtNama().getText());
        s.setHarga(harga);
        s.setStok(stok);

        dao.insert(s); // Langsung simpan baru
        JOptionPane.showMessageDialog(view, "Sparepart berhasil ditambahkan!");
        
        tampilkanData();
        bersihkanForm();
    }
    
    public void ubahData() {
        if (view.getTxtId().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data sparepart di tabel yang ingin diubah terlebih dahulu!");
            return;
        }

        if (view.getTxtNama().getText().trim().isEmpty() || view.getTxtHarga().getText().trim().isEmpty() || view.getTxtStok().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua data tidak boleh kosong saat diubah!");
            return;
        }

        int harga, stok;
        try {
            harga = Integer.parseInt(view.getTxtHarga().getText());
            stok = Integer.parseInt(view.getTxtStok().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga dan Stok harus berupa angka!");
            return;
        }

        Sparepart s = new Sparepart();
        s.setId(Integer.parseInt(view.getTxtId().getText())); // Ambil ID untuk diupdate
        s.setNamaSparepart(view.getTxtNama().getText());
        s.setHarga(harga);
        s.setStok(stok);

        dao.update(s); // Jalankan update database
        JOptionPane.showMessageDialog(view, "Sparepart berhasil diubah!");
        
        tampilkanData();
        bersihkanForm();
    }

    public void hapusData() {
        if (!view.getTxtId().getText().isEmpty()) {
            int id = Integer.parseInt(view.getTxtId().getText());
            int konfirmasi = JOptionPane.showConfirmDialog(view, "Yakin ingin menghapus sparepart ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                dao.delete(id);
                JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                tampilkanData();
                bersihkanForm();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Pilih data sparepart di tabel terlebih dahulu!");
        }
    }

    public void bersihkanForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtHarga().setText("");
        view.getTxtStok().setText("");
        
        setNextIdKeForm();
    }
}
