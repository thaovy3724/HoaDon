/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyhoadon;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class QuanLy {
    public void quanLy(){
        int luaChon;
        Scanner sc = new Scanner(System.in);
        do{
            
            luaChon = sc.nextInt();
        }
        while(luaChon >= 1 && luaChon < 5);
    }
}
