# Favourite Products

This application is the LuizaLabs challenge for a back-end dev position.

Summarizing, this is a Java Spring Boot REST API for client and its favourites products management. It provides information about the said entities in JSON format as long as the entity is authenticated.

## Design choices
* MySQL and Java Spring boot were choosed as the main stacks of this project
* The client doesn't have a password. He/she requests a password that is send by e-mail so the login can happen.
* Although this means a greater amount of time spent writing queries and more coupling with MySQL specifically, raw SQL still runs faster;
* Data was normalized across both tables avoiding duplication and inconsistency;
* There are no JOINs in any query whatsoever, which makes them run faster, and makes it easier to implement vertical sharding (separating users and transactions in different servers), in case it is needed for scalability's sake;
* JSON Web Tokens (JWT) were used to authenticate/authorize users;
* JWT are passed as Bearer's tokens in the header with a key of "Authorization" (`{ "Authorization": "Bearer aAsds12878hjJUK..."}`);
* Each token contains data about the user (email, name, expiration time, when it was issued, and the issuer)
* Those claims are used to give the user access to the resources under his/her control, and time claims are checked;
* The token expires in 1 hour
* A [MVC architecture](#project-structure) was followed, in which the model defines the entities in the given [model](./src/main/java/br/com/luizalabs/favourite/favouriteproducts/model); the [controller](.//src/main/java/br/com/luizalabs/favourite/favouriteproducts/controller) exposese the routes, and the services [services](.//src/main/java/br/com/luizalabs/favourite/favouriteproducts/service) provide the business rules for the manipulation and presentation/visualization of the entities.
* This approach keeps the code more modular (with separation of concerns), extensible and decoupled, improving developers' experience.


## Building

First of all, clone this repository:

`git clone https://github.com/IanPedroV/favourite-products`

The docker compose will handle all the building and stuff for you. If you don't have docker, just run the project
inside your IDE.

Having docker, just run:

`docker-compose up`

You might need to grab a cup of coffee while your application is coming up. This application runs on port 8080, keep that in mind.


## Testing

This application contains unit tests covering it's controllers. Be my guess to run those guys and see that everything is NOT on fire.

## Running

As explained before, this is a REST API, as there is no visual interface you need to make HTTP requests in order to see the API functionality. All the endpoints, needed parameters and related stuff are described in the Swagger documentation: [HERE](http://localhost:8080/swagger-ui.html#). The app needs to be up in order to see swagger docs. In seeing the swagger information perform the following actions:

Request a password, that will be send to your registered e-mail so you can login:

`POST to (/login/requestpassword)`

Get the JWT Token (users/login) and copy it:

`Login with the registered user`

Now you can access the routes putting the key in the Authorization header.

The token expires after 1 hour, then you have to login again.

Example of a request to a authenticated route:

`curl -X GET \ http://localhost:8080/client/ \ -H 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpYW4wMTk4QGdtYWlsLmNvbSIsImV4cCI6MTU5NTEzNjE0MCwiaWF0IjoxNTk1MTAwMTQwfQ.blT6DDx_W8GxykzoURed--nZFCZH1VqOkdPMnibHIgM' \ -H 'cache-control: no-cache'`
