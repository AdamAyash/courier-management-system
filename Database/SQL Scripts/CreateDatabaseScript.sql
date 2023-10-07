﻿USE [master]
GO
--създаване на базатат данни и определяне на пътя към файловете чрез параметри

IF NOT EXISTS 
(
SELECT name FROM 
master.sys.databases 
WHERE name = N'<database_name, ,database_name>'
)
CREATE DATABASE [<database_name, ,database_name>]
 CONTAINMENT = NONE
 ON  PRIMARY 
( 
NAME = sample_database_file1,
	  FILENAME = N'<data_filename1, , C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\Data\Datasample_database_1.mdf>',
		  SIZE = 10MB,
          MAXSIZE = 50MB,
          FILEGROWTH = 10%)
 LOG ON 
( 
NAME = logical_log_filename1,
	  FILENAME = N'<log_filename1, , C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\Data\Datasample_database_1.ldf>',
          SIZE = 10MB,
          MAXSIZE = 50MB,
          FILEGROWTH = 10%
)
GO

--сменяне на collation-a на базата

ALTER DATABASE [<database_name, ,database_name>]
	COLLATE Cyrillic_General_CI_AS
GO


