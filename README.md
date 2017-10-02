

Broker WebApp
===================
[![Build Status](https://travis-ci.org/msefaertekin/broker-webapp.svg?branch=master)](https://travis-ci.org/msefaertekin/broker-webapp)

Broker WebApp project is a platform for brokers to provide trading services for traders.

### **How to start server?**

#### **Docker Compose**

>Before you start:

> - Install Docker and Docker Compose.

In order to run it first you need the following command

    ./mvnw install dockerfile:build

and then

    docker-compose up -d

#### **JAR File (Production Mode)**

In this mode the application will be run on local Mysql database. 

> Before you start :

> - Mysql datasource configuration in application.yml file must be updated to reflect your local mysql database instance. Database named "broker" must be created beforehand.

In order to run it first you need the following command

    ./mvnw clean package

and then

    java -jar ./target/broker-webapp-0.1.0.jar

#### **JAR File (Development Mode)**

In this mode the application will be run in volatile in-memory H2 database. 

In order to run it first you need the following command

    ./mvnw clean package

and then

	java -jar -Dspring.profiles.active=development ./target/broker-webapp-0.1.0.jar

### **How to start clients?**

Server runs on port 8080 by default in all options. This value can be overridden from docker-compose.yml or application.yml depending on the environment you work. So after you make sure the server is up and running clients can connect to:

    http://localhost:8080

### **Features**

#### **Users**
    
Following users are predefined for testing purposes:

Brokers:

Username     | Password
-------- | ---
broker1 | brokerpass
broker2    | brokerpass

Traders:

Username     | Password
-------- | ---
trader1 | traderpass
trader2    | traderpass

Alternatively, you may add traders to the system from signup section.   

#### **Screens**

> ##### Login Page:
  It allows brokers and traders to login.
    
> ##### Signup Page:
  It is used for registration of traders.
    
> ##### Market Overview for Brokers:
  Brokers can view rates and update spreads from this screen.
    
> ##### All Trades
  Brokers can see all trades occurred in the market.
    
> ##### Market Overview for Traders:
  Traders can see best bid and best offers in market. There is an option to see the market in realtime. They can buy or sell stocks by specifying the quantity they want.
    
> ##### My Trades	  
  Traders can see their trades occurred in the market.

### **Components**

The application is designed to serve resources in **Rest API** architecture. **Java 8** and **Spring Boot** framework are used in backend services. **Mysql** DB is the primary choice in database layer.  In testing and development mode **H2** in-memory database is preferred. **Spring Security** secured application with its diverse configuration options. At front end **html5** is enriched with **Angular JS** dynamism to have a desired **single page application**. In addition, **Docker** is also effectively used to ease the deployment of the application.Package structure is organized in **hexagonal** architecture. Continuous Integration(CI) is achieved by build automation features of **Travis CI**.
