# TallerArepAppSegura
## APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES
## Descripción 
El siguiente Taller se desarrollaron   dos proyectos distintos que se comunican mediante peticiones HTTP, con el objetivo de que un usuario se pueda loguear y la aplicación le ofresca un servicio, en este caso ver una noticia actual. Estos seran almacenados en containers Dockers que de manera similar a las maquinas virtuales pueden virtualizar, pero en vez de hardware , los contenedores virtualizaran el sistema operativo de un servidor. Luego de mostrar y describir como se desarrollo el taller utilizando herramientas como llaves y certificados garantizando autenticación, autorización e integridad entre los servicios.Se exponen muestras de la arquitectura de seguridad y el resultado en una maquina Aws.

## Prerrequisitos
Para la realización y ejecución tanto del programa como de las pruebas de este, se requieren ser instalados los siguientes programas:
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [Docker](https://www.docker.com). Docker, es una tecnología de creación de contenedores que permite la creación y el uso de contenedores de Linux®. La comunidad open source Docker trabaja para mejorar estas tecnologías a fin de beneficiar a todos los usuarios de forma gratuita.

## Ejecución 
Para tener el proyecto se puede descargar en un archivo zip o bien con el siguiente comando 
```
git clone https://github.com/davinchicoronado/TallerArepAppSegura.git
```

(Opcional) Para ejecutar los proyectos localmente, utilizando la herramienta Maven, nos dirigimos al directorios donde se encuentran alojados los proyectos, y dentro de estos ejecutamos en un Shell o Símbolo del Sistema los siguientes comandos:

```
mvn exec:java -Dexec.mainClass="edu.escuelaing.arem.app.SparkWebApp"
mvn exec:java  -Dexec.mainClass="edu.escuelaing.arem.api.SparkWebApi" 
```
Luego con el siguiente comando, en cada una de los shells de los proyectos crearemos las imagenes, y los containers haciendo referencia a estas, ademas de crear una red privada con una dirección ip para cada container, permitiendo la comunicación entre ellos. En estos contenedores se encontraran los proyectos desplegados(En los archivos docker-composey Dockerfile se puede ver con mas detalle como fueron asignadas estas direcciones y ademas de como agregar las llaves y certificados, ya que en el codigo se hacen referencias de estas, y poder permitir compilar los proyectos en los containers).
```
docker-compose up -d
```

addKeycerts

Para probar el proyecto localmente se deben cambiar las urls en el codigo y cambiar las llaves y los certificados debidamente. El archivo llaves los contiene para cada caso en especifico.

llaves 

## Implementación

Del lado del cliente se tiene el proyecto LoginService y tiene una clase llamada LoginAppClient que proporcionará la vista al usuario, y esta consume servicios de una API llamada APIClient que como funcion tiene, es hacer peticiones http del lado del cliente.Como también verificar  las contraseña y el nombre del usuario que esten correctos, asi como también cifrar las contraseñas. También tiene un metodo que permite confiar en la petición http por medio de un certificado correspondiente al otro proyecto (secureApiClient).

Por otro lado se tiene el proyecto NewsService que proporciona una noticia actual, con la clase ServerNews la cual tiene peticiones http de lado del servidor pero estas peticiones al igual que LoginAppClient estan cifradas, este proyecto contiene una llave y como anteriormente se describia el otro proyecto tiene el certificado de esta llave. Permitiendo asi tener garantizado  autenticación, autorización e integridad entre los servicios.











## Despliegue Aws



## CircleCi

 ## Javadoc
 Para generar el javadoc de los proyectos se deberan ubicar en sus correspondientes directorios en un shell y ejecutar el siguiente comando.
 
```
mvn javadoc:javadoc
```
Tambien podrá encontrar la documentación en el directorio target/site/apidocs.

## Autor 
David Leonardo Coronado
