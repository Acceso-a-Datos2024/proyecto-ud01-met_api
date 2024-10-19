package com.example.proyectoud1_metapi.Model;

import java.io.*;
import java.util.HashMap;

public class Cache {
    private HashMap<String, String> cacheMap;
    private String path = "src/main/resources/cache";

    public Cache(String cacheType) {
        try {
            this.path = this.path.concat(cacheType);
            ObjectInputStream lector = new ObjectInputStream(new FileInputStream(path));
            this.cacheMap = (HashMap<String, String>) lector.readObject();
            lector.close();
        } catch (IOException | ClassNotFoundException e) {
            this.cacheMap = new HashMap<>();
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
            ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(this.path));
            escritor.writeObject(this.cacheMap);
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
