CREATE OR ALTER PROCEDURE SP_GENERATE_NEXT_UNIQUE_ID
	@COUNTER_NAME	NVARCHAR(128),
	 @NEXT_ID		INT OUTPUT
AS
BEGIN
	DECLARE @NEXT_UNIQUE_ID AS INT = 
	(
		SELECT
			CURRENT_INDEX_POSITION 
			FROM COUNTERS WITH(NOLOCK)
		WHERE COUNTER_NAME = @COUNTER_NAME
	)
	 
	IF(@NEXT_UNIQUE_ID) IS NOT NULL
	BEGIN
		SET @NEXT_UNIQUE_ID = @NEXT_UNIQUE_ID + 1
		UPDATE COUNTERS
		SET CURRENT_INDEX_POSITION = @NEXT_UNIQUE_ID
		WHERE COUNTER_NAME = @COUNTER_NAME
	END

	PRINT @NEXT_UNIQUE_ID

	SET @NEXT_ID = ISNULL(@NEXT_UNIQUE_ID, -1) 
END
GO