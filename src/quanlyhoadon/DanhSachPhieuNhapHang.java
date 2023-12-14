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
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class DanhSachPhieuNhapHang implements ThaoTac {
	private int n, a = 0;
	ChiTietPhieuNhapHang[] ctpnh;
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUCTOR
	public DanhSachPhieuNhapHang() {}
	
	//NHẬP XUẤT, DANH SÁCH
	public void NhapDanhSach() {
		System.out.println("Số lượng phiếu nhập hàng: ");
		n = sc.nextInt();
		ctpnh = new ChiTietPhieuNhapHang[n];
		for(int i = 0; i < n; i++) {
			System.out.println("\t\t\t\t\tPHIẾU NHẬP HÀNG THỨ " + (i + 1));
			ctpnh[i] = new ChiTietPhieuNhapHang();
			ctpnh[i].Nhap();
			GhiFileJava("PhieuNhapHang.txt");
			if(i > 0) {
				ThayDoi_MaPhieu(i);
			}
		}
	}
        
	public void XuatDanhSach() {
		
		System.out.println("\t\t\t\t\t\t=======DANH SÁCH PHIẾU NHẬP HÀNG=======");
		System.out.println("\n==============================================================================================================================================================");
		System.out.format("|| %4s | %8s | %12s | %32s | %30s | %15s | %8s | %9s | %10s ||\n", "STT", "MÃ PHIẾU", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "NHÂN VIÊN PT NHẬP HÀNG", "NHÀ CUNG CẤP", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN");
		try {
			for(int i = 0; i < n; i++) {
				System.out.format("|| %4s |", (i + 1));
				ctpnh[i].Xuat();
			}
		}catch(NullPointerException npe) {}
		System.out.println("==============================================================================================================================================================");
	}
	
	//KIỂM TRA MÃ PHIẾU
	public void ThayDoi_MaPhieu(int i) {
		String maphieu = ctpnh[i].getMaphieu();
		String phieu = "";
		do {
			if(KiemTra_MaPhieu(maphieu, i)) {
				System.out.println();
				XuatDanhSach();
				System.err.println("\nPhiếu nhập hàng thứ " + (i + 1) + " có mã " + maphieu + " bị trùng mã phiếu. Ấn enter để tiếp tực");
				sc.nextLine();
				System.err.print("Nhập lại mã phiếu cho phiếu nhập hàng: ");
				phieu = sc.nextLine();
				ctpnh[i].setMaphieu(phieu);
				GhiFileJava("PhieuNhapHang.txt");
				maphieu = ctpnh[i].getMaphieu();
			}
		}while(KiemTra_MaPhieu(maphieu, i));
	}
	public boolean KiemTra_MaPhieu(String maphieu, int k) {
		DocFileJava("PhieuNhapHang.txt");
		for(int i = a - 1; i >= 0; i--) {
			if(ctpnh[i].getMaphieu().indexOf(maphieu) != -1 && i != k)
				return true;
		}
		return false;
	}
	
	//CÁC THAO TÁC TRÊN DANH SÁCH
	//=====THÊM PHIẾU NHẬP HÀNG VÀO DANH SÁCH=====
	@Override
	public void Insert(int soluong) {
		ctpnh = Arrays.copyOf(ctpnh, n + soluong);
		int j = n + soluong;
		j = j - soluong;
		n += soluong;
		for(int i = j; i < n; i++) {
			ctpnh[i] = new ChiTietPhieuNhapHang();
			System.out.println("\n\t\t\t\t\tPHIẾU NHẬP HÀNG THỨ " + (i + 1));
			ctpnh[i].Nhap();
			GhiFileJava("PhieuNhapHang.txt");
			ThayDoi_MaPhieu(i);
		}
	}
	//=====XOÁ PHIẾU NHẬP HÀNG=====
	@Override
	public void Delete(String maphieu) {
		boolean flag = false;
		int j = 0;
		try {
			for(int i = 0; i < n; i++) {
				if(ctpnh[i].getMaphieu().indexOf(maphieu) != -1) {
					flag = true;
					j = i;
					break;
				}
			}
		}catch(NullPointerException npe) {}
		for(int i = j; i < n - 1; i++) {
			ctpnh[i] = ctpnh[i + 1];
		}
		n--;
		GhiFileJava("PhieuNhapHang.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công");
		else 
			System.err.println("\nKhông tìm thấy phiếu cần xoá");
	}	
	//=====SỬA THÔNG TIN PHIẾU NHẬP HÀNG=====
	@Override
	public void Adjust(String maphieu) {
		int select = 0;
		do {
			System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Sửa chi tiết phiếu nhập hàng");
			System.out.println("\t\t\t\t\t2.Thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			if(select < 1 || select > 2)
				System.err.println("\nBạn đã nhập sai lựa chọn của mình. Xin mời nhập lại");
		}while(select < 1 || select > 2);
		for(int i = 0; i < n; i++) {
			if(ctpnh[i].getMaphieu().indexOf(maphieu) != -1) {
				if(select == 1) {
					System.out.print("Sửa thông tin của nhân viên thứ " + (i + 1) + "\n");
					System.out.println("\n==============================================================================================================================================================");
					System.out.println("\t\t\t\t\t\t======SỬA THÔNG TIN=====");
					System.out.format("|| %4s | %8s | %12s | %32s | %30s | %15s | %8s | %7s | %10s ||\n", "STT", "MÃ PHIẾU", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "NHÂN VIÊN PT NHẬP HÀNG", "LOẠI XE NHẬP VỀ", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN");
					System.out.format("|| %4s |", (i + 1));
					ctpnh[i].Xuat();
					System.out.println("==============================================================================================================================================================");
					ctpnh[i] = new ChiTietPhieuNhapHang();
					ctpnh[i].Nhap();
					GhiFileJava("PhieuNhapHang.txt");
					ThayDoi_MaPhieu(i);
					return;
				}
				else
					return;
			}
		}
	}
	//=========TÌM KIẾM TRONG DANH SÁCH PHIẾU=========
	//TÌM KIẾM THEO MÃ
	public void Search_MP(String maphieu) {
		System.out.println("\t\t\t\t\t\t======KẾT QUẢ======");
		System.out.println("\n==============================================================================================================================================================");
		System.out.format("|| %4s | %8s | %12s | %32s | %30s | %15s | %8s | %7s | %10s ||\n", "STT", "MÃ PHIẾU", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "NHÂN VIÊN PT NHẬP HÀNG", "LOẠI XE NHẬP VỀ", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN");
		for(int i = 0; i < n; i++) {
			if(ctpnh[i].getMaphieu().indexOf(maphieu) != -1) {
				System.out.format("|| %4s |", i + 1);
				ctpnh[i].Xuat();
				System.out.println("==============================================================================================================================================================");
				break;
			}
		}
	}
	//TÌM KIẾM THEO THƯƠNG HIỆU
	public void Search_TT(String thuonghieu) {
		System.out.println("\t\t\t\t\t\t=====KẾT QUẢ======");
		System.out.println("\n==============================================================================================================================================================");
		System.out.format("|| %4s | %8s | %12s | %32s | %30s | %15s | %8s | %7s | %10s ||\n", "STT", "MÃ PHIẾU", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "NHÂN VIÊN PT NHẬP HÀNG", "LOẠI XE NHẬP VỀ", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN");
		for(int i = 0; i < n; i++) {
			if(ctpnh[i].getThuonghieu().indexOf(thuonghieu) != -1) {
				System.out.format("|| %4s |", i + 1);
				ctpnh[i].Xuat();
			}
		}
		System.out.println("==============================================================================================================================================================");
	}
        
	//TÌM KIẾM SẢN PHẨM
	public void Search() {
		int select = 0;
		while(true) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Ấn phím 1 để tìm kiếm phiếu hàng theo mã");
			System.out.println("\t\t\t\t\t2.Ấn phím 2 để tìm kiếm phiếu hàng theo thương hiệu");
			System.out.println("\t\t\t\t\t3.Ấn phím 3 để tìm kiếm khách hàng theo loại sản phẩm");
			System.out.println("\t\t\t\t\t4.Ấn phím 4 để thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			switch(select) {
				case 1:
					System.out.print("Nhập vào mã phiếu cần tìm: ");
					String maphieu = sc.nextLine();
					Search_MP(maphieu);
					break;
				case 2:
					System.out.print("Nhập vào thương hiệu cần tìm: ");
					String thuonghieu = sc.nextLine();
					thuonghieu.toUpperCase();
					Search_TT(thuonghieu);
					break;
				case 3:
					System.out.print("Bạn cần tìm loại sản phẩm gì: ");
					String loaisp = sc.nextLine();
//					Search_Loai(loaisp);
					break;
				case 4: 
					return;
				default:
					System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin mời vào lại chức năng!!!");
					break;
			}
		}
	}
	//=====KẾT THÚC VIỆC TÌM KIẾM PHIẾU NHẬP HÀNG=====
	public void ThaoTac() {
		int select = 0;
		String maphieu = "";
		loop:
			while(true) {
				System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm phiếu nhập hàng");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá phiếu nhập hàng");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa thông tin phiếu nhập hàng");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm phiếu nhập hàng");
				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách phiếu nhập hàng");
				System.out.println("\t\t\t\t\t6.Ấn phím 6 để dừng các thao tác");
				System.out.print("\nLựa chọn của bạn là: ");
				select = sc.nextInt();
				sc.nextLine();
				switch(select) {
					case 1:
						System.out.print("Nhập vào số lượng phiếu nhập hàng cần thêm: ");
						int sl = sc.nextInt();
						Insert(sl);
						XuatDanhSach();
						break;
					case 2:
						System.out.print("Nhập vào mã phiếu cần xoá: ");
						maphieu = sc.nextLine();
						Delete(maphieu);
						XuatDanhSach();
						break;
					case 3:
						System.out.print("Nhập vào mã phiếu cần sửa: ");
						maphieu = sc.nextLine();
						Adjust(maphieu);
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
	@Override
	public void GhiFileJava(String filename) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(n);
			try {
				for(int i = 0; i < n; i++) {
					ctpnh[i].GhiFile(filename);
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
		int i = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filename));
			n = dis.readInt();
			ctpnh = new ChiTietPhieuNhapHang[n];
			try {
				while(true) {
					String maphieu = dis.readUTF();
					String tennv = dis.readUTF();
					String thuonghieu = dis.readUTF();
					String tensp = dis.readUTF();
					String loaisp = dis.readUTF();
					int sl = dis.readInt();
					float gia = dis.readFloat();
					double thanhtien = dis.readDouble();
					ctpnh[i] = new ChiTietPhieuNhapHang(maphieu, tennv, thuonghieu, tensp, loaisp, sl, gia, thanhtien);
					i++;
				}
			}catch(EOFException ex) {}
			finally {
				a = i;
				dis.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}