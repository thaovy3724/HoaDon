/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author HP
 */

public class DanhSachNhaCungCap implements ThaoTac {
	NhaCungCap[] ncc;
	private int n, a = 0;
	Scanner sc = new Scanner(System.in);
	//CONSTRUCTOR
	public DanhSachNhaCungCap(){}
	//CÁC HÀM NHẬP XUẤT
	public void NhapDanhSach() {
		System.out.print("Nhập vào số lượng nhà cung cấp: ");
		n = sc.nextInt();
		ncc = new NhaCungCap[n];
		for(int i = 0; i < n; i++) {
			System.out.print("\n\t\t\t\t\tNHÀ CUNG CẤP THỨ " + (i + 1) + "\n");
			ncc[i] = new NhaCungCap();
			ncc[i].Nhap();
			GhiFileJava("NhaCungCap.txt");
			if(i > 0){
				ThayDoiMNCC(i);
			}
		}
	}
	public void XuatDanhSach() {
	        System.out.println("\t\t\t\t\t\t\t\t\t\t=======DANH SÁCH NHÀ CUNG CẤP=======");
	        System.out.println("==========================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %15s | %15s | %13s | %30s ||\n", "STT", "MÃ NCC", "HỌ VÀ TÊN LÓT CỦA NHÀ CUNG CẤP", "TÊN", "GIỚI", "NGÀY SINH", "SĐT", "ĐỊA CHỈ");
		try {
		    for(int i = 0; i < n; i++) {
			System.out.format("|| %4s |",(i + 1));
			ncc[i].Xuat();
		    }	
		}catch(NullPointerException npe) {}	
		System.out.println("==========================================================================================================================================================");
	}
	//CÁC HÀM KIỂM TRA THUỘC TÍNH
	public void ThayDoiMNCC(int i) {
		String nhacungcap;
		String mancc = ncc[i].getMancc();
		do {
		   if(KiemTraMNCC(mancc, i)) {
			System.out.println();
			XuatDanhSach();
			System.err.println("\nNha cung cấp thứ " + (i+1) + " có mã " + mancc + " bị trùng mã nhân viên. Ấn enter để tiếp tục");
			sc.nextLine();
			System.err.print("Nhập lại mã cho nhà cung cấp:  ");
			nhacungcap = sc.nextLine();
			ncc[i].setMancc(nhacungcap);
			GhiFileJava("NhaCungCap.txt");
			mancc = ncc[i].getMancc();
		    }
		}while(KiemTraMNCC(ncc[i].getMancc(), i));
	}
	public boolean KiemTraMNCC(String manhanvien, int k) {
		DocFileJava("NhaCungCap.txt");
		for(int i = a - 1; i >= 0; i--) {
			if(ncc[i].getMancc().indexOf(manhanvien) != -1 && i != k) {
				return true;
			}
		}
		return false;
	}
	//CÁC THAO TÁC TRÊN DANH SÁCH KHÁCH HÀNG
	//=====ĐƯA DỮ LIỆU CỦA KHÁCH HÀNG VÀO HOÁ ĐƠN=====
	public String TruyenDuLieu_NCC(String manhacungcap) {
		for(int i = 0; i < n; i++) {
			if(ncc[i].getMancc().indexOf(manhacungcap) != -1) {
				return ncc[i].ho + " " + ncc[i].ten;
			}
		}
		return null;
	}
	//=====KIỂM TRA SỰ TỒN TẠI CỦA KHÁCH HÀNG TRONG DANH SÁCH=====
	public boolean Search_Exist(String manhacungcap) {
		for(int i = 0; i < n; i++) {
			if(ncc[i].getMancc().indexOf(manhacungcap) != -1){
				return true;
			}
		}
		return false;
	}
	//======TÌM KIẾM THEO HỌ VÀ TÊN LÓT, TÊN=======
	public boolean Search_Name(String name) {
		for(int i = 0; i < n; i++) {
			if(ncc[i].ho.indexOf(name) != -1) {
				return true;
			}
			else if(ncc[i].ten.indexOf(name) != -1) {
				return true;
			}
		}
		return false;
	}
	//THÊM MỘT NHÀ CUNG CÁP VÀO DANH SÁCH
	@Override
	public void Insert(int sl) {
		ncc = Arrays.copyOf(ncc, n + sl);
		int j = n + sl;
		j -= sl;
		n += sl;
		for(int i = j; i < n; i++) {
			ncc[i] = new NhaCungCap();
			System.out.print("\n\t\t\t\t\tNHÀ CUNG CẤP THỨ " + (i + 1) + "\n");
			ncc[i].Nhap();
			GhiFileJava("NhaCungCap.txt");
			ThayDoiMNCC(i);
		}
		
	}
	//=====XOÁ MỘT NHÀ CUNG CẤP TRONG DANH SÁCH=====
	@Override
	public void Delete(String manv) {
		boolean flag = false;
		int j = 0;
		for(int i = 0; i < n; i++) {
			if(ncc[i].getMancc().indexOf(manv) != -1) {
				flag = true;
				j = i;
				break;
			}
		}
		for(int i = j; i < n - 1; i++) {
			ncc[i] = ncc[i + 1];
		} 
		n--;
		GhiFileJava("NhaCungCap.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công khách hàng");
		else 
			System.err.println("\nKhông tìm thấy nhà cung cấp cần xoá");
	}
	//====SỬA THÔNG TIN NHÀ CUNG CẤP=====
	@Override
	public void Adjust(String manv) {
		int select = 0;
		do {
			System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Sửa chi tiết nhà cung cấp");
			System.out.println("\t\t\t\t\t2.Thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			if(select < 1 || select > 2)
				System.err.println("\nBạn đã nhập sai lựa chọn của mình. Xin mời nhập lại");
		}while(select < 1 || select > 2);
		for(int i = 0; i < n; i++) {
			if(ncc[i].getMancc().indexOf(manv) != -1) {
				if(select == 1) {
					System.out.print("Sửa thông tin của nhân viên thứ " + (i + 1) + "\n");
					System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=======");
					System.out.println("==========================================================================================================================================================");
		                        System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %30s ||\n", "STT", "MÃ NCC", "HỌ VÀ TÊN LÓT CỦA NHÀ CUNG CẤP", "TÊN", "GIỚI", "NGÀY SINH", "SĐT", "ĐỊA CHỈ");
					System.out.format("|| %4s |", (i + 1));
					ncc[i].Xuat();
					System.out.println("==========================================================================================================================================================");
					ncc[i] = new NhaCungCap();
					ncc[i].Nhap();
					GhiFileJava("NhaCungCap.txt");
					ThayDoiMNCC(i);
					return;
				}
				else
					return;
				
			}
		}
	}
	//=====TÌM KIẾM NHÀ CUNG CẤP=====
	public void Search_MaKhachHang(String mancc) {
		System.out.println("\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("==========================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %30s ||\n", "STT", "MÃ NCC", "HỌ VÀ TÊN LÓT CỦA NHÀ CUNG CẤP", "TÊN", "GIỚI", "NGÀY SINH", "SĐT", "ĐỊA CHỈ");
		for(int i = 0; i < n; i++) {
			if(ncc[i].getMancc().indexOf(mancc) != -1) {
				System.out.format("|| %4s |", i + 1);
				ncc[i].Xuat();	
				break;
			}
		}
		System.out.println("==========================================================================================================================================================");
	}
	public void Search_HoTenLot(String ho) {
		System.out.println("\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("==========================================================================================================================================================");
	        System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %30s ||\n", "STT", "MÃ NCC", "HỌ VÀ TÊN LÓT CỦA NHÀ CUNG CẤP", "TÊN", "GIỚI", "NGÀY SINH", "SĐT", "ĐỊA CHỈ");
		for(int i = 0; i < n; i++) {
			if(ncc[i].getHo().indexOf(ho) != -1) {
				System.out.format("|| %4s |", i + 1);
				ncc[i].Xuat();	
			}
		}
		System.out.println("==========================================================================================================================================================");
	}
	public void Search_Ten(String ten) {
		System.out.println("\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("==========================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %30s ||\n", "STT", "MÃ NCC", "HỌ VÀ TÊN LÓT CỦA NHÀ CUNG CẤP", "TÊN", "GIỚI", "NGÀY SINH", "SĐT", "ĐỊA CHỈ");
		for(int i = 0; i < n; i++) {
			if(ncc[i].getTen().indexOf(ten) != -1) {
				System.out.format("|| %4s |", i + 1);
				ncc[i].Xuat();	
			}
		}
		System.out.println("==========================================================================================================================================================");
	}
	public void Search() {
		int select = 0;
		while(true) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Ấn phím 1 để tìm kiếm khách hàng theo mã");
			System.out.println("\t\t\t\t\t2.Ấn phím 2 để tìm kiếm khách hàng theo họ và tên lót");
			System.out.println("\t\t\t\t\t3.Ấn phím 3 để tìm kiếm khách hàng theo tên");
			System.out.println("\t\t\t\t\t4.Ấn phím 4 để thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			switch(select) {
				case 1:
					System.out.print("Nhập vào mã khách hàng cần tìm: ");
					String makh = sc.nextLine();
					Search_MaKhachHang(makh);
					break;
				case 2:
					System.out.print("Nhập vào họ và tên lót mà bạn muốn tìm: ");
					String ho = sc.nextLine();
					Search_HoTenLot(ho);
					break;
				case 3:
					System.out.print("Nhập vào tên mà bạn muốn tìm: ");
					String ten = sc.nextLine();
					Search_Ten(ten);
					break;
				case 4: 
					return;
				default:
					System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin mời vào lại chức năng!!!");
					break;
			}
		}
		
	}
	//====KẾT THÚC TÌM KIẾM KHÁCH HÀNG=====
	public void ThaoTac() {
		int select = 0;
		String manv = "";
		loop:
			while(true) {
				System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm khách hàng");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá khách hàng");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa thông tin khách hàng");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm khách hàng");
				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách");
				System.out.println("\t\t\t\t\t6.Ấn phím 6 để dừng các thao tác");
				System.out.print("\nLựa chọn của bạn là: ");
				select = sc.nextInt();
				sc.nextLine();
				switch(select) {
					case 1:
						System.out.print("Nhập vào số lượng nhân viên cần thêm: ");
						int sl = sc.nextInt();
						Insert(sl);
						XuatDanhSach();
						break;
					case 2:
						System.out.print("Nhập vào mã khách hàng cần xoá: ");
						manv = sc.nextLine();
						Delete(manv);
						XuatDanhSach();
						break;
					case 3:
						System.out.print("Nhập vào mã khách hàng cần sửa: ");
						manv = sc.nextLine();
						Adjust(manv);
						XuatDanhSach();
						break;
					case 4:
						Search();
						break;
					case 5: 
						XuatDanhSach();
						break;
					case 6:
						break loop;
					default:
						System.err.println("Bạn đã nhập sai lựa chọn");
						break;
				}
			}
	}
	
	//ĐỌC FILE GHI FILE
//	@Override
//	public void GhiFileJava(String filename) {
//		try {
//			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
//			dos.writeInt(n);
//			System.out.println();
//			try {
//				for(int i = 0; i < n; i++) {
//					ncc[i].GhiFile(filename);
//				}
//			}catch(NullPointerException npe) {
//				
//			}
//			dos.close();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public void DocFileJava(String filename) {
//		int  i = 0;
//		try {
//			DataInputStream dis = new DataInputStream(new FileInputStream(filename));
//			n = dis.readInt();
//			ncc = new NhaCungCap[n];
//			try {
//				while(true) {
//					String mancc = dis.readUTF();
//					String ho = dis.readUTF();
//					String ten = dis.readUTF();
//					String ngaysinh  = dis.readUTF();
//					String gioitinh = dis.readUTF();
//					String sdt = dis.readUTF();
//					String diachi = dis.readUTF();
//					ncc[i] = new NhaCungCap(mancc,ho,ten, ngaysinh, gioitinh, sdt ,diachi);
//					i++;
//				}
//			}catch(EOFException ex) {
//				
//			}
//			finally {
//				a = i;
//				dis.close();
//			}
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
        
        @Override
	public void GhiFileJava(String filename) {
		try {
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(n);
			try {
				for(int i = 0; i < n; i++) {
					ncc[i].GhiFile(filename);
				}
			}catch(NullPointerException npe) {
				
			}
			dos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void DocFileJava(String filename) {
		int  i = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filename));
			n = dis.readInt();
			ncc = new NhaCungCap[n];
			try {
				while(true) {
					String mancc = dis.readUTF();
					String ho = dis.readUTF();
					String ten = dis.readUTF();
					String gioitinh = dis.readUTF();
					String ngaysinh  = dis.readUTF();
					String sdt = dis.readUTF();
                                        String diachi = dis.readUTF();
					ncc[i] = new NhaCungCap(mancc, ho, ten, gioitinh,  ngaysinh, sdt, diachi);
					i++;	
				}
			}catch(EOFException e) {}
			finally {
				a = i;
				dis.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}