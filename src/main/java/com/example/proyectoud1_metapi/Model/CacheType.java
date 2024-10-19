package com.example.proyectoud1_metapi.Model;

public enum CacheType {
    STORAGE("/objects.bin"),
    BLACKLIST("/blacklist.bin");

    public final String NAME;

    private CacheType(String name) {
        this.NAME = name;
    }
}
