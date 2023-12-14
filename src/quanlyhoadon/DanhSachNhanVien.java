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
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class DanhSachNhanVien implements ThaoTac {
	NhanVien[] nv;
	private int n, a = 0;
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUCTOR
	public DanhSachNhanVien() {
		
	}

    public NhanVien[] getNv() {
        return nv;
    }

    public void setNv(NhanVien[] nv) {
        this.nv = nv;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
        
	//HÀM NHẬP XUẤT
	public void NhapDanhSachNV() {
			System.out.print("Nhập vào số lượng nhân viên: ");
			n = sc.nextInt();
			nv = new NhanVien[n];
			for(int i = 0; i < n; i++) {
				System.out.print("\n\t\t\t\t\tNHÂN VIÊN THỨ " + (i + 1) + "\n");
				nv[i] = new NhanVien();
				nv[i].Nhap();
				GhiFileJava("NhanVien.txt");
				if(i > 0) {
					ThayDoiMNV(i);
				}
			}
	}
	public void XuatDanhSachNV() {
		
		System.out.println("\t\t\t\t\t\t\t\t\t=======DANH SÁCH NHÂN VIÊN=======");
		System.out.println("===========================================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %5s | %15s | %15s | %13s | %25s | %15s ||\n", "STT", "MÃ NV", "HỌ VÀ TÊN LÓT CỦA NHÂN VIÊN", "TÊN", "GIỚI", "NGÀY SINH", "NGÀY VÀO LÀM", "SĐT", "CHỨC VỤ", "LƯƠNG");
		try {
			for(int i = 0; i < n; i++) {
				System.out.format("|| %4s |", (i + 1));
				nv[i].Xuat();
			}
		}catch(NullPointerException npe) {
			
		}
		System.out.println("===========================================================================================================================================================================");
	}
	//==============CÁC THAO TÁC TRÊN DANH SÁCH=================
	
	//=====ĐƯA DỮ LIỆU CỦA NHÂN VIÊN VÀO HOÁ ĐƠN =====
	public void ThayDoiMNV(int i) {
		DocFileJava("NhanVien.txt");
		String nhanvien;
		String manv = nv[i].getManv();
		do {
			if(KiemTraMNV(manv, i)) {
				System.out.println();
				XuatDanhSachNV();
				System.err.println("\nNhân viên thứ " + (i+1) + " có mã " + manv + " bị trùng mã nhân viên. Ấn enter để tiếp tục");
				sc.nextLine();
				System.err.print("Nhập lại mã NV cho nhân viên: ");
				nhanvien = sc.nextLine();
				nv[i].setManv(nhanvien);
				GhiFileJava("NhanVien.txt");
				manv = nv[i].getManv();
			}
		}while(KiemTraMNV(nv[i].getManv(), i));
	}
	public boolean KiemTraMNV(String manhanvien, int k) {
		for(int i = a - 1; i >= 0; i--) {
			if(nv[i].getManv().indexOf(manhanvien) != -1 && i != k) {
				return true;
			}
		}
		return false;
	}
	
	public String Search_MNV(String manhanvien) {
		for(int i = 0; i < n; i++) {
			if(nv[i].getManv().indexOf(manhanvien) != -1) {
				return nv[i].ho + " " + nv[i].ten;
			}
		}
		return null;
	}
	//TÌM KIẾM THEO HỌ VÀ TÊN LÓT, TÊN
	public boolean Search_Name(String name) {
		for(int i = 0; i < n; i++) {
			if(nv[i].ho.indexOf(name) != -1) {
				return true;
			}
			else if(nv[i].ten.indexOf(name) != -1) {
				return true;
			}
		}
		return false;
	}
	
	//THÊM MỘT NHÂN VIÊN VÀO DANH SÁCH
	@Override
	public void Insert(int sl) {
		nv = Arrays.copyOf(nv, n + sl);
		int j = n + sl;
		j -= sl;
		n += sl;
		for(int i = j; i < n; i++) {
			nv[i] = new NhanVien();
			System.out.print("\n\t\t\t\t\tNHÂN VIÊN THỨ " + (i + 1) + "\n");
			nv[i].Nhap();
			GhiFileJava("NhanVien.txt");
			ThayDoiMNV(i);
		}
	}
	//XOÁ MỘT NHÂN VIÊN TRONG DANH SÁCH
	@Override
	public void Delete(String manv) {
		boolean flag = false;
		int j = 0;
		for(int i = 0; i < n; i++) {
			if(nv[i].getManv().indexOf(manv) != -1) {
				flag = true;
				j = i;
				break;
			}
		}
		for(int i = j; i < n - 1; i++) {
			nv[i] = nv[i + 1];
		} 
		n--;
		GhiFileJava("NhanVien.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công dữ liệu");
		else 
			System.err.println("\nKhông tìm thấy nhân viên cần tìm");
	}
	//SỬA CHI TIẾT SẢN PHẨM
	@Override
	public void Adjust(String manv) {
		int select = 0;
		do {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Sửa chi tiết nhân viên");
			System.out.println("\t\t\t\t\t2.Thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			if(select < 1 || select > 2)
				System.err.println("\nBạn đã nhập sai lựa chọn của mình. Xin mời nhập lại");
		}while(select < 1 || select > 2);
		for(int i = 0; i < n; i++) {
			if(nv[i].getManv().indexOf(manv) != -1) {
				if(select == 1) {
					System.out.print("Sửa thông tin của nhân viên thứ " + (i + 1) + "\n");
					System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=====");
					System.out.println("===========================================================================================================================================================================");
					System.out.format("|| %4s | %8s | %28s | %10s | %5s | %15s | %15s | %13s | %20s | %15s ||\n", "STT", "MÃ NV", "HỌ VÀ TÊN LÓT CỦA NHÂN VIÊN", "TÊN", "GIỚI", "NGÀY SINH", "NGÀY VÀO LÀM", "SĐT", "CHỨC VỤ", "LƯƠNG");
					System.out.format("|| %4s |", (i + 1));
					nv[i].Xuat();
					System.out.println("===========================================================================================================================================================================");
					nv[i] = new NhanVien();
					nv[i].Nhap();
					GhiFileJava("NhanVien.txt");
					ThayDoiMNV(i);
					return;
				}
				else 
					return;
			}
		}
	}
	//TÌM KIẾM NHÂN VIÊN
	public void Search_MaNhanVien(String manv) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("===========================================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %5s | %15s | %15s | %13s | %20s | %15s ||\n", "STT", "MÃ NV", "HỌ VÀ TÊN LÓT CỦA NHÂN VIÊN", "TÊN", "GIỚI", "NGÀY SINH", "NGÀY VÀO LÀM", "SĐT", "CHỨC VỤ", "LƯƠNG");
		for(int i = 0; i < n; i++) {
			if(nv[i].getManv().indexOf(manv) != -1) {
				System.out.format("|| %4s |", i + 1);
				nv[i].Xuat();
				break;
			}
		}
		System.out.println("===========================================================================================================================================================================");	
		System.out.println("Nhấn phím enter để tiếp tục");
		sc.nextLine();
	}
	
	public void Search_HoTenLot(String ho) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("===========================================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %5s | %15s | %15s | %13s | %20s | %15s ||\n", "STT", "MÃ NV", "HỌ VÀ TÊN LÓT CỦA NHÂN VIÊN", "TÊN", "GIỚI", "NGÀY SINH", "NGÀY VÀO LÀM", "SĐT", "CHỨC VỤ", "LƯƠNG");
		for(int i = 0; i < n; i++) {
			if(nv[i].getHo().indexOf(ho) != -1) {
				System.out.format("|| %4s |", i + 1);
				nv[i].Xuat();
			}
		}
		System.out.println("===========================================================================================================================================================================");	
		System.out.println("\nNhấn phím enter để tiếp tục");
		sc.nextLine();
	}
	public void Search_Ten(String ten) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("===========================================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %5s | %15s | %15s | %13s | %20s | %15s ||\n", "STT", "MÃ NV", "HỌ VÀ TÊN LÓT CỦA NHÂN VIÊN", "TÊN", "GIỚI", "NGÀY SINH", "NGÀY VÀO LÀM", "SĐT", "CHỨC VỤ", "LƯƠNG");
		for(int i = 0; i < n; i++) {
			if(nv[i].getTen().indexOf(ten) != -1) {
				System.out.format("|| %4s |", i + 1);
				nv[i].Xuat();	
			}
		}
		System.out.println("===========================================================================================================================================================================");	
		System.out.println("\nNhấn phím enter để tiếp tục");
		sc.nextLine();
	}
	public void Search() {
		int select = 0;
		while(true) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Ấn phím 1 để tìm kiếm nhân viên theo mã");
			System.out.println("\t\t\t\t\t2.Ấn phím 2 để tìm kiếm nhân viên theo họ và tên lót");
			System.out.println("\t\t\t\t\t3.Ấn phím 3 để tìm kiếm nhân viên theo tên");
			System.out.println("\t\t\t\t\t4.Ấn phím 4 để thoát");
			System.err.format("\nLƯU Ý: nếu bạn tìm kiếm nhân viên không có trong danh sách thì danh sách sẽ không xuất gì cả!!!\n");
			System.out.print("\nLựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			switch(select) {
				case 1:
					System.out.print("Nhập vào mã nhân viên cần tìm: ");
					String manv = sc.nextLine();
					Search_MaNhanVien(manv);
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
	public void ThaoTac() {
		int select = 0;
		String manv = "";
		while(true) {
				System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm nhân viên");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá nhân viên");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa thông tin nhân viên");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm nhân viên");
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
						XuatDanhSachNV();
						break;
					case 2:
						System.out.print("Nhập vào mã nhân viên cần xoá: ");
						manv = sc.nextLine();
						Delete(manv);
						XuatDanhSachNV();
						break;
					case 3:
						System.out.print("Nhập vào mã nhân viên cần sửa: ");
						manv = sc.nextLine();
						Adjust(manv);
						XuatDanhSachNV();
						break;
					case 4:
						Search();
						break;
					case 5: 
						XuatDanhSachNV();
						break;
					case 6:
						return;
					default:
						System.err.println("Bạn đã nhập sai lựa chọn");
						break;
				}
			}
	}
	//=====================================================
	//ĐỌC FILE GHI FILE
	@Override
	public void GhiFileJava(String filename) {
		try {
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(n);
			try {
				for(int i = 0; i < n; i++) {
					nv[i].GhiFile(filename);
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
			nv = new NhanVien[n];
			try {
				while(true) {
					String manv = dis.readUTF();
					String ho = dis.readUTF();
					String ten = dis.readUTF();
					String gioitinh = dis.readUTF();
					String ngaysinh  = dis.readUTF();
					String ngayvaolam = dis.readUTF();
					String sdt = dis.readUTF();
					String chucvu = dis.readUTF();
					float luong = dis.readFloat();
					nv[i] = new NhanVien(manv,ho, ten, ngaysinh, gioitinh,  ngayvaolam, sdt, chucvu, luong);
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
