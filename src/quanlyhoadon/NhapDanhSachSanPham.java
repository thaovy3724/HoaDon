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
public class NhapDanhSachSanPham {
    public static void main(String[] args) {
		DanhSachSanPham dssp = new DanhSachSanPham();	
//		dssp.NhapDanhSach();
		dssp.DocFileJava("SanPham.txt");
		dssp.XuatDanhSach();
		dssp.ThaoTac();
	}
}
