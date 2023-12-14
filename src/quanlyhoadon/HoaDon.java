/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyhoadon;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class HoaDon {
    DanhSachNhanVien nv = new DanhSachNhanVien();
    DanhSachKhachHang kh = new DanhSachKhachHang();
    private String maHoaDon;
    private String ngayLap;
    private String maNhanVien;
    private String hoNhanVien;
    private String tenNhanVien;
    private String maKhachHang;
    private String hoKhachHang;
    private String tenKhachHang;
    private double tongTien;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String ngayLap, String maNhanVien, String hoNhanVien, String tenNhanVien, String maKhachHang, String hoKhachHang, String tenKhachHang, Double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.maNhanVien = maNhanVien;
        this.hoNhanVien = hoNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.maKhachHang = maKhachHang;
        this.hoKhachHang = hoKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.tongTien = tongTien;
    }
    
    public HoaDon(HoaDon hd) {
        this.maHoaDon = hd.maHoaDon;
        this.ngayLap = hd.ngayLap;
        this.maNhanVien = hd.maNhanVien;
        this.hoNhanVien = hd.hoNhanVien;
        this.tenNhanVien = hd.tenNhanVien;
        this.maKhachHang = hd.maKhachHang;
        this.hoKhachHang = hd.hoKhachHang;
        this.tenKhachHang = hd.tenKhachHang;
        this.tongTien = hd.tongTien;
    }

    
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public HoaDon(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
////////////// Ham nhap
    
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Định dạng mã sản phẩm: SP + [XX]");
        do{
            System.out.println("Định dạng mã sản phẩm: SP + [XX]");
            System.out.print("Nhập mã hóa đơn: ");
            maHoaDon = sc.nextLine();
            if(!maHoaDon.matches("^SP[0-9]{2}$"))
                System.out.println("Sai số lượng kí tự.");
        }
        while(!maHoaDon.matches("^SP[0-9]{2}$"));
//        System.out.print("Nhap ngay lap hoa don: ");
//        ngayLap = sc.nextLine();
        System.err.println("\nNgày nhập hoá đơn phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		System.err.println();
		do {
			System.out.print("\nNgày lập hoá đơn: ");
			ngayLap = sc.nextLine();
			
			if(!CheckDate(ngayLap)) {
				System.err.println("Ngày tháng năm không hợp lệ. Xin mời nhập lại!");
				System.err.println();
			}
				
		}while(!CheckDate(ngayLap));
//        System.out.print("Nhap ma nhan vien: ");
//        maNhanVien = sc.nextLine();
        do {
            nv.DocFileJava("NhanVien.txt");
            nv.XuatDanhSachNV();
            System.out.print("Nhập mã nhân viên phụ trách: ");
            maNhanVien = sc.nextLine();
            if(nv.Search_MNV(maNhanVien) == null) {
		System.err.println("\nMã nhân viên không có trong danh sách!");
            }
	}while(nv.Search_MNV(maNhanVien)== null);
//        System.out.print("Nhap ma khach hang: ");
//        maKhachHang = sc.nextLine();
	do {
            kh.DocFileJava("KhachHang.txt");
            kh.XuatDanhSach();
            System.out.print("Nhập mã khách hàng: ");
            maKhachHang = sc.nextLine();
            if(kh.TruyenDuLieu_KH(maKhachHang) == null)
		System.err.println("\nMã khách hàng mà bạn vừa nhập không có trong danh sách!");
	}while(kh.TruyenDuLieu_KH(maKhachHang) == null);
        
    }
    public boolean CheckDate(String date) {
		df.setLenient(false);
		try {
			df.parse(date);
		}catch(ParseException e) {
			return false;
		}
		return true;
	}
    	public void xuat() {
		System.out.format(" %10s | %13s | %30s | %25s | %15s | %15s ||\n", maHoaDon, maKhachHang, tenKhachHang, maNhanVien, ngayLap, tongTien);
	}
//    CHI TIET HOA DON
//    public void xuat(){
//        System.out.format(" %10s | %20s | %15s | %15s\n", maHoaDon, ngayLap, maNhanVien, maKhachHang);
//        System.out.println("Ma hoa don: " + maHoaDon);
//        System.out.println("Ngay lap hoa don: " + ngayLap);
//        System.out.println("Ma nhan vien: " + maNhanVien);
//        System.out.println("Ma khach hang: " + maKhachHang);
//    }
//    public void nhap(String ma){
//        //nhap thong tin chi tiet hoa don cua moi hoa don (gan theo ma hoa don)
//        cthd = Arrays.copyOf(cthd, cthd.length + 1);
//        cthd[cthd.length - 1] = new ChiTietHoaDon();
//        cthd[cthd.length - 1].nhap(ma);
//    }
//    
//    public void them(ChiTietHoaDon ct){
//        cthd = Arrays.copyOf(cthd, cthd.length + 1);
//        cthd[cthd.length - 1] = new ChiTietHoaDon(ct);
//    }
//    
//    public double tinhTongTien(){
//        int kq = 0;
//        for(int i = 0; i < cthd.length; i++){
//            if(cthd[i] != null)
//                kq += cthd[i].getDonGia() * cthd[i].getSoLuong();
//        }
//        return kq;
//    }
    public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(maHoaDon);
                dos.writeUTF(ngayLap);
                dos.writeUTF(maNhanVien);
                dos.writeUTF(hoNhanVien);
                dos.writeUTF(hoNhanVien);
		dos.writeUTF(maKhachHang);
                dos.writeUTF(hoKhachHang);
                dos.writeUTF(tenKhachHang);
		dos.close();
    }
    
}
