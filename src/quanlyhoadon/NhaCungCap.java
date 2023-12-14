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
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class NhaCungCap {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String ho;
        String ten;
	private String mancc,gioitinh, ngaysinh, sdt, diachi;
	//CONSTRUCTOR
	public NhaCungCap() {
		mancc = "";
                ho ="";
                ten ="";
                gioitinh="";
                ngaysinh="";
		sdt = "";
		diachi = "";
	}
	public NhaCungCap(NhaCungCap a) {
		mancc = a.mancc;
                ho = a.ho;
                ten = a.ten;
		sdt = a.sdt;
                gioitinh = a.gioitinh;
                ngaysinh = a.ngaysinh;
		diachi = a.diachi;
	}

    public NhaCungCap(String mancc,String ho, String ten,  String gioitinh, String ngaysinh, String sdt, String diachi) {
        this.ho = ho;
        this.ten = ten;
        this.mancc = mancc;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.diachi = diachi;
    }

        

	//SETTER	

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
	public String getHo() {
		return ho;
	}

    public String getTen() {
        return ten;
    }

    public String getMancc() {
        return mancc;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    //GETTER
    public String getDiachi() {
        return diachi;
    }

    //CAC HAM KHAC
    
    public boolean CheckNS(String date) {
		df.setLenient(false);
		try {
			df.parse(date);
		}catch(ParseException e) {
			return false;
		}
		return true;
	}
        public void Nhap() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập vào mã khách nha cung cap: ");
		mancc = sc.nextLine();
		
		System.out.print("Họ và tên lót: ");
		ho = sc.nextLine();
		
		System.out.print("Tên: ");
		ten = sc.nextLine();
                System.out.print("Giới tính: ");
		gioitinh = sc.nextLine();
                
                
		System.err.println("\nNgày tháng năm sinh của nhà cung cấp phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		do {
			System.out.print("Ngày tháng năm sinh: ");
			ngaysinh = sc.nextLine();
			if(!CheckNS(ngaysinh)) {
				System.err.print("\nNgày tháng năm sinh không hợp lệ. Xin mời nhập lại!!!");
				System.err.println();
			}
		}while(!CheckNS(ngaysinh));
                
		System.err.println("\nSố điện thoại của nhà cung cấp phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		do {
			System.out.print("Số điện thoại (nhập đúng 10 số): ");
			sdt = sc.nextLine();
			if(sdt.length() != 10) {
				System.err.println("Bạn đã nhập thiếu hoặc thừa số");
			}
		}while(sdt.length() != 10);
		
		System.out.print("Địa chỉ: ");
		diachi = sc.nextLine();
	}
	
	public void Xuat() {
		System.out.format(" %8s | %30s | %10s | %15s | %15s  | %9s | %30s ||\n", mancc, ho, ten, gioitinh, ngaysinh, sdt, diachi);
	}
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(mancc);
		dos.writeUTF(ho);
		dos.writeUTF(ten);
		dos.writeUTF(gioitinh);
		dos.writeUTF(ngaysinh);
		dos.writeUTF(sdt);
		dos.writeUTF(diachi);
		dos.close();
	}
}
