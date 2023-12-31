DROP TABLE IF EXISTS USERS
GO

CREATE TABLE USERS
(
	ID			   INT				NOT NULL,
	UPDATE_COUNTER INT				NOT NULL,
	USERNAME	   NVARCHAR(128)	NOT NULL,
	PASSWORD	   NVARCHAR(256)	NOT NULL,
	ACCESS_ID	   INT			    NOT NULL,
	CONSTRAINT PK_USERS_ID PRIMARY KEY(ID),
	CONSTRAINT FK_USERS_ACCESS_ID FOREIGN KEY(ACCESS_ID)
	REFERENCES ACCESS(ID)
)
GO


CREATE UNIQUE INDEX UX_USERS_USERNAME
ON USERS(USERNAME)
GO

CREATE NONCLUSTERED INDEX IX_USERS_USERNAME_PASSWORD
ON USERS(USERNAME, PASSWORD)
GO


INSERT INTO USERS
(
	ID,			  
	UPDATE_COUNTER,
	USERNAME,	  
	PASSWORD,	  
	ACCESS_ID
)
VALUES(1, 0, N'admin',N'$argon2id$v=19$m=15360,t=2,p=1$cxa/NpxUAUxu/4+LskHjtWLH/JR9jUUHbYe9AnwZ9t4bBIdqDzqhBgbxdsyCclXXEUo0rtjoSjngc/OT4pyTPg$LzjBkgFv05L+OcLxlgo5WFHoX60hup0Nze957WRGmOM', 1)
GO
