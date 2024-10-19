package com.example.proyectoud1_metapi.Model;

import java.io.*;
import java.util.HashMap;

/*Clase para facilitar el manejo de los archivos de la cache que guardan
HashMaps*/
public class Cache {
    private HashMap<String, String> cacheMap;
    private String path = "src/main/resources/cache";

    public Cache(String cacheType) {
        try {
            this.path = this.path.concat(cacheType);    //A partir del CacheType que recibimos, el archivo que queremos leer, obtenemos la ruta
            ObjectInputStream lector = new ObjectInputStream(new FileInputStream(path));    //Lector de nuestro archivo en cache
            this.cacheMap = (HashMap<String, String>) lector.readObject();  //En nuestro archivo en cache está almacenado un HashMap
            lector.close();
        } catch (IOException | ClassNotFoundException e) {
            this.cacheMap = new HashMap<>();    //Si salta una exceptión porque el archivo está vacío inicializamos nuestro HashMap vacío
            System.out.println("Caché vacío en: " + path);
        }

    }

    public HashMap<String, String> getCacheArchive() {
        return cacheMap;
    }

    public void setCacheArchive(HashMap<String, String> cacheArchive) {
        this.cacheMap = cacheArchive;
    }

    public void add(String id, String value) {
        this.cacheMap.put(id, value);
        System.out.println("Añadido con éxito: " + value);
    }

    public void save() {
        try {
            ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(this.path));  //Stream para escribir nuestro HashMap en el archivo de cache correspondiente
            escritor.writeObject(this.cacheMap);    //Guardamos nuestro archivo de cache actualizado
            escritor.close();
            System.out.println("Guardado con éxito");
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo caché de " + this.path);
        }
    }

    public boolean existsId(String id) {
        return cacheMap.containsKey(id);
    }
    
    public void show() {
        this.cacheMap.forEach((k, v) -> {
            System.out.println(k + ": " + v);});
    }
}
