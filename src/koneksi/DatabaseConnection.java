/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ID_PC
 */
public class DatabaseConnection {
    private static Connection koneksi;
    
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                // Sesuaikan nama database dengan 'db_bengkel'
                String url = "jdbc:mysql://localhost:3306/db_bengkel";
                String user = "root";
                String password = ""; // Kosongkan jika menggunakan XAMPP bawaan
                
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi ke Database Bengkel Sukses!");
            } catch (SQLException e) {
                System.out.println("Koneksi Gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}
