FROM maven:3-adoptopenjdk-11
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests=true
ENTRYPOINT ["java", "-jar", "target/moroccanpixels-0.0.1-SNAPSHOT.jar"]
