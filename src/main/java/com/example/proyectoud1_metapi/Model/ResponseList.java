package com.example.proyectoud1_metapi.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/*Muchas de las request a la API nos devuelven un JSON con un campo total que
indica la cantidad de objetos que se ajustan a la búsqueda y otro campo que es
una lista con todos los ids de dichos objetos. Se ha creado está clase para
facilitar el manejo de esas respuestas de la API*/
public class ResponseList{
	@JsonProperty("total")
	private int total;

	@JsonProperty("objectIDs")
	private List<Integer> objectIDs;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setObjectIDs(List<Integer> objectIDs){
		this.objectIDs = objectIDs;
	}

	public List<Integer> getObjectIDs(){
		return objectIDs;
	}
}