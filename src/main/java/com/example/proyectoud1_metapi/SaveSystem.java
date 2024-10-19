package com.example.proyectoud1_metapi;

import com.example.proyectoud1_metapi.Model.ArtPiece;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveSystem {

     public static void save(File file, ArtPiece artPiece){
        String extension = getFileExtension(file);

        switch (extension){
            case "txt":
                saveAsText(file, artPiece);
                break;
            case "json":
                saveAsJson(file, artPiece);
                break;
            case "xml":
                saveAsXml(file, artPiece);
                break;
            default:
                System.out.println("Unsupported file format");
                break;
        }

    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";  // No extension found
    }

    private static void saveAsText(File file, ArtPiece artPiece){
         String content= artPiece.toShortString();

         try (PrintWriter writer = new PrintWriter(file)) {
             writer.println(content);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    private static void saveAsJson(File file, ArtPiece artPiece){
        // Usar ObjectMapper para pasar los objetos a formato JSON
        ObjectMapper mapper = new ObjectMapper();

        try {
            // writeValue pasa a JSON y luego guarda todo en el mismo paso
            mapper.writeValue(file, artPiece);
            System.out.println("Archivo JSON escrito con exito");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private  static  void  saveAsXml(File file, ArtPiece artPiece){
        XmlMapper xmlMapper = new XmlMapper();

        try {
            // writeValue pasa a XML y luego guarda todo en el mismo paso
            xmlMapper.writeValue(file, artPiece);
            System.out.println("Archivo XML escrito con exito");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
