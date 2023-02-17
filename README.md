# AREP Laboratorio 3

En este taller usted debe explorar la arquitectura del microframework WEB denominado sparkweb. Este micro framework permite construir aplicaciones web de manera simple usando funciones lambda.

Esta app permite leer archivos estaticos css, html, js y jpg.

## Iniciando

### Prerequisites

- Java - Ambiente de desarrollo
- Maven - Administrador de dependencias
- Git - Sistema de control de versiones

### Instalando el entorno

Descargamos el archivo .zip o lo clonamos con el comando:

```
git clone https://github.com/Camilomora117/Taller3-AREP.git
```

Una vez descargado el repositorio nos dirigimos al directorio raiz del proyecto y ejecutamos el comando:

```
mvn clean package exec:java
```

Finalmente ingrese al navegador de su preferencia y usamos el link (El cual es el localhost que corre por el puerto 35000):
http://localhost:35000

#Para usar los servicios
Si desea usar leer los archivos estaticos use los siguientes links

```
http://localhost:35000/index.html
```

```
http://localhost:35000/style.css
```

```
http://localhost:35000/imagen.jpg
```

```
http://localhost:35000/app.js
```

Ademas una pagina web donde involucra todos los archivos:

```
http://localhost:35000/pagina.html
```

## Documentación

Para visualizar la documentación de javadoc ejecutamos el comando: 

```
mvn javadoc:javadoc
```

Y entramos a la siguien ruta:

```
...\miprimera-app\target\site\apidocs
```

## Corriendo Tests unitatios

Nos ubicamos en el directorio principal del repositorio y ejecutamos el comando:

```
mvn test
```

## Construido con

* [Maven](https://maven.apache.org/) - Dependency Management

## Versonamiento

Versión 1.0

## Autores

* Yesid Camilo Mora Barbosa (camilomora117)

## Explicaciones

* Extensibilidad: Podemos leer cualquier archivo que se encuentre en un directorio que nosotros establecimos.

* Patrones: Hacemos uso del patron singleton para crear una sola instancia del servidor y una sola instancia de StaticFiles.

* Modular: En HttpServer tenemos el servidor el cual es el metodo main que inicia la conexión del socket en espera de solicitudes de información de peliculas, esta a su vez crea la tabla respectiva del JSON que contiene la información de la pelicula buscada. La clase Cache unicamente guarda la informacion de peliculas que ya se buscarón anteriormente para que no se busque de nuevo. Por ultimo HttpConnection realiza el enlace entre la API de peliculas y el socket creado por HttpServer.
