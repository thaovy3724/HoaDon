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
public class NhapDanhSachNhanVien {
	public static void main(String[] args) {
		DanhSachNhanVien dsnv = new DanhSachNhanVien();
		dsnv.NhapDanhSachNV();
		dsnv.DocFileJava("NhanVien.txt");
		dsnv.XuatDanhSachNV();
		dsnv.ThaoTac();
	}
}
