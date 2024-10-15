# E-commerce
Online shopping

The company's e-commerce database has the PRICES table, which reflects the final price (RRP) and the rate applied to a product in a chain between certain dates.

The Company needs to know the price of one product depending on time, brand, and date, to achieve this goal it needs to create an Endpoint that Returns as output data: product identifier, chain identifier, rate to apply, application dates, and final price to apply.

To validate the integrity of the information, the following tests must be carried out:

- Test 1: request at 10:00 on the 14th for product 35455 for brand 1.
- Test 2: request at 16:00 on the 14th for product 35455 for brand 1.
- Test 3: request at 21:00 on the 14th for product 35455 for brand 1.
- Test 4: request at 10:00 on the 15th for product 35455 for brand 1.
- Test 5: request at 21:00 on the 16th for product 35455 for brand 1.

It was developed as a Microservice with the following structure:

NOTE: Once Backend starts accessing this URL, http://localhost:8080/swagger-ui/index.html, you will better understand endpoint/s. For example: with parameters "applicationdate" = "2020-06-14 16:00:00", "productid" = 35455, and "brandid" = 1.

Structure of Application in next image:

![image](https://github.com/user-attachments/assets/3624b56c-29ab-49d4-a462-1f5575947d87)

Short Description About different classes

- EcommerceApplication => Class for starting the Application.
  
- PriceController => Controller created with the objective of handling all requests made to the "getFee()" EndPoint.

- PriceI => Interface declaring the contract that will be implemented by the service/s.

- PriceImpl => Service implementing the contract defined by the interface "PriceI", and the logic
    needed to know the right price for a product at a specific time of day and brand.

- PriceEntity => A POJOs class representing data that can be persisted in the database. In this case information about 
    PRICE depends on the fee when it is requested or needed to know the right price. 

- PriceDto => A POJO class that will contain the structure and date that will be returned like respond by the "getFee()" Endpoint.

- PriceRepository => Its objective is the interaction with the DatabaseIts objective is the interaction with the
    Database.

- BadRequestException, ControllerExceptionHandler, ErrorMessage, and ResourceNotFoundException => Classes related with
    Exception Handling.    
   
- Common => Utility class for common use where the treatment of dates is centralized, 
    such as the format and the conversion of string to date and date to String.
    Access to the resource file "application.properties" is implemented as well as date validation.
  
- LoadData => A component was created to load the test data into the H2 database at the start of the application.

# Below are some tests performed as well as the results of the tests in Postman:

- Test 1: Request at 10:00 on the 14th for product 35455 for brand 1:

http://localhost:8080/ecom/2020-06-14 10:00:00/35455/1
![image](https://github.com/user-attachments/assets/efdfcf8d-8d40-478f-b62f-e1eb11956189)


- Test 2: Request at 16:00 on day 14 for product 35455 for brand 1:

http://localhost:8080/ecom/2020-06-14 16:00:00/35455/1 
![image](https://github.com/user-attachments/assets/1f51d9bf-0be5-4d71-b7e0-da119658dc9b)

- Test 3: Request at 21:00 on day 14 for product 35455 for brand 1

http://localhost:8080/ecom/2020-06-14 21:00:00/35455/1
![image](https://github.com/user-attachments/assets/16cfe0f3-200d-4eae-a2e6-1f05253990c9)


- Test 4: Request at 10:00 on the 15th for product 35455 for brand 1

http://localhost:8080/ecom/2020-06-15 10:00:00/35455/1 
![image](https://github.com/user-attachments/assets/d7fde655-846e-4b61-b999-e3289c5a6ed8)


- Test 5: Request at 21:00 on day 16 for product 35455 for brand 1

http://localhost:8080/ecom/2020-06-16 21:00:00/35455/1 
![image](https://github.com/user-attachments/assets/af716ab3-2455-4e60-94ac-f8669a66f003)

- Bad date format

http://localhost:8080/ecom/2020-06-50 21:00:00/35455/1 
![image](https://github.com/user-attachments/assets/4bca3ef6-0a37-4910-9c70-9bd7df80c587)

- Brand Id = 0

http://localhost:8080/ecom/2020-06-14 21:00:00/35455/0 
![image](https://github.com/user-attachments/assets/bc0d0408-159f-4964-9522-0e7ad601e9e1)


