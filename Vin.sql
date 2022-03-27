﻿USE [JVVinHome]
GO
/****** Object:  Table [dbo].[BLOCK]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BLOCK](
	[MABLOCK] [char](1) NOT NULL,
	[TANG] [char](4) NOT NULL,
 CONSTRAINT [PK_BLOCK] PRIMARY KEY CLUSTERED 
(
	[MABLOCK] ASC,
	[TANG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CANHO]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CANHO](
	[MACH] [char](7) NOT NULL,
	[TRANGTHAI] [bit] NULL,
	[MALOAI] [char](5) NULL,
	[MANV] [char](10) NULL,
	[MABLOCK] [char](1) NULL,
	[TANG] [char](4) NULL,
 CONSTRAINT [PK_CANHO] PRIMARY KEY CLUSTERED 
(
	[MACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CTHDD]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CTHDD](
	[MACH] [char](7) NOT NULL,
	[MAHDDIEN] [char](8) NOT NULL,
	[DONGIADIEN] [money] NULL,
	[TONGLDTT] [int] NULL,
	[THANHTIEN] [money] NULL,
 CONSTRAINT [PK_CTHDD] PRIMARY KEY CLUSTERED 
(
	[MACH] ASC,
	[MAHDDIEN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CTHDDVC]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CTHDDVC](
	[MACH] [char](7) NOT NULL,
	[MAHDDVC] [char](8) NOT NULL,
	[PHIANNINH] [money] NULL,
	[PHIMOITRUONG] [money] NULL,
	[PHIGIUXE] [money] NULL,
	[THANHTIEN] [money] NULL,
 CONSTRAINT [PK_CTHDDVC] PRIMARY KEY CLUSTERED 
(
	[MACH] ASC,
	[MAHDDVC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CTHDN]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CTHDN](
	[MACH] [char](7) NOT NULL,
	[MAHDNUOC] [char](8) NOT NULL,
	[DONGIANUOC] [money] NULL,
	[TONGLNTT] [int] NULL,
	[THANHTIEN] [money] NULL,
 CONSTRAINT [PK_CTHDN] PRIMARY KEY CLUSTERED 
(
	[MACH] ASC,
	[MAHDNUOC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DICHVUCONG]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DICHVUCONG](
	[MAHDDVC] [char](8) NOT NULL,
	[MANV] [char](10) NULL,
	[NGAYTB] [date] NULL,
 CONSTRAINT [PK_DICHVUCONG] PRIMARY KEY CLUSTERED 
(
	[MAHDDVC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiCH]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiCH](
	[MALOAI] [char](5) NOT NULL,
	[DIENTICH] [float] NULL,
	[SOPHONG] [int] NULL,
 CONSTRAINT [PK_LoaiCH] PRIMARY KEY CLUSTERED 
(
	[MALOAI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NHANKHAU]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NHANKHAU](
	[MACH] [char](7) NOT NULL,
	[STT] [int] IDENTITY(1,1) NOT NULL,
	[HOTENNK] [nvarchar](50) NULL,
	[NTNS] [date] NULL,
	[PHAINK] [nvarchar](5) NULL,
	[QUEQUAN] [nvarchar](50) NULL,
	[SDT] [varchar](15) NULL,
	[MANV] [char](10) NULL,
 CONSTRAINT [PK_NHANKHAU] PRIMARY KEY CLUSTERED 
(
	[STT] ASC,
	[MACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [char](10) NOT NULL,
	[HOTENNV] [nvarchar](50) NULL,
	[NS] [date] NULL,
	[PHAI] [nvarchar](5) NULL,
	[CHUCVU] [nvarchar](30) NULL,
	[MAIL] [nchar](30) NULL,
 CONSTRAINT [PK_NHANVIEN] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QLDIEN]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[QLDIEN](
	[MAHDDIEN] [char](8) NOT NULL,
	[NGAYKT] [date] NULL,
	[CHISODIEN] [float] NULL,
	[MANV] [char](10) NULL,
 CONSTRAINT [PK_QLDIEN] PRIMARY KEY CLUSTERED 
(
	[MAHDDIEN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QLNUOC]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[QLNUOC](
	[MAHDNUOC] [char](8) NOT NULL,
	[NGAYCHOT] [date] NULL,
	[CHISONUOC] [float] NULL,
	[MANV] [char](10) NULL,
 CONSTRAINT [PK_QLNUOC] PRIMARY KEY CLUSTERED 
(
	[MAHDNUOC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[ID] [char](10) NOT NULL,
	[PASS] [nchar](15) NOT NULL,
	[MANV] [char](10) NULL,
 CONSTRAINT [PK_TAIKHOAN] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[THAMSO]    Script Date: 4/27/2021 9:00:23 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[THAMSO](
	[MATS] [char](3) NOT NULL,
	[TENTS] [nvarchar](30) NULL,
	[GIATRI] [float] NULL,
 CONSTRAINT [PK_THAMSO] PRIMARY KEY CLUSTERED 
(
	[MATS] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO

INSERT [dbo].[TAIKHOAN] ([ID], [PASS], [MANV]) VALUES (N'PGD1234567', N'123321', N'1811061805')
INSERT [dbo].[TAIKHOAN] ([ID], [PASS], [MANV]) VALUES (N'QLTK251096', N'QLTK251096', N'1811061540')
INSERT [dbo].[THAMSO] ([MATS], [TENTS], [GIATRI]) VALUES (N'A04', N'Phí an ninh', 50000)
INSERT [dbo].[THAMSO] ([MATS], [TENTS], [GIATRI]) VALUES (N'D01', N'Đơn giá điện', 3500)
INSERT [dbo].[THAMSO] ([MATS], [TENTS], [GIATRI]) VALUES (N'G05', N'Phí giữ xe', 50000)
INSERT [dbo].[THAMSO] ([MATS], [TENTS], [GIATRI]) VALUES (N'N02', N'Đơn giá nước', 12000)
INSERT [dbo].[THAMSO] ([MATS], [TENTS], [GIATRI]) VALUES (N'R03', N'Phí môi trường', 20000)
ALTER TABLE [dbo].[CANHO]  WITH CHECK ADD  CONSTRAINT [Luu] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[CANHO] CHECK CONSTRAINT [Luu]
GO
ALTER TABLE [dbo].[CANHO]  WITH CHECK ADD  CONSTRAINT [thuoc] FOREIGN KEY([MABLOCK], [TANG])
REFERENCES [dbo].[BLOCK] ([MABLOCK], [TANG])
GO
ALTER TABLE [dbo].[CANHO] CHECK CONSTRAINT [thuoc]
GO
ALTER TABLE [dbo].[CANHO]  WITH CHECK ADD  CONSTRAINT [Trong] FOREIGN KEY([MALOAI])
REFERENCES [dbo].[LoaiCH] ([MALOAI])
GO
ALTER TABLE [dbo].[CANHO] CHECK CONSTRAINT [Trong]
GO
ALTER TABLE [dbo].[CTHDD]  WITH CHECK ADD  CONSTRAINT [Relationship15] FOREIGN KEY([MACH])
REFERENCES [dbo].[CANHO] ([MACH])
GO
ALTER TABLE [dbo].[CTHDD] CHECK CONSTRAINT [Relationship15]
GO
ALTER TABLE [dbo].[CTHDD]  WITH CHECK ADD  CONSTRAINT [Relationship16] FOREIGN KEY([MAHDDIEN])
REFERENCES [dbo].[QLDIEN] ([MAHDDIEN])
GO
ALTER TABLE [dbo].[CTHDD] CHECK CONSTRAINT [Relationship16]
GO
ALTER TABLE [dbo].[CTHDDVC]  WITH CHECK ADD  CONSTRAINT [Relationship21] FOREIGN KEY([MACH])
REFERENCES [dbo].[CANHO] ([MACH])
GO
ALTER TABLE [dbo].[CTHDDVC] CHECK CONSTRAINT [Relationship21]
GO
ALTER TABLE [dbo].[CTHDDVC]  WITH CHECK ADD  CONSTRAINT [Relationship22] FOREIGN KEY([MAHDDVC])
REFERENCES [dbo].[DICHVUCONG] ([MAHDDVC])
GO
ALTER TABLE [dbo].[CTHDDVC] CHECK CONSTRAINT [Relationship22]
GO
ALTER TABLE [dbo].[CTHDN]  WITH CHECK ADD  CONSTRAINT [Relationship18] FOREIGN KEY([MACH])
REFERENCES [dbo].[CANHO] ([MACH])
GO
ALTER TABLE [dbo].[CTHDN] CHECK CONSTRAINT [Relationship18]
GO
ALTER TABLE [dbo].[CTHDN]  WITH CHECK ADD  CONSTRAINT [Relationship19] FOREIGN KEY([MAHDNUOC])
REFERENCES [dbo].[QLNUOC] ([MAHDNUOC])
GO
ALTER TABLE [dbo].[CTHDN] CHECK CONSTRAINT [Relationship19]
GO
ALTER TABLE [dbo].[DICHVUCONG]  WITH CHECK ADD  CONSTRAINT [Relationship6] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[DICHVUCONG] CHECK CONSTRAINT [Relationship6]
GO
ALTER TABLE [dbo].[NHANKHAU]  WITH CHECK ADD  CONSTRAINT [CapNhat] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[NHANKHAU] CHECK CONSTRAINT [CapNhat]
GO
ALTER TABLE [dbo].[NHANKHAU]  WITH CHECK ADD  CONSTRAINT [Gom] FOREIGN KEY([MACH])
REFERENCES [dbo].[CANHO] ([MACH])
GO
ALTER TABLE [dbo].[NHANKHAU] CHECK CONSTRAINT [Gom]
GO
ALTER TABLE [dbo].[QLDIEN]  WITH CHECK ADD  CONSTRAINT [Relationship8] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[QLDIEN] CHECK CONSTRAINT [Relationship8]
GO
ALTER TABLE [dbo].[QLNUOC]  WITH CHECK ADD  CONSTRAINT [Relationship7] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[QLNUOC] CHECK CONSTRAINT [Relationship7]
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [Relationship3] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [Relationship3]
GO
