/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Sparepart;
import java.util.List;
/**
 *
 * @author ID_PC
 */
public interface SparepartDAO {
    void insert(Sparepart s);
    void update(Sparepart s);
    void delete(int id);
    List<Sparepart> getAll();
    List<Sparepart> search(String keyword); // Fitur Cari
    
    int getNextId();
}