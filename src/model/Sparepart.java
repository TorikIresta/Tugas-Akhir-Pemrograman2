/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ID_PC
 */
public class Sparepart {
    private int id;
    private String namaSparepart;
    private int harga;
    private int stok;

    // Constructor Kosong
    public Sparepart() {}

    // Constructor dengan Parameter
    public Sparepart(int id, String namaSparepart, int harga, int stok) {
        this.id = id;
        this.namaSparepart = namaSparepart;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaSparepart() { return namaSparepart; }
    public void setNamaSparepart(String namaSparepart) { this.namaSparepart = namaSparepart; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}
