# Informe Técnico: Gestión de Inventario en Java

# Grupo 14 

## 1. Requisitos de la Aplicación

### Requisitos Funcionales (RF)
- **RF-01: Gestión de productos:** La aplicación debe permitir la creación de productos con atributos: `codigo`, `nombre`, `descripcion`, `precio` y `stock`.  
- **RF-02: Operaciones del inventario:** El sistema debe poder agregar, eliminar, buscar y listar productos.  
- **RF-03: Interfaz de usuario:** Se requiere una interfaz de línea de comandos con un menú interactivo.

### Requisitos No Funcionales (RNF)
- **RNF-01:** La arquitectura debe seguir el paradigma de Programación Orientada a Objetos (POO).  
- **RNF-02:** La aplicación debe manejar errores de entrada de datos (`InputMismatchException`).  

---

## 2. Arquitectura de la Solución

El diseño del software se basa en un enfoque **POO modular**, separando responsabilidades en tres clases:

- **Producto.java:** Clase POJO que encapsula los datos y comportamiento de un producto.  
- **Inventario.java:** Capa de servicio que gestiona la colección de productos usando un `HashMap` para un acceso rápido y eficiente.  
- **MenuPrincipal.java:** Capa de presentación que maneja la lógica del menú y la interacción con el usuario.  

---

## 3. Implementación y Lógica de Negocio

Se implementaron los métodos necesarios en cada clase para cumplir los requisitos del proyecto:

### Inventario.java
- `agregarProducto(Producto)`: Añade un objeto Producto al inventario.  
- `eliminarProducto(String codigo)`: Elimina un producto por su clave única.  
- `buscarProductosPorNombre(String nombre)`: Permite búsqueda flexible y retorna una `List` de resultados.  
- `listarTodosLosProductos()`: Devuelve una `List` de todos los productos en el inventario.  

### MenuPrincipal.java
- Orquesta las llamadas a los métodos de `Inventario` según la opción seleccionada por el usuario.  
- Incluye validación básica de la entrada del usuario.  

---

## 4. Estrategia de Pruebas

Se utilizó una **estrategia de pruebas de caja blanca**, con una clase dedicada a validar el comportamiento del código.

### Pruebas Unitarias
- Se creó `PruebasUnitarias.java` para verificar cada método de forma aislada.  
- Se utilizó `assert` para confirmar el comportamiento esperado de las clases `Producto` e `Inventario`.   

### Pruebas de Integración
- Se validó el flujo completo de la aplicación mediante la interfaz de consola.  
- Se confirmó la correcta interacción entre todas las clases.  

---

## 5. Conclusión

El proyecto cumple con la pauta de evaluación. La solución es **modular, funcional y bien validada** mediante pruebas unitarias e integración.  
El código es **limpio y legible**, lo que facilita su mantenimiento y futuras expansiones.