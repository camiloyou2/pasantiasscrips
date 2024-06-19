package com.example.demo.repositoio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import com.example.demo.modelo.GeneradorImagenes;
import com.example.demo.modelo.ServiciosUniversidad;
import com.example.demo.modelo.TipoSolicitud;
import com.example.demo.modelo.VerificarCertificado;
import com.example.demo.modelo.datosBasicosPdf;
import com.example.demo.modelo.datos_verificacion;

import com.example.demo.modelo.Solicitud;
import com.example.demo.modelo.Solicitud_servicios;
import com.example.demo.modelo.TIPOESTADOS;
import com.example.demo.servicio.demoConfiguration;
import com.example.demo.modelo.login_principal;
import com.example.demo.modelo.soliservicios;
import com.example.demo.modelo.verificar_loging;

import com.example.demo.modelo.visualizar_solicitud;
import com.example.demo.modelo.datospersonales;
import com.example.demo.modelo.Concecutivo;
import com.example.demo.modelo.DatoBasicos;
import com.example.demo.modelo.Datosalario;
import com.example.demo.modelo.Empleado;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;


/**
 * Repository class for handling database operations related to persona data.
 */
@Repository
public class PersonaSql  {
	
	
	@Autowired
	@Qualifier("jdbcMaster")
	 private  JdbcTemplate firstJdbcTemplate;
	@Autowired
	@Qualifier("jdbcSlave")
	private JdbcTemplate secondJdbcTemplate;
	
	@Autowired
	@Qualifier("localsave")
	private JdbcTemplate localsave;



public String ultimosdigitoscedula = "" ;

/**
 * Retrieves personal data based on the given cedula.
 * @param cedula The cedula to search for.
 * @return A list of personal data.
 */

public static String obtenerUltimosTresDigitos(String cedula) {
    if (cedula == null || cedula.length() < 3) {
        throw new IllegalArgumentException("La cédula debe tener al menos 3 dígitos");
    }
    // Obtener los últimos tres dígitos de la cédula
    return cedula.substring(cedula.length() - 3);
}

public List<datospersonales>  datosPersonales(String cedula ) {
String  sql  =	"select   personageneralfoto.PEGE_ID , personageneralfoto.PGFO_ARCHIVO , \r\n"
		+ "personageneralfoto.PGFO_EXTENSION, personanaturalgeneral.peng_primernombre ,  personanaturalgeneral.peng_segundonombre , personanaturalgeneral.peng_primerapellido ,   personanaturalgeneral.peng_segundoapellido from personanaturalgeneral inner join personageneralfoto\r\n"
		+ "on  personanaturalgeneral.pege_id = personageneralfoto .pege_id\r\n"
		+ "inner join PERSONAGENERAL \r\n"
		+ "on  PERSONAGENERAL.pege_id = personageneralfoto .pege_id\r\n"
		+ "where personageneral.pege_documentoidentidad = '"+ cedula +"'"
		; 

	List<datospersonales> datos_personales = this.firstJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(datospersonales.class));
	return datos_personales;
}

/**
 * Executes the stored procedure PR_GLOBALUDEC_I_SOLICITUD to add a new Solicitud.
 * @param page_id The page ID.
 * @return A map containing the result of the stored procedure execution.
 */  

public Map<String, Object> PR_GLOBALUDEC_I_SOLICITUD(int page_id) {
 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.secondJdbcTemplate)
         .withProcedureName("PR_GLOBALUDEC_I_SOLICITUD");

 jdbcCall.declareParameters(
         new SqlParameter("P_SOLI_OBSERVACIONES", Types.VARCHAR),
         new SqlParameter("P_SOLI_REGISTRADOPOR", Types.VARCHAR),
         new SqlParameter("P_SOLI_FECHACAMBIO", Types.DATE),
         new SqlParameter("P_SOLI_ATENDIDO", Types.VARCHAR),
         new SqlParameter("P_SOLI_FECHARESUELTA", Types.DATE),
         new SqlParameter("P_SOLI_FECHASOLICITUD", Types.DATE),
         new SqlParameter("P_TISO_ID", Types.INTEGER),
         new SqlParameter("P_PEGE_ID", Types.INTEGER),
         new SqlParameter("P_SOLI_TIPOUSUARIO", Types.VARCHAR),
         new SqlParameter("v_RETORNO", Types.NUMERIC),
         new SqlParameter("v_NUMERO", Types.NUMERIC));

 Map<String, Object> inParams = new HashMap<>();
 inParams.put("P_SOLI_OBSERVACIONES", null);
 inParams.put("P_SOLI_REGISTRADOPOR", null);
 inParams.put("P_SOLI_FECHACAMBIO", null);
 inParams.put("P_SOLI_ATENDIDO", "0");
 inParams.put("P_SOLI_FECHARESUELTA", null);
 inParams.put("P_SOLI_FECHASOLICITUD", new java.sql.Date(System.currentTimeMillis()));
 inParams.put("P_TISO_ID", 14);
 inParams.put("P_PEGE_ID", page_id);
 inParams.put("P_SOLI_TIPOUSUARIO", null);
 inParams.put("v_RETORNO", null); // Parámetro de salida
 inParams.put("v_NUMERO", null); // Parámetro de salida

 Map<String, Object> resultMap = jdbcCall.execute(inParams);
 return resultMap;
}

/**
 * Executes the stored procedure PR_GLOBALUDEC_I_SOLI_SERVI to add a new Solicitud servicios.
 * @param id The ID of the Solicitud.
 * @param serv_id The ID of the Servicio.
 * @return A map containing the result of the stored procedure execution.
 */

public Map<String, Object> PR_GLOBALUDEC_I_SOLI_SERVI( Object id, String serv_id,int pege_id,String rangos_fecha ) {
	

	//String codigo_identificador=  cedula.substring(cedula.length() - 3) +id;
 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.secondJdbcTemplate)
         .withProcedureName("PR_GLOBALUDEC_I_SOLI_SERVI");

 jdbcCall.declareParameters(
         new SqlParameter("P_SOLI_ID", Types.INTEGER),
         new SqlParameter("P_SERV_ID", Types.INTEGER),
         new SqlParameter("P_SOSE_DESCRIPCION", Types.VARCHAR),
         new SqlParameter("P_SOSE_REGISTRADOPOR", Types.VARCHAR),
         new SqlParameter("P_SOSE_FECHACAMBIO", Types.DATE),
         new SqlParameter("P_SOSE_ATENDIDO", Types.VARCHAR),
         new SqlParameter("P_SOSE_ASUNTO", Types.VARCHAR),
         new SqlParameter("P_UNPR_ID", Types.INTEGER),
         new SqlParameter("P_SOSE_ESTAMENTOACADEMICO", Types.VARCHAR),
         new SqlParameter("P_SOSE_FECHAHORAENTREGA", Types.DATE),
         new SqlParameter("P_SOSE_DATOSDESTACAR", Types.VARCHAR),
         new SqlParameter("P_SOSE_LUGAREVENTO", Types.VARCHAR),
		    new SqlParameter("P_SOSE_FECHAEVENTO", Types.DATE),
		    new SqlParameter("P_SOSE_IDEAP", Types.VARCHAR),
		    new SqlParameter("P_SOSE_DURACION", Types.VARCHAR),
		    new SqlParameter("V_RETORNO", Types.NUMERIC));

 
 
 
 Map<String, Object> inParams = new HashMap<>();
 inParams.put("P_SOLI_ID",   id); // ----
 if (  serv_id.equals("Bancario")) {
	// codigo_identificador = codigo_identificador +107;
     inParams.put("P_SERV_ID", 108);  
 
 }
 else {
	 //codigo_identificador = codigo_identificador +106;
     inParams.put("P_SERV_ID", 107);  
   
 }
 //System.out.println(codigo_identificador+"  -------identificador");
 
 inParams.put("P_SOSE_DESCRIPCION", "Solicitid de certificado");  // --
 inParams.put("P_SOSE_REGISTRADOPOR", "16289");  // --
 inParams.put("P_SOSE_FECHACAMBIO", new java.sql.Date(System.currentTimeMillis())); //--
 inParams.put("P_SOSE_ATENDIDO", "0"); //--
 inParams.put("P_SOSE_ASUNTO",pege_id +"-"+id);
 inParams.put("P_UNPR_ID", null);
 inParams.put("P_SOSE_ESTAMENTOACADEMICO", null);
 inParams.put("P_SOSE_FECHAHORAENTREGA", null);
 inParams.put("P_SOSE_DATOSDESTACAR", rangos_fecha);
 inParams.put("P_SOSE_LUGAREVENTO", null);
 inParams.put("P_SOSE_FECHAEVENTO", null);
 inParams.put("P_SOSE_IDEAP", null); 
 inParams.put("P_SOSE_DURACION", null); 
 inParams.put("V_RETORNO", null); // Parámetro de salida
 Map<String, Object> resultMap = jdbcCall.execute(inParams);
 return resultMap;
}
	
/**
 * Executes the stored procedure PR_GLOBALUDEC_I_SOLICITUDPROCE to add a new Solicitud procedo.
 * @param id The ID of the Solicitud.
 * @return A map containing the result of the stored procedure execution.
 */

public Map<String, Object> PR_GLOBALUDEC_I_SOLICITUDPROCE( Object id) {
 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.secondJdbcTemplate)
         .withProcedureName("PR_GLOBALUDEC_I_SOLICITUDPROCE");

 jdbcCall.declareParameters(
         new SqlParameter("P_SOPR_DESCRIPCION", Types.VARCHAR),          
         new SqlParameter("P_SOPR_NUMEROCUENTA", Types.VARCHAR),        
         new SqlParameter("P_SOSE_ID", Types.INTEGER),           
         new SqlParameter("P_BAGE_ID", Types.INTEGER),         
         new SqlParameter("P_SOPR_DETALLE", Types.VARCHAR),           
         new SqlParameter("P_PEUN_ID", Types.VARCHAR),         
         new SqlParameter("P_ESTA_ID", Types.INTEGER),        
         new SqlParameter("P_RESO_ID", Types.VARCHAR),               
         new SqlParameter("P_SOPR_PORCENTAJE", Types.VARCHAR),         
         new SqlParameter("P_SOPR_FECHACAMBIO", Types.DATE),          
         new SqlParameter("P_SOPR_REGISTRADOPOR", Types.VARCHAR),
         new SqlParameter("V_RETORNO", Types.NUMERIC));
Map<String, Object> inParams = new HashMap<>();
 inParams.put("P_SOPR_DESCRIPCION",null); 
 inParams.put("P_SOPR_NUMEROCUENTA", null);  
 inParams.put("P_SOSE_ID", id);  // --
 inParams.put("P_BAGE_ID", null);  
 inParams.put("P_SOPR_DETALLE", null); 
 inParams.put("P_PEUN_ID", null); 
 inParams.put("P_ESTA_ID", 146); // --
 inParams.put("P_RESO_ID", null);
 inParams.put("P_SOPR_PORCENTAJE", null);
 inParams.put("P_SOPR_FECHACAMBIO",  new java.sql.Date(System.currentTimeMillis())); // --
 inParams.put("P_SOPR_REGISTRADOPOR", 16279); // --
 inParams.put("V_RETORNO", null); // Parámetro de salida
 Map<String, Object> resultMap = jdbcCall.execute(inParams);
 return resultMap;
}	
	
/**
 * Verifies the existence of TIPOESTADOS based on the specified parameter.
 * 
 * @param parametrocedula The parameter used to verify the existence.
 * @return A list of TIPOESTADOS found based on the verification.
 */

public  List<TIPOESTADOS>  verificar_existenci(String parametrocedula) {	  
    //   2010-09-30
	String  sql  =	"select ties_descripcion from TIPOESTADOS" ; 
	
	List<TIPOESTADOS> usuario_encontrado = this.secondJdbcTemplate.query(  sql, BeanPropertyRowMapper.newInstance(TIPOESTADOS.class));
		return usuario_encontrado;
}
/**
 * Verifies the existence of a user login based on the specified parameters.
 * 
 * @param parametrocedula The parameter used for user identification.
 * @param TPdocumento The type of document used for identification.
 * @param FechaExpedicio The date of document issuance (format: yyyy-MM-dd).
 * @return A list of verificar_loging objects representing the existence of user login.
 * @throws ParseException if there is an error parsing the date.
 */
public  List<verificar_loging>  verificar_existencia(String parametrocedula, String TPdocumento, String FechaExpedicio) throws ParseException {
	  
    //   2010-09-30
	
	String fecha = String.valueOf(FechaExpedicio.charAt(8)) + String.valueOf(FechaExpedicio.charAt(9))+ "/" + String.valueOf(FechaExpedicio.charAt(5))+ String.valueOf(FechaExpedicio.charAt(6))+ "/"+ String.valueOf(FechaExpedicio.charAt(2)+  String.valueOf(FechaExpedicio.charAt(3))) ;
	System.out.println(fecha);
	String  sql  =	"SELECT  CASE  WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END AS existe_registro  FROM PERSONANATURALGENERAL  INNER JOIN PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id  where PERSONAGENERAL.PEGE_DOCUMENTOIDENTIDAD    = "+ "'"+ parametrocedula+ "'"  + "    and TIPODOCUMENTOGENERAL.TIDG_ABREVIATURA ="+  "'" + TPdocumento+ "'"  + "and PERSONAGENERAL.PEGE_FECHAEXPEDICION ="+ "'"+fecha+"'"  ; 
	List<verificar_loging> usuario_encontrado = this.firstJdbcTemplate.query(  sql, BeanPropertyRowMapper.newInstance(verificar_loging.class));
		return usuario_encontrado;
}	
	

/**
 * Selects and retrieves a list of visualizar_solicitud objects representing requested solicitations.
 * 
 * @param page_id The identifier of the page associated with the solicitations.
 * @return A list of visualizar_solicitud objects representing the requested solicitations.
 */


public List<visualizar_solicitud>  selctdesolicitudes(int page_id) {


String  sql  = "SELECT solicitud.soli_fechasolicitud , solicitud.pege_id as identificador, solicitud_servicios.sose_datosdestacar as rangoFecha,  solicitud.soli_id as soliid ,  \r\n"
	+ "servicios.serv_nombre , SOLICITUD_SERVICIOS.sose_atendido , SOLICITUD_SERVICIOS.sose_asunto as  concecutivo  \r\n"
	+ "FROM SOLICITUD  INNER JOIN SOLICITUD_SERVICIOS ON SOLICITUD.SOLI_ID= SOLICITUD_SERVICIOS.SOLI_ID \r\n"
	+ "INNER JOIN SOLICITUDPROCESO ON SOLICITUD_SERVICIOS.SOSE_ID = SOLICITUDPROCESO.SOSE_ID   INNER JOIN\r\n"
	+ "servicios ON servicios.serv_id = SOLICITUD_SERVICIOS.serv_id     WHERE solicitud.pege_id =" + page_id + " and solicitud.tiso_id = 14"; 

List<visualizar_solicitud> usuario_encontrado = this.secondJdbcTemplate.query(  sql, BeanPropertyRowMapper.newInstance(visualizar_solicitud.class));
return usuario_encontrado;
}
 

/**
 * Retrieves a list of total requests that have not been attended.
 *
 * @return A list of {@link visualizar_solicitud} objects representing the total requests.
 */

public List<visualizar_solicitud>  SolicitudestotalesSql() {


String  sql  = "SELECT solicitud.soli_fechasolicitud , solicitud.soli_id as soliid, solicitud.pege_id as identificador, solicitud_servicios.sose_datosdestacar as rangoFecha ,  \r\n"
	+ "servicios.serv_nombre , SOLICITUD_SERVICIOS.sose_atendido  , SOLICITUD_SERVICIOS.sose_asunto as  concecutivo \r\n"
	+ "FROM SOLICITUD  INNER JOIN SOLICITUD_SERVICIOS ON SOLICITUD.SOLI_ID= SOLICITUD_SERVICIOS.SOLI_ID \r\n"
	+ "INNER JOIN SOLICITUDPROCESO ON SOLICITUD_SERVICIOS.SOSE_ID = SOLICITUDPROCESO.SOSE_ID   INNER JOIN\r\n"
	+ "servicios ON servicios.serv_id = SOLICITUD_SERVICIOS.serv_id     WHERE    solicitud.tiso_id = 14 and SOLICITUD_SERVICIOS.sose_atendido = 0"  ; 

List<visualizar_solicitud> usuario_encontrado = this.secondJdbcTemplate.query(  sql, BeanPropertyRowMapper.newInstance(visualizar_solicitud.class));
return usuario_encontrado;

}

/**
 * Updates the status of a request and its associated services to indicate they have been attended.
 *
 * @param soli_id The ID of the request to be updated.
 * @param secondJdbcTemplate The {@link JdbcTemplate} used to execute the update queries.
 */

public void  ModificarEstadosql(String soli_id) {
System.out.println(soli_id + "   .lll");

String  sql  = "update solicitud set  soli_atendido = 1  WHERE    solicitud.soli_id =  " + soli_id  ; 
String sql2 = " update SOLICITUD_SERVICIOS set sose_atendido = 1  WHERE SOLICITUD_SERVICIOS.soli_id =  " + soli_id;
 this.secondJdbcTemplate.update(sql);
this.secondJdbcTemplate.update(sql2);




}



public  List<Empleado>  empleados() {
	
	String  sql  = "select * from Empleados where id = 2" ;                             
	
	
	//String  sql  = "SELECT  TIDG_ABREVIATURA,PEGE_DOCUMENTOIDENTIDAD, PEGE_FECHAEXPEDICION FROM PERSONANATURALGENERAL  INNER JOIN PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id";
	List<Empleado> lista = this.localsave.query(sql , BeanPropertyRowMapper.newInstance(Empleado.class));
	return lista;

}



/**
 * Retrieves basic personal data from the database based on the provided pege_id.
 *
 * @param pege_id The ID of the person for whom data is to be retrieved.
 * @return A list of {@link datosBasicosPdf} objects containing basic personal data.
 */
		
		public  List<datosBasicosPdf>  datosBasicosPdfSql(String pege_id) {
			//System.out.println(pege_id);
			System.out.println("holasw");
			System.out.println(pege_id);
			
			String  sql = "SELECT    personanaturalgeneral.peng_primernombre|| ' '||\r\n"
					+ "    personanaturalgeneral.peng_segundonombre ||' '|| personanaturalgeneral.peng_primerapellido ||' '||  \r\n"
					+ "    personanaturalgeneral.peng_segundoapellido as nombre ,\r\n"
					+ "    personageneral.pege_documentoidentidad as doc , PERSONANATURALGENERAL.peng_sexo as sexo , \r\n"
					+ "    tipodocumentogeneral.tidg_descripcion as tipodoc , personageneral.pege_lugarexpedicion as expedicion\r\n"
					+ "    FROM PERSONANATURALGENERAL  INNER JOIN\r\n"
					+ "    PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id\r\n"
					+ "    INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id \r\n"
					+ "    \r\n"
					+ "    where personanaturalgeneral.pege_id = " + pege_id;
					
			List<datosBasicosPdf> listaDatosBasicos = this.firstJdbcTemplate.query(sql , BeanPropertyRowMapper.newInstance(datosBasicosPdf.class));
			return listaDatosBasicos;

		}		
		/**
		 * Retrieves the concecutivo (subject) from SOLICITUD_SERVICIOS based on the provided soli_id.
		 *
		 * @param soli_id The ID of the solicitud_servicios to retrieve the concecutivo for.
		 * @return A list of {@link Concecutivo} objects containing the concecutivo.
		 */
public List<Concecutivo> Getconcecutivo (String soli_id) {
	String  sql  = " select SOLICITUD_SERVICIOS.sose_asunto as  concecutivo \r\n"
			+ "FROM SOLICITUD_SERVICIOS \r\n "
			
			+ "    WHERE     SOLICITUD_SERVICIOS.soli_id = "  + soli_id ; 
	List<Concecutivo> listaDatosBasicos = this.secondJdbcTemplate.query(sql , BeanPropertyRowMapper.newInstance(Concecutivo.class));
	return listaDatosBasicos;

}
/**
 * Retrieves basic data for a person based on the provided pege_id.
 *
 * @param pege_id The ID of the person for whom data is to be retrieved.
 * @return A list of {@link DatoBasicos} objects containing basic personal data.
 */	
public  List<DatoBasicos>  DatoBasicoSql(String pege_id) {
	//System.out.println(pege_id);

	System.out.println(pege_id);
	String sql = " SELECT    personanaturalgeneral.peng_primernombre|| ' '||\r\n"
			+ "    personanaturalgeneral.peng_segundonombre ||' '|| personanaturalgeneral.peng_primerapellido ||' '||  \r\n"
			+ "    personanaturalgeneral.peng_segundoapellido as nombre , personanaturalgeneral.peng_emailinstitucional as correo,\r\n"
			+ "    personageneral.pege_documentoidentidad as doc FROM PERSONANATURALGENERAL  INNER JOIN\r\n"
			+ "    PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id\r\n"
			+ "    INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id       where personanaturalgeneral.pege_id = " +pege_id  ;
	List<DatoBasicos> listaDatosBasicos = this.firstJdbcTemplate.query(sql , BeanPropertyRowMapper.newInstance(DatoBasicos.class));
	return listaDatosBasicos;

}

/**
 * Retrieves a list of salary-related parameters from the database.
 *
 * @return A list of {@link Datosalario} objects containing salary parameters.
 */
public  List<Datosalario>  datossalrios() {
	
	String  sql  = "select * from Parametros" ;                             
	
	
	//String  sql  = "SELECT  TIDG_ABREVIATURA,PEGE_DOCUMENTOIDENTIDAD, PEGE_FECHAEXPEDICION FROM PERSONANATURALGENERAL  INNER JOIN PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id";
	List<Datosalario> lista = this.localsave.query(sql , BeanPropertyRowMapper.newInstance(Datosalario.class));
	return lista;

}


/**
 * Retrieves a list of services offered by the university from the database.
 *
 * @return A list of {@link ServiciosUniversidad} objects representing university services.
 */
public  List<ServiciosUniversidad>  serviciosuniversidad() {
	
	String  sql  = "select * from ServiciosUniversidad" ;                             
	
	
	//String  sql  = "SELECT  TIDG_ABREVIATURA,PEGE_DOCUMENTOIDENTIDAD, PEGE_FECHAEXPEDICION FROM PERSONANATURALGENERAL  INNER JOIN PERSONAGENERAL ON personanaturalgeneral.pege_id= PERSONAGENERAL.pege_id INNER JOIN TIPODOCUMENTOGENERAL ON personageneral.tidg_id = TIPODOCUMENTOGENERAL.tidg_id";
	List<ServiciosUniversidad> lista = this.localsave.query(sql , BeanPropertyRowMapper.newInstance(ServiciosUniversidad.class));
	return lista;

}


/**
 * Retrieves verification certificates based on a given concecutivo from the database.
 *
 * @param concecutivo The concecutivo used to filter the results.
 * @return A list of {@link VerificarCertificado} objects representing verification certificates.
 */
public List<VerificarCertificado> GetvervicarConcecutivo (String concecutivo) {

String sql = "select   SOLICITUD_SERVICIOS.serv_id as  tipo, SOLICITUD.pege_id as identificador , SOLICITUD_SERVICIOS.sose_asunto as concecutivo\r\n"
		+ "    FROM SOLICITUD  INNER JOIN SOLICITUD_SERVICIOS ON SOLICITUD.SOLI_ID= SOLICITUD_SERVICIOS.SOLI_ID \r\n"
		+ "     WHERE     SOLICITUD_SERVICIOS.sose_asunto =   "+ "'" +concecutivo+"'";	
	List<VerificarCertificado> listaDatosBasicos = this.secondJdbcTemplate.query(sql , BeanPropertyRowMapper.newInstance(VerificarCertificado.class));
	return listaDatosBasicos;

}
}




