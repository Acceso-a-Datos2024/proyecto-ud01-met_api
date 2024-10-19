package com.example.proyectoud1_metapi.Model;

/*Enum para representar los distintos tipos de cache que utlizamos, una para los
ids con objetos asociados a ellos y otra para los ids que no tienen objetos
asociados*/
public enum CacheType {
    STORAGE("/objects.bin"),
    BLACKLIST("/blacklist.bin");

    public final String NAME;

    private CacheType(String name) {
        this.NAME = name;
    }
}
