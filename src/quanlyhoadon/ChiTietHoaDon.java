/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyhoadon;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class ChiTietHoaDon {
    DanhSachSanPham dssp = new DanhSachSanPham();
    private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietHoaDon() {
    }
    
    public ChiTietHoaDon(String maHoaDon, String maSanPham, int soLuong, double donGia, double thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
    
    public ChiTietHoaDon(ChiTietHoaDon c) {
        this.maHoaDon = c.maHoaDon;
        this.maSanPham = c.maSanPham;
        this.soLuong = c.soLuong;
        this.donGia = c.donGia;
        this.thanhTien = c.thanhTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    public void nhap(String ma){
        Scanner sc = new Scanner(System.in);
        maHoaDon = ma;
//        System.out.print("Nhap ma san pham: ");
//        maSanPham = sc.nextLine();
        do {
            dssp.DocFileJava("SanPham.txt");
            dssp.XuatDanhSach();
            System.out.print("Nhập vào mã sản phẩm: ");
		maSanPham = sc.nextLine();
		if(!dssp.Search_MaSP(maSanPham)) {
                    System.err.println("\nKhông tìm thấy mã sản phẩm mà bạn vừa nhập!!!");
	}
	}while(!dssp.Search_MaSP(maSanPham));	
        System.out.print("Nhap so luong: ");
        soLuong = sc.nextInt();
        System.out.print("Nhap don gia: ");
        donGia = dssp.Search_DG(maSanPham);
        thanhTien = soLuong * donGia;  
    }
    
    public void xuat(){
        System.out.format(" %10s | %10s | %10s | %20s | %20s \n", maHoaDon, maSanPham, soLuong, donGia, thanhTien);
//        System.out.println("Ma hoa don: " + maHoaDon);
//        System.out.println("Ma san pham: " + maSanPham);
//        System.out.println("So luong: " + soLuong);
//        System.out.println("Don gia: " + donGia);
//        System.out.println("Thanh tien: " + thanhTien);
    }
    
    public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(maHoaDon);
                dos.writeUTF(maSanPham);
                dos.writeInt(soLuong);
		dos.writeDouble(donGia);
                thanhTien = soLuong * donGia;
                dos.writeDouble(thanhTien);
		dos.close();
    }

}
