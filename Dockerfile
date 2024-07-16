FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /home/app
COPY src ./src
COPY pom.xml .
RUN mvn -f ./pom.xml clean package

FROM openjdk:17
COPY --from=build /home/app/target/employee.jar /home/app/employee.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/employee.jar"]
