FROM openjdk:17
EXPOSE 8089
ADD target/first.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
