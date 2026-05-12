# Content Manager
Proyecto de clase de JavaFX, es una app para gestionar contenido

Este proyecto es una aplicación de escritorio desarrollada en JavaFX que integra gestión de bases de datos, manejo de ficheros, consumo de APIs externas y diseño de interfaces avanzadas. Se ha desarrollado de forma incremental a través de 5 fases de práctica.

## Características del Proyecto
La aplicación permite la gestión integral de una entidad (ajustable a cualquier temática) a través de las siguientes funcionalidades clave:

### Interfaz:
 - Diseño con Layouts: Uso de BorderPane como raíz, GridPane para formularios y TabPane para la navegación por pestañas.
 - Controles Variados: Implementación de TextField, Slider, ComboBox, CheckBox y RadioButton.
 - Sistema de Menús: Barra de menú completa con opciones de Archivo (Nuevo, Salir), Ver (Listado) y Ayuda (Acerca de).
### Persistencia y Datos
- Modelo de Objetos: Implementación del patrón DO (Data Object) en un paquete model.
- Base de Datos MySQL/MariaDB: Integración del patrón DAO (Data Access Object) para realizar operaciones INSERT y SELECT de forma persistente.
- Gestión de Errores: Control de excepciones SQLException con alertas visuales al usuario.
### Intercambio de Información 
- Exportación: Guardado de datos en ficheros .txt mediante BufferedWriter y FileChooser.
- Importación: Carga de datos desde archivos de texto plano, procesando líneas mediante split(";").
### Integración con Api Externa
- HttpClient: Realización de peticiones HTTP asíncronas a una API
- Procesamiento JSON: Recepción y extracción de datos específicos desde respuestas JSON para su visualización y almacenamiento opcional en la BD.

## Tecnologías utilizadas
- Lenguaje: Java 21
- Framework: JavaFX con Maven
- Base de Datos: MySQL / MariaDB
- Herramientas: VS Code
- Api Externa Gemini 2.0

## Instalación y Ejecución
1. Clonar el Repositorio "git clone https://github.com/TuUsuario/NombreDelRepo.git"
2. Configurar la Base de Datos, con el script que viene en la carpeta BDDy además crear un .env con los credenciales deseados
3. Compilar y ejecutar "mvn clean javafx:run"

---
Realizado por Alejandra Ruiz Bermúdez
