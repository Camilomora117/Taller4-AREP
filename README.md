# AREP Laboratorio 4

Para este taller se construyó un servidor Web (tipo Apache) en Java. El servidor puede entregar páginas html, y archivos css, js y jpg. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS.  El servidor debe atender múltiples solicitudes no concurrentes.


## Iniciando

### Prerequisites

- Java - Ambiente de desarrollo
- Maven - Administrador de dependencias
- Git - Sistema de control de versiones

### Instalando el entorno

Descargamos el archivo .zip o lo clonamos con el comando:

```
git clone https://github.com/Camilomora117/Taller4-AREP.git
```

Una vez descargado el repositorio nos dirigimos al directorio raiz del proyecto y ejecutamos el comando:

```
mvn clean package exec:java
```

Finalmente ingrese al navegador de su preferencia y usamos el link (El cual es el localhost que corre por el puerto 35000):
http://localhost:35000/

## Documentación

Para visualizar la documentación de javadoc ejecutamos el comando: 

```
mvn javadoc:javadoc
```

## Corriendo Tests unitatios

Nos ubicamos en el directorio principal del repositorio y ejecutamos el comando:

```
mvn test
```

## Construido con

* [Maven](https://maven.apache.org/) - Dependency Management

## Solución

En este taller creamos nuestra implementacion de las anotaciones @Component y @RequestMapping para poder simular un microfamework spring.

## Explicaciones

* Patrones: Hacemos uso del patron singleton para crear una sola instancia del servidor y una sola instancia de StaticFiles.

## Versonamiento

Versión 1.0

## Autores

* Yesid Camilo Mora Barbosa (camilomora117)
