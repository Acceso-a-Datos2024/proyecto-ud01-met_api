package com.example.proyectoud1_metapi.Model;

public class test {
    public static void main(String args[])  //static method
    {
        ApiRequester r = new ApiRequester();

        System.out.println(r.getRandomArtPiece().getPrimaryImage());
    }
}
