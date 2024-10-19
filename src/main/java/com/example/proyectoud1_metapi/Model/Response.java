package com.example.proyectoud1_metapi.Model;

import java.util.List;

/*Clase para manejar la respuesta de la API al buscar todos los departementos
existentes, se usa principalmente para construir el desplegable de la interfaz
gráfica mediante el cual filtrar los resultados de la búsqueda por departamento*/
public class Response{
	private List<Departments> departments;

	public List<Departments> getDepartments(){
		return departments;
	}

	public void setDepartments(List<Departments> departments) {
		this.departments = departments;
	}



}