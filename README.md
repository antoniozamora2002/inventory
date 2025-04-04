# Inventory Management System

Este proyecto es un sistema de gestión de inventarios desarrollado con Spring Boot. Permite a los usuarios gestionar productos, proveedores, y órdenes de compra de manera eficiente.

## Características

- Gestión de productos: añadir, editar, eliminar y ver productos.
- Gestión de proveedores: añadir, editar, eliminar y ver proveedores.
- Gestión de órdenes de compra: crear, actualizar, y ver órdenes de compra.
- Autenticación y autorización de usuarios.
- API RESTful para integración con otros sistemas.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para construir aplicaciones Java.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **Spring Security**: Para la autenticación y autorización.
- **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
- **Maven**: Gestión de dependencias y construcción del proyecto.

## Requisitos Previos

- **JDK 17**
- **Maven** 3.6.0 o superior

## Instalación

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/antoniozamora2002/inventory.git
    ```

2. Navegar al directorio del proyecto:
    ```bash
    cd inventory
    ```

3. Construir el proyecto:
    ```bash
    mvn clean install
    ```

4. Ejecutar la aplicación:
    ```bash
    mvn spring-boot:run
    ```

## Uso

Una vez que la aplicación esté en ejecución, se puede acceder a la interfaz de usuario en `http://localhost:8080`. La API RESTful está disponible en `http://localhost:8080/api`.

## Contacto

Antonio Zamora - [antoniozp02@gmail.com](mailto:antoniozp02@gmail.com)