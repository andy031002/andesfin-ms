AndesFin – Microservicio de Simulación de Inversiones

Autor: Andres Acurio

Descripción

Este proyecto corresponde al desarrollo de un microservicio backend para la fintech AndesFin. La aplicación permite simular combinaciones de productos financieros con el fin de obtener una propuesta de inversión que aproveche de mejor manera el capital disponible de un usuario.

El sistema recibe una lista de productos con su costo, riesgo y porcentaje de retorno, evalúa diferentes combinaciones posibles y selecciona la que ofrece mejores resultados sin sobrepasar el capital indicado.
Cada simulación se guarda para poder consultarla después y mantener un historial.

Tecnologías utilizadas

Java

Spring Boot

Spring Web

Spring Data JPA (Hibernate) como ORM

PostgreSQL como base de datos relacional

Docker Compose para levantar la base de datos

Maven Wrapper para la gestión del proyecto

Base de datos

La base de datos se crea automáticamente al iniciar el contenedor de Docker.
Se incluyen datos iniciales para pruebas:

Usuarios precargados

Productos financieros precargados

Esto permite ejecutar el sistema desde cero sin configuraciones manuales.

Cómo ejecutar el proyecto
1. Levantar la base de datos

Desde la carpeta del proyecto:

docker compose up -d

2. Ejecutar la aplicación

En la misma carpeta del proyecto:

.\mvnw spring-boot:run


La aplicación se ejecutará en:

http://localhost:3000

Endpoints principales
Método	Endpoint	Descripción
GET	/usuarios	Lista todos los usuarios
GET	/productos	Lista productos activos
POST	/simulaciones	Realiza una simulación de inversión
GET	/simulaciones/{usuarioId}	Consulta simulaciones de un usuario
Lógica de simulación

La aplicación evalúa distintas combinaciones de productos considerando:

No superar el capital disponible

Maximizar la ganancia total

Considerar el riesgo

Calcular retorno estimado

Se guardan los productos seleccionados, la ganancia total, el retorno porcentual y el capital restante.

Organización del proyecto

El proyecto está dividido en capas:

Controller: manejo de endpoints REST

Service: lógica de negocio

Repository: acceso a base de datos

Entity: modelos de las tablas

DTO: objetos para enviar y recibir datos

Estado actual

El microservicio permite:

Consultar usuarios

Consultar productos

Ejecutar simulaciones

Guardar resultados

Consultar historial de simulaciones
