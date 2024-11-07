# Gestion MVP

**Gestion MVP** es un proyecto API REST en desarrollo para la gestión de empleados, actividades y productos en una empresa. La aplicación está construida con un enfoque en una arquitectura de capas, utilizando **Spring** y **JPA-Hibernate** para la gestión de datos, y **MySQL** como base de datos.

## Tecnologías utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Framework**: Utilizado para gestionar la infraestructura de la aplicación.

  
  - **Spring MVC**: Para la gestión de controladores y vistas en la arquitectura.
  - **Spring Data JPA**: Para la integración con la base de datos utilizando Hibernate.
- **MySQL**: Base de datos relacional utilizada para almacenar la información.
- **JPA/Hibernate**: Para la persistencia de datos y mapeo objeto-relacional.
- **DTOs (Data Transfer Objects)**: Utilizados para la transferencia eficiente de datos entre las capas.
- **Arquitectura en capas**:
  - **Service**: Capa de lógica de negocio.
  - **Repository**: Capa de acceso a datos.
  - **Controller**: Capa encargada de manejar las solicitudes HTTP.