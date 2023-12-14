/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class NhapDanhSachNhaCungCap {
	public static void main(String[] args) {
		DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();	
		dsncc.NhapDanhSach();
		dsncc.DocFileJava("NhaCungCap.txt");
		dsncc.XuatDanhSach();
		dsncc.ThaoTac();
	}
}