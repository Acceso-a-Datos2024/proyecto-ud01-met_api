package com.example.proyectoud1_metapi.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Random;

/*Clase para realizar consultas a la API*/
public class ApiRequester {
    private String baseURL= "https://collectionapi.metmuseum.org/public/collection/v1/objects";
    private String urlSearch= "https://collectionapi.metmuseum.org/public/collection/v1/search";
    private String urlDepartaments= "https://collectionapi.metmuseum.org/public/collection/v1/departments";
    private Cache objectsCache = new Cache(CacheType.STORAGE.NAME);
    private Cache blacklistCache = new Cache(CacheType.BLACKLIST.NAME);

    public ApiRequester() {}

    public ArtPiece getRandomArtPiece() {
        ArtPiece artPiece = new ArtPiece();
        int id = getRandomId();
        ObjectMapper mapper = new ObjectMapper();
        
        if (this.objectsCache.existsId(Integer.toString(id))) { //Comprobamos si el id está en nuestra cache
            try {
                artPiece = mapper.readValue(this.objectsCache.getCacheArchive().get(Integer.toString(id)), ArtPiece.class);
                System.out.println("Objeto leído con éxito: " + artPiece.getObjectID());
                return artPiece;    //Devolvemos el objeto desde la cache
            } catch (JsonProcessingException ex){
                System.out.println("Error lectura de caché objetos");
            }
        }
        
        if (this.blacklistCache.existsId(Integer.toString(id))) {   //Comporbamos si el id está en nuestra cache de ids no válidos
            System.out.println(id + " en la blacklist");
            return getRandomArtPiece(); //Realizamos una búsqueda con otro id
        } else {
            try {
                URL url = new URL(baseURL.concat("/").concat(String.valueOf(id)));  //Construimos la url de nuestra request
                artPiece = mapper.readValue(url, ArtPiece.class);   //Mapeamos la response de la API a la clase ArtPiece
            
                JsonNode root = mapper.readTree(url);
                this.objectsCache.add(root.findValue("objectID").asText(), root.toString());    //Guardamos el objeto en la cache con su id como clave
                this.objectsCache.save();
            
                if (artPiece.getPrimaryImage() == null || artPiece.getPrimaryImage().trim().isEmpty()) {    //Comprobamos que la obra tiene o no una imagen disponible
                    System.out.println("Error imagen nula");
                    artPiece = getRandomArtPiece(); //REalizamos otra búsqueda
                }
            
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {   //Error de lectura porque el id no tiene un objeto asociado
                System.out.println("Error id vacío: " + e.getMessage());
                this.blacklistCache.add(Integer.toString(id), Integer.toString(id));    //Alamcenamos el id en nuestra cache blacklist
                this.blacklistCache.save();
                artPiece = getRandomArtPiece(); //Realizamos otra búsqueda
            }
            return artPiece;
        }
    }
    
    public int getRandomId() {
        Random rand = new Random();
        return rand.nextInt(1, getTotalNumberOfArtPieces() + 1);
    }

    public int getTotalNumberOfArtPieces() {
        /*Antes de nada intentamos leer de la cache un archivo total.txt en el
        que estará almacenado el total de objetos disponibles, si lo conseguimos
        leer sacamos el total de ahí y nos ahorramos la siguiente consulta*/
        try (BufferedReader lector = new BufferedReader(new FileReader("src/main/resources/cache/total.txt"));) {
            System.out.println("Total leído de caché");
            return Integer.parseInt(lector.readLine());
        } catch (IOException ex) {
            int total = -1;
            try {
                ObjectMapper mapper = new ObjectMapper();
                
                //Realizamos una request para todos los objetos disponibles en la API
                //Esto nos devolvera un JSON con un campo total (el total de objetos disponible) y una lista con todos los ids
                //Mapeamos la response a nuestra clase ResponseList
                ResponseList response = mapper.readValue(new URL(baseURL), ResponseList.class);
                
                //Obtenemos el total
                total = response.getTotal();
                
                try (BufferedWriter escritor = new BufferedWriter(new FileWriter("src/main/resources/cache/total.txt"));) {
                    escritor.write(String.valueOf(total));  //Lo guardamos en cache, esta vez en un archivo de texto
                    System.out.println("Total guardado con éxtio en caché");
                }
                
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Total leído de api");
            return total;
        }
    }
    
    
    /*public int getTotalNumberOfArtPieces2() {
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
    }*/




    public ArtPiece getSearchArtPiece(String etiqueta , Integer departmentId) throws MalformedURLException {
        ArtPiece artPiece = new ArtPiece();
        try{
            
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder searchUrl = new StringBuilder(urlSearch.concat("?q=").concat(etiqueta));
            if (departmentId != null) {
                searchUrl.append("&departmentId=").append(departmentId);
            }
            ResponseList response = mapper.readValue(new URL(searchUrl.toString()), ResponseList.class);


            if (response.getObjectIDs() != null && !response.getObjectIDs().isEmpty()) {
                int firstId = response.getObjectIDs().get(0);
                System.out.println(firstId);
                
                if (this.objectsCache.existsId(String.valueOf(firstId))) {
                    artPiece = mapper.readValue(this.objectsCache.getCacheArchive().get(Integer.toString(firstId)), ArtPiece.class);
                    System.out.println("Objeto leído con éxito de la caché: " + artPiece.getObjectID());
                    return artPiece;
                }
                
                URL artPieceUrl = new URL(baseURL.concat("/").concat(String.valueOf(firstId)));
                
                JsonNode root = mapper.readTree(artPieceUrl);
                this.objectsCache.add(root.findValue("objectID").asText(), root.toString());
                this.objectsCache.save();

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
