DROP TABLE IF EXISTS CITIES
GO

CREATE TABLE CITIES
( 
	ID			INT				NOT NULL,
	NAME		NVARCHAR(64)	NOT NULL,
	STATE		NVARCHAR(64)	NOT NULL,
	POST_CODE 	NVARCHAR(16)	NOT NULL
	CONSTRAINT PK_CITIES_ID PRIMARY KEY (ID) 
)
GO
