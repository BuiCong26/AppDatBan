CREATE DATABASE DatBan_db
GO
USE DatBan_db
GO
CREATE TABLE KhachHang
(
	idKhachHang INT IDENTITY(1,1) PRIMARY KEY,
	tenKhachHang NVARCHAR(30),
	PhoneNumber NVARCHAR(20),
)
GO
CREATE TABLE Ban
(
	idBan INT IDENTITY(100,1) PRIMARY KEY,
	tenBan NVARCHAR(20),
	ghichu NVARCHAR(50)
)
GO
CREATE TABLE Datban
(
	idKhachHang INT FOREIGN KEY REFERENCES dbo.KhachHang(idKhachHang),
	idBan INT FOREIGN KEY REFERENCES dbo.Ban(idBan),
	PRIMARY KEY(idKhachHang,idBan),
	Thoigian_Datban DATETIME,
	Thoigian_Hethan DATETIME
)
GO
CREATE TABLE MonAn_DoUong
(
   idMonAn INT IDENTITY(1000,1) PRIMARY KEY,
   TenMon NVARCHAR(30),
   Mota NVARCHAR(30),
   Photo NVARCHAR(500)
)
CREATE TABLE Orders
(
	idOrder INT IDENTITY(1,1) PRIMARY KEY,
	NgaytaoOrder DATETIME,
	idKhachHang INT FOREIGN KEY REFERENCES dbo.KhachHang(idKhachHang),
	idBan INT FOREIGN KEY REFERENCES dbo.Ban(idBan)
)
GO
CREATE TABLE ChitietOrders
(
	idOrder INT FOREIGN KEY REFERENCES dbo.Orders(idOrder),
	idMonAn INT FOREIGN KEY REFERENCES dbo.MonAn_DoUong(idMonAn),
	PRIMARY KEY (idOrder,idMonAn),
	Ghichu NVARCHAR(50)	
)
GO


INSERT INTO MonAn_DoUong(TenMon,Mota,Photo) VALUES(N'Gà Sốt Mật Ong', N'Món ăn thơm ngon',N'https://cdn.huongnghiepaau.com/wp-content/uploads/2019/01/ga-sot-mat-ong.jpg')
INSERT INTO MonAn_DoUong(TenMon,Mota,Photo) VALUES(N'Cơm Chiên Hải Sản', N'Món ăn thơm ngon',N'https://cdn.tgdd.vn/2021/01/CookProduct/comchienhaisan-1200x676.jpg')
INSERT INTO MonAn_DoUong(TenMon,Mota,Photo) VALUES(N'Lẩu Gà', N'Lẩu chua ngọt',N'https://yummyday.vn/uploads/images/cach-nau-lau-ga-chua-cay.jpg')
INSERT INTO MonAn_DoUong(TenMon,Mota,Photo) VALUES(N'Lẩu Thái Tomyum', N'Lẩu chua ngọt',N'https://yt.cdnxbvn.com/medias/uploads/38/38541-recipe2765128921.jpg')
SELECT * FROM dbo.MonAn_DoUong

INSERT INTO KhachHang(tenKhachHang,PhoneNumber) VALUES (N'Nguyễn Đức Anh','0394582875')
INSERT INTO KhachHang(tenKhachHang,PhoneNumber) VALUES (N'Nguyễn Thuỳ Dung','0857923595')
SELECT * FROM dbo.KhachHang

INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 01',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 02',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 03',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 04',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 05',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 06',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 07',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 08',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 09',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 10',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 11',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 12',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 13',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 14',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 15',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 16',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 17',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 18',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 19',NULL)
INSERT INTO Ban(tenBan,ghichu) VALUES (N'Ban 20',NULL)
SELECT * FROM dbo.Ban

INSERT INTO Datban(idKhachHang,idBan,Thoigian_Datban,Thoigian_Hethan) VALUES ('1','100','8/9/2019 9:20:00','8/9/2019 10:20:00')
INSERT INTO Datban(idKhachHang,idBan,Thoigian_Datban,Thoigian_Hethan) VALUES ('2','101','8/10/2019 9:20:00','8/10/2019 10:20:00')
SELECT * FROM dbo.Datban
 
INSERT INTO Orders(NgaytaoOrder,idKhachHang,idBan) VALUES ('8/9/2019 9:20:00','1','100')
INSERT INTO Orders(NgaytaoOrder,idKhachHang,idBan) VALUES ('8/10/2019 9:20:00','2','101')
SELECT * FROM dbo.Orders

INSERT INTO ChitietOrders(idOrder,idMonAn,Ghichu) VALUES ('1','1000',NULL)
INSERT INTO ChitietOrders(idOrder,idMonAn,Ghichu) VALUES ('2','1001',NULL)
SELECT * FROM dbo.ChitietOrders