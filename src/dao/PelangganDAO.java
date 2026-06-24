/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Pelanggan;
import java.util.List;

/**
 *
 * @author ID_PC
 */
public interface PelangganDAO {
    void insert(Pelanggan p);
    void update(Pelanggan p);
    void delete(int id);
    List<Pelanggan> getAll();
    List<Pelanggan> search(String keyword); // Fitur Cari

    int getNextId();
    
    java.util.Map<String, String> getDetailPembayaran(int pelangganId);
    void simpanTransaksi(String nama, String plat, String sparepart, int hargaPart, int biayaServis, int totalTagihan, int totalBayar, int kembalian);
    java.util.List<java.util.Map<String, Object>> getAllTransaksi();
    java.util.List<java.util.Map<String, Object>> searchTransaksi(String keyword);
}

