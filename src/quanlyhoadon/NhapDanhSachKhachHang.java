/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;

/**
 *
 * @author HP
 */
public class NhapDanhSachKhachHang {
	public static void main(String[] args) {
		DanhSachKhachHang dsncc = new DanhSachKhachHang();	
		dsncc.NhapDanhSach();
		dsncc.DocFileJava("KhachHang.txt");
		dsncc.XuatDanhSach();
		dsncc.ThaoTac();
	}
}
