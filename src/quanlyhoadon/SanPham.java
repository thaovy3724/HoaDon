/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author HP
 */
public abstract class SanPham implements Serializable {
     protected String masp, thuonghieu, tensp, loaisp;
     protected int sl;
     protected float gia;
     protected double thanhtien;
     public transient Scanner sc = new Scanner(System.in);
     public SanPham(){
         masp="";
         tensp="";
         thuonghieu="";
         loaisp="";
         sl =0;
         gia =0;
     }
    public SanPham(String masp, String thuonghieu, String tensp, String loaisp, int sl, float gia, double thanhtien) {
        this.masp = masp;
        this.thuonghieu = thuonghieu;
        this.tensp = tensp;
        this.loaisp = loaisp;
        this.sl = sl;
        this.gia = gia;
        this.thanhtien = thanhtien;
    }
    public SanPham(SanPham a){
        masp = a.masp;
        tensp = a.tensp;
        thuonghieu= a.thuonghieu;
        loaisp = a.loaisp;
        sl = a.sl;
        gia = a.gia;
    } 
    //SETTER
    public void setMasp(String masp) {
        this.masp = masp;
    }
    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }
    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }
    public void setSl(int sl) {
        this.sl = sl;
    }
    public void setGia(float gia) {
        this.gia = gia;
    }
    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
    //GETTER
    public String getMasp() {
        return masp;
    }
    public String getTensp() {
        return tensp;
    }
    public String getThuonghieu() {
        return thuonghieu;
    }
    public String getLoaisp() {
        return loaisp;
    }
    public int getSl() {
        return sl;
    }
    public float getGia() {
        return gia;
    }
    public double getThanhtien() {
        return thanhtien;
    }
    public abstract void Nhap();
    public abstract void Xuat();
    public void GhiFile(String filename) throws IOException{
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
        dos.writeUTF(masp);
        dos.writeUTF(tensp); 
        dos.writeUTF(thuonghieu); 
        dos.writeUTF(loaisp);
        dos.writeInt(sl);
        dos.writeFloat(gia);
        dos.writeDouble(thanhtien);
    }
}
