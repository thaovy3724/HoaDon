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
public class NhapDanhSachPhieuNhapHang {
	public static void main(String[] args) {
		DanhSachPhieuNhapHang dsph = new DanhSachPhieuNhapHang();
//		dsph.NhapDanhSach();
		dsph.DocFileJava("PhieuNhapHang.txt");
		dsph.XuatDanhSach();
		dsph.ThaoTac();
	}
}