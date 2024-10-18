package com.example.proyectoud1_metapi.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

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