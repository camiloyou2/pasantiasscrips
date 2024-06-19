package com.example.demo.servicio;
import java.util.*;




public interface RepositorioPersona /*extends JpaRepository<Persona,String> */{



	//public List<PersonaDto> listar();



	 public List<Map<String, Object>> login_principal(); 

}
