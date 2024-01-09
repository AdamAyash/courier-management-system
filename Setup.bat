if not exist "C:\CourierManagementSystem" mkdir C:\CourierManagementSystem\Data

xcopy %cd%\Data C:\CourierManagementSystem\Data /O /X /E /H /K


