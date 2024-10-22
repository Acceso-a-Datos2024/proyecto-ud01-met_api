[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/JkDJUAAN)

# Index
- [1. Introducción al supuesto con descripción de la API y capturas POSTMAN](#1-introducción-al-supuesto-con-descripción-de-la-api-y-capturas-postman)
- [2. Manual técnico para desarrolladores](#2-manual-técnico-para-desarrolladores) 
- [3. Manual de usuario con juego de pruebas](#3-manual-de-usuario-con-juego-de-pruebas) 
- [4. Explicación del reparto de las tareas](#4-explicación-del-reparto-de-las-tareas-entre-ambos-integrantes) 
- [5. Extras realizados](#5-extras-realizados) 
- [6. Propuestas de mejora](#6-propuestas-de-mejora-nuevas-opciones-control-de-errores-) 
- [7. Conclusiones y opinión del trabajo realizado, dedicación temporal y cualificación estimada](#7-conclusiones-opinión-del-trabajo-realizado-dedicación-temporal-y-cualificación-estimada)

---

# 1. Introducción al supuesto con descripción de la API y capturas POSTMAN
La API utilizada por nuestro grupo es [The Metropolitan Museum of Art Collection API](https://metmuseum.github.io/), que nos proporciona acceso a datos de más de 470,000 obras del Metropolitan Museum of Art.Toda la información es de dominio público, e incluye imágenes en alta resolución.

En las siguientes capturas podemos ver distintas peticiones realizadas a la API a traves de Postman.

#### Peticion de los id de las obras dentro de la categoría de arte egipcio
![Captura de pantalla 2024-10-14 132102](https://github.com/user-attachments/assets/d180770d-4170-4652-ac3b-68f9fcf4d163)

#### Peticion de los datos de la obra con id 437430
![Captura de pantalla 2024-10-14 132012](https://github.com/user-attachments/assets/e18c0f99-9568-433b-8a3e-d7d6b1283655)

#### Petición de los id de las pinturas con imagenes de Auguste Renoir
![Captura de pantalla 2024-10-14 132126](https://github.com/user-attachments/assets/732e174e-65f4-4e98-8449-6bd8f2ecfe0f)


# 2. Manual técnico para desarrolladores

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#2-manual-t%C3%A9cnico-para-desarrolladores)

El trabajo trata de seguir el MVC, por lo que en su estructura encontrarás una carpeta Model y una carpeta Controller. A continuación hablamos un poco de cada una y de lo que contienen.

## Model

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#model)

En la carpeta Model encontramos todas las clases que nos servirán para la realización de consultas a la API, el manejo de sus respuestas a partir de objetos a las que mapearemos dichas respuestas y la administración de la cache de nuestra aplicación.

### Clases para el manejo de objetos:

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#clases-para-el-manejo-de-objetos)

- **ArtPiece**: Representa una obra de arte obtenida por consulta desde la API. Al realizar una petición por un id específico la API nos devuelve un JSON con las propiedades de la obra correspondiente, esta es la clase a la que se mapea ese JSON. Cuenta con un constructor vacío y otro con parámetros (este no se usa ya que no realizamos ninguna petición POST), métodos setters y getters de todos sus atributos (los más utilizados son los getters del id, imagen, título, descripción y autor) además de un método toString. Autogenerada con jsonschema2pojo.
- **Deparments**: La API también cuenta con una serie de JSONs que representan los departamentos del museo. Esta es la clase a la que se mapea un departamento. Al igual que ArtPiece tiene dos constructores uno vacío y otro con parámetros, setters y getters de sus atributos y un método toString. Esta clase cuenta solo con dos atributos: id y nombre, ambos se usan a la hora de construir el desplegable de la interfaz gráfica que permite al usuario filtrar por departamento. El método toString solo devuelve el nombre del departamento pero se tiene para facilidad de uso y no tener que llamar el getter del nombre cuando queramos mostrar por pantalla (en el desplegable) su nombre.
- **ElementMeasurement**: Representa una de las propiedades de ArtPiece que a su vez es otro objeto. Este objeto contiene el diámetro de la obra y un HashMap para listar otras propiedades adicionales. No se usa mucho. Al ser autogenerada con jsonschema2pojo cuenta con un constructor vacío y otro con parámetros, getters y setters de todos sus atributos y un método toString.
- **Measurements**: Otra clase que representa un propiedad de ArtPiece que es a su vez otro objeto. Contiene el nombre y una descripción de la obra además de un objeto de la clase ElementMeasurement y otro HashMap para propiedades adicionales de la obra. No se usa mucho. Al ser autogenerada con jsonschema2pojo cuenta con un constructor vacío y otro con parámetros, getters y setters de todos sus atributos y un método toString.
- **Response**: Clase que utilizamos para el mapeo de la respuesta de la API cuando le consultamos por todos los departamentos del museo. Contienen simplemente una lista de objetos departamento, cada uno con su id y nombre.
- **ResponseList**: Al realizar una consulta que devuelva varias obras la API devuelve un JSON con un campo total, que indica el total de resultados encontrados, y otro campo con una lista de ids de todos los objetos encontrados. Para obtener fácilmente esa lista de ids se genero esta clase con jsonschema2pojo para mapear la respuesta de la API a un objeto y con un método get obtener la lista. Cuenta con un constructor vacío y con parámetros, setters y getters para sus dos atributos (total y la lista de ids) y un método toString.
- **Tag**: Otra clase que representa una propiedad de ArtPiece que es a su vez otro objeto. Contiene etiquetas que dan información sobre la obra, URLs relacionadas y un HashMap para propiedades adicionales. No se usa mucho. Al ser autogenerada con jsonschema2pojo cuenta con un constructor vacío y otro con parámetros, getters y setters de todos sus atributos y un método toString.

### Clases para el manejo de la cache:

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#clases-para-el-manejo-de-la-cache)

#### CacheType:

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#cachetype)

Enum que representa los distintos tipos de cache disponibles, es decir los dos archivos desde los que podemos leer o a los que podemos describir. Contamos con dos archivos diferentes porque hay ids de obras en la API que no se corresponden a ninguna obra por lo que a parte de un archivo para los ids de objetos que sí existen y dichos objetos (STORAGE), tenemos otro con un HashMap de ids no válidos (BLACKLIST).

[![imagen](https://private-user-images.githubusercontent.com/115022266/378508123-7a95178d-f82c-49da-83a7-1dbd34588438.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4MTIzLTdhOTUxNzhkLWY4MmMtNDlkYS04M2E3LTFkYmQzNDU4ODQzOC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kZDEzNThlOGFmZGU3NmYyN2QwMDg5ZWU4ZmNmOGUyZWQ4ZWIwNzJkZjJlODM3MmVlNDgwN2QyYjEyYzVhOWNiJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.u6LPBc2Bqbs7JCdXDfzhx5CqKm0-gjGaYsFVL-pqpUc)](https://private-user-images.githubusercontent.com/115022266/378508123-7a95178d-f82c-49da-83a7-1dbd34588438.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4MTIzLTdhOTUxNzhkLWY4MmMtNDlkYS04M2E3LTFkYmQzNDU4ODQzOC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kZDEzNThlOGFmZGU3NmYyN2QwMDg5ZWU4ZmNmOGUyZWQ4ZWIwNzJkZjJlODM3MmVlNDgwN2QyYjEyYzVhOWNiJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.u6LPBc2Bqbs7JCdXDfzhx5CqKm0-gjGaYsFVL-pqpUc)

#### Cache:

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#cache)

En una carpeta del proyecto llamada cache almacenamos archivos binarios que contienen un HashMap con objetos ArtPiece (la clave de cada objeto es su id y el valor es su string en formato JSON) que ya nos ha devuelto la API en consultas anteriores. Para manejar la cache de forma más sencilla desde el ApiRequester se ha creado esta clase que encapsula todas las operaciones de lectura, escritura y guardado de los archivos de la cache.

Sus atributos son dos:

- **cacheMap**: El HashMap que obtendremos tras leer el archivo correspondiente de la cache.
- **path**: String de la ruta de la carpeta cache del proyecto.

El constructor de un objeto cache pide un parámetro cacheType, un string que obtendremos a través de nuestra enum CacheType para indicar con cual de los dos archivos de cache va a trabajar este objeto Cache. Durante el proceso de construcción se concatena el cacheType al path del objeto Cache para luego leer el HashMap existente en el archivo y inicializar el atributo cacheMap de nuestro objeto con el HashMap leído desde el archivo.

[![imagen](https://private-user-images.githubusercontent.com/115022266/378508501-dd5333ce-95eb-4008-b9c9-fe2fb68ec642.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4NTAxLWRkNTMzM2NlLTk1ZWItNDAwOC1iOWM5LWZlMmZiNjhlYzY0Mi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xNWUyMDVlNTQ2MTJjZGVlZGJlOGZiZmEyZWM1OTUzZWZiNmU5ZTJmNmI4MTQ2ZDI3NTRmZDQ2OTZjMzg2N2Y3JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kYODecRCeFVVMJjudevx5EdS-sX6IUh6-CjBkVqD1P8)](https://private-user-images.githubusercontent.com/115022266/378508501-dd5333ce-95eb-4008-b9c9-fe2fb68ec642.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4NTAxLWRkNTMzM2NlLTk1ZWItNDAwOC1iOWM5LWZlMmZiNjhlYzY0Mi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xNWUyMDVlNTQ2MTJjZGVlZGJlOGZiZmEyZWM1OTUzZWZiNmU5ZTJmNmI4MTQ2ZDI3NTRmZDQ2OTZjMzg2N2Y3JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kYODecRCeFVVMJjudevx5EdS-sX6IUh6-CjBkVqD1P8)

Cuenta con métodos setter y getter de cacheMap además de uno que muestra por pantalla el contenido del mismo por si es necesario realizar pruebas. Pero los métodos más usados son los siguientes:

- **add**: Al pasarle el id de un objeto (en string) y el objeto en sí (en string formato JSON) los añade al cacheMap de nuestro objeto cache.
    
    [![imagen](https://private-user-images.githubusercontent.com/115022266/378508651-6748ca2b-ee03-4e93-90f1-6d3d443cbc68.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4NjUxLTY3NDhjYTJiLWVlMDMtNGU5My05MGYxLTZkM2Q0NDNjYmM2OC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hMjYyYzg4ZmE4MTBjMzI0ODRjMTc0M2YxMzVhZDFhODgzMzg3ZTQwOGFjOTNlNzFjOWI4MDg4ZjcxYzNhNmUyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.IigvocfxEiINSj_1PKjhph_vk6cQymTZnuOqWvEHs3Y)](https://private-user-images.githubusercontent.com/115022266/378508651-6748ca2b-ee03-4e93-90f1-6d3d443cbc68.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4NjUxLTY3NDhjYTJiLWVlMDMtNGU5My05MGYxLTZkM2Q0NDNjYmM2OC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hMjYyYzg4ZmE4MTBjMzI0ODRjMTc0M2YxMzVhZDFhODgzMzg3ZTQwOGFjOTNlNzFjOWI4MDg4ZjcxYzNhNmUyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.IigvocfxEiINSj_1PKjhph_vk6cQymTZnuOqWvEHs3Y)
    
- **save**: Guarda en el archivo de cache correspondiente el cacheMap actual de nuestro objeto; no añade al archivo sino que sobrescribe ya que siempre estamos trabajando con una copia del HashMap de la cache ya que en el constructor inicializamos el cacheMap al HashMap leído del archivo.
    
    [![imagen](https://private-user-images.githubusercontent.com/115022266/378508826-ceac5e31-3548-4222-809f-a3182f735195.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4ODI2LWNlYWM1ZTMxLTM1NDgtNDIyMi04MDlmLWEzMTgyZjczNTE5NS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT03MDE3ZTJjNjRlZDlkNDUyOTQwMzFjZjcwZmVmMTU0YTM1MWQ2YmYyNmQwNTE2YWYwYzgyNmEwOTliODhkOGNjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.WlEcPQRPHLu8MA1OjoNOhViq1fXEEYXp-ExsDfdcxko)](https://private-user-images.githubusercontent.com/115022266/378508826-ceac5e31-3548-4222-809f-a3182f735195.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4ODI2LWNlYWM1ZTMxLTM1NDgtNDIyMi04MDlmLWEzMTgyZjczNTE5NS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT03MDE3ZTJjNjRlZDlkNDUyOTQwMzFjZjcwZmVmMTU0YTM1MWQ2YmYyNmQwNTE2YWYwYzgyNmEwOTliODhkOGNjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.WlEcPQRPHLu8MA1OjoNOhViq1fXEEYXp-ExsDfdcxko)
    
- **existsId**: Comprueba si el id que le pasamos ya existe en el HashMap de nuestro archivo de cache correspondiente.
    

[![imagen](https://private-user-images.githubusercontent.com/115022266/378508959-dde0e8d1-d606-47a7-89e3-c7843e4d57c4.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4OTU5LWRkZTBlOGQxLWQ2MDYtNDdhNy04OWUzLWM3ODQzZTRkNTdjNC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kM2RhZDI5ZTI0MDJiYTNmYWE4NzkwMDEyNzdiMzFiNjA5ZDIxYmZmYjNlZDUzMDFlMDkyYTI4MjM1YmRkNDJkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.JAzd7hFo6k3afdP22GkXOfhLOsrn1QqewMRQ34VWSKY)](https://private-user-images.githubusercontent.com/115022266/378508959-dde0e8d1-d606-47a7-89e3-c7843e4d57c4.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTUwMjIyNjYvMzc4NTA4OTU5LWRkZTBlOGQxLWQ2MDYtNDdhNy04OWUzLWM3ODQzZTRkNTdjNC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kM2RhZDI5ZTI0MDJiYTNmYWE4NzkwMDEyNzdiMzFiNjA5ZDIxYmZmYjNlZDUzMDFlMDkyYTI4MjM1YmRkNDJkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.JAzd7hFo6k3afdP22GkXOfhLOsrn1QqewMRQ34VWSKY)

### Clase para la realización de consultas a la API:

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#clase-para-la-realizaci%C3%B3n-de-consultas-a-la-api)

#### ApiRequester:

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#apirequester)

Es la clase que se encarga de construir las URLs de consulta a la API, recibir sus respuestas y mapearlas a las clases correspondientes, enviarle los datos pertinentes a los controladores y realizar las llamadas para leer o actualizar la cache.

Cuenta con tres atributos string que sirven para la construcción de URLs de consulta a la API:

- baseURL: Para la búsqueda de objetos por id.
- urlSearch: Para la realización de búsqueda por parámetros.
- urlDepartments: Para obtener una Response de los departamentos del museo.

También tiene un atributo de tipo Cache para cada uno de los archivos de nuestra cache:

- objectsCache: Para los ids relacionados con objetos que sí existen en la API.
- blacklistCache: Para los ids relacionados con objetos que no existen en la API.

El único constructor de la clase está vacío porque todos los atributos se usan con sus valores por defecto.

El método **getTotalNumberOfArtPieces** realiza una consulta a la API para que devuelva una ResponseList con todos los objetos disponibles. Debido a que lo único que nos interesa el el campo total y al gran número de ids disponibles (lo cual ralentiza la aplicación) una vez obtenido el número total se guarda en un archivo en la cache para no tener que interpretar una respuesta tan grande en subsecuentes llamadas.
![image](https://github.com/user-attachments/assets/2eb59ec4-1c36-447b-83b0-5c5e70cae7de)

El método **getRandomId** utiliza el número total de ids disponibles para generar uno aleatorio.
![image](https://github.com/user-attachments/assets/29cf911e-7ba4-42cf-994d-dbd39f3e28bd)


El método **getRandomArtPiece** se usa para obtener un objeto aleatorio de la API a partir de un id generado aleatoriamente. Sin embargo como en la API existen ids que no están vinculados a ningún objeto tuvieron que implementarse una serie de contingencias y la cache blacklist para evitar posibles errores. Primero el método comprueba si el id aleatorio ya está almacenado en una de las dos caches, sí se encuentra en la cache de objetos válidos lo leé de ahí y lo devuelve, sí se encuentra en la cache de ids no válidos se vuelve a llamar a sí mismo. Si no está en ninguna de la cache construimos nuestra URL de petición concatenando baseURL con nuestro id aleatorio y mapeamos la respuesta a un objeto ArtPiece. Si el mapea resultó erróneo es que el id no está vínculado a un objeto así que lo almacenamos en nuestra blacklist y volvemos a llamar al método. Si el mapeo se realizó con éxito guardamos el id y el objeto en la cache de objetos válidos pero realizamos otra comprobación: si el objeto obtenido tiene una imagen disponible para ser mostrada. Si no, volvemos a generar otro id para realizar otra petición, si el objeto cuenta con una imagen disponible devolvemos nuestro objeto ArtPiece que será usado por el un controlador para actualizar la vista correspondiente.
![image](https://github.com/user-attachments/assets/42738557-f538-4ccb-b91f-07560b13682e)


El método **searchGetArtPiece** realiza consultas a la API con los filtros de búsqueda introducidos por el usuario en la interfaz gráfica, comprobando cuales de los opcionales añadió. Primero construye la URL de petición concatenando los filtros de búsqueda introducidos por el usuario a nuestra urlSearch y la respuesta obtenido será mapeada a un objeto tipo ResponseList. A partir de nuestro objeto ResponseList comprobamos si la búsqueda obtuvo una respuesta o no (si la lista de ids de nuestro objeto ResponseList está vacía o no). Si está vacía devolvemos null y el controlador ya se encargará de actualizar la vista de manera adecuada. Si obtuvo respuesta cogemos el primer id de la lista y comprobamos si está presente en nuestra cache de ids y objetos válidos. Si es así leemos su JSON de ahí, lo mapeamos a un objeto ArtPiece y se lo devolvemos al controlador. Si no está en cache construimos una URL de petición por ese id, concatenando el id a nuestra baseURL y realizamos la consulta a la API, guardamos el id y su objeto en la cache y mapeamos a un objeto ArtPiece la respuesta, que se lo devolveremos al controlador.

![image](https://github.com/user-attachments/assets/47dde001-d870-4054-9762-21f80fec3dcb)

## Controller

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#controller)

En la carpeta Controller encontramos archivos que comunican la parte gráfica de la aplicación con la lógica a través de eventos. Hay un archivo por cada vista, en este caso uno para el Acceso y otro para el Home.

### AccesoController

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#accesocontroller)

Es el archivo que se encarga de manejar el login a la aplicación, teniendo como único objetivo verificar si los datos metidos por el usuario coinciden con las credenciales guardadas en acceso.properties

El método **validarCredenciales** es el que comprueba si los datos coinciden y en caso de que así sea cambia la vista a la principal. En caso contrario avisa al usuario mediante un label.

[![image](https://private-user-images.githubusercontent.com/119294634/378940495-ae58b64d-2abb-4f14-bc80-8d39f0c1fd6d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwNDk1LWFlNThiNjRkLTJhYmItNGYxNC1iYzgwLThkMzlmMGMxZmQ2ZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mOWU0ZDNiZGU3OWJkZDUyZDhkZGVkZTFiZjk2ODZjMWI2ZDE2OWY5ODZlMGYzMzQ2MzE1YzE5Y2MxOGM3NTYzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.g-5pgQXIpMS6T8xPNIgE0DMgH-yrSGRV3JwyNO5TAww)](https://private-user-images.githubusercontent.com/119294634/378940495-ae58b64d-2abb-4f14-bc80-8d39f0c1fd6d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwNDk1LWFlNThiNjRkLTJhYmItNGYxNC1iYzgwLThkMzlmMGMxZmQ2ZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mOWU0ZDNiZGU3OWJkZDUyZDhkZGVkZTFiZjk2ODZjMWI2ZDE2OWY5ODZlMGYzMzQ2MzE1YzE5Y2MxOGM3NTYzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.g-5pgQXIpMS6T8xPNIgE0DMgH-yrSGRV3JwyNO5TAww)

Al querer una mayor seguridad la contraseña se guarda hasheada en nuestro fichero por lo que hay que hacer lo mismo con la que introduce el usuario para poder compararlas. De esto se encarga la función **hashPassword** que es llamada por la anterior.

[![image](https://private-user-images.githubusercontent.com/119294634/378940631-e57b8242-d385-4e48-8ac6-ddce05f18799.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwNjMxLWU1N2I4MjQyLWQzODUtNGU0OC04YWM2LWRkY2UwNWYxODc5OS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kMmIyN2NkNDYwNjZjZmU3NDNlYTFiNmQ3YzI5MTUyOGNiM2Y2MDg3YTJlYzg1ZGNiOGY1ZmM5ZjEwOGU0YWJiJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.b_u8KnCnVa7pZe6hDFz5YF7MFpQEvpxujhu7G1SWUXs)](https://private-user-images.githubusercontent.com/119294634/378940631-e57b8242-d385-4e48-8ac6-ddce05f18799.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwNjMxLWU1N2I4MjQyLWQzODUtNGU0OC04YWM2LWRkY2UwNWYxODc5OS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kMmIyN2NkNDYwNjZjZmU3NDNlYTFiNmQ3YzI5MTUyOGNiM2Y2MDg3YTJlYzg1ZGNiOGY1ZmM5ZjEwOGU0YWJiJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.b_u8KnCnVa7pZe6hDFz5YF7MFpQEvpxujhu7G1SWUXs)

### HomeController

[](https://github.com/Acceso-a-Datos2024/proyecto-ud01-met_api/blob/main/README.md#homecontroller)

Este archivo maneja toda la vista principal, requiriendo metodos del ApiRequester para cargar los datos.

**initialize** se ejecuta nada mas entrar en la vista y carga todos los datos necesarios, como los departamentos para llenar el comboBox. Además se encarga de preguntar si se quiere restaurar la última búsqueda.

[![image](https://private-user-images.githubusercontent.com/119294634/378940750-bb54e200-b89e-4fda-abf9-defdae28c1de.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwNzUwLWJiNTRlMjAwLWI4OWUtNGZkYS1hYmY5LWRlZmRhZTI4YzFkZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yYjRkMjY3N2M0ZjExZjZhMGU4YTI0MGM5MmEwNzM0YjFlMjhmOGE2YWNmMzhjNzAzNTU4MWIwYmRhZTBkZjM1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.GhH_KsY3vtaEtFyiy3tK1TAfSbDAInHXqfpkhsR69OQ)](https://private-user-images.githubusercontent.com/119294634/378940750-bb54e200-b89e-4fda-abf9-defdae28c1de.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwNzUwLWJiNTRlMjAwLWI4OWUtNGZkYS1hYmY5LWRlZmRhZTI4YzFkZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yYjRkMjY3N2M0ZjExZjZhMGU4YTI0MGM5MmEwNzM0YjFlMjhmOGE2YWNmMzhjNzAzNTU4MWIwYmRhZTBkZjM1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.GhH_KsY3vtaEtFyiy3tK1TAfSbDAInHXqfpkhsR69OQ)

Para obtener los departamentos utiliza **getDepartmentsFromJson** que coge todos los departamentos guardados en un JSON. Al ser algo que no va a cambiar frecuentemente evitamos hacer una consulta a la api cada vez que se inicie el programa

[![image](https://private-user-images.githubusercontent.com/119294634/378940892-fc546923-82ce-47fe-8bae-3342356f4c86.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwODkyLWZjNTQ2OTIzLTgyY2UtNDdmZS04YmFlLTMzNDIzNTZmNGM4Ni5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05YjIzYzc5NTQxYWFmNDUxY2UyZWQ5NjUyZmQ5YzdmMmVhNDYwYzBlZjI5OTcwZGY4ZWI2MDRhNDIxNzMzNzNkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.h5i41lSwfMvg1BsUTrlbEgZC8YJD-EAu0Tj4xQI_BhU)](https://private-user-images.githubusercontent.com/119294634/378940892-fc546923-82ce-47fe-8bae-3342356f4c86.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwODkyLWZjNTQ2OTIzLTgyY2UtNDdmZS04YmFlLTMzNDIzNTZmNGM4Ni5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05YjIzYzc5NTQxYWFmNDUxY2UyZWQ5NjUyZmQ5YzdmMmVhNDYwYzBlZjI5OTcwZGY4ZWI2MDRhNDIxNzMzNzNkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.h5i41lSwfMvg1BsUTrlbEgZC8YJD-EAu0Tj4xQI_BhU)
La función **closing** se encarga de preguntar al usuario si desea guardar el estado de la sesión cuando se va a cerrar el programa (y hay datos visualizados en la interfaz). Si la respuesta es afirmativa también se encarga de guardar estos datos en formato JSON haciendo uso de una de las funciones del **SaveSystem**, del que hablaremos más adelante.

El método que se encarga de interceptar la señal de cierre del programa se encuentra en la clase HomeApplication.

![6](https://github.com/user-attachments/assets/39819663-cccc-42d4-bb2d-657517622c64)

La función principal es **buscarObra** ya que es la encargada de preparar todos los datos metidos por el usuario. Una vez comprueba que todo está correcto llama a la clase **ApiRequester** para que haga la petición a la Api, que devuelve todos los datos necesarios.

[![image](https://private-user-images.githubusercontent.com/119294634/378940989-b805740e-02df-4cec-bc05-5ddeecae6565.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwOTg5LWI4MDU3NDBlLTAyZGYtNGNlYy1iYzA1LTVkZGVlY2FlNjU2NS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01ZWVjMDVmZTc2ZmVhMzhkMThkN2ZhYmU1N2ViN2RiNmFmODlhY2U1YmJhYzgyYzgyZGM3Yzg5MjU4OGFlNWY1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.462KWy33qnGBj2K3vm9zEWLc0wj--faQe0t0lu7a27o)](https://private-user-images.githubusercontent.com/119294634/378940989-b805740e-02df-4cec-bc05-5ddeecae6565.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQwOTg5LWI4MDU3NDBlLTAyZGYtNGNlYy1iYzA1LTVkZGVlY2FlNjU2NS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01ZWVjMDVmZTc2ZmVhMzhkMThkN2ZhYmU1N2ViN2RiNmFmODlhY2U1YmJhYzgyYzgyZGM3Yzg5MjU4OGFlNWY1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.462KWy33qnGBj2K3vm9zEWLc0wj--faQe0t0lu7a27o)

Una vez la función anterior recupere los datos pasa a llamar a **cargarDatos**, que además de cargar  los datos de mayor interés en la vista, también se encarga de limpiarla en caso de que no se haya encontrado ninguna obra de arte con las características especificadas.

[![image](https://private-user-images.githubusercontent.com/119294634/378941085-63564408-f290-4d3b-a4f2-fe0146ec13b6.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQxMDg1LTYzNTY0NDA4LWYyOTAtNGQzYi1hNGYyLWZlMDE0NmVjMTNiNi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yNTU5MjVkZWQyYTU2YTMwY2MyM2JhNTA2OGZiYTBmNDBmMWZmZjA1ZDA4NWM4ZGE3NWY5NTBjMDVkNzJmNjI4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9._rhnnb5fGyEBESZic7KaSsOG4NnjOBwIoescDBGUP-M)](https://private-user-images.githubusercontent.com/119294634/378941085-63564408-f290-4d3b-a4f2-fe0146ec13b6.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQxMDg1LTYzNTY0NDA4LWYyOTAtNGQzYi1hNGYyLWZlMDE0NmVjMTNiNi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yNTU5MjVkZWQyYTU2YTMwY2MyM2JhNTA2OGZiYTBmNDBmMWZmZjA1ZDA4NWM4ZGE3NWY5NTBjMDVkNzJmNjI4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9._rhnnb5fGyEBESZic7KaSsOG4NnjOBwIoescDBGUP-M)

A mayores, **buscarObraAleatoria** buscará una obra aleatoria por si el usuario quiere ver obras de arte sin tener claro algún parámetro de búsqueda.

[![image](https://private-user-images.githubusercontent.com/119294634/378941149-4dc1fe0f-d954-4555-9a87-58df1805e1d3.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQxMTQ5LTRkYzFmZTBmLWQ5NTQtNDU1NS05YTg3LTU4ZGYxODA1ZTFkMy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mNDA0NDllNGJjMjQxNWI0NzYzNDExYTI2YTBkZmQzNWQwNzEzYzk3YjU0ODFiMzhmOTBhMzcxYmI4ZWJhMTgxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.Z7sHMearOddNs4rDfRIz-FJ18v-9IpcuVSrfPbABsfM)](https://private-user-images.githubusercontent.com/119294634/378941149-4dc1fe0f-d954-4555-9a87-58df1805e1d3.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjk2MjAzMTQsIm5iZiI6MTcyOTYyMDAxNCwicGF0aCI6Ii8xMTkyOTQ2MzQvMzc4OTQxMTQ5LTRkYzFmZTBmLWQ5NTQtNDU1NS05YTg3LTU4ZGYxODA1ZTFkMy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDIyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAyMlQxODAwMTRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mNDA0NDllNGJjMjQxNWI0NzYzNDExYTI2YTBkZmQzNWQwNzEzYzk3YjU0ODFiMzhmOTBhMzcxYmI4ZWJhMTgxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.Z7sHMearOddNs4rDfRIz-FJ18v-9IpcuVSrfPbABsfM)
Por último está la función **exportData**, que se encarga de manejar el FileChooser y comprobar la existencia de datos que guardar. Tanto el formato como el nombre del archivo y los datos se pasan al SaveSystem, que es quien manejará cada casuística.

![13](https://github.com/user-attachments/assets/d9b3cbc4-2ea2-4557-8682-2345f6772b91)


## HomeAplication y SaveSystem

**HomeAplication** es la calse desde donde se ejecuta la aplicación, y debe contar siempre con el método **start** donde se configuran el stage y la escena.  En nuestro caso usamos dos atributos de la clase para guardar el estado de estos dos elementos.

En la configuración del stage es donde se especifica también que hacer cuando se cierre, vinculado a la función **closing** de la que habíamos hablado anteriormente.

![14](https://github.com/user-attachments/assets/216b1f61-ad0e-4774-b3be-328ed5e0c0a5)


También añadimos un método **setRoot**, que se encarga de manejar el cambio de escenas, configurando y cargando la nueva escena en el stage. 

![15](https://github.com/user-attachments/assets/1722bef0-52e5-48c3-a5e9-19aaa90c91fe)


**SaveSystem** es la clase que se encarga de transformar y guardar los datos a distintos formatos. Para ello cuenta con la clase **save** que recupera con **getFileExtension** el tipo de archivo y llama a una función diferente por cada formato.

![16](https://github.com/user-attachments/assets/9c99ef26-101a-428d-9b37-8e7cf4795660)

![17](https://github.com/user-attachments/assets/db6878c3-cbce-4a72-b883-8ce185bdadca)

Las funciones de cada formato son muy similares entre ellas, utilizando un la función de escritura y conversión que más se adapte a cada una.

![18](https://github.com/user-attachments/assets/1663e9fb-a45c-4215-8557-33328421203c)



# 3. Manual de usuario con juego de pruebas
Esta aplicación está  diseñada para descubrir nuevas obras de arte dentro de la colección del Museo Metropolitano de Arte, no para consultar obras especificas, aunque el usuario podrá acotar los resultados para encontrar una pieza que coincida con sus intereses. 

Al iniciar la aplicación lo primero que nos aparecerá es el login. Podremos acceder con el usuario "admin" y la contraseña "admin123". 

![image](https://github.com/user-attachments/assets/73c5301f-22ee-4ad6-a799-b801e590d5b8)



Tras acceder el usuario deberá proporcionar una palabra clave (obligatoria), y otros parámetros optativos para filtrar la búsqueda según departamento o buscando solo entre las obras populares. Debemos tener en cuenta que no todas las obras del MET cuentan con imágenes de dominio público, en cuyo caso se indicará que no hay imágenes disponibles. También existe la posibilidad de pedir una obra aleatoria, que siempre devolverá una con imagen.

![image](https://github.com/user-attachments/assets/ae8a0c3c-6990-484a-b66e-22dc16f5dfb5)


Una vez realizada la búsqueda el resultado puede tardar unos pocos segundos en aparecer, dependiendo de la cantidad de obras que coincidan con los criterios. Esta espera será más breve cuanto más se use la aplicación gracias al uso del cache. 
Cuando todo este listo podremos ver una imagen (si existe una de dominio público) de la obra, su título, autor, año de creación y medio.
![image](https://github.com/user-attachments/assets/e0788cdf-c2ec-4696-8f71-2de3beff48df)

Estos datos se pueden exportar en diferentes formatos para distintos usos. El botón de exportar se encuentra en la barra de tareas, en la esquina superior derecha. En estos momentos la aplicación permite:
- **La exportación de los datos resumidos:** donde los datos mostrados en la interfaz se guardan en un archivo de texto. Es un formato más sencillo para un uso casual.
- **La exportación de los datos completos:** los datos completos de la obra se pueden guardar en formato json, xml y binario. Esto incluye toda la información proporcionada por la API.
# 4. Explicación del reparto de las tareas entre ambos integrantes

#### Iago
- Login
- Cambio de escenas
- Encriptado de contraseña
- Consulta según parametros
- Muestra de datos

#### Manuel
- API request
- Implementación de Caché
- Generación e implementación de clases

#### Samuel
- Estilos
- Interfaz
- Exportación
- Guardar y cargar última sesión

#### Entre todos
- Documentación
- Creación del Jar
  
# 5. Extras realizados
- **Almacenaje de datos**: El usuario tiene la opción de exportar el objeto de su búsqueda en archivos .json, .txt, .xml o .bin
  
- **Control de errores**:
	- Nos aseguramos que el usuario introdujo el parámetro obligatorio para realizar una consulta
	- Si la consulta no devuelve ningún resultado se avisa al usuario de manera adecuada
	- Capturamos el error de la API de tener ids válidos sin ningún objeto asociado y lo manejamos adecuadamente
	- Si el objeto obtenido no cuenta con alguno de los atributos que mostramos en la vista lo manejamos con valores por defecto, ej: un objeto sin imagen disponible mostramos una imagén avisando al usuario que no hay imagen disponible
   
- **Uso offline**: Cada vez que obtenemos una nuevo objeto lo guardamos en nuestra cache, también hacemos lo mismo con el total de obras disponibles y los departamentos; por lo tanto es posible para nuestra app funcionar offline
  
- **Almacenamiento del último estado de la aplicación**: Al cerrar la aplicación se le muestra un mensaje al usuario preguntándole si quiere guardar la consulta actual, con sus parámetros y objeto devuelto en un archivo .json. La siguiente vez que se inicié la sesión y un archivo de save existe se le preguntará al usuario si desea restaurar la última sesión
  
- **Adición de un login**: El usuario tendrá que logearse a una cuenta almacenada en un fichero properties que almacena las credenciales (nombre de usuario y contraseña). En la vista de acceso, el usuario ingresará sus datos, los cuales se validarán comparándolos con los almacenados previamente. Para asegurar las credenciales, se utiliza el algoritmo de cifrado SHA-256, que garantiza que la contraseña no sea almacenada ni transmitida en texto plano 

# 6. Propuestas de mejora: nuevas opciones, control de errores ...
- **Paginación**: En lugar de mostrar únicamente el primer resultado de una consulta, se podría implementar un sistema de navegación con flechas que permita al usuario desplazarse entre los distintos resultados aportando mayor variedad y además permitir ordenarlos por diferentes campos como el nombre de la obra o la fecha de creación.
- **Más opciones de filtrado**
- **Opción para limpiar la cache**
- **QoL Changes**: Opción para borrar todos los campos del formulario 
- **Importar**: Además de la opción de exportar los datos, sería útil implementar una funcionalidad que permita importarlos. Serviría para la reutilización de información previamente exportada.
  
# 7. Conclusiones, opinión del trabajo realizado, dedicación temporal y cualificación estimada
Al principio costo hacerse al modelo de trabajo en grupo por GitHub y congigurar el JavaFX. Sin embargo, una vez superados esos obstáculos avanzamos bastante en poco tiempo.

Para futuros proyectos hemos aprendido a dedicarle más tiempo a la planificación y organización.

Con respecto a la API encontramos, a medida que trabajábamos con ella, diversos errores que condicionaron el funcionamento de nuestra app; por ejemplo no se pueden realizar consultas filtradas por autor a la API, existen ids sin objetos asociados, la estructura de los objeetos no es consistente...

Con todo estimamos que las horas trabajadas han sido al rededor de 14, de las cuales 10 han sido en horario de clase.

Creemos que nos quedó un trabajo muy bueno y esperamos una nota acorde (entre un 9 - 10) :)
