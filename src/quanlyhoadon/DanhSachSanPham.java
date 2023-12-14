/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author HP
 */
public class DanhSachSanPham implements ThaoTac{
	SanPham[] sp;
	private int n, a = 0;
	Scanner sc = new Scanner(System.in);
	//CONSTRUCTOR
	public DanhSachSanPham() {}
	//NHẬP DANH SÁCH XUẤT DANH SÁCH
	public void NhapDanhSach() {
		System.out.print("Nhập vào số lượng sản phẩm: ");
		n = sc.nextInt();
		sp = new SanPham[n];
		int select = 0;
		for(int i = 0; i < n; i++) {
			System.out.println("\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("1.Sản phẩm là thuốc");
			System.out.println("2.Sản phẩm là vật tư y tế");
			do {
				System.out.print("Lựa chọn của bạn là gì: ");
				select = sc.nextInt();
				if(select < 1 || select > 2) {
					System.err.println("Bạn đã nhập sai lựa chọn của mình, mù hay sao mà nhập sai vậy!!!");
				}
			}while(select < 1 || select > 2);
			if(select == 1) {
				sp[i] = new Thuoc();
				System.out.print("\n\t\t\t\t\tSẢN PHẨM THỨ " + (i + 1) + " (THUỐC)\n");
				sp[i].Nhap();
				GhiFileJava("SanPham.txt");
				sc.nextLine();
				if(i > 0) {
					ThayDoiMSP(i);
				}
			}
			else if(select == 2) {
				System.out.print("\n\t\t\t\t\tSẢN PHẨM THỨ " + (i + 1) + "(VẬT TƯ Y TẾ)\n");
				sp[i] = new VatTuYTe();
				sp[i].Nhap();
				GhiFileJava("SanPham.txt");
				sc.nextLine();
				if(i > 0) {
					ThayDoiMSP(i);
				}
			}
		}
	}
	 public void XuatDanhSach() {
             System.out.println("\t\t\t\t\t\t\t\t\t\t======DANH SÁCH SẢN PHẨM ======");
             System.out.println("==============================================================================================================================================================================================================================");
             System.out.format("|| %4s |%8s | %12s | %32s | %20s | %8s | %12s | %15s | %12s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)", "CẤU TRÚC", "ĐƠN VỊ TÍNH");
             try {
                 for (int i = 0; i < n; i++) {
                   System.out.format("|| %4s |", (i + 1));
                  sp[i].Xuat();
                }
             } catch (NullPointerException npe) {
        }
        System.out.println(
        "==============================================================================================================================================================================================================================");
    }
	//KẾT THÚC NHẬP, XUẤT DANH SÁCH
	
	//CÁC HÀM LIÊN KẾT VÀ KIỂM TRA TÍNH ĐÚNG SAI
	public void DanhSach_SL() {
		System.out.println("\t\t=======DANH SÁCH SẢN PHẨM=======");
		System.out.println("======================================================================================================");
		System.out.format("|| %4s | %15s | %12s | %32s | %20s | %8s||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG");
		for(int i = 0; i < n; i++) {
			System.out.format("||%4s | %15s | %12s | %32s | %20s | %8s||\n", (i + 1), sp[i].masp, sp[i].thuonghieu, sp[i].tensp, sp[i].loaisp, sp[i].sl);
		}
		System.out.println("======================================================================================================");
	}
	public void ThayDoiMSP(int i) {
		do {
			if(KiemTraMSP(sp[i].masp, i)) {
				System.out.println();
				XuatDanhSach();
				System.err.println("\nSản phẩm thứ "+ (i+1) + " có mã " + sp[i].masp + " bị trùng mã sản phẩm. Ấn enter để tiếp tục");
				sc.nextLine();
				System.err.print("Nhập lại mã sp cho sản phẩm: ");
				sp[i].masp = sc.nextLine();
				GhiFileJava("SanPham.txt");
			}
		}while(KiemTraMSP(sp[i].masp, i));
	}
	public boolean KiemTraMSP(String masanpham, int k) {
		DocFileJava("SanPham.txt");
		for(int i = a - 1; i >= 0; i--) {
			if(sp[i].masp.indexOf(masanpham) != -1 && i != k) {
				return true;
			}
		}
		return false;
	}
	public boolean Search_MaSP(String masanpham) {
		for(int i = 0; i < n; i++) {
			if(sp[i].masp.indexOf(masanpham) != -1) {
				return true;
			}
		}
		return false;
	}
	//KIỂM TRA SỐ LƯỢNG
	public boolean Search_SL(int soluong, String masanpham) {
		for(int i = 0; i < n; i++) {
			if(sp[i].masp.indexOf(masanpham) != -1) {
				if(sp[i].sl < soluong || soluong < 0)
					return false;
				else{
					return true;
				}
			}
		}
		return true;
	}
	public boolean Search_SLPNH(int sl, String thuonghieu, String tensp) {
		boolean flag = true;
		for(int i = 0; i < n; i++) {
			if(sp[i].thuonghieu.indexOf(thuonghieu) != -1 && sp[i].tensp.indexOf(tensp) != -1) {
				if(sl <= 0)
					return false;
				else {
					sp[i].sl += sl;
					return true;
				}
			}		
		}
		if(flag) {
			if(sl <= 0)
				return false;
			return true;
		}
		return false;
	}
	public boolean Search_TT(String thuonghieu) {
		for(int i = 0; i < n; i++) {
			if(sp[i].thuonghieu.equals(thuonghieu))
				return true;
		}
		return false;
	}
	//KẾT THÚC KIỂM TRA SỐ LƯỢNG
	public float Search_DG(String masanpham) {
		for(int i = 0; i < n; i++) {
			if(sp[i].masp.indexOf(masanpham) != -1) {
				return sp[i].gia;
			}
		}
		return 0;
	}
        
        
        
	//CÁC THAO TÁC XỬ LÝ TRÊN DANH SÁCH
//	public void Insert(int sl) {
//		sp = Arrays.copyOf(sp, n + sl);
//		int select, j = n + sl;
//		j -= sl;
//		n += sl;
//		for(int i = j; i < n; i++) {
//			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
//			System.out.println("\t\t\t\t\t1.Thêm sản phẩm là THUỐC");
//			System.out.println("\t\t\t\t\t2.Thêm Sản phẩm là VẬT TƯ Y TẾ");
//			System.out.println("\t\t\t\t\t3.Thoát khỏi việc thêm sản phẩm");
//			do {
//				System.out.print("Lựa chọn của bạn là: ");
//				select = sc.nextInt();
//				if(select < 1 || select > 2) {
//					System.err.println("Bạn đã nhập sai lựa chọn của mình, xin mời nhập lại");
//				}
//			}while(select < 1 || select > 2);
//			if(select == 1) {
//				sp[i] = new Thuoc();
//				System.out.print("\t\t\t\t\tSẢN PHÂM THỨ " + (i + 1) + " (THUỐC)\n");
//				sp[i].Nhap();
//				GhiFileJava("SanPham.txt");
//				sc.nextLine();
//				ThayDoiMSP(i);
//			}
//			else if(select == 2) {
//				sp[i] = new VatTuYTe();
//				System.out.print("\t\t\t\t\tSẢN PHÂM THỨ " + (i + 1) + " (VẬT TƯ Y TẾ)\n");
//				sp[i].Nhap();
//				GhiFileJava("SanPham.txt");
//				sc.nextLine();
//				ThayDoiMSP(i);
//			}
//			else if(select == 3)
//				return;
//		}
//	}
//	public void ThaoTac() {
//		int select = 0;
//		String masp = "";
//		loop:
//			while(true) {
//				System.out.println("\n\t\t\t\t\t======CÁC THAO TÁC======");
//				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm sản phẩm");
//				System.out.println("\t\t\t\t\t5.Ấn phím 6 để dừng các thao tác");
//				System.out.print("Lựa chọn của bạn là: ");
//				select = sc.nextInt();
//				sc.nextLine();
//				switch(select) {
//					case 1:
//						System.out.print("Nhập vào số lượng sản phẩm cần thêm: ");
//						int sl = sc.nextInt();
//						Insert(sl);
//						XuatDanhSach();
//						break;
//					case 6:
//						break loop;
//					default:
//						System.err.println("Bạn đã nhập sai lựa chọn");
//						continue loop;
//				}
//			}
//	}
        
        
        //CÁC THAO TÁC XỬ LÝ TRÊN DANH SÁCH
	@Override
	public void Insert(int sl) {
		sp = Arrays.copyOf(sp, n + sl);
		int select, j = n + sl;
		j -= sl;
		n += sl;
		for(int i = j; i < n; i++) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Thêm sản phẩm là THUỐC");
			System.out.println("\t\t\t\t\t2.Thêm Sản phẩm là VẬT TƯ Y TẾ");
			System.out.println("\t\t\t\t\t3.Thoát khỏi việc thêm sản phẩm");
			do {
				System.out.print("Lựa chọn của bạn là: ");
				select = sc.nextInt();
				if(select < 1 || select > 2) {
					System.err.println("Bạn đã nhập sai lựa chọn của mình, xin mời nhập lại");
				}
			}while(select < 1 || select > 2);
			if(select == 1) {
				sp[i] = new Thuoc();
				System.out.print("\t\t\t\t\tSẢN PHÂM THỨ " + (i + 1) + " (THUỐC)\n");
				sp[i].Nhap();
				GhiFileJava("SanPham.txt");
				sc.nextLine();
				ThayDoiMSP(i);
			}
			else if(select == 2) {
				sp[i] = new VatTuYTe();
				System.out.print("\t\t\t\t\tSẢN PHÂM THỨ " + (i + 1) + " (VẬT TƯ Y TẾ)\n");
				sp[i].Nhap();
				GhiFileJava("SanPham.txt");
				sc.nextLine();
				ThayDoiMSP(i);
			}
			else if(select == 3)
				return;
		}
	}
	@Override
	public void Delete(String masanpham) {
		boolean flag = false;
		int j = 0; 
		for(int i = 0; i < n; i++) {
			if(sp[i].masp.indexOf(masanpham) != -1) {
				flag = true;
				j = i;
				break;
			}
		}
		for(int i = j; i < n - 1; i++) {
			sp[i] = sp[i + 1];
		}
		n--;
		GhiFileJava("SanPham.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công");
		else	
			System.err.println("\nKhông tìm thấy mã sản phẩm cần xoá!!!");
	}
	
	@Override
	public void Adjust(String msp) {
		int select = 0;
		boolean flag = false;
		for(int i = 0; i < n; i++) {
			if(sp[i].masp.indexOf(msp) != -1) {
				do {
					System.out.println("\n\t\t\t\t\t=======BẢNG LỰA CHỌN=======");
					System.out.println("\t\t\t\t\t1.Ấn phím 1 để sửa thuộc tính sản phẩm thành thuộc tính THUỐC");
					System.out.println("\t\t\t\t\t2.Ấn phím 2 để sửa thuộc tính sản phẩm thành thuộc tính VẬT TƯ Y TẾ");
					System.out.println("\t\t\t\t\t3.Ấn phím 3 để thoát");
					System.out.print("\nLựa chọn của bạn là: ");
					select = sc.nextInt();
					sc.nextLine();
					
					if(select < 1 || select > 3)
						System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin mời nhập lại!!!");
				}while(select < 1 || select > 3);
				
				switch(select) {
					case 1:
						flag = true;
						System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=======");
						System.out.println("==============================================================================================================================================================================================================================");
						System.out.format("|| %4s | %8s | %12s | %32s | %20s | %4s | %12s | %15s | %15s  | %15s  ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)", "CẤU TRÚC", "ĐƠN VỊ TÍNH");
						System.out.format("|| %4s |", i + 1);
						sp[i].Xuat();
						System.out.println("==============================================================================================================================================================================================================================");	
						System.out.println("\nSửa thuộc tính sản phẩm thành thuốc");
						sp[i] = new Thuoc();
						sp[i].Nhap();
						GhiFileJava("SanPham.txt");
						break;
					case 2: 
						flag = true;
						System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=======");
						System.out.println("==============================================================================================================================================================================================================================");							
						System.out.format("|| %4s | %8s | %12s | %32s | %20s | %4s | %12s | %15s | %15s  | %15s  ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)", "CẤU TRÚC", "ĐƠN VỊ TÍNH");
						System.out.format("|| %4s |", i + 1);
						sp[i].Xuat();
						System.out.println("==============================================================================================================================================================================================================================");	
						System.out.println("\nSửa thuộc tính sản phẩm thành vật tư y tế");
						sp[i] = new VatTuYTe();
						sp[i].Nhap();
						GhiFileJava("SanPham.txt");
						break;
					case 3:
						flag = true;
						break;
				}
				ThayDoiMSP(i);
			}
		}
		if(flag == false) {
			System.err.println("\nKhông tìm thấy sản phẩm mà bạn cần sửa!!!");
			return;
		}
	}
	public void Search_MSP(String masp) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ=======");
		System.out.println("==============================================================================================================================================================================================================================");		
		System.out.format("|| %4s | %8s | %12s | %32s | %20s | %4s | %12s | %15s | %15s  | %15s  ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)", "CẤU TRÚC", "ĐƠN VỊ TÍNH");
		for(int i = 0; i < n; i++) {
			if(sp[i].masp.indexOf(masp) != -1) {
				System.out.format("|| %4s |", i + 1);
				sp[i].Xuat();	
				break;
			}
		}
		System.out.println("==============================================================================================================================================================================================================================");	
	}
	public void Search_ThuongHieu(String th) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ=======");
		System.out.println("==============================================================================================================================================================================================================================");			
                System.out.format("|| %4s | %8s | %12s | %32s | %20s | %4s | %12s | %15s | %15s  | %15s  ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)", "CẤU TRÚC", "ĐƠN VỊ TÍNH");
                for(int i = 0; i < n; i++) {
			if(sp[i].thuonghieu.indexOf(th) != -1) {
				System.out.format("|| %4s |", i + 1);
				sp[i].Xuat();
			}
		}
		System.out.println("==============================================================================================================================================================================================================================");	
	}
	public void Search_LoaiSP(String l) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ=======");
		System.out.println("==============================================================================================================================================================================================================================");			
                System.out.format("|| %4s | %8s | %12s | %32s | %20s | %4s | %12s | %15s | %15s  | %15s  ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI SP", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)", "CẤU TRÚC", "ĐƠN VỊ TÍNH");
                for(int i = 0; i < n; i++) {
			if(sp[i].loaisp.indexOf(l) != -1) {
				System.out.format("|| %4s |", (i + 1));
				sp[i].Xuat();
				break;
			}
		}
		System.out.println("==============================================================================================================================================================================================================================");	
	}
	public void Search() {
		int select = 0;
		loop:
		while(true) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN CHỨC NĂNG======");
			System.out.println("\t\t\t\t\t1.Ấn phím 1 để tìm sản phẩm theo mã sản phẩm");
			System.out.println("\t\t\t\t\t2.Ấn phím 2 để tìm sản phẩm tên thương hiệu");
			System.out.println("\t\t\t\t\t3.Ấn phím 3 để tìm sản phẩm theo loại sản phẩm");
			System.out.println("\t\t\t\t\t4.Ấn phím 4 để thoát");
			System.out.print("\nLựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			switch(select) {
				case 1: 
					System.out.println("Nhập vào mã sản phẩm cần tìm: ");
					String masp = sc.nextLine();
					Search_MSP(masp);
					break;
					
				case 2:
					System.out.print("Nhập vào thương hiệu mà bạn muốn tìm: ");
					String thuonghieu = sc.nextLine();
					thuonghieu.toUpperCase();
					Search_ThuongHieu(thuonghieu);
					break;
				
				case 3:
					System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN TÌM KIẾM======");
					System.out.println("\t\t\t\t\t1.Nếu bạn muốn tìm thuoc thì hãy gõ đúng cú pháp 'thuoc'");
					System.out.println("\t\t\t\t\t2.Nếu bạn muốn tìm vật tư y tế số sàn thì gõ vào 'VTYT'");
					System.out.print("Lựa chọn của bạn là: ");
					String loaisp = sc.nextLine();
					loaisp.toLowerCase();
					Search_LoaiSP(loaisp);
					break;
				case 4: 
					break loop;
				default:
					System.err.println("Vì bạn nhập sai lựa chọn nên chương trình bị dừng");
					break;
			}
		}
	}
	public void ThaoTac() {
		int select = 0;
		String masp = "";
		loop:
			while(true) {
				System.out.println("\n\t\t\t\t\t======CÁC THAO TÁC======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm sản phẩm");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá sản phẩm");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa chi tiết sản phẩm");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm các loại sản phẩm");
				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách");
				System.out.println("\t\t\t\t\t5.Ấn phím 6 để dừng các thao tác");
				System.out.print("Lựa chọn của bạn là: ");
				select = sc.nextInt();
				sc.nextLine();
				switch(select) {
					case 1:
						System.out.print("Nhập vào số lượng san phẩm cần thêm: ");
						int sl = sc.nextInt();
						Insert(sl);
						XuatDanhSach();
						break;
					case 2:
						System.out.print("Nhập vào mã sản phẩm cần xoá: ");
						masp = sc.nextLine();
						Delete(masp);
						XuatDanhSach();
						break;
					case 3:
						System.out.print("Nhập vào mã sản phẩm cần sửa: ");
						masp = sc.nextLine();
						Adjust(masp);
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
						continue loop;
				}
			}
	}
        
        
        
        
        
        
	//ĐỌC FILE, GHI FILE
	public void GhiFileJava(String filename) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(n);
			for(int i = 0; i < n; i++) {
				if(sp[i] instanceof Thuoc) {
					sp[i] = (Thuoc) sp[i];
					sp[i].GhiFile(filename);
				}
				if(sp[i] instanceof VatTuYTe) {
					sp[i] = (VatTuYTe) sp[i];
					sp[i].GhiFile(filename);
				}
			}
			dos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void DocFileJava(String filename) {
		int i = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filename));
			n = dis.readInt();
			sp = new SanPham[n];
			try {
				while(true) {
					int code = dis.readInt();
					String masp = dis.readUTF();
                                        String tensp = dis.readUTF();
					String thuonghieu = dis.readUTF();
					String loaisp = dis.readUTF();
					int sl = dis.readInt();
					float gia = dis.readFloat();
					double thanhtien = dis.readDouble();
					if(code == 0) {
						String cautruc = dis.readUTF();
						sp[i] = new Thuoc(masp, thuonghieu, tensp, loaisp, sl, gia, thanhtien, cautruc);
					}
					else if(code == 1) {
						String donvitinh = dis.readUTF();
						sp[i] = new VatTuYTe(masp, thuonghieu, tensp, loaisp, sl, gia, thanhtien,donvitinh);
					}
					i++;
				}
			}catch(EOFException e) {
			}
			finally {
				a = i;
				dis.close();
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
