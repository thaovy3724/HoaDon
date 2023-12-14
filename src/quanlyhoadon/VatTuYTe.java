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
public class VatTuYTe extends SanPham {
        DanhSachSanPham dssp = new DanhSachSanPham();
	private String donvitinh;
	//CONSTRUCTOR
	public VatTuYTe() {
            donvitinh ="";
        }
	public VatTuYTe(String masp, String thuonghieu, String tensp, String loaisp, int sl, float gia, double thanhtien,  String donvitinh) {
               super(masp, thuonghieu, tensp, loaisp, sl, gia, thanhtien);
               this.donvitinh = donvitinh;
         }
	public VatTuYTe(VatTuYTe a) {
		super((VatTuYTe) a);
		donvitinh = a.donvitinh;
	}
	//SETTER
    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }
	//GETTER
    public String getDonvitinh() {
        return donvitinh;
    }
	//CAC HAM KHAC
	@Override
	public void Nhap() {
		do {
			System.out.println("Khi nhập mã sản phẩm, tối thiểu chỉ có 3 kí tự và tối đa là 5 kí tự:");
			System.out.print("Mời bạn nhập mã sản phẩm: ");
			masp = sc.nextLine();
			if(masp.length() < 3 || masp.length() > 5) 
				System.err.println("Bạn đã nhập mã sản phẩm thiếu hoặc thừa kí tự. Xin mời nhập lại!!!");
		}while(masp.length() < 3 || masp.length() > 5);
		System.out.print("Thương hiệu : ");
		thuonghieu = sc.nextLine();
                
                System.out.print("Tên của vật tư: ");
		tensp = sc.nextLine();
                
                loaisp = "VTYT";
                
		System.out.print("Số lượng vật tư: ");
		sl = sc.nextInt();
		sc.nextLine();
                
		System.out.print("Giá tiền của mỗi vật tư : ");
		gia = sc.nextFloat();
                sc.nextLine();
                
                System.out.print("Đơn vị : ");
		donvitinh = sc.nextLine();
                
                thanhtien = gia * sl;
                
}
	@Override
	public void Xuat() {
		System.out.format("%8s | %12s | %32s | %20s | %8s | %14s | %17s | %12s | %15s ||\n",  masp, thuonghieu, tensp, loaisp, sl, gia, thanhtien,null, donvitinh);
	}
        
	@Override
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		int code = 1;
		dos.writeInt(code);
		dos.writeUTF(masp);
		dos.writeUTF(thuonghieu);
		dos.writeUTF(tensp);
                dos.writeUTF(loaisp);
		dos.writeInt(sl);
		dos.writeFloat(gia);
		dos.writeDouble(thanhtien);
                dos.writeUTF(donvitinh);
		dos.close();
	}
}