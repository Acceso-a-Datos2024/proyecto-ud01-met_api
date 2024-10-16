package com.example.proyectoud1_metapi.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class ApiRequester {
    private String baseURL= "https://collectionapi.metmuseum.org/public/collection/v1/objects";

    public ApiRequester() {}

    public ArtPiece getRandomArtPiece() {
        ArtPiece artPiece = new ArtPiece();
        int id = getRandomId();
        try {
            URL url = new URL(baseURL.concat("/").concat(String.valueOf(id)));
            ObjectMapper mapper = new ObjectMapper();
            artPiece = mapper.readValue(url, ArtPiece.class);
        } catch (MalformedURLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return artPiece;
    }
    
    public int getRandomId() {
        Random rand = new Random();
        return rand.nextInt(1, getTotalNumberOfArtPieces() + 1);
    }

    public int getTotalNumberOfArtPieces() {
        int total = -1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ResponseList response = mapper.readValue(new URL(baseURL), ResponseList.class);
            total = response.getTotal();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }
}
