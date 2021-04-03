# TallerArepAppSegura
## APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES
## Descripción 
El siguiente Taller se desarrollaron   dos proyectos distintos que se comunican mediante peticiones HTTP, con el objetivo de almacenar información de un usuario 
estos seran almacenados en containers Dockers que de manera similar a las maquinas virtuales pueden virtualizar, pero en vez de hardware , los contenedores virtualizaran el sistema operativo de un servidor. Luego de mostrar y describir como desplegarlo localmente, se exponen muestras de como desplegarlo en una maquina Aws.

## Prerrequisitos
Para la realización y ejecución tanto del programa como de las pruebas de este, se requieren ser instalados los siguientes programas:
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [Docker](https://www.docker.com). Docker, es una tecnología de creación de contenedores que permite la creación y el uso de contenedores de Linux®. La comunidad open source Docker trabaja para mejorar estas tecnologías a fin de beneficiar a todos los usuarios de forma gratuita.

## Ejecución 
Para tener el proyecto se puede descargar en un archivo zip o bien con el siguiente comando 
```
git clone https://github.com/davinchicoronado/TALLER-DE-DE-MODULARIZACI-N-CON-VIRTUALIZACI-N-E-INTRODUCCI-N-A-DOCKER-Y-A-AWS.git
```

(Opcional) Para ejecutar los proyectos localmente, utilizando la herramienta Maven, nos dirigimos al directorios donde se encuentran alojados los proyectos, y dentro de estos ejecutamos en un Shell o Símbolo del Sistema los siguientes comandos:

```
mvn exec:java -Dexec.mainClass="edu.escuelaing.arem.app.SparkWebApp"
mvn exec:java  -Dexec.mainClass="edu.escuelaing.arem.api.SparkWebApi" 
```
Luego con el siguiente comando, en cada una de las lineas de comandos de los proyectos crearemos las imagenes, y los containers haciendo referencia a estas, ademas de crear una red privada con una dirección ip para cada container, permitiendo la comunicación entre ellos. En estos contenedores se encontraran los proyectos desplegados(En los archivos docker-compose se puede ver con mas detalle como fueron asignadas estas direcciones).
```
docker-compose up -d
```
Para crear containers logService mas adelante se explicará como crearlos, ya que para desplegarlo en una maquina Aws es el mismo proceso.

## Implementación

Del lado del cliente se tiene el proyecto App-Lb-RoundRobin y como primera instancia existe la clase SparkWebApp que proporcionará la vista al usuario, y esta consume servicios de una APIrest llamada APIServiceRest que como funcion tiene, es hacer peticiones http del lado del cliente.
Estas peticiones las realiza con un conjunto de urls las cuales permiten hallar los logServices y en que puertos encontrarlos en sus correspondientes contenedores.


ApiServiceRest antes de hacer las peticiones http, realiza el balanceo Round Robin intercalando las urls en las peticiones, cumpliendo con el objetivo de trabajo propuesto. Ademas de proveer los servicios de consulta (queryDates) y guardado de datos (saveDates, dadas las especificaciones)



Las direcciones con localhost se dejan como prueba por si el desarrollador quiere tantear los proyectos ejecutandolos localmente, pero tendrá que cambiar las url en el metodo  balanceRoundRobin anteriormente descrito.

Luego se puede encontrar con el proyecto LogService con la clase SparkWebApi que provee servicios de almacenamiento y consulta (saveDates , queryDates) en una base de datos Mongo desplegada en un container, ademas de tener un metodo privado que le provee la colección de dicha base de datos no relacional (colección analoga a una tabla) y hacer dichas operaciones. 



Este proyecto se encuentra desplegado en varios contenedores, siendo 4 especificamente (el primero por el docker-compose).

### Despliegue Aws

En este punto se puede encontrar con 3 images, la primera haciendo referencia al proyecto App-Lb-RoundRobin, la segunda al proyecto LogService y la ultima a la base de datos Mongo. Conociendo esto se crearon 3 respositorios en dockerhub y a cada uno de estos se procede a subir una de estas imagenes.
Luego se realiza todo el proceso de creación de una maquina en Aws, creando la llave y realizando sus respectivas configuraciones para acceder a ella asi como las configuraciones basicas como la instalación de docker.

Una de las facilidades de tener el archivo docker-compose es que nos permite crear algunos containers automaticamente, asi como también las redes necesarias para una buena comunicación entre los mismos. Proceso que se debe realizar manualmente en la maquina Aws.

Primero se debe crear la red privada. 



Como segunda instancia se crea un container que desplegará el proyecto App-Lb-RoundRobin, correspondiente al cliente. La siguiente imagen permite ver en que puerto,red y direccion será visto, ademas del repositorio asociado.



Se repite el proceso para la creación de los otros contenedores con sus correspondientes direcciones ip, y puertos tal y como estan en el codigo java, ademas de sus correspondientes repositorios. La siguiente imagen permite ver todos los contenedores creados. 



La siguiente imagen permite ver con mas detalle que contenedores fueron creados en dicha red.
<p align="center">
    <img src="https://github.com/davinchicoronado/TALLER-DE-DE-MODULARIZACI-N-CON-VIRTUALIZACI-N-E-INTRODUCCI-N-A-DOCKER-Y-A-AWS/blob/master/Img/netDates.png?raw=true" alt="Sublime's custom image"/>
</p>

Por ultimo definimos unas reglas de entrada para que el puerto 8088 sea visto, y se pueda ubicar la app del cliente en la maquina Aws.



Obteniendo los siguientes resultados.



Localmente se puede ver que el html para el cliente hay una correcta ortografía, esto no sucede cuando se despliega en la maquina Aws, una conclusión podría ser que la maquina se encuentra en Estados Unidos por tal razón su lenguaje esta en ingles y no reconoce las tildes.

## CircleCi

 ## Javadoc
 Para generar el javadoc de los proyectos se deberan ubicar en sus correspondientes directorios en un shell y ejecutar el siguiente comando.
 
```
mvn javadoc:javadoc
```
Tambien podrá encontrar la documentación en el directorio target/site/apidocs.

## Autor 
David Leonardo Coronado
