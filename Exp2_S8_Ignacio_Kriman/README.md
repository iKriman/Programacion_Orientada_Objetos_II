# Sistema Cine Magenta - Exp2_S7_Grupo14

## Descripción
Sistema simple para gestionar películas del cine Magenta usando Java Swing y MySQL.

## Estructura del Proyecto
```
Exp2_S5_Grupo14/
├── src/com/cine/magenta/
│   ├── model/Pelicula.java      # Clase película
│   ├── dao/PeliculaDAO.java     # Manejo de datos
│   ├── gui/MainFrame.java       # Ventana principal
│   └── utils/ConexionDB.java    # Conexión MySQL
├── sql/crear_base_datos.sql     # Script de BD
├── lib/mysql-connector-j-9.4.0.jar
└── README.md
```

## Configuración Paso a Paso

### 1. Crear Base de Datos

```sql
-- Ejecutar en MySQL Workbench o línea de comandos
CREATE DATABASE IF NOT EXISTS Cine_DB;
USE Cine_DB;

CREATE TABLE Cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero VARCHAR(20) NOT NULL
);
```

### 2. Configurar Conexión
En el archivo `ConexionDB.java`, cambiar la contraseña:
```java
private static final String PASSWORD = "tu_password_mysql";
```

### 3. Ejecutar en NetBeans
1. Abrir proyecto en NetBeans
2. Verificar que el conector MySQL esté agregado
3. Ejecutar MainFrame.java

## Tipos de Datos Java ↔ MySQL

| Campo | Java | MySQL | Descripción |
|-------|------|-------|-------------|
| id | int | INT AUTO_INCREMENT | Número único |
| titulo | String | VARCHAR(150) | Título película |
| director | String | VARCHAR(50) | Nombre director |
| anio | int | INT | Año estreno |
| duracion | int | INT | Minutos duración |
| genero | String | VARCHAR(20) | Tipo película |

## Clases Principales

### 1. Pelicula.java
- Clase modelo con atributos básicos
- Constructores y métodos get/set
- Método de validación simple

### 2. ConexionDB.java
- Método `conectar()` para obtener conexión
- Método `cerrar()` para cerrar conexión
- Manejo básico de errores

### 3. PeliculaDAO.java
- `guardarPelicula()` - Insertar nueva película
- `obtenerPeliculas()` - Listar todas
- `buscarPorId()` - Buscar por ID

### 4. MainFrame.java
- Ventana principal con menú
- Botones de acceso rápido
- Prueba automática de conexión

## Funcionalidades Actuales

✅ **Semana 6:**
- Conexión JDBC con MySQL
- Estructura básica del proyecto
- Ventana principal con menús
- Clases modelo y DAO básicas

✅ **Semana 7 (Actual):**
- Se añadio la funcionalidad de buscar, editar/actualizar, eliminar y tambien de limpiar el formulario.
- Se modifico el Jframe para cumplir con los requerimientos.
- Se añadio un DROP TABLE en el codigo sql para garantizar el funcionamiento de este.

🚧 **Próximas Entregas:**
- Semana 8: Validaciones y refinamiento

## Géneros Disponibles
- Acción
- Romance
- Animación
- Aventura
- Comedia
- Terror
- Drama
- Ciencia Ficción

## Instrucciones de Entrega

### Para crear el RAR:
1. Comprimir toda la carpeta `Exp2_S5_Grupo14`
2. Incluir:
   - Proyecto completo de Java
   - Script SQL (`sql/crear_base_datos.sql`)
   - Documentación (README.md)
   - Conector MySQL

### Archivos importantes:
- `MainFrame.java` - Clase principal
- `crear_base_datos.sql` - Script de BD
- `mysql-connector-j-9.4.0.jar` - Driver JDBC

## Problemas Comunes

**Error de conexión:**
- Verificar que MySQL esté ejecutándose
- Comprobar usuario y contraseña
- Confirmar que existe la base de datos

**Error de compilación:**
- Verificar que el conector MySQL esté agregado
- Comprobar versión de Java (8 o superior)

## Autores
- Grupo 14 - Bimestre 3
- Programación Orientada a Objetos II

---

**Nota:** Este es un proyecto educativo enfocado en aprender JDBC y conexiones a bases de datos. El código está simplificado para facilitar el entendimiento.