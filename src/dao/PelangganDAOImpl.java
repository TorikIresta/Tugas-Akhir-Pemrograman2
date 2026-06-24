/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import koneksi.DatabaseConnection;
import model.Pelanggan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ID_PC
 */
public class PelangganDAOImpl implements PelangganDAO {
    private Connection conn = DatabaseConnection.getKoneksi();

    @Override
    public int getNextId() {
        String sql = "SHOW TABLE STATUS LIKE 't_pelanggan'";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("Auto_increment");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 1; 
    }
    
    @Override
    public void insert(Pelanggan p) {
        String sql = "INSERT INTO t_pelanggan (nama, nomor_plat, keluhan) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getNomorPlat());
            ps.setString(3, p.getKeluhan());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public void update(Pelanggan p) {
        String sql = "UPDATE t_pelanggan SET nama=?, nomor_plat=?, keluhan=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getNomorPlat());
            ps.setString(3, p.getKeluhan());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM t_pelanggan WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public List<Pelanggan> getAll() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM t_pelanggan";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Pelanggan(rs.getInt("id"), rs.getString("nama"), 
                        rs.getString("nomor_plat"), rs.getString("keluhan")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    @Override
    public List<Pelanggan> search(String keyword) {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM t_pelanggan WHERE nama LIKE ? OR nomor_plat LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Pelanggan(rs.getInt("id"), rs.getString("nama"), 
                            rs.getString("nomor_plat"), rs.getString("keluhan")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }
    
    @Override
    public java.util.Map<String, String> getDetailPembayaran(int pelangganId) {
        java.util.Map<String, String> data = new java.util.HashMap<>();

        // 1. Ambil teks keluhan pelanggan dari database
        String sqlPelanggan = "SELECT nama, nomor_plat, keluhan FROM t_pelanggan WHERE id = ?";
        String nama = "", plat = "", keluhan = "";

        try (PreparedStatement ps = conn.prepareStatement(sqlPelanggan)) {
            ps.setInt(1, pelangganId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nama = rs.getString("nama");
                    plat = rs.getString("nomor_plat");
                    keluhan = rs.getString("keluhan");
                } else {
                    return data; // Keluar jika data pelanggan tidak ada
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        // 2. Ambil semua data sparepart untuk dicocokkan dengan teks keluhan
        int totalHargaPart = 0;
        String sqlSparepart = "SELECT nama_sparepart, harga FROM t_sparepart";

        try (PreparedStatement ps = conn.prepareStatement(sqlSparepart);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String namaPart = rs.getString("nama_sparepart");
                int hargaPart = rs.getInt("harga");

                // Jika teks keluhan pelanggan mengandung nama sparepart ini, akumulasikan harganya
                if (keluhan.toLowerCase().contains(namaPart.toLowerCase())) {
                    totalHargaPart += hargaPart;
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        // 3. Kembalikan data yang siap ditampilkan di form pembayaran
        data.put("nama", nama);
        data.put("nomor_plat", plat);
        data.put("sparepart", keluhan);
        data.put("harga_part", String.valueOf(totalHargaPart));

        return data;
    }   
    
    @Override
    public void simpanTransaksi(String nama, String plat, String sparepart, int hargaPart, int biayaServis, int totalTagihan, int totalBayar, int kembalian) {
        String sql = "INSERT INTO t_transaksi (nama_pelanggan, nomor_plat, sparepart_diganti, total_harga_part, biaya_servis, total_tagihan, total_bayar, kembalian) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, plat);
            ps.setString(3, sparepart);
            ps.setInt(4, hargaPart);
            ps.setInt(5, biayaServis);
            ps.setInt(6, totalTagihan);
            ps.setInt(7, totalBayar);
            ps.setInt(8, kembalian);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getAllTransaksi() {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        // Mengambil langsung semua data secara urut dari t_transaksi
        String sql = "SELECT id_transaksi, nama_pelanggan, nomor_plat, sparepart_diganti, " +
                     "total_harga_part, biaya_servis, total_tagihan, total_bayar, kembalian " +
                     "FROM t_transaksi ORDER BY id_transaksi ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                java.util.Map<String, Object> map = new java.util.HashMap<>();
                // Kunci nama key map agar konsisten
                map.put("id_pel", rs.getInt("id_transaksi")); // Ditampilkan sementara sebagai ID di kolom pertama
                map.put("nama", rs.getString("nama_pelanggan"));
                map.put("plat", rs.getString("nomor_plat"));
                map.put("sparepart", rs.getString("sparepart_diganti"));
                map.put("harga_part", rs.getInt("total_harga_part"));
                map.put("biaya_servis", rs.getInt("biaya_servis"));
                map.put("total_tagihan", rs.getInt("total_tagihan"));
                map.put("total_bayar", rs.getInt("total_bayar"));
                map.put("kembalian", rs.getInt("kembalian"));
                list.add(map);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> searchTransaksi(String keyword) {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        String sql = "SELECT id_transaksi, nama_pelanggan, nomor_plat, sparepart_diganti, " +
                     "total_harga_part, biaya_servis, total_tagihan, total_bayar, kembalian " +
                     "FROM t_transaksi WHERE nama_pelanggan LIKE ? OR nomor_plat LIKE ? " +
                     "ORDER BY id_transaksi ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    java.util.Map<String, Object> map = new java.util.HashMap<>();
                    map.put("id_pel", rs.getInt("id_transaksi"));
                    map.put("nama", rs.getString("nama_pelanggan"));
                    map.put("plat", rs.getString("nomor_plat"));
                    map.put("sparepart", rs.getString("sparepart_diganti"));
                    map.put("harga_part", rs.getInt("total_harga_part"));
                    map.put("biaya_servis", rs.getInt("biaya_servis"));
                    map.put("total_tagihan", rs.getInt("total_tagihan"));
                    map.put("total_bayar", rs.getInt("total_bayar"));
                    map.put("kembalian", rs.getInt("kembalian"));
                    list.add(map);
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }
}


