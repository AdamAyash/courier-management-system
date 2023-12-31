DROP TABLE IF EXISTS COMPANIES
GO

CREATE TABLE COMPANIES
(
	ID				INT					NOT NULL,
	UPDATE_COUNTER	INT					NOT NULL,
	NAME			NVARCHAR(128)		NOT NULL,
	EGFN			NVARCHAR(32)		NOT NULL,
	CITY_ID			INT					NOT NULL,
	PHONE_NUMBER	NVARCHAR(32)		NOT NULL,
	EMAIL			NVARCHAR(64)		NOT NULL,
	CONSTRAINT PK_COMPANIES_ID PRIMARY KEY(ID)
)
GO

CREATE UNIQUE INDEX UX_COMPANIES_EGFN
ON COMPANIES(EGFN)
GO

CREATE UNIQUE INDEX UX_COMPANIES_PHONE_NUMBER
ON COMPANIES(PHONE_NUMBER)
GO

EXEC SP_ADD_NEW_COUNTER N'COMPANIES', N'COMPANIES', N'ID'