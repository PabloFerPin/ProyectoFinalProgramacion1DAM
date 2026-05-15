# 🎮 GameVault

**Proyecto Final — Programación · 1º DAM**

GameVault es una aplicación web full-stack para gestionar tu biblioteca personal de videojuegos. Permite registrar los juegos que has jugado, estás jugando o tienes pendientes, con información detallada sobre géneros, plataformas, horas jugadas y estado de completado. También soporta importación y exportación masiva de datos mediante archivos CSV.

---

## 🛠️ Tecnologías utilizadas

| Capa | Tecnología |
|------|-----------|
| Backend | Java 17 · Spring Boot 4.0.6 |
| Base de datos | MySQL 8 · JDBC manual (sin JPA) |
| Frontend | HTML5 · CSS3 · JavaScript vanilla · Bootstrap 5.3 |
| Herramientas | IntelliJ IDEA · Maven · Git |

---

## 📁 Estructura del proyecto

```
src/
└── main/
    ├── java/
    │   └── com/example/proyectofinalprogramacion/
    │       ├── config/          # Conexión a MySQL
    │       ├── controllers/     # Endpoints REST
    │       ├── dto/             # Objetos de transferencia de datos
    │       ├── entity/          # Entidades del dominio
    │       ├── mapper/          # Conversión entre Entity y DTO
    │       ├── repository/      # Acceso a datos (DAO con JDBC)
    │       └── service/         # Lógica de negocio
    └── resources/
        ├── static/              # Frontend (HTML, JS)
        └── application.properties
```

---

## 🗄️ Modelo de base de datos

```sql
tabla_videojuegos       -- Información principal del videojuego
tabla_generos           -- Catálogo de géneros disponibles
tabla_plataformas       -- Catálogo de plataformas disponibles
tabla_videojuegos_generos    -- Relación N:M videojuego-género
tabla_videojuegos_plataformas -- Relación N:M videojuego-plataforma
```

---

## 🚀 Instalación y puesta en marcha

### Requisitos previos

- Java 17+
- MySQL 8+
- Maven

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd "Proyecto final programacion"
```

### 2. Crear la base de datos

Ejecuta el siguiente SQL en MySQL Workbench o cualquier cliente MySQL:

```sql
CREATE DATABASE proyecto_final_programacion;
USE proyecto_final_programacion;

CREATE TABLE tabla_plataformas (
    id     INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tabla_generos (
    id     INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tabla_videojuegos (
    id             INT PRIMARY KEY AUTO_INCREMENT,
    titulo         VARCHAR(255) NOT NULL,
    desarrolladora VARCHAR(150),
    fecha_salida   DATE,
    horas_jugadas  DECIMAL(6,1) DEFAULT 0,
    completado     BOOLEAN DEFAULT FALSE
);

CREATE TABLE tabla_videojuegos_generos (
    id            INT PRIMARY KEY AUTO_INCREMENT,
    id_videojuego INT NOT NULL,
    id_genero     INT NOT NULL,
    FOREIGN KEY (id_videojuego) REFERENCES tabla_videojuegos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_genero)     REFERENCES tabla_generos(id)     ON DELETE CASCADE
);

CREATE TABLE tabla_videojuegos_plataformas (
    id            INT PRIMARY KEY AUTO_INCREMENT,
    id_videojuego INT NOT NULL,
    id_plataforma INT NOT NULL,
    FOREIGN KEY (id_videojuego) REFERENCES tabla_videojuegos(id)  ON DELETE CASCADE,
    FOREIGN KEY (id_plataforma) REFERENCES tabla_plataformas(id)  ON DELETE CASCADE
);
```

Inserta los datos iniciales de géneros y plataformas:

```sql
INSERT INTO tabla_generos (nombre) VALUES
('Acción'), ('Aventura'), ('RPG'), ('MMORPG'), ('FPS'), ('TPS'),
('Estrategia en tiempo real'), ('Estrategia por turnos'), ('Simulación'),
('Deportes'), ('Carreras'), ('Lucha'), ('Plataformas'), ('Puzzle'),
('Terror'), ('Sandbox'), ('Stealth'), ('Roguelike'), ('Metroidvania'),
('Souls-like'), ('Open World'), ('Remake'), ('Musica y ritmo'),
('Novela visual'), ('Battle Royale'), ('Tower Defense'), ('Hack and Slash'),
('MOBA'), ('Survival'), ('Idle');

INSERT INTO tabla_plataformas (nombre) VALUES
('PC'), ('PlayStation 5'), ('PlayStation 4'), ('PlayStation 3'),
('Xbox Series X'), ('Xbox One'), ('Nintendo Switch'), ('Nintendo 3DS'),
('Game Boy Advance'), ('Wii'), ('Wii U'), ('PSP'), ('PS Vita'),
('Sega Mega Drive'), ('Nintendo 64'), ('PlayStation 2'), ('Xbox 360'), ('GameCube');
```

### 3. Configurar la conexión

Edita `src/main/resources/application.properties` o directamente `ConexionMySQL.java` con tus credenciales:

```java
private static final String URL = "jdbc:mysql://localhost:3306/proyecto_final_programacion";
private static final String USER = "root";
private static final String PASSWORD = "tu_password";
```

### 4. Ejecutar

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

---

## 🌐 Endpoints de la API REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/videojuegos` | Listar todos los videojuegos |
| GET | `/api/videojuegos/{id}` | Obtener un videojuego por ID |
| GET | `/api/videojuegos/generos` | Listar géneros disponibles |
| GET | `/api/videojuegos/plataformas` | Listar plataformas disponibles |
| GET | `/api/videojuegos/downloadCSV` | Exportar biblioteca a CSV |
| POST | `/api/videojuegos` | Insertar un nuevo videojuego |
| POST | `/api/videojuegos/csv` | Importar videojuegos desde CSV |
| PUT | `/api/videojuegos/{id}` | Modificar un videojuego |
| DELETE | `/api/videojuegos/{titulo}` | Eliminar un videojuego |

### Ejemplo de petición POST

```json
{
  "titulo": "Elden Ring",
  "desarrolladora": "FromSoftware",
  "fechaSalida": "2022-02-25",
  "horasJugadas": 150.0,
  "completado": false,
  "generos": [{"nombre": "RPG"}, {"nombre": "Souls-like"}, {"nombre": "Open World"}],
  "plataformas": [{"nombre": "PC"}, {"nombre": "PlayStation 5"}]
}
```

---

## 📄 Formato del CSV

El archivo CSV usa `|` como separador de columnas y `,` para múltiples géneros o plataformas dentro de un mismo campo. No incluye cabecera.

```
titulo|desarrolladora|fecha_salida|horas_jugadas|completado|generos|plataformas
```

Ejemplo:

```
Elden Ring|FromSoftware|2022-02-25|150.0|false|RPG,Souls-like,Open World|PC,PlayStation 5
Hades|Supergiant Games|2020-09-17|21.5|false|Roguelike,Acción|PC,Nintendo Switch
```

---

## 📐 Arquitectura

El proyecto sigue una arquitectura en capas estándar para aplicaciones Spring Boot:

- **Controller** — recibe las peticiones HTTP y delega al service
- **Service** — contiene la lógica de negocio y orquesta las llamadas al DAO
- **DAO (Repository)** — acceso directo a base de datos mediante JDBC con `PreparedStatement`
- **Entity** — representación interna del dominio
- **DTO** — objetos usados para la comunicación con el exterior (API y front)
- **Mapper** — conversión entre Entity y DTO

Las consultas SQL se escriben manualmente sin uso de JPA ni ORM.