package com.example.demo.servicio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.TipoSolicitud;

import com.example.demo.repositoio.PersonaSql;

@Service
public class personaService  implements RepositorioPersona  {


	@Autowired
	private PersonaSql personaSql;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	
	

	
	
	@Override
	public List<Map<String, Object>>  login_principal() {
		
		String  sql  = "SELECT  TIDG_ABREVIATURA,PEGE_DOCUMENTOIDENTIDAD, PEGE_FECHAEXPEDICION FROM PERSONANATURALGENERAL  INNER JOIN PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id";	
			List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		return null;
	

	}
	
}
