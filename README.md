Perfulandia SPA - Sistema de Ventas de Perfumes - Arquitectura de Microservicios

Perfulandia SPA es un proyecto académico de una tienda online de perfumes implementada con una arquitectura de microservicios, desarrollada con Spring Boot, con el objetivo de modernizar el sistema monolítico original. El sistema se componer de diversos microservicios integrando múltiples módulos que trabajan de forma independiente para facilitar el mantenimiento, la escalabilidad y la implementación continua.
Descripción General

El sistema se compone de tres microservicios principales:

    UsuarioService: Gestiona la información de los usuarios/clientes
    ProductService: Administra el catálogo de productos/perfumes
    CarritoService: Maneja los carritos de compra de los clientes

Cada microservicio tiene su propia base de datos independiente y se comunica con los demás mediante APIs REST.
Microservicios - Cada servicio se comunica mediante APIs REST, y se conecta a su propia base de datos MySQL.
1. UsuarioService : registro y gestión de usuarios.

    Puerto: 8081
    Base de datos: perfulandia_usuarios
    Funcionalidades:
        CRUD de usuarios
        Gestión de roles (ADMIN, GERENTE, USUARIO)
        Autenticación básica

2. ProductService : gestión de perfumes.

    Puerto: 8082
    Base de datos: perfulandia_productos
    Funcionalidades:
        CRUD de productos/perfumes
        Consulta de catálogo
        Integración con UsuarioService para validaciones

3. CarritoService: operaciones del carrito de compras.

    Puerto: 8083
    Base de datos: perfulandia_carritos
    Funcionalidades:
        Gestión de carritos por usuario
        Cálculo automático de totales
        Agregar/eliminar productos del carrito
        Integración con UsuarioService y ProductService

Requisitos Técnicos

    Java 17+
    Spring Boot 3.1.0
    MySQL 8.0+
    Maven
    Lombok

Instalación

    Clonar el repositorio git clone https://github.com/3ddrrr/perfulandia.git
    Configurar las bases de datos en MySQL
    Ejecutar cada microservicio por separado
    Probar los endpoints con Postman

Microservicios Implementados

1. productservice
Paquete base: com.perfulandia.productservice
Endpoints para CRUD de productos.
Base de datos: tabla producto
Swagger disponible en: /swagger-ui/index.html
 Pruebas unitarias con Mockito (ProductServiceTest)


2. usuarioservice
Paquete base: com.perfulandia.usuarioservice
Endpoints para gestionar usuarios con roles (ADMIN, GERENTE, Usuario).
Base de datos: tabla usuario
 Pruebas unitarias con Mockito (UsuarioServiceTest)

 Swagger activo.

3. carritoservice

Paquete base: com.perfulandia.carritoservice
Maneja carritos de compras asociados a usuarios.
Carrito contiene lista de ItemCarrito con producto, cantidad y precio.
Comunicación interna con productservice simulada vía RestTemplate.
Pruebas unitarias con Mockito (CarritoServiceTest)
Swagger UI habilitado.

🧪 Pruebas Unitarias (Mockito)

ProductoServiceTest
Método probado: guardarProducto, listarProductos, buscarPorId
Mocks: ProductoRepository
Datos de prueba: objetos Producto con id, nombre, precio, stock.
Resultado: todos los tests pasan (✅)

UsuarioServiceTest
Método probado: guardar, listar, buscar, eliminar
Mocks: UsuarioRepository
Datos: objetos Usuario con nombre, correo, rol.
Resultado: todos los tests pasan (✅)

CarritoServiceTest
Método probado: crearCarrito, obtenerCarritoPorUsuario, agregarProducto, eliminarCarrito
Mocks: CarritoRepository
Simulación parcial de RestTemplate para validación externa
Resultado: todos los tests pasan (✅) excepto agregarProducto() que se depura manualmente por inicialización de lista.

🔍 Swagger
Todos los servicios tienen Swagger habilitado vía dependencia:
