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
