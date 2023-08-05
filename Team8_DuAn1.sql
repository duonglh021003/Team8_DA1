CREATE DATABASE Team8_DuAn1

GO
-- ThietKe
CREATE TABLE ThietKe(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50) DEFAULT NULL,
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
)
insert into ThietKe values
('ma02',N'dương','2002-12-12','2002-12-12')
select *
from 
ThietKe


GO
-- DoiTuongSuDung
CREATE TABLE DoiTuongSuDung(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL, 
KichThuoc DECIMAL(20,2) DEFAULT NULL,
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
)
insert into DoiTuongSuDung values
('ma02',N'người lớn',3.3,'2002-12-12','2002-12-12')
select *
from
DoiTuongSuDung


GO
-- NSX
CREATE TABLE NSX(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL, 
QuocGia NVARCHAR(50) DEFAULT NULL, 
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
)
insert into NSX values
('ma01',N'FPT',N'VN','2002-12-12','2002-12-12')
select *
from
NSX


GO
-- MauSac
CREATE TABLE MauSac(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL ,
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
)
insert into MauSac values
('ma01',N'đỏ','2002-12-12','2002-12-12')
select *
from
MauSac


GO
-- SanPham
CREATE TABLE SanPham(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL ,
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
)
insert into SanPham values
('ma01',N'3D','2002-12-12','2002-12-12')
select *
from
SanPham


GO
-- Lop
CREATE TABLE Lop(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
SoLuong int DEFAULT NULL,
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
)
insert into Lop values
('5','2002-12-12','2002-12-12')
select *
from
Lop


GO
--ChiTietSP
CREATE TABLE ChiTietSP(
MaQR BIGINT PRIMARY KEY ,
NgaySanXuat Date DEFAULT NULL,
HanSuDung Date DEFAULT NULL,
Gia DECIMAL(20,0) DEFAULT NULL,
MoTa NVARCHAR(50) DEFAULT NULL,
SoLuong int DEFAULT NULL,
TrangThai INT DEFAULT 0 ,
IdTK BIGINT , 
IdDTSD BIGINT , 
IdNSX BIGINT , 
IdMS BIGINT , 
IdSP BIGINT , 
IdLop BIGINT , 
)
insert into ChiTietSP values
(83758902,'2002-12-12','2002-12-12',10,N'không',20,DEFAULT,1,1,1,1,1,1)
select *
from
ChiTietSP


GO
-- ChucVu
CREATE TABLE ChucVu(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL 
)
insert into ChucVu values
('ma01',N'quản lý'),
('ma02',N'nhân viên')
select *
from
ChucVu

GO
-- Thu2
CREATE TABLE Thu2(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into Thu2 values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
Thu2


GO
-- Thu3
CREATE TABLE Thu3(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into Thu3 values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
Thu3

GO
-- T4
CREATE TABLE Thu4(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into Thu4 values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
Thu4

GO
-- T5
CREATE TABLE Thu5(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into Thu5 values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
Thu5

GO
-- T6
CREATE TABLE Thu6(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into Thu6 values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
Thu6

GO
-- T7
CREATE TABLE Thu7(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into Thu7 values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
Thu7

GO
-- CN
CREATE TABLE ChuNhat(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
Ca NVARCHAR(50) DEFAULT NULL 
)
insert into ChuNhat values
('ma01',N'nghỉ'),
('ma02',N'ca 1(8-12)'),
('ma03',N'ca 2(13-17h)'),
('ma04',N'full time(8-17h)')
select *
from
ChuNhat

GO
--NhanVien
CREATE TABLE NhanVien(
MaQR BIGINT PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL, 
GioiTinh NVARCHAR(50) DEFAULT NULL,
Email NVARCHAR(50) DEFAULT NULL,
QueQuan NVARCHAR(50) DEFAULT NULL,
NgaySinh Date DEFAULT NULL,
Sdt VARCHAR(15) DEFAULT NULL,
MatKhau NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 0 ,
IdChucVu BIGINT , 
)
insert into NhanVien values
(12072005,'ma01','dương',N'true',N'd@gmail.com',N'HN','2002-12-12','0123467890','1',DEFAULT,1)
select *
from
NhanVien

GO
-- DanhSachLamViec
CREATE TABLE DanhSachLamViec(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
NgayTao Date DEFAULT NULL,
NgaySua Date DEFAULT NULL,
IdT2 BIGINT ,
IdT3 BIGINT ,
IdT4 BIGINT ,
IdT5 BIGINT ,
IdT6 BIGINT ,
IdT7 BIGINT ,
IdCN BIGINT ,
MaQRNhanVien BIGINT
)
insert into DanhSachLamViec values
('ma01','2002-12-12','2002-12-12',2,2,2,2,2,2,2,12072005)
select *
from
DanhSachLamViec

GO
-- CheckIn
CREATE TABLE CheckIn(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
NgayTao Date DEFAULT NULL,
GioVao VARCHAR(50) DEFAULT NULL,
PhutVao VARCHAR(50) DEFAULT NULL,
PhutCoDinh VARCHAR(50) DEFAULT 0,
BaoCao NVARCHAR(50) DEFAULT NULL,
MaQRNhanVien BIGINT
)
insert into CheckIn values
('ma01','2002-12-12','08','01','00',N'khong',12072005),
('ma02','2002-12-12','07','45','00',N'khong',12072005),
('ma03','2002-12-12','08','14','00',N'khong',12072005),
('ma04','2002-12-12','08','16','00',N'khong',12072005),
('ma05','2002-12-12','08','00','00',N'khong',12072005),
('ma06','2002-12-12','07','59','00',N'khong',12072005),
('ma07','2002-12-12','13','14','00',N'khong',12072005),
('ma08','2002-12-12','12','44','00',N'khong',12072005),
('ma09','2002-12-12','13','09','00',N'khong',12072005)

select *
from
CheckIn

GO
-- CheckOut
CREATE TABLE CheckOut(
Id BIGINT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE, 
NgayTao Date DEFAULT NULL,
GioRa VARCHAR(50) DEFAULT NULL,
PhutRa VARCHAR(50) DEFAULT NULL,
BaoCao NVARCHAR(50) DEFAULT NULL,
GhiChu NVARCHAR(50) DEFAULT NULL,
MaQRNhanVien BIGINT
)
insert into CheckOut values
('ma01','2002-12-12','17','00',N'khong',N'khong',12072005)
select *
from
CheckOut




GO
--KhachHang
CREATE TABLE KhachHang(
MaQR BIGINT PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL, 
GioiTinh NVARCHAR(50) DEFAULT NULL,
Email NVARCHAR(50) DEFAULT NULL,
QueQuan NVARCHAR(50) DEFAULT NULL,
NgaySinh Date DEFAULT NULL,
Sdt VARCHAR(15) DEFAULT NULL,
TrangThai INT DEFAULT 0 ,
)
insert into KhachHang values
(87647384,'ma01','dương',N'true',N'd@gmail.com',N'HN','2002-12-12','0123467890',DEFAULT)
select *
from
KhachHang


GO
--PhieuGiamGia
CREATE TABLE PhieuGiamGia(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE, 
Ten NVARCHAR(50) DEFAULT NULL, 
NgayBD Date DEFAULT NULL,
NgayKT Date DEFAULT NULL,
HinhThucGiamGia VARCHAR(50) DEFAULT NULL,
GiaTriGiam DECIMAL(20,0) DEFAULT NULL,
TrangThai INT DEFAULT 0 , 
)
insert into PhieuGiamGia values
('ma01',N'phiếu 1','2002-12-12','2002-12-12',N'hinh 1',100,DEFAULT)
select *
from
PhieuGiamGia


GO
--HoaDon
CREATE TABLE HoaDon(
Id BIGINT IDENTITY(1,1) PRIMARY KEY ,
Ma VARCHAR(20) UNIQUE,
NgayTao Date DEFAULT NULL,
MoTa NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 0 ,
MaQRKhachHang BIGINT ,
MaQRNhanVien BIGINT,

)
insert into HoaDon values
('ma01','2002-12-12',N'không',DEFAULT,87647384,12072005)
select *
from
HoaDon


GO
-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
IdHoaDon BIGINT ,
MaQRCTSP BIGINT ,
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
TongTien DECIMAL(20,0), 
TrangThai INT DEFAULT 0 ,
IdPhieuGiamGia BIGINT ,
CONSTRAINT PK_HoaDonChiTiet PRIMARY KEY (IdHoaDon,MaQRCTSP),
CONSTRAINT FK1 FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(Id),
CONSTRAINT FK2 FOREIGN KEY(MaQRCTSP) REFERENCES ChiTietSP(MaQR),
CONSTRAINT FK3 FOREIGN KEY(IdPhieuGiamGia) REFERENCES PhieuGiamGia(Id),
)
insert into HoaDonChiTiet values
(1,83758902,5,10,50,DEFAULT,1)
select *
from
HoaDonChiTiet


GO
-- ChiTietSP - ThietKe
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdTK) REFERENCES ThietKe(Id)
-- ChiTietSP - DoiTuongSuDung
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdDTSD) REFERENCES DoiTuongSuDung(Id)
-- ChiTietSP - NSX
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdNSX) REFERENCES NSX(Id)
-- ChiTietSP - MauSac
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdMS) REFERENCES MauSac(Id)
-- ChiTietSP - SanPham
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdSP) REFERENCES SanPham(Id)
-- ChiTietSP - Lop
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdLop) REFERENCES Lop(Id)
--NhanVien - ChucVu
ALTER TABLE NhanVien ADD FOREIGN KEY(IdChucVu) REFERENCES ChucVu(Id)
--HoaDon - NhanVien
ALTER TABLE HoaDon ADD FOREIGN KEY(MaQRNhanVien) REFERENCES NhanVien(MaQR)
--HoaDon - KhachHang
ALTER TABLE HoaDon ADD FOREIGN KEY(MaQRKhachHang) REFERENCES KhachHang(MaQR)
-- DanhSachLamViec - T2
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdT2) REFERENCES Thu2(Id)
-- DanhSachLamViec - T3
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdT3) REFERENCES Thu3(Id)
-- DanhSachLamViec - T4
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdT4) REFERENCES Thu4(Id)
-- DanhSachLamViec - T5
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdT5) REFERENCES Thu5(Id)
-- DanhSachLamViec - T6
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdT6) REFERENCES Thu6(Id)
-- DanhSachLamViec - T7
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdT7) REFERENCES Thu7(Id)
-- DanhSachLamViec - CN
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(IdCN) REFERENCES ChuNhat(Id)
-- DanhSachLamViec - NhanVien
ALTER TABLE DanhSachLamViec ADD FOREIGN KEY(MaQRNhanVien) REFERENCES NhanVien(MaQR)
-- CheckIn - NhanVien
ALTER TABLE CheckIn ADD FOREIGN KEY(MaQRNhanVien) REFERENCES NhanVien(MaQR)
-- CheckOut - NhanVien
ALTER TABLE CheckOut ADD FOREIGN KEY(MaQRNhanVien) REFERENCES NhanVien(MaQR)
