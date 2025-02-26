# Automatizar processo de build da aplicação e deploy no docker
FROM maven:3-amazoncorretto-21 as build
WORKDIR /app
# Executa a cópia do projeto
COPY . .
# Executa o build
RUN mvn clean package -DskipTests

FROM openjdk:21
WORKDIR /app
# Copia do build o jar
COPY --from=build ./app/target/*.jar ./app.jar
ENTRYPOINT java -jar app.jar