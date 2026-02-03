📌 AndesFin Microservicio de Simulación de Inversiones

Autor: Andres Acurio

🧠 Descripción del Proyecto

Este proyecto consiste en el desarrollo de un microservicio backend para la fintech AndesFin, cuyo objetivo es permitir a los usuarios simular combinaciones de productos financieros y obtener una propuesta de inversión óptima en función de su capital disponible.

El sistema calcula automáticamente la mejor selección de productos considerando:

Capital disponible del usuario

Costo de cada producto

Riesgo de cada producto

Porcentaje de retorno esperado

Además, guarda cada simulación realizada para mantener trazabilidad, historial y evidencia de cálculo.

⚙️ Tecnologías Utilizadas
Tecnología	Uso
Java 17 / 21	Lenguaje principal
Spring Boot	Framework backend
Spring Web	API REST
Spring Data JPA	ORM obligatorio
Hibernate	Implementación JPA
PostgreSQL	Base de datos relacional
Docker Compose	Levantar la base de datos
Maven Wrapper	Gestión de dependencias
🗄 Base de Datos

La base de datos se crea automáticamente al levantar Docker.

Incluye:

Usuarios precargados (mínimo 5)

Productos financieros precargados (mínimo 8)

Las tablas se crean mediante scripts SQL para que el entorno esté listo sin pasos manuales.

🚀 Cómo Ejecutar el Proyecto
1️⃣ Levantar la base de datos

Desde la carpeta del proyecto:

docker compose up -d


Esto crea:

Base de datos PostgreSQL

Tablas

Datos iniciales

2️⃣ Ejecutar la aplicación Spring Boot
.\mvnw spring-boot:run


El backend correrá en:

http://localhost:3000

🔌 Endpoints Disponibles
📍 Obtener usuarios
GET /usuarios

📍 Obtener productos activos
GET /productos

📍 Crear simulación
POST /simulaciones

📍 Ver simulaciones de un usuario
GET /simulaciones/{usuarioId}

🧮 Lógica de Simulación

El sistema evalúa todas las combinaciones posibles de productos (algoritmo de optimización tipo fuerza bruta) y selecciona la que:

Maximiza la ganancia total

Minimiza el riesgo en caso de empate

Utiliza mayor parte del capital

Se almacenan:

Productos seleccionados

Ganancia total

Retorno total (%)

Capital restante

🏗 Arquitectura Aplicada

DTO Pattern → Transferencia de datos entre capas

Repository Pattern → Abstracción de acceso a datos

Service Pattern → Lógica de negocio

ORM obligatorio → Spring Data JPA / Hibernate

📂 Estructura del Proyecto
controller → Endpoints REST  
service → Lógica de negocio  
repository → Acceso a datos  
entity → Modelos de base de datos  
dto → Objetos de transferencia  

✅ Estado del Proyecto

✔ Microservicio funcional
✔ Base de datos automática
✔ Optimización de inversión
✔ Persistencia de simulaciones
✔ API REST completa
