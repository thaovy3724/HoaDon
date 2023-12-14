/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlyhoadon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
//import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class DanhSachHoaDon {
    DanhSachNhanVien dsnv = new DanhSachNhanVien();
    DanhSachKhachHang dskh = new DanhSachKhachHang();
    private ChiTietHoaDon[] cthd;
    private HoaDon [] hd;
    Scanner sc = new Scanner(System.in);
    private int sizeHD, sizeCTHD, a = 0, b = 0;
    
    public DanhSachHoaDon() {
    }

    public DanhSachNhanVien getNv() {
        return dsnv;
    }

    public void setNv(DanhSachNhanVien nv) {
        this.dsnv = nv;
    }

    public DanhSachKhachHang getKh() {
        return dskh;
    }

    public void setKh(DanhSachKhachHang kh) {
        this.dskh = kh;
    }

    public ChiTietHoaDon[] getCthd() {
        return cthd;
    }

    public void setCthd(ChiTietHoaDon[] cthd) {
        this.cthd = cthd;
    }

    public HoaDon[] getHd() {
        return hd;
    }

    public void setHd(HoaDon[] hd) {
        this.hd = hd;
    }

    public int getSizeHD() {
        return sizeHD;
    }

    public void setSizeHD(int sizeHD) {
        this.sizeHD = sizeHD;
    }

    public int getSizeCTHD() {
        return sizeCTHD;
    }

    public void setSizeCTHD(int sizeCTHD) {
        this.sizeCTHD = sizeCTHD;
    }

public void NhapDanhSach() throws FileNotFoundException {
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhập vào số lượng hoá đơn: ");
    sizeHD = sc.nextInt();
     System.out.print("\\\\\\\\n Đây là sl hoa don: " + sizeHD);
     sc.nextLine();
     // Khởi tạo mảng hd
     hd = new HoaDon[sizeHD];  
    for(int i = 0; i < sizeHD; i++) {
        if(hd[i] == null)
            System.out.println("\n\nHD:  " + 1);
        System.out.print("\n\t\t\t\t\tHOÁ ĐƠN THỨ " + (i + 1) + "\n");
        hd[i] = new HoaDon();
        hd[i].nhap();
        if(i > 0){                
        ThayDoiMHD(hd[i].getMaHoaDon());
        }
        System.out.print("\\\\\\\\nĐây là stt i: " + i + " Đây là sl hoa don: " + sizeHD++);
        String mahd = null;
        if(hd[i] != null){
            mahd = hd[i].getMaHoaDon();
            System.out.print(" \n\n mahd :" + mahd);
        }
        else if( hd[i] == null){
                System.out.print(" \n\nHD trống");
        }
        else
            System.out.print(" Hoa Don " + i + " không tồn tại");
       
        System.out.print("\n\\Nhập số chi tiết hóa đơn: ");
        sizeCTHD = sc.nextInt();
        sc.nextLine();
        cthd = new ChiTietHoaDon[sizeCTHD];
        for(int j = 0; j < sizeCTHD; j++)
        {
            cthd[j] = new ChiTietHoaDon();
            cthd[j].nhap(mahd);
        }
        hd[i].setTongTien(tinhTongTien(hd[i].getMaHoaDon()));
        System.out.print("\n\\Tổng tiền của hóa đơn này là: " + hd[i].getTongTien());
    }
    System.out.print("\n\nĐây là test in hóa đơn: \n\n");
    XuatDanhSach();
    GhiFileJava("HoaDondata.txt", "ChiTietHoaDondata.txt");
}
   /////////////Kiem tra ma co trung hay khong
        
    public void ThayDoiMHD(String mahd) throws FileNotFoundException {
		do {
			if(KiemTraTrungMaHD(mahd)) {
				XuatDanhSach();
				System.err.println();
                                int stt = TimSTTMaHD(mahd);
				System.err.format("\nHoá đơn thứ " + stt + " có mã " + mahd + " bị trùng mã hoá đơn. Ấn phím enter để tiếp tục");
				sc.nextLine();
				System.err.println("Hãy nhập lại mã HĐ cho hoá đơn: ");
				mahd = sc.nextLine();
				hd[stt - 1].setMaHoaDon(mahd);
				GhiFileJava("HoaDondata.txt","ChiTietHoaDondata.txt");
				hd[stt - 1].setMaHoaDon(mahd);
			}
		}while(KiemTraTrungMaHD(mahd));
	}
    	
public boolean KiemTraTrungMaHD(String mahd) throws FileNotFoundException {
    DocFileJava("HoaDondata.txt","ChiTietHoaDondata.txt");
    boolean trung = false;
    for(int i = 0; i < sizeHD; i++) {
        if(hd[i].getMaHoaDon().equals(mahd)) {
            trung = true;  
	}
    }
    return trung;
    }

public int TimSTTMaHD(String mahd) throws FileNotFoundException{
    int stt = 0;
    DocFileJava("HoaDondata.txt","ChiTietHoaDondata.txt");
	for(int i = 0; i < sizeHD; i++) {
            if(hd[i].getMaHoaDon().contains(mahd)) {
		stt = i + 1;
            }
	}
    return stt;
}
//public void ThayDoiMHD(String mahd) throws FileNotFoundException {
//    // Kiểm tra mã hóa đơn đã được nhập trước đó
//    do{
//    if (KiemTraTrungMaHD(mahd)) {
//        // Nếu trùng, yêu cầu người dùng nhập lại
//        System.err.println("Mã hóa đơn " + mahd + " đã bị trùng.");
//        System.err.print("Vui lòng nhập lại mã hóa đơn: ");
//        mahd = sc.nextLine();
//    }
//    // Cập nhật mã hóa đơn trong mảng `hd`
//    for (int i = 0; i < sizeHD; i++) {
//        if (hd[i].getMaHoaDon().equals(mahd)) {
//            hd[i].setMaHoaDon(mahd);
//            break;
//        }
//    }
//    }
//    while(KiemTraTrungMaHD(mahd));
//    // Ghi dữ liệu vào file
//    GhiFileJava("HoaDondata.txt", "ChiTietHoaDondata.txt");
//    }

    public double tinhTongTien(String ma){
        double tongTien = 0;
        for(int i = 0; i < sizeCTHD; i++){
            if(cthd[i].getMaHoaDon().equals(ma)){
                tongTien += cthd[i].getThanhTien();
            }
        }
        return tongTien;
    }
    //////////////Xuat danh sach
    	public void XuatDanhSach(){
		System.out.println("\t\t\t\t\t\t\t\t=====DANH SÁCH HOÁ ĐƠN=====");
		System.out.println("\n================================================================================================================================================================================================================");
		System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ");
		try {
			for(int i = 0; i < hd.length; i++) {
				System.out.format("|| %4s |", (i + 1));
                                if(hd[i] != null){
                                    hd[i].xuat();
                                    System.out.println("Thông tin chi tiết hóa đơn:");
                                    System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN");
                                    for(int j = 0; j < sizeCTHD; j++) {
                                        System.out.format("|| %4s |", (j + 1));
                                        if(cthd[j] != null && cthd[i].getMaHoaDon().equals(hd[i].getMaHoaDon()));
                                        cthd[j].xuat();
                                }
                }
			}
		}catch(NullPointerException npe) {	
		}
		System.out.println("================================================================================================================================================================================================================");
		
	}

 
        //////////////////////  
    public void them(int sl){
        Scanner sc = new Scanner(System.in);
        //nhap thong tin hoa don
        for(int i = 0; i < sl; i++){
            hd = Arrays.copyOf(hd, hd.length + 1);
            hd[hd.length - 1] = new HoaDon();
            hd[hd.length - 1].nhap();

            int lc = 1;
            double tongTien = 0.0;
            int slCTHD = 0;
            System.out.println("Nhap thong tin chi tiet hoa don: ");
            do{
                cthd = Arrays.copyOf(cthd, cthd.length + 1);
                cthd[cthd.length - 1] = new ChiTietHoaDon();
                cthd[cthd.length - 1].nhap(hd[i].getMaHoaDon());
                slCTHD++;
                System.out.print("Nhap tiep(1: co || 0: khong): ");
                lc = sc.nextInt();
            }
            while(lc == 1);
            tongTien += tinhTongTien(hd[i].getMaHoaDon());
            hd[i].setTongTien(tongTien); 
            setSizeCTHD(slCTHD);
        }
    }

    
    //*phuong thuc sua
    public void sua(){
        
    }
    
    //*phuong thuc tim
    public void tim(){
        //Menu tim
        int luaChon;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1. Tim hoa don theo ma hoa don.");
            System.out.println("2. Tim hoa don theo ma nhan vien.");
            System.out.println("3. Tim hoa don theo ma khach hang.");
            System.out.println("Nhap so khac de thoat.");
            System.out.print("Moi ban nhap lua chon: ");
            luaChon = sc.nextInt();
            switch(luaChon){
                case 1:
                    timHoaDonTheoMa();
                    break;
                case 2:
                    timHoaDonTheoMaNhanVien();
                    break;
                case 3:
                    timHoaDonTheoMaKhachHang();
                    break;
                default:
                    System.out.println("Da thoat khoi chuong trinh.");
            }
                
        }
        while (luaChon >= 1 && luaChon < 3);
    }
    
    public void timHoaDonTheoMa(){
        Scanner sc = new Scanner(System.in);
        String ma = sc.nextLine();
        boolean timThay = false;
        for(int i = 0; i < hd.length; i++){
            if(hd[i] != null && hd[i].getMaHoaDon().equals(ma)){
                timThay = true;
                System.out.println("Thong tin hoa don can tim: ");
                hd[i].xuat();
            }
        }
        if(!timThay)
            System.out.println("Khong tim thay hoa don!");
    }
    
    public boolean timHoaDonTheoMa(String ma){
        boolean found = false;
        for(int i = 0; i < hd.length; i++){
            if(hd[i] != null && hd[i].getMaHoaDon().equals(ma)){
                found = true;
            }
        }
        return found;
    }
    
    public void timHoaDonTheoMaNhanVien(){
        for (int i = 0; i < dsnv.getNv().length; i++) {
        NhanVien nv = dsnv.getNv()[i];
            if (nv != null) {
                nv.Xuat();
            }
        }
    }
    
    public void timHoaDonTheoMaKhachHang(){
        
    }
    
    public void xoa(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma hoa don ban muon xoa: ");
        String ma = sc.nextLine();
        boolean timThay = false;
        for(int i = 0; i < hd.length; i++){
            if(hd[i] != null && hd[i].getMaHoaDon().equals(ma)){
                if(i == hd.length - 1)
                    hd[i] = null;
                else
                    for(int j = i; j < hd.length - 1; j++)
                        hd[j] = hd[j + 1];
                break;                
            }
        }
    }
    
    public void tongTienSanPhamTheoNam(){
        
        for(int i = 0; i < cthd.length; i++){
            
        }
    }
    public void menu() throws FileNotFoundException {
                //GhiFileJava("HoaDondata.txt","ChiTietHoaDondata.txt");
		int select = 0;
		String mahd = "";
		loop:
			while(true) {
				System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm hoá đơn");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá hoá đơn");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa thông tin hoá đơn");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm hoá đơn");
				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách hoá đơn");
				System.out.println("\t\t\t\t\t6.Ấn phím 6 để dừng các thao tác");
				System.out.print("\nLựa chọn của bạn là: ");
				select = sc.nextInt();
				sc.nextLine();
				switch(select) {
					case 1:
						NhapDanhSach();
						break;
					case 2:
						System.out.print("Nhập vào mã hoá đơn cần xoá: ");
						mahd = sc.nextLine();
						xoa();
						XuatDanhSach();
						break;
					case 3:
						System.out.print("Nhập vào mã hoá đơn cần sửa: ");
						mahd = sc.nextLine();
						sua();
						break;
					case 4:
						tim();
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
    public void GhiFileJava(String filename1, String filename2) {
		try {
			DataOutputStream dos1 = new DataOutputStream(new FileOutputStream(filename1));
                        DataOutputStream dos2 = new DataOutputStream(new FileOutputStream(filename2));
                        int i = 0, j = 0;
                        dos1.writeInt(sizeHD);
                        dos2.writeInt(sizeCTHD);
			try {
				for(i = 0; i < hd.length; i++)
					hd[i].GhiFile(filename1);
                                        
                                for(j = 0; j < cthd.length; i++)
                                            cthd[j].GhiFile(filename2);
			}catch(NullPointerException npe) {			
			}
			dos1.close();
                        dos2.close();
		}catch(IOException e) {}
    }
    
    public void DocFileJava(String filename1, String filename2) throws FileNotFoundException {
        int i = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filename1));
			try {
                            sizeHD = dis.readInt();
                            hd = new HoaDon[sizeHD];
				while(true) {
                                    // Đọc thông tin từ tệp tin và tạo đối tượng HoaDon
                                        String maHoaDon = dis.readUTF();
                                        String ngayLap = dis.readUTF();
                                        String maNhanVien = dis.readUTF();
                                        String hoNhanVien = dis.readUTF();
                                        String tenNhanVien = dis.readUTF();
                                        String maKhachHang = dis.readUTF();
                                        String hoKhachHang = dis.readUTF();
                                        String tenKhachHang = dis.readUTF();
                                        double tongTien = dis.readDouble();
					hd[i] = new HoaDon(maHoaDon, ngayLap, maNhanVien, hoNhanVien, tenNhanVien, maKhachHang, hoKhachHang, tenKhachHang, tongTien);
                                        i++;
				}
			}catch(EOFException e) {
                        }
			finally {
                                a = i;
				dis.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
                
            int  j = 0;
		try {
                    
			DataInputStream dis = new DataInputStream(new FileInputStream(filename2));
                        sizeCTHD = dis.readInt();
			cthd = new ChiTietHoaDon[sizeCTHD];
			try {
				while(true) {
                                String maHoaDon = dis.readUTF();
                                String maSanPham = dis.readUTF();
                                int soLuong = dis.readInt();
                                Double donGia = dis.readDouble();
                                Double thanhTien = dis.readDouble();
                                cthd[j] = new ChiTietHoaDon(maHoaDon, maSanPham, soLuong, donGia, thanhTien);
                                j++;
				}
			}catch(EOFException e) {}
			finally {
				b = j;
				dis.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
    
}
