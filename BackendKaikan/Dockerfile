# IMAGEN MODELO
FROM eclipse-temurin:21.0.7_6-jdk AS builder


# DEFINIR DIRECTORIO RAIZ DE NUESTRO CONTENEDOR
WORKDIR /app

# COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY . .

# ✅ Asegurar permisos de ejecución para mvnw
RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre


WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080


# LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java", "-jar", "app.jar"]
