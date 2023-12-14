/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author HP
 */

public class Thuoc extends SanPham {
        DanhSachSanPham dssp = new DanhSachSanPham();
	private String cautruc;
	//CONSTRUCTOR
	public Thuoc() {}
        public Thuoc(String masp, String thuonghieu, String tensp, String loaisp, int sl, float gia, double thanhtien, String cautruc) {
           super(masp, thuonghieu, tensp, loaisp, sl, gia, thanhtien);
           this.cautruc = cautruc;
        }
        public Thuoc(Thuoc a) {
           super((SanPham) a);
           cautruc = a.cautruc;
        }
	//SETTER
        public void setCautruc(String cautruc) {
            this.cautruc = cautruc;
        }
    //GETTER
        public String getCautruc() {
             return cautruc;
        }
    //CAC HAM KHAC
        public String KiemTranNhapMaThuoc(){
            String nhap;
            while(true){
                nhap = sc.nextLine();
                nhap = nhap.toUpperCase();
                if(nhap.matches("SP" + "[0,9]{2}")){
                    return nhap;
                }
                else{
                    System.err.println("Định dạng mã sản phẩm: SP.. VD: SP01");
                }
                System.out.print("Mã sản phẩm không hợp lệ. Mời bạn nhập lại: ");
            }
        }
        
    @Override
    public void Nhap() {
        do {
            System.out.print("Mời bạn nhập mã sản phẩm: ");
            masp = sc.nextLine();
            if((masp.matches("SP" + "[0,9]{2}")))
                System.err.println("Bạn đã nhập mã sản phẩm thiếu hoặc thừa kí tự. Xin mời nhập lại!!!");
        }while(masp.length() < 3 || masp.length() > 5);
        
        System.out.print("Thương hiệu của thuốc: ");
        thuonghieu = sc.nextLine();
        
        System.out.print("Tên của thuốc: ");
        tensp = sc.nextLine();
        
        loaisp = "THUOC";
        
        System.out.print("Số lượng thuốc: ");
        sl = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Giá tiền của mỗi thuốc : ");
        gia = sc.nextFloat();
        sc.nextLine();
        
        System.out.print("Kết cấu của mỗi thuốc : ");
        cautruc = sc.nextLine();
        
        thanhtien = gia * sl;
    }
	@Override
	public void Xuat() {
		System.out.format("%8s | %12s | %32s | %20s | %8s | %14s | %17s | %12s | %15s ||\n",  masp, thuonghieu, tensp, loaisp, sl, gia, thanhtien, cautruc,null);
	}
	@Override
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		int code = 0;
		dos.writeInt(code);
		dos.writeUTF(masp);
		dos.writeUTF(thuonghieu);
		dos.writeUTF(tensp);
                dos.writeUTF(loaisp);
		dos.writeInt(sl);
		dos.writeFloat(gia);
		dos.writeDouble(thanhtien);
                dos.writeUTF(cautruc);
		dos.close();
	}
}

