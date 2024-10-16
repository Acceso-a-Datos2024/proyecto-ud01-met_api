package com.example.proyectoud1_metapi.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class ApiRequester {
    private String baseURL= "https://collectionapi.metmuseum.org/public/collection/v1/objects";

    public ApiRequester() {}

    public ArtPiece getRandomArtPiece() {
        return new ArtPiece();
    }

    public int getTotalNumberOfArtPieces() {
        int total = -1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ResponseList response = mapper.readValue(new URL(baseURL), ResponseList.class);
            response.getObjectIDs().stream().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }
}
