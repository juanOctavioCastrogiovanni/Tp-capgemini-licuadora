# Overview
This project is a final practical work developed by Juan Octavio Castrogiovanni for the JAVA developer training program at the National Technological University (UTN) financed by Capgemini. You can check the author's portfolio at juanoctaviocastrogiovanni.com.ar

## Backend
The backend of the application was developed using Spring Boot and the ORM (Object-Relational Mapping) tool used was Hibernate. The database used in this project is MySQL. The development of this project took 2 months, following best practices and using the MVC (Model-View-Controller) design pattern. The Swagger dependency was added for API documentation. This project was deployed on the railway.app server, and the database was deployed on the Claver Cloud platform.

## Frontend
The frontend of the application was developed using Angular.js and it was deployed on the Firebase platform provided by Google. To access the frontend code, please visit the following GitHub repository: [Angular repository](https://github.com/juanOctavioCastrogiovanni/ecommerce-angular-capgemini/tree/main)

## Access
To access the web page, please use the following link: [Web](https://capgemini-tp-licuadora.web.app/publicaciones)
To access the API documentation, please use the following link: [API documentation](https://tp-capgemini-licuadora-production.up.railway.app/swagger-ui/index.html#/)

## UML Database Diagram
A UML diagram of the database design can be found on the following link: ![UML](https://github.com/juanOctavioCastrogiovanni/Tp-capgemini-licuadora/blob/main/diagrama-uml%20.png)

This diagram illustrates the relationship between the different entities and tables in the database, and can be used as a reference for understanding the structure of the data stored in the application.

## Practical work 
This is a practical work for the Systems Design course at UTN.

We have been asked to design and develop an E-Commerce platform for the sale of semi-customized clothing products.

Survey:
The platform managers load the base products that can be customized and sold, such as t-shirts, hats, smartphone cases, shoes, among others. For each product, they define a name, a base price, a description, and an estimated manufacturing time (they take care of manufacturing them). For each base product, the customization area must be defined, and for each of these areas, the types of customizations that can be made must be defined. For example: text, color image, black and white image, emoji, among others.

Sellers, who are customers of the managers (pay for the use of the platform), can customize each of the products that they have available, adding images or phrases to be printed on the predefined areas.

For each customization, they define a name and a price, which is added to the base price of the product to form the final selling price. Then, they can publish these customized products on their store (each seller has their own store within the platform). Sellers must be able to pause and cancel their publications at any time.

Buyers are considered "sporadic", that is, they are not frequent and do not need to generate an account in the system to be able to make a purchase.

It should be noted that the platform must include the concept of "shopping cart", that is, the buyer can select all the products they want and then purchase them all together.

Once the customer selects the customized product, the system must calculate the total of the purchase, so that, subsequently, the buyer pays for it by any of the accepted payment methods. Each seller defines the Payment Methods they accept. Once the payment is accepted, an Electronic Invoice must be generated from an External Service.

It is requested to implement it in a Web architecture, with 2 components:

FrontEnd: Front project using the Angular2 FW
BackEnd: REST API project with persistence - using Java-Spring

The buyer's screens must be implemented:
Login
Purchase process
Product listing - Search
Add to cart
Confirm Purchase
Show Purchase Result
View the list of purchases made
All methods must be developed in the REST API to support this

ONLY BY API
CRUD of Base Products
CRUD of Customized Products

NO NEED
Make user registration screen
Make the screens of what is requested in the API

Delivery considerations
The work done must be exposed in 5 minutes!
So the presentation must be scripted and practiced

Therefore, you must have preloaded data, such as base products, customized products, features, users, and everything you consider necessary
To test the APIs you have the requests/JSON already formed in Postman
For the screens the same, they have to have planned what data they need.
