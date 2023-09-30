# HRManage

## Integrantes del equipo
- Juan Pablo Mateus Pardo Codigo: 0000224628
- Juan Esteban Villalobos Clavijo Codigo: 0000247532
- Juan Sebastian Viracacha Suares Codigo: 0000247432
## Contacto
- juanmatpa@unisabana.edu.co
- juanvicl@unisabana.edu.co
- juanvirsu@unisabana.edu.co
## Proyecto HrManage
### ¿Que hace nuestro proyecto?

### Gestión de Recursos Humanos:

El proyecto de Gestión de Recursos Humanos tiene como objetivo proporcionar funciones para la gestión eficiente de recursos humanos en una organización. Esta aplicación se ha desarrollado siguiendo las mejores prácticas de desarrollo de software y cumple con estándares de calidad para garantizar un código limpio, mantenible y una experiencia de usuario efectiva.

### Características Principales:

1. Gestión de Empleados
Permite crear, modificar y eliminar registros de empleados.
Mantiene información de contacto y datos personales de los empleados.
Asigna roles y responsabilidades a los empleados. También incluye la capacidad de configurar roles.
2. Gestión de Perfiles de Empleados
Almacena perfiles de habilidades y experiencia de los empleados.
Registra certificaciones de los empleados.

### Principios de Diseño:

- Separación de Preocupaciones: Hemos aplicado el principio de separación de preocupaciones para mantener el código organizado y fácil de mantener, separando la lógica de negocio del acceso a datos y los controladores.

- DTO Independientes: No utilizamos objetos de base de datos como DTO (Objeto de Transferencia de Datos); hemos creado DTO independientes para transferir datos entre las capas de la aplicación.

- Relaciones: Hemos implementado relaciones como @OneToMany o @ManyToOne cuando ha sido necesario para representar las relaciones entre entidades, por ejemplo, entre empleados y roles.

- Base de Datos en Memoria: Hemos creado una base de datos propia que se puede ejecutar en cualquier máquina, utilizando herramientas como H2 para bases de datos en memoria.

- Lombok: Hemos utilizado Lombok, una biblioteca de Java, para simplificar la creación de getters, setters y otros métodos comunes.

- Código Limpio: Hemos seguido las buenas prácticas de código limpio para que el código sea legible y mantenible.

- Control de Versiones: Hemos cumplido con las reglas de los commits, siguiendo una convención clara y significativa al realizar commits en el sistema de control de versiones (por ejemplo, Git).

### Pruebas y Documentación:

- Pruebas: Hemos implementado pruebas unitarias y de integración con al menos un 85% de cobertura utilizando herramientas como JaCoCo. Estas pruebas incluyen aserciones y verificaciones apropiadas para validar el comportamiento del código.

- Integración Continua: Hemos configurado la integración continua en el proyecto, lo que significa que las pruebas se ejecutan automáticamente cada vez que se realiza un cambio en el código.

- Documentación: La documentación se encuentra en la carpeta "docs" del proyecto e incluye un archivo JSON de Postman para probar la API, diagramas (diagrama de componentes, diagrama de paquetes y modelo de datos) en un formato que se puede visualizar en herramientas como Draw.io, así como documentación sobre cómo ejecutar el proyecto.

- OpenAPI (Swagger): Hemos implementado OpenAPI (anteriormente conocido como Swagger) para documentar la API de manera efectiva.

### Próximos Pasos:

En una fase posterior del proyecto, se agregará la parte frontal (frontend) para mejorar la experiencia del usuario.

### ¿Como ejecutarlo?

Nuestro proyecto se basa en una simulación de gestión de recursos humanos donde nuestro propósito es la gestión, creación y eliminación de empleados y perfiles de empleados logrando crear, modificar, obtener y eliminar cada uno de ellos. Para la clase de empleados fue necesario utilizar código, nombre, edad, rol, email, número telefónico y responsabilidades como atributos y para la clase de perfilEmpleado se utilizó código, nombre, habilidades, experiencias, certificaciones, eliminar. Para poder ejecutar nuestro programa es necesario descargar Postman, ya que con este podremos ejecutar las opciones POST, PUT y DELETE; se puede detallar que en cada clase de la carpeta "Controller" están los métodos con sus respectivos endPoints logrando que sea posible ejecutar nuestro programa sin ningún error.

### Cómo Ejecutar el Proyecto
### 1. Configuración de la Base de Datos
Para ejecutar el proyecto con una base de datos externa, sigue estos pasos:

-Configura la base de datos: Asegúrate de tener acceso a la base de datos que has pagado. Esto puede incluir detalles como la dirección del servidor, el nombre de la base de datos, el usuario y la contraseña.

-Configuración de Propiedades: En el proyecto, busca el archivo de configuración de propiedades, por ejemplo, application.properties o application.yml, y asegúrate de proporcionar la información de conexión a tu base de datos externa en estas propiedades.

-Ejemplo de configuración en un archivo .properties:

spring.datasource.url=jdbc:mysql://localhost:3306/mi_basededatos
spring.datasource.username=mi_usuario
spring.datasource.password=mi_contraseña

-Ejecución de la Aplicación: Ahora puedes ejecutar la aplicación. Dependiendo de tu entorno de desarrollo, puedes hacerlo a través de una IDE o utilizando comandos de línea de comandos.

### 2. Probar la API con Postman
- Una vez que la aplicación esté en funcionamiento y conectada a tu base de datos externa, puedes utilizar Postman para probar la API. Sigue estos pasos:

- Importa la Colección de Postman: En la carpeta "docs" de este proyecto, encontrarás un archivo JSON de Postman que contiene una colección de solicitudes para probar la API. Importa este archivo en tu cliente de Postman.

- Configura Variables de Entorno: En Postman, configura las variables de entorno para que coincidan con la dirección y el puerto de tu servidor local donde se ejecuta la aplicación. Esto te permitirá realizar solicitudes a la API de manera fácil y rápida.

- Ejecuta Solicitudes: Utiliza las solicitudes de la colección de Postman para interactuar con la API. Asegúrate de configurar los encabezados y los cuerpos de las solicitudes según sea necesario para tu caso de uso específico.

- Obtén y Analiza Respuestas: Postman te mostrará las respuestas de la API en formato JSON. Puedes analizar estas respuestas para verificar que la API funcione correctamente.

Con estos pasos, podrás ejecutar la aplicación y probar la API de Gestión de Recursos Humanos utilizando Postman con tu base de datos externa.
