[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/JkDJUAAN)

# Index
- [1. Introducción al supuesto con descripción de la API y capturas POSTMAN]()
- [2. Manual técnico para desarrolladores]()
- [3. Manual de usuario con juego de pruebas]() 
- [4. Explicación del reparto de las tareas]() 
- [5. Extras realizados]() 
- [6. Propuestas de mejora]() 
- [7. Conclusiones y opinión]()

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


# 2. Manual técnico para desarrolladores (puede ser mediante capturas con explicaciones o vídeo tutorial*)

## Model
En la carpeta Model encontramos todas las clases que nos servirán para la realización de consultas a la API, el manejo de sus respuestas a partir de objetos a las que mapearemos dichas respuestas y la administración de la cache de nuestra aplicación.

### Clases para el manejo de objetos:
- **ArtPiece**: Representa una obra de arte obtenida por consulta desde la API. Al realizar una petición por un id específico la API nos devuelve un JSON con las propiedades de la obra correspondiente, esta es la clase a la que se mapea ese JSON. Cuenta con un constructor vacío y otro con parámetros (este no se usa ya que no realizamos ninguna petición POST), métodos setters y getters de todos sus atributos (los más utilizados son los getters del id, imagen, título, descripción y autor) además de un método toString. Autogenerada con jsonschema2pojo.
- **Deparments**: La API también cuenta con una serie de JSONs que representan los departamentos del museo. Esta es la clase a la que se mapea un departamento. Al igual que ArtPiece tiene dos constructores uno vacío y otro con parámetros, setters y getters de sus atributos y un método toString. Esta clase cuenta solo con dos atributos: id y nombre, ambos se usan a la hora de construir el desplegable de la interfaz gráfica que permite al usuario filtrar por departamento. El método toString solo devuelve el nombre del departamento pero se tiene para facilidad de uso y no tener que llamar el getter del nombre cuando queramos mostrar por pantalla (en el desplegable) su nombre.
- **ElementMeasurement**: Representa una de las propiedades de ArtPiece que a su vez es otro objeto. Este objeto contiene el diámetro de la obra y un HashMap para listar otras propiedades adicionales. No se usa mucho. Al ser autogenerada con jsonschema2pojo cuenta con un constructor vacío y otro con parámetros, getters y setters de todos sus atributos y un método toString.
- **Measurements**: Otra clase que representa un propiedad de ArtPiece que es a su vez otro objeto. Contiene el nombre y una descripción de la obra además de un objeto de la clase ElementMeasurement y otro HashMap para propiedades adicionales de la obra. No se usa mucho. Al ser autogenerada con jsonschema2pojo cuenta con un constructor vacío y otro con parámetros, getters y setters de todos sus atributos y un método toString.
- **Response**: Clase que utilizamos para el mapeo de la respuesta de la API cuando le consultamos por todos los departamentos del museo. Contienen simplemente una lista de objetos departamento, cada uno con su id y nombre.
- **ResponseList**: Al realizar una consulta que devuelva varias obras la API devuelve un JSON con un campo total, que indica el total de resultados encontrados, y otro campo con una lista de ids de todos los objetos encontrados. Para obtener fácilmente esa lista de ids se genero esta clase con jsonschema2pojo para mapear la respuesta de la API a un objeto y con un método get obtener la lista. Cuenta con un constructor vacío y con parámetros, setters y getters para sus dos atributos (total y la lista de ids) y un método toString.
- **Tag**: Otra clase que representa una propiedad de ArtPiece que es a su vez otro objeto. Contiene etiquetas que dan información sobre la obra, URLs relacionadas y un HashMap para propiedades adicionales. No se usa mucho. Al ser autogenerada con jsonschema2pojo cuenta con un constructor vacío y otro con parámetros, getters y setters de todos sus atributos y un método toString.

### Clases para el manejo de la cache:
#### CacheType:
Enum que representa los distintos tipos de cache disponibles, es decir los dos archivos desde los que podemos leer o a los que podemos describir.
Contamos con dos archivos diferentes porque hay ids de obras en la API que no se corresponden a ninguna obra por lo que a parte de un archivo para los ids de objetos que sí existen y dichos objetos (STORAGE), tenemos otro con un HashMap de ids no válidos (BLACKLIST).

![imagen](https://github.com/user-attachments/assets/7a95178d-f82c-49da-83a7-1dbd34588438)


#### Cache:
En una carpeta del proyecto llamada cache almacenamos archivos binarios que contienen un HashMap con objetos ArtPiece (la clave de cada objeto es su id y el valor es su string en formato JSON) que ya nos ha devuelto la API en consultas anteriores. Para manejar la cache de forma más sencilla desde el ApiRequester se ha creado esta clase que encapsula todas las operaciones de lectura, escritura y guardado de los archivos de la cache.
Sus atributos son dos:
- **cacheMap**: El HashMap que obtendremos tras leer el archivo correspondiente de la cache.
- **path**: String de la ruta de la carpeta cache del proyecto.
El constructor de un objeto cache pide un parámetro cacheType, un string que obtendremos a través de nuestra enum CacheType para indicar con cual de los dos archivos de cache va a trabajar este objeto Cache. Durante el proceso de construcción se concatena el cacheType al path del objeto Cache para luego leer el HashMap existente en el archivo y inicializar el atributo cacheMap de nuestro objeto con el HashMap leído desde el archivo.

![imagen](https://github.com/user-attachments/assets/dd5333ce-95eb-4008-b9c9-fe2fb68ec642)


Cuenta con métodos setter y getter de cacheMap además de uno que muestra por pantalla el contenido del mismo por si es necesario realizar pruebas. Pero los métodos más usados son los siguientes:
- **add**: Al pasarle el id de un objeto (en string) y el objeto en sí (en string formato JSON) los añade al cacheMap de nuestro objeto cache.
  
  ![imagen](https://github.com/user-attachments/assets/6748ca2b-ee03-4e93-90f1-6d3d443cbc68)
  

- **save**: Guarda en el archivo de cache correspondiente el cacheMap actual de nuestro objeto; no añade al archivo sino que sobrescribe ya que siempre estamos trabajando con una copia del HashMap de la cache ya que en el constructor inicializamos el cacheMap al HashMap leído del archivo.
  
  ![imagen](https://github.com/user-attachments/assets/ceac5e31-3548-4222-809f-a3182f735195)
  

- **existsId**: Comprueba si el id que le pasamos ya existe en el HashMap de nuestro archivo de cache correspondiente.
  
![imagen](https://github.com/user-attachments/assets/dde0e8d1-d606-47a7-89e3-c7843e4d57c4)


### Clase para la realización de consultas a la API:
#### ApiRequester:
Es la clase que se encarga de construir las URLs de consulta a la API, recibir sus respuestas y mapearlas a las clases correspondientes, enviarle los datos pertinentes a los controladores y realizar las llamadas para leer o actualizar la cache.
Cuenta con tres atributos string que sirven para la construcción de URLs de consulta a la API:
- baseURL: Para la búsqueda de objetos por id.
- urlSearch: Para la realización de búsqueda por parámetros.
- urlDepartments: Para obtener una Response de los departamentos del museo.
También tiene un atributo de tipo Cache para cada uno de los archivos de nuestra cache:
- objectsCache: Para los ids relacionados con objetos que sí existen en la API.
- blacklistCache: Para los ids relacionados con objetos que no existen en la API.
      
El único constructor de la clase está vacío porque todos los atributos se usan con sus valores por defecto.

El método **getTotalNumberOfArtPieces** realiza una consulta a la API para que devuelva una ResponseList con todos los objetos disponibles. Debido a que lo único que nos interesa el el campo total y al gran número de ids disponibles (lo cual ralentiza la aplicación) una vez obtenido el número total se guarda en un archivo en la cache para no tener que interpretar una respuesta tan grande en subsecuentes llamadas.
![image](https://github.com/user-attachments/assets/4e24ac4e-c621-4f3b-aff7-31cac3d65eb5)


El método **getRandomId** utiliza el número total de ids disponibles para generar uno aleatorio.
![image](https://github.com/user-attachments/assets/28bf43ac-d0db-4f05-be08-f9783760e15a)


El método **getRandomArtPiece** se usa para obtener un objeto aleatorio de la API a partir de un id generado aleatoriamente. Sin embargo como en la API existen ids que no están vinculados a ningún objeto tuvieron que implementarse una serie de contingencias y la cache blacklist para evitar posibles errores.
Primero el método comprueba si el id aleatorio ya está almacenado en una de las dos caches, sí se encuentra en la cache de objetos válidos lo leé de ahí y lo devuelve, sí se encuentra en la cache de ids no válidos se vuelve a llamar a sí mismo. Si no está en ninguna de la cache construimos nuestra URL de petición concatenando baseURL con nuestro id aleatorio y mapeamos la respuesta a un objeto ArtPiece. Si el mapea resultó erróneo es que el id no está vínculado a un objeto así que lo almacenamos en nuestra blacklist y volvemos a llamar al método. Si el mapeo se realizó con éxito guardamos el id y el objeto en la cache de objetos válidos pero realizamos otra comprobación: si el objeto obtenido tiene una imagen disponible para ser mostrada. Si no, volvemos a generar otro id para realizar otra petición, si el objeto cuenta con una imagen disponible devolvemos nuestro objeto ArtPiece que será usado por el un controlador para actualizar la vista correspondiente.
![image](https://github.com/user-attachments/assets/88a0f6fe-3cd6-49ff-a7a0-93826c664e68)


El método **searchGetArtPiece** realiza consultas a la API con los filtros de búsqueda introducidos por el usuario en la interfaz gráfica, comprobando cuales de los opcionales añadió. Primero construye la URL de petición concatenando los filtros de búsqueda introducidos por el usuario a nuestra urlSearch y la respuesta obtenido será mapeada a un objeto tipo ResponseList.
A partir de nuestro objeto ResponseList comprobamos si la búsqueda obtuvo una respuesta o no (si la lista de ids de nuestro objeto ResponseList está vacía o no). Si está vacía devolvemos null y el controlador ya se encargará de actualizar la vista de manera adecuada. Si obtuvo respuesta cogemos el primer id de la lista y comprobamos si está presente en nuestra cache de ids y objetos válidos. Si es así leemos su JSON de ahí, lo mapeamos a un objeto ArtPiece y se lo devolvemos al controlador. Si no está en cache construimos una URL de petición por ese id, concatenando el id a nuestra baseURL y realizamos la consulta a la API, guardamos el id y su objeto en la cache y mapeamos a un objeto ArtPiece la respuesta, que se lo devolveremos al controlador.

![image](https://github.com/user-attachments/assets/d4bf8b01-2703-41f0-9f32-0fd9eda87888)


##Jar

![image](https://github.com/user-attachments/assets/5221d32e-d1af-4710-91c6-39617470bb55)

![image](https://github.com/user-attachments/assets/6f15032d-5112-4e36-87d1-7418c6c7bde4)

![image](https://github.com/user-attachments/assets/40ebff83-5d5c-4cf1-9116-599ab44746d2)



# 3. Manual de usuario con juego de pruebas (puede ser mediante capturas con explicaciones o vídeo tutorial*)
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
- Consulta segun parametros
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
  
# 5. Extras realizados (solo si habéis codificado alguno)
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
  
# 7. Conclusiones y opinión del trabajo realizado. Incluid dedicación temporal y cualificación estimada
Al principio costo hacerse al modelo de trabajo en grupo por GitHub y congigurar el JavaFX. Sin embargo, una vez superados esos obstáculos avanzamos bastante en poco tiempo.

Creemos que nos quedó un trabajo muy bueno y esperamos una nota acorde (entre un 9 - 10) :)

Para futuros proyectos hemos aprendido a dedicarle más tiempo a la planificación y organización.

Con respecto a la API encontramos, a medida que trabajábamos con ella, diversos errores que condicionaron el funcionamento de nuestra app; por ejemplo no se pueden realizar consultas filtradas por autor a la API, existen ids sin objetos asociados, la estructura de los objeetos no es consistente...
