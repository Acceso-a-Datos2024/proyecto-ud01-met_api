[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/JkDJUAAN)

# Index
- [1. Introducción al supuesto con descripción de la API y capturas POSTMAN](#1-introducción-al-supuesto-con-descripción-de-la-api-y-capturas-postman)
- [2. Manual técnico para desarrolladores](#2-manual-técnico-para-desarrolladores-puede-ser-mediante-capturas-con-explicaciones-o-vídeo-tutorial) 
- [3. Manual de usuario con juego de pruebas](#3-manual-de-usuario-con-juego-de-pruebas-puede-ser-mediante-capturas-con-explicaciones-o-vídeo-tutorial) 
- [4. Explicación del reparto de las tareas](#4-explicación-del-reparto-de-las-tareas-entre-ambos-integrantes) 
- [5. Extras realizados](#5-extras-realizados-solo-si-habéis-codificado-alguno) 
- [6. Propuestas de mejora](#6-propuestas-de-mejora-nuevas-opciones-control-de-errores-) 
- [7. Conclusiones y opinión](#7-conclusiones-y-opinión-del-trabajo-realizado-incluid-dedicación-temporal-y-cualificación-estimada)

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


# 2. Manual  técnico para desarrolladores (puede ser mediante capturas con explicaciones o vídeo tutorial*)

Esta aplicación está  diseñada para descubrir nuevas obras de arte dentro de la colección del Museo Metropolitano de Arte, no para consultar obras especificas, aunque el usuario podrá acotar los resultados para encontrar una pieza que coincida con sus intereses. 

Al iniciar la aplicación lo primero que nos aparecerá es el login. Podremos acceder con el usuario "admin" y la contraseña "admin123". 
==añadir imagen==

Tras acceder el usuario deberá proporcionar una palabra clave (obligatoria), y otros parámetros optativos para filtrar la búsqueda según departamento o buscando solo entre las obras populares. Debemos tener en cuenta que no todas las obras del MET cuentan con imágenes de dominio público, en cuyo caso se indicará que no hay imágenes disponibles. También existe la posibilidad de pedir una obra aleatoria, que siempre devolverá una con imagen.

==añadir imagen==

Una vez realizada la búsqueda el resultado puede tardar unos pocos segundos en aparecer, dependiendo de la cantidad de obras que coincidan con los criterios. Esta espera será más breve cuanto más se use la aplicación gracias al uso del cache. 
Cuando todo este listo podremos ver una imagen (si existe una de dominio público) de la obra, su título, autor, año de creación y medio.
==añadir image==

Estos datos se pueden exportar en diferentes formatos para distintos usos. El botón de exportar se encuentra en la barra de tareas, en la esquina superior derecha. En estos momentos la aplicación permite:
- **La exportación de los datos resumidos:** donde los datos mostrados en la interfaz se guardan en un archivo de texto. Es un formato más sencillo para un uso casual.
- **La exportación de los datos completos:** los datos completos de la obra se pueden guardar en formato json, xml y binario. Esto incluye toda la información proporcionada por la API.
# 3. Manual de usuario con juego de pruebas (puede ser mediante capturas con explicaciones o vídeo tutorial*)
# 4. Explicación del reparto de las tareas entre ambos integrantes.
# 5. Extras realizados (solo si habéis codificado alguno)
# 6. Propuestas de mejora: nuevas opciones, control de errores ...
# 7. Conclusiones y opinión del trabajo realizado. Incluid dedicación temporal y cualificación estimada.

