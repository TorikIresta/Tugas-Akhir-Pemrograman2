/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ID_PC
 */
public class Pelanggan {
    private int id;
    private String nama;
    private String nomorPlat;
    private String keluhan;

    // Constructor Kosong
    public Pelanggan() {}

    // Constructor dengan Parameter
    public Pelanggan(int id, String nama, String nomorPlat, String keluhan) {
        this.id = id;
        this.nama = nama;
        this.nomorPlat = nomorPlat;
        this.keluhan = keluhan;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNomorPlat() { return nomorPlat; }
    public void setNomorPlat(String nomorPlat) { this.nomorPlat = nomorPlat; }

    public String getKeluhan() { return keluhan; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }
}
