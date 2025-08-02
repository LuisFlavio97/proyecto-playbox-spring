# 📦 Sistema de Gestión de Inventario

Proyecto desarrollado como parte de mi formación profesional. Este sistema permite gestionar videojuegos como productos, registrar usuarios, controlar transacciones y generar reportes en PDF.

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- JasperReports

## ✨ Funcionalidades

- ✅ CRUD de videojuegos
- ✅ CRUD de usuarios
- ✅ Registro de transacciones (entradas y salidas)
- ✅ Generación de reportes en PDF con JasperReports
- ✅ Validaciones básicas en formularios
- ✅ Roles de usuario y seguridad
- ✅ Pruebas unitarias con JUnit para endpoints del API

## 📁 Estructura del proyecto

Proyecto_Playbox/
├── src/
│   ├── main/
│   │   ├── java/com/cibertec/
│   │   │   ├── config/           # Configuración general de Spring
│   │   │   ├── controllers/      # Controladores MVC
│   │   │   ├── dto/              # Clases DTO (Data Transfer Objects)
│   │   │   ├── models/           # Entidades JPA
│   │   │   └── repositories/     # Repositorios JPA
│   │   └── resources/
│   │       ├── templates/        # Vistas Thymeleaf
│   │       ├── static/           # Archivos estáticos (CSS, JS)
│   │       ├── images/           # Imágenes usadas en vistas
│   │       ├── reportes/         # Archivos .jasper o .jrxml (JasperReports)
│   │       └── application.properties
│   │
│   └── test/java/com/cibertec/   # Pruebas unitarias con JUnit + Mockito
├── pom.xml                       # Configuración de Maven
└── docs/
└── db-schema.sql            # Esquema de base de datos

## ⚙️ Cómo ejecutar el proyecto localmente

1. Clona el repositorio

```bash
git clone https://github.com/LuisFlavio97/proyecto-playbox-spring.git
cd inventario
```

2. Crea una base de datos en MySQL llamada bd_playbox
   
3. Ejecuta el script docs/db-schema.sql para generar las tablas

4. Configura el archivo application.properties con tus credenciales:
```properties
#mysql
spring.datasource.url=jdbc:mysql://localhost:3306/bd_playbox
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

#UTF-8
spring.web.encoding.charset=UTF-8
spring.web.encoding.enabled=true
spring.web.encoding.force=true
```

5. Ejecuta la aplicación
```bash
./mvnw spring-boot:run
```
6. Accede desde el navegador a:
http://localhost:8080

## 🧪 Pruebas unitarias

El proyecto incluye pruebas unitarias con JUnit 5 y Mockito para controladores y servicios.
```bash
./mvnw test
```
## 🚀 Mejoras futuras
1. Separar backend como API REST (Spring Web)
2. Front-end moderno en Angular
3. Pruebas de integración
4. Dockerización del proyecto

## 📄 Licencia
Este proyecto es de uso personal con fines educativos.
No se autoriza su uso comercial sin permiso del autor.
