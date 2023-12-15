DROP TABLE IF EXISTS CLIENTS
GO 

CREATE TABLE CLIENTS
(
	ID					INT				NOT NULL,
	UPDATE_COUNTER		INT				NOT NULL,
	FIRST_NAME			NVARCHAR(64)	NOT NULL,
	MIDDLE_NAME			NVARCHAR(64)	NOT NULL,
	LAST_NAME			NVARCHAR(64)	NOT NULL,
	UCN					NVARCHAR(16)	NOT NULL,
	COMPANY_ID			INT				NOT NULL,
	[USER_ID]			INT				NOT NULL,
	PHONE_NUMBER		NVARCHAR(16)	NOT NULL,
	CONSTRAINT PK_CLIENTS_ID PRIMARY KEY(ID),
	CONSTRAINT FK_CLIENTS_COMPANY_ID FOREIGN KEY(COMPANY_ID)
	REFERENCES COMPANIES(ID)
)
GO

CREATE UNIQUE INDEX UX_CLIENTS_UCN
ON CLIENTS(UCN)
GO

CREATE UNIQUE INDEX UX_CLIENTS_PHONE_NUMBER
ON CLIENTS(PHONE_NUMBER)
GO

EXEC SP_ADD_NEW_COUNTER 'CLIENTS', 'CLIENTS', 'ID'
GO