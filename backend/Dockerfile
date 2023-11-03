FROM maven:3.8-openjdk-11

ADD . /src

WORKDIR /src

RUN mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/backend.jar"]