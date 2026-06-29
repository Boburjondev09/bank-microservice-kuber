FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Копируем ТОЛЬКО pom.xml и скачиваем зависимости (этот шаг закэшируется!)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходный код (выполняется быстро при изменениях в Java)
COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 5001
ENTRYPOINT ["java", "-jar", "app.jar"]