# Getting Started

## Documentación de referencia
La ejecución de la prueba consta de dos puntos:

### Punto 1: Filtrado y procesamiento de libros

La clase FilterServiceImpl contiene 9 métodos, cada uno de ellos ofreciendo la solución a 
uno de los puntos detallados en el folleto de la prueba. La solución se presenta impresa 
por consola mediante un logger -dado que no se especifica cómo debe presentarse. 
El procesado de la lista se hace en el constructor del service y se ha utilizado 
la Api Stream() en aras de ofrece un código claro, legible y eficiente.

El archivo CSV del último punto opcional se genera en la raiz del proyecto.

### Punto 2: Microservicio con Token

* Se ha implementado Feign como cliente http debido a su facilidad de uso.
* Se extrae el token (y se imprime mediante un log). Se añade la fecha.
* Se expone el endpoint solicitado.
* Se rechaza el token si está vacío.

### Otros

Se implementa [Swagger](http://localhost:8081/swagger-ui/index.html) y [OpenApi](http://localhost:8081/v3/api-docs)
Se implementan logs y se formatean mediante appenders.
