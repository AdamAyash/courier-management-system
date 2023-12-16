CREATE OR ALTER VIEW VI_ORDERS
AS 
	SELECT												 
		ORDERS.ID											 AS ORDER_ID,
		OFFICE.NAME											 AS OFFICE_NAME,
		COMPANY.NAME										 AS COMPANY_NAME,
		COMPANY.EGFN										 AS COMPANY_BULSTAT,
		CONCAT(EMPLOYEE.FIRST_NAME, ' ', EMPLOYEE.LAST_NAME) AS EMPLOYEE_FULL_NAME,
		CONCAT(CLIENT.FIRST_NAME,  ' ', CLIENT.LAST_NAME)    AS CLIENT_FULL_NAME,
		CLIENT.UCN											 AS CLIENT_UCN
		FROM ORDERS WITH(NOLOCK)
		INNER JOIN OFFICES AS OFFICE WITH(NOLOCK)
			ON ORDERS.OFFICE_ID = OFFICE.ID
		INNER JOIN COMPANIES AS COMPANY WITH(NOLOCK)
			ON OFFICE.COMPANY_ID = ORDERS.COMPANY_ID
		INNER JOIN EMPLOYEES AS EMPLOYEE WITH(NOLOCK)
			ON EMPLOYEE.ID = ORDERS.EMPLOYEE_ID
		INNER JOIN CLIENTS AS CLIENT WITH(NOLOCK)
		ON CLIENT.ID = ORDERS.CLIENT_ID
GO
