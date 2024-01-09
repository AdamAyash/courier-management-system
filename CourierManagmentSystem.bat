if not exist "C:\CourierManagementSystem" mkdir C:\CourierManagementSystem 

robocopy Data C:\CourierManagementSystem /E

cd C:\Users\mobir\Documents\Projects\courier-management-system\CourierManagementSystem\target
java -jar CourierManagementSystem-1.0-SNAPSHOT-jar-with-dependencies.jar