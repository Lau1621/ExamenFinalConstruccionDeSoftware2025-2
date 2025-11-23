# 🏯 Sistema de Comunicación de Pilares - Castillo Infinito

Sistema de gestión y comunicación para los Pilares Hashira en el Castillo Infinito de Muzan, implementado con **Arquitectura Hexagonal** (Puertos y Adaptadores).

## 📋 Descripción

API REST que permite:

- Consultar información y ubicación de los Pilares
- Actualizar posiciones en tiempo real
- Registrar mensajes tácticos fragmentados
- Reconstruir comunicaciones distorsionadas
- Calcular triangulación estimada del enemigo

---

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 4.0.0**
- **Spring Data JPA**
- **MySQL 8.0+**
- **Maven**
- **Lombok**

---

## ⚙️ Requisitos Previos

1. **Java JDK 21** o superior instalado
2. **MySQL 8.0+** en ejecución
3. **Maven** (incluido Maven Wrapper en el proyecto)

---

## 🚀 Cómo Ejecutar el Proyecto

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd final
```

### 2. Crear la base de datos MySQL

```sql
CREATE DATABASE IF NOT EXISTS examen_final;
```

### 3. Configurar credenciales de base de datos

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/examen_final
spring.datasource.username=root
spring.datasource.password=root
```

### 4. Ejecutar la aplicación

**Opción 1: Maven Wrapper (Recomendado)**

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

**Opción 2: Maven instalado**

```bash
mvn spring-boot:run
```

### 5. Verificar que la aplicación está corriendo

Abrir en el navegador: http://localhost:8080/api/pilares/1

---

## 🗄️ Scripts SQL

### Script de Creación de Base de Datos

```sql
CREATE DATABASE IF NOT EXISTS examen_final
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE examen_final;
```

### Datos Iniciales (Automático)

El sistema incluye 9 Pilares precargados automáticamente en `src/main/resources/data.sql`:

```sql
INSERT INTO pillars (id, name, pos_x, pos_y, state) VALUES
(1, 'Giyu Tomioka', -500, -200, 'Combatiendo'),
(2, 'Shinobu Kocho', 300, 150, 'Explorando'),
(3, 'Kyojuro Rengoku', -100, 400, 'Herido'),
(4, 'Tengen Uzui', 200, -300, 'Combatiendo'),
(5, 'Muichiro Tokito', 0, 0, 'Explorando'),
(6, 'Mitsuri Kanroji', -200, 250, 'Combatiendo'),
(7, 'Obanai Iguro', 400, -100, 'Explorando'),
(8, 'Sanemi Shinazugawa', -350, -50, 'Combatiendo'),
(9, 'Gyomei Himejima', 100, 300, 'Combatiendo');
```

### Tablas Generadas Automáticamente

Spring Boot crea las tablas automáticamente con `spring.jpa.hibernate.ddl-auto=update`:

- **pillars**: Almacena información de los Pilares
- **messages**: Almacena mensajes fragmentados y reconstruidos

---

## 📡 API Endpoints

### 1. 🔍 Consultar Información de un Pilar

**Endpoint:** `GET /api/pilares/{id}`

**Descripción:** Obtiene la información completa de un Pilar específico.

**Ejemplo de Request:**

```bash
curl http://localhost:8080/api/pilares/1
```

**Response (200 OK):**

```json
{
  "id": 1,
  "nombre": "Giyu Tomioka",
  "posX": -500,
  "posY": -200,
  "estado": "Combatiendo"
}
```

**Códigos de Respuesta:**

- `200 OK`: Pilar encontrado
- `404 NOT FOUND`: No existe un Pilar con ese ID

---

### 2. 📍 Obtener Triangulación del Enemigo

**Endpoint:** `GET /api/inteligencia/triangulacion`

**Descripción:** Calcula la posición estimada de Muzan basándose en las coordenadas de todos los Pilares.

**Ejemplo de Request:**

```bash
curl http://localhost:8080/api/inteligencia/triangulacion
```

**Response (200 OK):**

```json
{
  "posiblePosicionMuzan": {
    "x": -16,
    "y": 50
  },
  "nivelConfianza": 0.91,
  "descripcion": "Probabilidad alta de presencia demoníaca en las coordenadas dadas."
}
```

**Códigos de Respuesta:**

- `200 OK`: Triangulación calculada exitosamente

---

### 3. 🔄 Actualizar Posición de un Pilar

**Endpoint:** `POST /api/pilares/actualizar-posicion`

**Descripción:** Actualiza la posición y estado de un Pilar.

**Ejemplo de Request:**

```bash
curl -X POST http://localhost:8080/api/pilares/actualizar-posicion \
  -H "Content-Type: application/json" \
  -d '{
    "pilarId": 1,
    "posX": -480,
    "posY": -210,
    "estado": "Herido"
  }'
```

**Request Body:**

```json
{
  "pilarId": 1,
  "posX": -480,
  "posY": -210,
  "estado": "Herido"
}
```

**Estados válidos:** `Combatiendo`, `Explorando`, `Herido`, `Descansando`

**Response (201 CREATED):**

```json
{
  "mensaje": "Posición actualizada exitosamente.",
  "pilar": {
    "id": 1,
    "nombre": "Giyu Tomioka",
    "posX": -480,
    "posY": -210,
    "estado": "Herido"
  }
}
```

**Códigos de Respuesta:**

- `201 CREATED`: Posición actualizada exitosamente
- `400 BAD REQUEST`: Datos inválidos o estado no permitido
- `404 NOT FOUND`: El Pilar no existe

---

### 4. 📨 Registrar Mensaje Táctico Fragmentado

**Endpoint:** `POST /api/mensajes`

**Descripción:** Los Pilares envían mensajes con interferencia que deben ser almacenados.

**Ejemplo de Request:**

```bash
curl -X POST http://localhost:8080/api/mensajes \
  -H "Content-Type: application/json" \
  -d '{
    "pilarId": 3,
    "contenidoFragmentado": "Muz... mov... norte... ata..."
  }'
```

**Request Body:**

```json
{
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... norte... ata..."
}
```

**Response (201 CREATED):**

```json
{
  "id": 1,
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... norte... ata...",
  "contenidoReconstruido": null,
  "timestamp": "2025-11-22T13:16:38"
}
```

**Códigos de Respuesta:**

- `201 CREATED`: Mensaje registrado exitosamente
- `400 BAD REQUEST`: Datos inválidos o campos vacíos
- `404 NOT FOUND`: El Pilar no existe

---

### 5. 🔧 Reconstruir Mensaje Táctico

**Endpoint:** `PUT /api/mensajes/{id}/reconstruir`

**Descripción:** Procesa y reconstruye un mensaje distorsionado.

**Ejemplo de Request:**

```bash
curl -X PUT http://localhost:8080/api/mensajes/1/reconstruir \
  -H "Content-Type: application/json" \
  -d '{
    "contenidoReconstruido": "Muzan se mueve hacia el norte. Preparar ataque."
  }'
```

**Request Body:**

```json
{
  "contenidoReconstruido": "Muzan se mueve hacia el norte. Preparar ataque."
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... norte... ata...",
  "contenidoReconstruido": "Muzan se mueve hacia el norte. Preparar ataque.",
  "timestamp": "2025-11-22T13:16:38"
}
```

**Códigos de Respuesta:**

- `200 OK`: Mensaje reconstruido exitosamente
- `400 BAD REQUEST`: Datos inválidos o contenido vacío
- `404 NOT FOUND`: No existe mensaje con ese ID

---

## 🏗️ Arquitectura

### Arquitectura Hexagonal (Puertos y Adaptadores)

```
┌─────────────────────────────────────────────────────────┐
│                    REST Controllers                     │
│           (PillarController, MessageController)         │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                      Validators                         │
│        (PillarValidator, MessageValidator)              │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                   Ports (Interfaces)                    │
│         (PillarPort, MessagePort, IntelligencePort)     │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                  Domain Services                        │
│     (PillarService, MessageService, IntelligenceService)│
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                     Adapters                            │
│           (PillarAdapter, MessageAdapter)               │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                 JPA Repositories                        │
│      (PillarJpaRepository, MessageJpaRepository)        │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
                  Database (MySQL)
```

**Ventajas:**

- ✅ Lógica de negocio independiente de frameworks
- ✅ Fácil de probar y mantener
- ✅ Cambios en infraestructura no afectan el dominio
- ✅ Código limpio y desacoplado

---

## 🛡️ Manejo de Excepciones

El sistema implementa un **GlobalExceptionHandler** que captura y formatea todos los errores:

### Tipos de Excepciones

| Excepción             | Código HTTP               | Descripción                     |
| --------------------- | ------------------------- | ------------------------------- |
| `ValidationException` | 400 BAD REQUEST           | Datos inválidos o campos vacíos |
| `NotFoundException`   | 404 NOT FOUND             | Recurso no encontrado           |
| `BusinessException`   | 400 BAD REQUEST           | Errores de lógica de negocio    |
| `Exception`           | 500 INTERNAL SERVER ERROR | Errores inesperados             |

### Formato de Error

```json
{
  "status": 400,
  "message": "ID del pilar no tiene un valor válido",
  "timestamp": "2025-11-22T13:15:00",
  "path": "/api/pilares/actualizar-posicion"
}
```

---

## ✅ Validaciones Implementadas

### PillarValidator

- ✅ ID del pilar no nulo y positivo
- ✅ Posiciones X e Y no nulas
- ✅ Estado debe ser: `Combatiendo`, `Explorando`, `Herido` o `Descansando`

### MessageValidator

- ✅ ID del mensaje no nulo y positivo
- ✅ ID del pilar no nulo y positivo
- ✅ Contenido fragmentado no vacío
- ✅ Contenido reconstruido no vacío

---

## 🧪 Pruebas Rápidas

### PowerShell

```powershell
# Consultar pilar
curl http://localhost:8080/api/pilares/1

# Triangulación
curl http://localhost:8080/api/inteligencia/triangulacion

# Actualizar posición
curl -Method POST -Uri "http://localhost:8080/api/pilares/actualizar-posicion" -ContentType "application/json" -Body '{"pilarId": 1, "posX": -400, "posY": -150, "estado": "Combatiendo"}'

# Crear mensaje
curl -Method POST -Uri "http://localhost:8080/api/mensajes" -ContentType "application/json" -Body '{"pilarId": 3, "contenidoFragmentado": "Muz... mov... norte..."}'

# Reconstruir mensaje
curl -Method PUT -Uri "http://localhost:8080/api/mensajes/1/reconstruir" -ContentType "application/json" -Body '{"contenidoReconstruido": "Muzan se mueve hacia el norte"}'
```

---

## 📁 Estructura del Proyecto

```
src/main/java/com/tdea/demo/
├── adapters/
│   ├── messages/
│   │   ├── entity/          # Entidad JPA de Message
│   │   ├── repository/      # Repository JPA de Message
│   │   └── MessageAdapter.java
│   ├── pillars/
│   │   ├── entity/          # Entidad JPA de Pillar
│   │   ├── repository/      # Repository JPA de Pillar
│   │   └── PillarAdapter.java
│   ├── rest/
│   │   ├── exception/       # Manejo global de excepciones
│   │   ├── request/         # DTOs de entrada
│   │   ├── response/        # DTOs de salida
│   │   ├── PillarController.java
│   │   ├── MessageController.java
│   │   └── IntelligenceController.java
│   └── utils/               # Validadores y excepciones
├── domain/
│   ├── models/              # Modelos de dominio
│   └── services/            # Servicios de negocio
└── ports/                   # Interfaces (Puertos)
```

---

## 🔧 Configuración

### application.properties

```properties
# Nombre de la aplicación
spring.application.name=final

# Configuración de base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/examen_final
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true

# Inicialización de datos
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

---

## 🎯 Pilares Disponibles

| ID  | Nombre             | PosX | PosY | Estado      |
| --- | ------------------ | ---- | ---- | ----------- |
| 1   | Giyu Tomioka       | -500 | -200 | Combatiendo |
| 2   | Shinobu Kocho      | 300  | 150  | Explorando  |
| 3   | Kyojuro Rengoku    | -100 | 400  | Herido      |
| 4   | Tengen Uzui        | 200  | -300 | Combatiendo |
| 5   | Muichiro Tokito    | 0    | 0    | Explorando  |
| 6   | Mitsuri Kanroji    | -200 | 250  | Combatiendo |
| 7   | Obanai Iguro       | 400  | -100 | Explorando  |
| 8   | Sanemi Shinazugawa | -350 | -50  | Combatiendo |
| 9   | Gyomei Himejima    | 100  | 300  | Combatiendo |

---

## 🐛 Solución de Problemas

### La aplicación no inicia

1. Verificar que MySQL esté corriendo
2. Verificar que exista la base de datos `examen_final`
3. Verificar credenciales en `application.properties`
4. Verificar que el puerto 8080 esté disponible

### Error de conexión a base de datos

```bash
# Verificar MySQL
mysql -u root -p
```

```sql
-- Verificar base de datos
SHOW DATABASES;
USE examen_final;
SHOW TABLES;
```

### Compilar el proyecto

```bash
.\mvnw.cmd clean compile
```

---

## 📝 Notas

- El sistema usa **arquitectura hexagonal** para mantener el código limpio y desacoplado
- Las **validaciones** se ejecutan antes de procesar cualquier request
- Las **excepciones** se manejan globalmente con mensajes descriptivos
- Los **datos iniciales** se cargan automáticamente al iniciar

---

## 👥 Autor

Proyecto desarrollado para la asignatura de Ingeniería de Software

---

## 📄 Licencia

Este proyecto es de uso educativo.

---

**✨ Sistema completamente funcional y listo para producción ✨**
