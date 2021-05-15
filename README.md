**What is this Microservice**

    This is REST service that will manage details of drivers. 
    
**REST endpoints**


**POST URL** : http://localhost:8080/driver/create

Request

{
    "firstName": "John",
    "lastName": "Smith",
    "birthDate": "1980-05-01"
}

Response

{
    "driver_id": 1,
    "firstName": "John",
    "lastName": "Smith",
    "birthDate": "1980-05-01",
    "creationDate": "2021-05-14 23:00:00"
}


**GET URL** : http://localhost:8080/drivers

Response

[
    {
        "driver_id": 1,
        "firstName": "John",
        "lastName": "Smith",
        "birthDate": "1980-05-01",
        "creationDate": "2021-05-14 23:00:00"
    },
    {
        "driver_id": 2,
        "firstName": "steve",
        "lastName": "Smith",
        "birthDate": "1990-05-01",
        "creationDate": "2021-05-14 23:00:00"
    }
]


**GET URL** : http://localhost:8080/drivers/byDate?date=1985-05-01


Response

[
    {
        "driver_id": 1,
        "firstName": "John",
        "lastName": "Smith",
        "birthDate": "1980-05-01",
        "creationDate": "2021-05-14 23:00:00"
    },
    {
        "driver_id": 2,
        "firstName": "steve",
        "lastName": "Smith",
        "birthDate": "1990-05-01",
        "creationDate": "2021-05-14 23:00:00"
    }
]


**Run this spring boot Application**


For running the Spring Boot application, open DriverManagementApplication, and run it as Java Application.

When the application runs successfully, it shows the message in the console, as shown below.

Tomcat server is the default server used. 
H2 is the in memory database used for storing the data.
