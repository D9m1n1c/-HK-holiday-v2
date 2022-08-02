# HK holiday retriever with Java

Web app that filters HK public holidays in 2021-2023. (as of 2/8/2022)

## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw spring-boot:run` (Windows), or `./mvnw spring-boot:run` (Mac & Linux), then open
http://localhost:8080 in your browser.


## Project planning (starts 30/8/22)
- Create web with components
- Get API data store in object
- Store Data in DB
- Buttons handle event -> fetch data from DB
- Handle business logic (date filtering)

## Project execution obstacles
1. Unable to fetch data from the given api due to security problem

`solution`: get cert from the api site then insert to the JDK environment with `sudo keytool -importcert -file ~/Desktop/HongkongPostRootCA3.cer -alias HKPostRootCA3 -keystore /Library/Java/JavaVirtualMachines/jdk-18.0.1.1.jdk/Contents/Home/lib/security/cacerts`

2. Difficulties with parsing fetched data due to combination of objects and arrays nested in each other, tried different libraries.
`solution`: Use combinations of types (JsonObjects, JsonArrays,strings)

3. Spent a bit of time on creating my first db and injecting the fetched object into db, with application.properties setup.



## Development timeline
- 30/7 Learn basic Java syntax, Fetch API data from site then parsing to useful types, console log to check.
- 31/7 Storing fetched data to object, creating and connecting mysql db, inject fetched data to db table.
- 1/8 Building frontend components, handling button events to fetch data and filtering logic in repository (learning jpql).
- I might have written some unnecessary classes as I was trying to separate the services.


