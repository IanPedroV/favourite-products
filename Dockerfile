From openjdk:8
copy ./target/favourite-products.jar favourite-products.jar
CMD ["java","-jar","favourite-products.jar"]