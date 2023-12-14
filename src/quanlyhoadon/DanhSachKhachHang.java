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

public class DanhSachKhachHang implements ThaoTac {
	KhachHang[] kh;
	private int n, a = 0;
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUCTOR
	public DanhSachKhachHang(){
		
	}

    public KhachHang[] getKh() {
        return kh;
    }

    public void setKh(KhachHang[] kh) {
        this.kh = kh;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
        
	//CÁC HÀM NHẬP XUẤT
	public void NhapDanhSach() {
		System.out.print("Nhập vào số lượng khách hàng: ");
		n = sc.nextInt();
		kh = new KhachHang[n];
		for(int i = 0; i < n; i++) {
			System.out.print("\n\t\t\t\t\tKHÁCH HÀNG THỨ " + (i + 1) + "\n");
			kh[i] = new KhachHang();
			kh[i].Nhap();
			GhiFileJava("KhachHang.txt");
			if(i > 0){
				ThayDoiMKH(i);
			}
		}
	}
	public void XuatDanhSach() {
			System.out.println("\t\t\t\t\t\t\t\t\t\t=======DANH SÁCH KHÁCH HÀNG=======");
			System.out.println("==========================================================================================================================================================");
			System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %2s | %30s ||\n", "STT", "MÃ KH", "HỌ VÀ TÊN CỦA KHÁCH HÀNG", "TÊN", "NGÀY SINH", "GIỚI", "SĐT", "LOẠI KH", "ĐỊA CHỈ");
			try {
				for(int i = 0; i < n; i++) {
					System.out.format("|| %4s |",(i + 1));
					kh[i].Xuat();
			}
				
			}catch(NullPointerException npe) {

			}
			
			System.out.println("==========================================================================================================================================================");
	}
	//CÁC HÀM KIỂM TRA THUỘC TÍNH
	public void ThayDoiMKH(int i) {
		String khachhang;
		String makh = kh[i].getMakh();
		do {
			if(KiemTraMKH(makh, i)) {
				System.out.println();
				XuatDanhSach();
				System.err.println("\nNhân viên thứ " + (i+1) + " có mã " + makh + " bị trùng mã nhân viên. Ấn enter để tiếp tục");
				sc.nextLine();
				System.err.print("Nhập lại mã cho khách hàng:  ");
				khachhang = sc.nextLine();
				kh[i].setMakh(khachhang);
				GhiFileJava("KhachHang.txt");
				makh = kh[i].getMakh();
			}
		}while(KiemTraMKH(kh[i].getMakh(), i));
	}
	public boolean KiemTraMKH(String manhanvien, int k) {
		DocFileJava("KhachHang.txt");
		for(int i = a - 1; i >= 0; i--) {
			if(kh[i].getMakh().indexOf(manhanvien) != -1 && i != k) {
				return true;
			}
		}
		return false;
	}
	//CÁC THAO TÁC TRÊN DANH SÁCH KHÁCH HÀNG
	//=====ĐƯA DỮ LIỆU CỦA KHÁCH HÀNG VÀO HOÁ ĐƠN=====
	public String TruyenDuLieu_KH(String makhachhang) {
		for(int i = 0; i < n; i++) {
			if(kh[i].getMakh().indexOf(makhachhang) != -1) {
				return kh[i].ho + " " + kh[i].ten;
			}
		}
		return null;
	}
	//=====KIỂM TRA SỰ TỒN TẠI CỦA KHÁCH HÀNG TRONG DANH SÁCH=====
	public boolean Search_Exist(String makhachhang) {
		for(int i = 0; i < n; i++) {
			if(kh[i].getMakh().indexOf(makhachhang) != -1){
				return true;
			}
		}
		return false;
	}
	//======TÌM KIẾM THEO LOẠI KHÁCH HÀNG======
	public String Search_LoaiKH(String makhachhang) {
		for(int i = 0; i < n; i++) {
			if(kh[i].getMakh().indexOf(makhachhang) != -1) {
				return kh[i].getLoaikh();
			}
		}
		return null;
	}
	//======TÌM KIẾM THEO HỌ VÀ TÊN LÓT, TÊN=======
	public boolean Search_Name(String name) {
		for(int i = 0; i < n; i++) {
			if(kh[i].ho.indexOf(name) != -1) {
				return true;
			}
			else if(kh[i].ten.indexOf(name) != -1) {
				return true;
			}
		}
		return false;
	}
	
	//THÊM MỘT KHÁCH HÀNG VÀO DANH SÁCH
	@Override
	public void Insert(int sl) {
		kh = Arrays.copyOf(kh, n + sl);
		int j = n + sl;
		j -= sl;
		n += sl;
		for(int i = j; i < n; i++) {
			kh[i] = new KhachHang();
			System.out.print("\n\t\t\t\t\tKHÁCH HÀNG THỨ " + (i + 1) + "\n");
			kh[i].Nhap();
			GhiFileJava("KhachHang.txt");
			ThayDoiMKH(i);
		}
		
	}
	//=====XOÁ MỘT KHÁCH HÀNG TRONG DANH SÁCH=====
	@Override
	public void Delete(String manv) {
		boolean flag = false;
		int j = 0;
		for(int i = 0; i < n; i++) {
			if(kh[i].getMakh().indexOf(manv) != -1) {
				flag = true;
				j = i;
				break;
			}
		}
		for(int i = j; i < n - 1; i++) {
			kh[i] = kh[i + 1];
		} 
		n--;
		GhiFileJava("KhachHang.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công khách hàng");
		else 
			System.err.println("\nKhông tìm thấy khách hàng cần xoá");
	}
	//====SỬA THÔNG TIN KHÁCH HÀNG=====
	@Override
	public void Adjust(String manv) {
		int select = 0;
		do {
			System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Sửa chi tiết nhân viên");
			System.out.println("\t\t\t\t\t2.Thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			if(select < 1 || select > 2)
				System.err.println("\nBạn đã nhập sai lựa chọn của mình. Xin mời nhập lại");
		}while(select < 1 || select > 2);
		for(int i = 0; i < n; i++) {
			if(kh[i].getMakh().indexOf(manv) != -1) {
				if(select == 1) {
					System.out.print("Sửa thông tin của nhân viên thứ " + (i + 1) + "\n");
					System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=======");
					System.out.println("==========================================================================================================================================================");
					System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %2s | %30s ||\n", "STT", "MÃ KH", "HỌ VÀ TÊN CỦA KHÁCH HÀNG", "TÊN", "NGÀY SINH", "GIỚI", "SĐT", "LOẠI KH", "ĐỊA CHỈ");
					System.out.format("|| %4s |", (i + 1));
					kh[i].Xuat();
					System.out.println("==========================================================================================================================================================");
					kh[i] = new KhachHang();
					kh[i].Nhap();
					GhiFileJava("KhachHang.txt");
					ThayDoiMKH(i);
					return;
				}
				else
					return;
				
			}
		}
	}
	//=====TÌM KIẾM KHÁCH HÀNG=====
	public void Search_MaKhachHang(String makh) {
		System.out.println("\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("==========================================================================================================================================================");
		
		System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %2s | %30s ||\n", "STT", "MÃ KH", "HỌ VÀ TÊN CỦA KHÁCH HÀNG", "TÊN", "NGÀY SINH", "GIỚI", "SĐT", "LOẠI KH", "ĐỊA CHỈ");
		for(int i = 0; i < n; i++) {
			if(kh[i].getMakh().indexOf(makh) != -1) {
				System.out.format("|| %4s |", i + 1);
				kh[i].Xuat();	
				break;
			}
		}
		System.out.println("==========================================================================================================================================================");
	}
	public void Search_HoTenLot(String ho) {
		System.out.println("\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("==========================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %2s | %30s ||\n", "STT", "MÃ KH", "HỌ VÀ TÊN CỦA KHÁCH HÀNG", "TÊN", "NGÀY SINH", "GIỚI", "SĐT", "LOẠI KH", "ĐỊA CHỈ");
		for(int i = 0; i < n; i++) {
			if(kh[i].getHo().indexOf(ho) != -1) {
				System.out.format("|| %4s |", i + 1);
				kh[i].Xuat();	
			}
		}
		System.out.println("==========================================================================================================================================================");
	}
	public void Search_Ten(String ten) {
		System.out.println("\t\t\t\t\t\t\t\t\t=======KẾT QUẢ======");
		System.out.println("==========================================================================================================================================================");
		System.out.format("|| %4s | %8s | %28s | %10s | %15s | %6s | %13s | %2s | %30s ||\n", "STT", "MÃ KH", "HỌ VÀ TÊN CỦA KHÁCH HÀNG", "TÊN", "NGÀY SINH", "GIỚI", "SĐT", "LOẠI KH", "ĐỊA CHỈ");
		for(int i = 0; i < n; i++) {
			if(kh[i].getTen().indexOf(ten) != -1) {
				System.out.format("|| %4s |", i + 1);
				kh[i].Xuat();	
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
	@Override
	public void GhiFileJava(String filename) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(n);
			System.out.println();
			try {
				for(int i = 0; i < n; i++) {
					kh[i].GhiFile(filename);
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
			kh = new KhachHang[n];
			try {
				while(true) {
					String makh = dis.readUTF();
					String ho = dis.readUTF();
					String ten = dis.readUTF();
					String ngaysinh = dis.readUTF();
					String gioitinh = dis.readUTF();
					String sdt = dis.readUTF();
					String loaikh = dis.readUTF();
					String diachi = dis.readUTF();
					kh[i] = new KhachHang(makh,ho, ten,  gioitinh,ngaysinh,  sdt, loaikh, diachi);
					i++;
				}
			}catch(EOFException ex) {
				
			}
			finally {
				a = i;
				dis.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}