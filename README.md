E-commerce

Online shopping

The company's e-commerce database has the PRICES table, which reflects the final price (RRP) and the rate applied to a product in a chain between certain dates.

The Company needs to know the price of one product depending on time, brand, and date, to achieve this goal it needs to create an Endpoint that Returns as output data: product identifier, chain identifier, rate to apply, application dates, and final price to apply.

It was developed a Microservice with the following structure:



Short Description About different classes

    EcommerceApplication => Class for starting the Application.

    PriceController => Controller created with the objective of handling all requests made to the "getFee()" EndPoint.

    PriceI => Interface declaring the contract that will be implemented by the service/s.

    PriceImpl => Service implementing the contract defined by the interface "PriceI", and the logic needed to know the right price for a product in specific time of day and Brand.

    PriceEntity => A POJOs class representing data that can be persisted in the database. In this case information about PRICE depending of fee in the moment the it is requested or needed to known the right price.

    PriceDto => A POJO class that will contain the structure and date that will be returned like respond by the "getFee()" Endpoint.

    PriceRepository => Its objective is the interaction with the DatabaseIts objective is the interaction with the Database.

    BadRequestException, ControllerExceptionHandler, ErrorMessage, and ResourceNotFoundException => Classes related with Exception Handling.

    Common => Utility class for common use where the treatment of dates is centralized, such as the format and the conversion of string to date and date to String. Access to the resource file "application.properties" is implemented as well as date validation.

    LoadData => Component created to load the Test data into the H2 database at starting the Application start.

Below are some tests performed as well as the results of the tests in Postman:

    Test 1: Request at 10:00 on the 14th for product 35455 for brand 1:

http://localhost:8080/ecom/2020-06-14 10:00:00/35455/1 image

    Test 2: Request at 16:00 on day 14 for product 35455 for brand 1:

http://localhost:8080/ecom/2020-06-14 16:00:00/35455/1 image

    Test 3: Request at 21:00 on day 14 for product 35455 for brand 1

http://localhost:8080/ecom/2020-06-14 21:00:00/35455/1 image

    Test 4: Request at 10:00 on the 15th for product 35455 for brand 1

http://localhost:8080/ecom/2020-06-15 10:00:00/35455/1 image

    Test 5: Request at 21:00 on day 16 for product 35455 for brand 1

http://localhost:8080/ecom/2020-06-16 21:00:00/35455/1 image

    Bad date format

http://localhost:8080/ecom/2020-06-50 21:00:00/35455/1 image

    Brand Id = 0

http://localhost:8080/ecom/2020-06-14 21:00:00/35455/0 image
