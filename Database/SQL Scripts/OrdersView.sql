CREATE OR ALTER VIEW VI_ORDERS
AS 
	SELECT												 
		ORDERS.ID											 AS ORDER_ID,
		(CASE
			WHEN ORDERS.[STATUS] = 0 
				THEN 'Delivered'
			WHEN ORDERS.[STATUS] = 1
				THEN 'Not Delivered'
			WHEN ORDERS.[STATUS] = 2
				THEN 'Rejected'
		END)												 AS ORDER_STATUS,
		ORDERS.[STATUS]										 AS ORDER_STATUS_ID,
		ORDER_TYPE.NAME										 AS ORDER_TYPE,
		ORDER_TYPE.PRICE									 AS ORDER_PRICE,
		OFFICE.NAME											 AS OFFICE_NAME,
		COMPANY.ID											 AS COMPANY_ID,
		COMPANY.NAME										 AS COMPANY_NAME,
		COMPANY.EGFN										 AS COMPANY_BULSTAT,
		EMPLOYEE.ID											 AS EMPLOYEE_ID,
		CONCAT(EMPLOYEE.FIRST_NAME, ' ', EMPLOYEE.LAST_NAME) AS EMPLOYEE_FULL_NAME,
		CLIENT.ID											 AS CLIENT_ID,
		CONCAT(CLIENT.FIRST_NAME,  ' ', CLIENT.LAST_NAME)    AS CLIENT_FULL_NAME,
		CLIENT.UCN											 AS CLIENT_UCN,
		ORDERS.DATE_REGISTERED								 AS DATE_REGISTERED,
		ORDERS.DELIVERY_DATE								 AS DELIVERY_DATE
		FROM ORDERS WITH(NOLOCK)
		INNER JOIN OFFICES AS OFFICE WITH(NOLOCK)
			ON ORDERS.OFFICE_ID = OFFICE.ID
		INNER JOIN EMPLOYEES AS EMPLOYEE WITH(NOLOCK)
			ON EMPLOYEE.ID = ORDERS.EMPLOYEE_ID
		INNER JOIN COMPANIES AS COMPANY WITH(NOLOCK)
			ON EMPLOYEE.COMPANY_ID = ORDERS.COMPANY_ID
		INNER JOIN CLIENTS AS CLIENT WITH(NOLOCK)
			ON CLIENT.ID = ORDERS.CLIENT_ID
		INNER JOIN ORDER_TYPES AS ORDER_TYPE
			ON ORDER_TYPE.ID = ORDERS.ORDER_TYPE_ID
		WHERE COMPANY.ID = EMPLOYEE.COMPANY_ID
GO

