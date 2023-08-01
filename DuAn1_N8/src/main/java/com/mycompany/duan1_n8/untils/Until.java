/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.untils;

import com.mycompany.duan1_n8.utilities.*;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author BXT
 */
public class Until {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_MA = 1;
    public static final int COLUMN_INDEX_TEN = 2;
    public static final int COLUMN_INDEX_GIOITINH = 3;
    public static final int COLUMN_INDEX_EMAIL = 4;
    public static final int COLUMN_INDEX_QUEQUAN = 5;
    public static final int COLUMN_INDEX_NGAYSINH = 6;
    public static final int COLUMN_INDEX_SDT = 7;
    public static final int COLUMN_INDEX_MATKHAU = 8;
    public static final int COLUMN_INDEX_TRANGTHAI = 9;
    public static final int COLUMN_INDEX_CHUCVU = 10;
    public static CellStyle cellStyleFormatNumber = null;
    
    
    public static final String NUMBER = "^[0-9]+$";
    public static final String SDT = "^[0-9]+$";
    public static final String TEXT = "[a-z A-Z]+";
    public static final String EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    public static final String NGAYSINH = "^(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[012])\\/((19|20)\\d{2})$";
}