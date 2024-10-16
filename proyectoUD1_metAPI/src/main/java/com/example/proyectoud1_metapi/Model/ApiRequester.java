package com.example.proyectoud1_metapi.Model;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class ApiRequester {
    private String baseURL= "https://collectionapi.metmuseum.org/public/collection/v1/objects";

    public ApiRequester() {}

    public ArtPiece getRandomArtPiece() {
        return new ArtPiece();
    }

    public int getTotalNumberOfArtPieces() {
        int total = -1;
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(baseURL))
                    .GET()
                    .build();
            String response = request.toString();
            System.out.println(request);
        } catch (URISyntaxException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }
}
