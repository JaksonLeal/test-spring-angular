# full stack web application developed with java and angular

![](https://www.pngrepo.com/png/303388/180/java-4-logo.png) ![](https://www.pngrepo.com/png/353396/180/angular-icon.png) ![](https://jwt.io/img/badge-compatible.svg) ![](https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/1280px-Spring_Framework_Logo_2018.svg.png) ![](https://1000marcas.net/wp-content/uploads/2020/11/MySQL-logo.png)

---
+ El proyecto cuenta con un CRUD de una sola tabla en la base de datos y un login usando Spring Security y JWT.
+ Backend desarrollado con java.
+ Frontend desarrollado con angular.

---
#### Para ejecutar el proyecto debes tener:
- java 17.
- npm 8.
- angular 14.
- node 16.
- Algun gestor de base de datos mySQL, yo use mysql workbench.

---
## start
---

1. En tu gestor de base de datos deberas ejecutar el siguiente comando, yo use mySQL workbench:

    	CREATE DATABASE test;
2. luego deberas ejecutar en tu IDE de preferencia el proyecto `testBack`, yo use springtoolsuite 4:

	+ El proyecto creara las tablas en la base de datos que acabas de crear.

3. El ultimo paso sera ejecutar en tu IDE de preferencia el protecto `testFront`, yo use visual studio code:
	+ En la terminal ubicada en el proyecto ejecutar la siguiente linea de comando:
	```
	ng serve
	```
---
Es posible que necesites modificar el archivo `application.properties` para que te funcione la base de datos, este se encuentra en el proyecto `testBack`.

---
## End
