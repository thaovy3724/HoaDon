///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package quanlyhoadon;
//
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//
///**
// *
// * @author DELL
// */
//public class QuanLyDSHD {
//    private DanhSachHoaDon dshd;
//    private NhanVienList dsnv;
//    private KhachHangList dskh;
//    private NhaCungCapList dsncc;
//    private DanhSachSanPham dssp;
//    Scanner sc = new Scanner(System.in);
//    
//    public QuanLyDSHD() {
//        dshd = new DanhSachHoaDon();
//        dsnv = new NhanVienList();
//        dskh = new KhachHangList();
//        dsncc = new NhaCungCapList();
//        dssp = new DanhSachSanPham();
//    }
//
//    public QuanLyDSHD(DanhSachHoaDon dshd, NhanVienList dsnv, KhachHangList dskh, NhaCungCapList ncc, DanhSachSanPham dssp) {
//        this.dshd = dshd;
//        this.dsnv = dsnv;
//        this.dskh = dskh;
//        this.dsncc = dsncc;
//        this.dssp = dssp;
//    }
//    
////    public void menu() throws FileNotFoundException{
////        mainKH kh = new mainKH();
////        kh.menu();
////	mainNV nv = new mainNV();
////        nv.menu();
//////        mainNCC ncc = new mainNCC();
//////        ncc.menu();
//////        ================================== 
////
////        Scanner sc = new Scanner(System.in);
////        int chucNang;
////        
////
////        do{
////            System.out.println("================MENU=================");
////            System.out.println("1. Nhap danh sach hoa don.");
////            System.out.println("2. Xuat thong tin hoa don.");
////            System.out.println("3. Them hoa don.");
////            System.out.println("4. Tim thong tin hoa don.");
////            System.out.println("5. Sua thong tin hoa don.");
////            System.out.println("6. Xoa thong tin hoa don.");
////            System.out.println("7. Thong ke.");
////            System.out.println("8. Thoat khoi chuong trinh.");
////            System.out.print("Nhap lua chon cua ban: ");
////            chucNang = sc.nextInt();
////
////            switch(chucNang){
////                case 1: 
////                    //nhap tu ban phim
////                    dshd.NhapDanhSach();
////                    break;
////                case 2: 
////                    dshd.XuatDanhSach();
////                    break;
////                case 3:
////                    break;
////                case 4: 
////                    dshd.tim(dsnv, dskh);
////                    break;
////                case 5: 
////                    dshd.sua(dsnv, dskh);
////                    break;
////                case 6: 
////                    dshd.xoa();
////                    break;
////                case 7:
////                    System.out.print("Ma san pham muon thong ke theo so tien: ");
////                    String spThongKe = sc.nextLine();
////                    break;
////                default: 
////                    System.out.println("Thoat khoi chuong trinh.");
////                    break;
////            }
////        
////        }
////        while(chucNang >= 1 && chucNang < 8);
////        dshd.GhiFileJava("HoaDondata.txt", "ChiTietHoaDondata.txt");
////        dsnv.GhiFileJava("NhanViendata.txt");
////        dskh.GhiFileJava("KhachHangdata.txt");
//////        dsncc.ghiFile("NhaCungCapdata.txt");
////    }
////    
//    public void menu() {
//		int select = 0;
//		String mahd = "";
//		loop:
//			while(true) {
//				System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
//				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm hoá đơn");
//				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá hoá đơn");
//				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa thông tin hoá đơn");
//				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm hoá đơn");
//				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách hoá đơn");
//				System.out.println("\t\t\t\t\t6.Ấn phím 6 để dừng các thao tác");
//				System.out.print("\nLựa chọn của bạn là: ");
//				select = sc.nextInt();
//				sc.nextLine();
//				switch(select) {
//					case 1:
//						System.out.print("Nhập vào số lượng hoá đơn cần thêm: ");
//						int sl = sc.nextInt();
//						Insert(sl);
//						break;
//					case 2:
//						System.out.print("Nhập vào mã hoá đơn cần xoá: ");
//						mahd = sc.nextLine();
//						Delete(mahd);
//						XuatDanhSach();
//						break;
//					case 3:
//						System.out.print("Nhập vào mã hoá đơn cần sửa: ");
//						mahd = sc.nextLine();
//						Adjust(mahd);
//						break;
//					case 4:
//						Search();
//						break;
//					case 5: 
//						XuatDanhSach();
//						break;
//					case 6:
//						break loop;
//					default:
//						System.err.println("Bạn đã nhập sai lựa chọn");
//						break;
//				}
//			}
//	}
//}
