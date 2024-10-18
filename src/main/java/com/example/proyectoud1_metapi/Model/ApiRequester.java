package com.example.proyectoud1_metapi.Model;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Random;

public class ApiRequester {
    private String baseURL= "https://collectionapi.metmuseum.org/public/collection/v1/objects";
    private String urlSearch= "https://collectionapi.metmuseum.org/public/collection/v1/search";
    private String urlDepartaments= "https://collectionapi.metmuseum.org/public/collection/v1/departments";

    public ApiRequester() {}

    public ArtPiece getRandomArtPiece() {
        ArtPiece artPiece = new ArtPiece();
        int id = getRandomId();
        try {
            URL url = new URL(baseURL.concat("/").concat(String.valueOf(id)));
            ObjectMapper mapper = new ObjectMapper();
            artPiece = mapper.readValue(url, ArtPiece.class);
            if (artPiece.getPrimaryImage() == null || artPiece.getPrimaryImage().trim().isEmpty()) {
                artPiece = getRandomArtPiece();
            }
        } catch (MalformedURLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            artPiece = getRandomArtPiece();
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
    
    public int getTotalNumberOfArtPieces2() {
        int total = -1;
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            ObjectMapper mapper = new ObjectMapper();
            total = mapper.readTree(response.body()).get("total").asInt();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }




    public ArtPiece getSearchArtPiece(String etiqueta) throws MalformedURLException {
        ArtPiece artPiece = new ArtPiece();
        try{

            ObjectMapper mapper = new ObjectMapper();
            ResponseList response = mapper.readValue(new URL(urlSearch.concat("?q=").concat(etiqueta)), ResponseList.class);

            if (response.getObjectIDs() != null && !response.getObjectIDs().isEmpty()) {
                int firstId = response.getObjectIDs().get(0);
                System.out.println(firstId);
                URL artPieceUrl = new URL(baseURL.concat("/").concat(String.valueOf(firstId)));

                artPiece = mapper.readValue(artPieceUrl, ArtPiece.class);
                System.out.println(artPiece);
            }else{
                System.out.println("No se encontró ninguna obra para la etiqueta: " + etiqueta);
                return null;
            }

        } catch (MalformedURLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return artPiece;

    }
}
