/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import koneksi.DatabaseConnection;
import model.Sparepart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ID_PC
 */
public class SparepartDAOImpl implements SparepartDAO {
    private Connection conn = DatabaseConnection.getKoneksi();

    @Override
    public int getNextId() {
        String sql = "SHOW TABLE STATUS LIKE 't_sparepart'";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("Auto_increment");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 1; 
    }
    
    public void insert(Sparepart s) {
        String sql = "INSERT INTO t_sparepart (nama_sparepart, harga, stok) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNamaSparepart());
            ps.setInt(2, s.getHarga());
            ps.setInt(3, s.getStok());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void update(Sparepart s) {
        String sql = "UPDATE t_sparepart SET nama_sparepart=?, harga=?, stok=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNamaSparepart());
            ps.setInt(2, s.getHarga());
            ps.setInt(3, s.getStok());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM t_sparepart WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<Sparepart> getAll() {
        List<Sparepart> list = new ArrayList<>();
        String sql = "SELECT * FROM t_sparepart";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Sparepart(rs.getInt("id"), rs.getString("nama_sparepart"), 
                        rs.getInt("harga"), rs.getInt("stok")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public List<Sparepart> search(String keyword) {
        List<Sparepart> list = new ArrayList<>();
        String sql = "SELECT * FROM t_sparepart WHERE nama_sparepart LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Sparepart(rs.getInt("id"), rs.getString("nama_sparepart"), 
                            rs.getInt("harga"), rs.getInt("stok")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }
    
}
