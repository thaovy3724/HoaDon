/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyhoadon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class Loai {
    private String maLoai;
    private String tenLoai;

    public Loai(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }
    
    public Loai(Loai l) {
        this.maLoai = l.maLoai;
        this.tenLoai = l.tenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
    public void nhap(){
        
    }
    
    public void xuat(){
        
    }
    
    public void ghiFile(String fileName) throws FileNotFoundException, IOException{
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName,Boolean.TRUE));
        dos.writeUTF(maLoai);
        dos.writeUTF(tenLoai);
    }
    
    public void docFile(String fileName) throws FileNotFoundException, IOException{
        DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
        maLoai = dis.readUTF();
        tenLoai = dis.readUTF();
    }
}
