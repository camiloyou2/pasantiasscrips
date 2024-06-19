package com.example.demo.controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.modelo.Certificado;
import com.example.demo.modelo.Concecutivo;
import com.example.demo.modelo.DatoBasicos;
import com.example.demo.modelo.DatosVerificarreques;
import com.example.demo.modelo.Datosalario;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.GeneradorImagenes;
import com.example.demo.modelo.ServiciosUniversidad;
import com.example.demo.modelo.Solicitud;
import com.example.demo.modelo.Solicitud_servicios;
import com.example.demo.modelo.TIPOESTADOS;
import com.example.demo.modelo.TipoSolicitud;
import com.example.demo.modelo.VerificarCertificado;
import com.example.demo.modelo.codigocapcha;
import com.example.demo.modelo.datosBasicosPdf;
import com.example.demo.modelo.datos_verificacion;
import com.example.demo.modelo.datospersonales;

import com.example.demo.repositoio.PersonaSql;
import com.example.demo.servicio.personaService;
import com.example.demo.modelo.login_principal;
import com.example.demo.modelo.soliservicios;
import com.example.demo.modelo.verificar_loging;
import com.example.demo.modelo.visualizar_solicitud;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@RestController
@CrossOrigin( origins="http://localhost:4200/")
public class controlador {

	public String ultimosdigitoscedula = "" ;
	@Autowired
	private personaService personaservice;
	
	@Autowired
	private PersonaSql personaSql;
	private codigocapcha codigoCapcha =  new codigocapcha();
String codigo_generado;
	
/**
 * Generates a PDF bank certificate using JasperReports.
 * 
 * @param datosBasicosPdfList A list of basic data required for the PDF.
 * @param concecutivo A string representing the certificate consecutive number.
 * @return A Certificado object containing the generated PDF as a byte array.
 */	  
public  Certificado pdfBancario( List<datosBasicosPdf> datosBasicosPdfList , String concecutivo) {

String filePath = "src" + File.separator +
"main" + File.separator +
"resources" + File.separator +
"templates" + File.separator +
"report" + File.separator + "pdfBancario.jrxml" ;
//"reportultimo.jrxml" ;

Map <String, Object> parametros = new HashMap <>();
String sexo ="";
String nombre ="";
if( datosBasicosPdfList.get(0).getSexo() == "F") {
	sexo = "La señora";
	nombre = datosBasicosPdfList.get(0).getNombre();
	
}else {
	sexo = "El señor";
	nombre = datosBasicosPdfList.get(0).getNombre();
}
String datos_del_certificado = sexo+ " "+nombre+", identificada con la"+ datosBasicosPdfList.get(0).getTipodoc() + "No."+datosBasicosPdfList.get(0).getDoc()+" de  "+ datosBasicosPdfList.get(0).getExpedicion()+", viene prestando sus servicios a la Institución desde el " + " (fecha rango) "+", en  la fecha se desempeña como "+ " (cargo) "+" de la Universidad de Cundinamarca.";
parametros.put("datos_del_certificado", datos_del_certificado);
parametros.put("sueldomensual",  " xx ");
parametros.put("primaantiguedad"," xx ");
parametros.put("primaalojamiento", " xx ");
parametros.put("primanavidad", " xx ");
parametros.put("vacaciones", " xx ");
parametros.put("primavacaciones", " xx ");
parametros.put("bonificaciondiciembre", " xx ");
parametros.put("bonificacionjulio", " xx ");
parametros.put("retroactivo", " xx ");
parametros.put("primasemestral", " xx ");
parametros.put("senoroseñorados", sexo + " no ha tenido licencias ni sanciones por ningún concepto con la Institución.");
parametros.put("encargadotalentohumano", "LUZ ETELVINA LOZANO SOTO");
parametros.put("ImageDir", "classpath:/static/images/");
parametros.put("codigo_derecho", "ADOr005_V7");
parametros.put("codigo_izquierdo", "33 – 1139");
parametros.put("concecutivo", concecutivo);
try {
JasperReport report = JasperCompileManager.compileReport(filePath);
JasperPrint  print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());


byte[] pdfBytes = convertirAPDF(print);
System.out.println("---------------------  2  ---------------------");	
Certificado certificado = new Certificado();
certificado.setPdfBytes(pdfBytes);
return certificado;
} catch (JRException e) {
	e.printStackTrace();

	Certificado certificado = new Certificado();
	
	return certificado;

}

  }


/**
 * Generates a labor certificate PDF using JasperReports.
 * 
 * @param datosBasicosPdfList A list of basic data required for the PDF.
 * @param concecutivo A string representing the certificate consecutive number.
 * @return A Certificado object containing the generated PDF as a byte array.
 */

public  Certificado LaboralPdf( List<datosBasicosPdf> datosBasicosPdfList , String concecutivo) {

	

String filePath = "src" + File.separator +
"main" + File.separator +
"resources" + File.separator +
"templates" + File.separator +
"report" + File.separator + "pdfLaboral.jrxml" ;
// "certificadolaboral.jrxml" ;

Map <String, Object> parametros = new HashMap <>();
String sexo ="";
String nombre ="";
if( datosBasicosPdfList.get(0).getSexo() == "F") {
	sexo = "La señora";
	nombre = datosBasicosPdfList.get(0).getNombre();
	
}else {
	sexo = "El señor";
	nombre = datosBasicosPdfList.get(0).getNombre();
}

//String cargotoales = "";
//for (ServiciosUniversidad servicio : personaSql.serviciosuniversidad()) {
  String cargotoales = "Mediante Anexo No. 579 Condiciones Generales de Contratación de Personal Docente Hora Cátedra, "+ "(Periodo Contratacion)"+", orientó los núcleos temáticos de: "+"(nucleos tematicos)"+"; Con una intensidad horaria de doce ("+" horas"+") horas semanales \n \n";
//  cargotoales = cargotoales + cargos;

//}
String datos_personales = sexo+ " "+nombre+" , identificado con "+datosBasicosPdfList.get(0).getTipodoc()+ " No. "+datosBasicosPdfList.get(0).getDoc()+" expedida en "+datosBasicosPdfList.get(0).getExpedicion()+"), de acuerdo con los Archivos de Gestión y las Bases de Datos de la Dirección de Talento Humano viene prestando sus servicios a la Universidad de Cundinamarca, Seccional Girardot, Programa Académico de Administración de Empresas como se relaciona a continuación ";
parametros.put("datos_del_certificado", datos_personales);
parametros.put("especificaciones_del_cargo", cargotoales);
String nombre_del_solicitante = "Esta certificación se expide a solicitud de "+" "+nombre;
parametros.put("nombre_del_solicitante", nombre_del_solicitante );
parametros.put("ImageDir", "classpath:/static/images/");
parametros.put("codigo_derecho", "ADOr005_V7");
parametros.put("codigo_izquierdo", "33 – 1139");
parametros.put("concecutivo", concecutivo);
try {
JasperReport report = JasperCompileManager.compileReport(filePath);
JasperPrint  print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());


byte[] pdfBytes = convertirAPDF(print);
System.out.println("---------------------  2  ---------------------");	
Certificado certificado = new Certificado();
certificado.setPdfBytes(pdfBytes);
return certificado;
} catch (JRException e) {
	e.printStackTrace();

	Certificado certificado = new Certificado();
	
	return certificado;

}
	
	

  }
	
	public static byte[] convertirAPDF(JasperPrint print) throws JRException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
	
	public static String convertirABase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
	

	
	/**
	 * Handles a POST request to submit a service request.
	 * 
	 * @param solicitud The request body containing the service request details.
	 */
	@PostMapping("/post_solicitud")
	@ResponseBody
		  public void Solicitud(@RequestBody  Solicitud solicitud) {


	 List<datospersonales> datos_personales = personaSql.datosPersonales(this.ultimosdigitoscedula);

	 Map<String, Object> resultMap = personaSql.PR_GLOBALUDEC_I_SOLICITUD(Integer.parseInt(datos_personales.get(0).getPEGE_ID()));
	 Map<String, Object> resultMapp = personaSql.PR_GLOBALUDEC_I_SOLI_SERVI(resultMap.get("P_RETORNO"), solicitud.getSERV_ID(),Integer.parseInt(datos_personales.get(0).getPEGE_ID()), solicitud.getFecha_inicio() +"&"+solicitud.getFecha_fin());	
	 Map<String, Object> resultMappp = personaSql.PR_GLOBALUDEC_I_SOLICITUDPROCE(resultMapp.get("P_RETORNO"));
	    	
	
	
	}
		
	
	 /**
     * Retrieves a list of personal solicitudes.
     * 
     * This method fetches and returns a list of personal solicitudes for a specific user.
     * The user ID is currently hardcoded as 192235.
     * 
     * @return A list of visualizar_solicitud objects representing the personal solicitudes.
     */
	 @GetMapping("/solicitudes_personales") 
	  public List<visualizar_solicitud> solicitudes_personales() {
		return  personaSql.selctdesolicitudes( 192235 ); 
	  }
	 
	// muestra las solicitudes del usuario que entra al portal
	  
	  /**
	     * Retrieves a list of visualizar_solicitud objects for the current user.
	     * 
	     * This method fetches personal data based on the last digits of the user's cedula
	     * and then retrieves the list of solicitudes for the user identified by the PEGE_ID.
	     * 
	     * @return A list of visualizar_solicitud objects representing the user's solicitudes.
	     */
	  
	 @GetMapping("/solicitues_visualizar") 
	  public List<visualizar_solicitud> Solicitud_visualizar() {
		  List<datospersonales> datos_personales = personaSql.datosPersonales(this.ultimosdigitoscedula);
		  //temporal mientras tengo la base de datos 

		// Crear un nuevo objeto visualizar_solicitud
		  // Establecer valores para los campos de solicitud1
	
		return personaSql.selctdesolicitudes(Integer.parseInt(datos_personales.get(0).getPEGE_ID()));
		    }
	  
	  @GetMapping("/Solicitudestotales") 
	  public List<visualizar_solicitud> Solicitudestotales() {

		return personaSql.SolicitudestotalesSql();
		    }
	 
	
	  /**
	     * Generates and returns a PDF certificate containing employee banking details.
	     * 
	     * This method compiles a Jasper report template, fills it with data fetched from the database,
	     * and exports it to a PDF file. The PDF is then converted to a byte array and returned as part
	     * of a Certificado object.
	     * 
	     * @return A Certificado object containing the generated PDF bytes.
	     */
	  
	  @PostMapping("/pdf_bancario") 
	  public   List<DatoBasicos>  pdf_bancario(@RequestBody DatosVerificarreques  pege_id) {
	System.out.println("   lkkdj ");
	
		  String[] parts =   pege_id.getDatosVerificar().split("-");
		  System.out.println(parts[0]);
		  List<Concecutivo> listconcecutivo = personaSql.Getconcecutivo(parts[2]);
		  
		  System.out.println(" -  "+ pege_id.getDatosVerificar() +"  - ");
		  System.out.println(" -  "+ parts[1] +"  - ");
		  System.out.println(" -  "+ parts[2] +"  - ");
		  System.out.println(listconcecutivo.get(0).getConcecutivo());
		  List<datosBasicosPdf> datosBasicosPdfList = personaSql.datosBasicosPdfSql(parts[0]);
		  List<DatoBasicos> xxx = personaSql.DatoBasicoSql( parts[0]);
		
		 // if (parts[2].equals("Bancario") ) {
			 xxx.get(0).setPdfBytes(  pdfBancario(datosBasicosPdfList,  listconcecutivo.get(0).getConcecutivo()).getPdfBytes());
			
		//  }
		  return 	 xxx;
	

	    }
	  
	  
	  @PostMapping("/pdf_laboral_funciones") 
	  public   List<DatoBasicos>  pdf_laboras(@RequestBody DatosVerificarreques  pege_id) {
	System.out.println("   lkkdj ");
		  String[] parts =   pege_id.getDatosVerificar().split("-");
		  System.out.println(parts[0]);
		  List<datosBasicosPdf> datosBasicosPdfList = personaSql.datosBasicosPdfSql(parts[0]);
		  List<DatoBasicos> xxx = personaSql.DatoBasicoSql( parts[0]);
		  List<Concecutivo> listconcecutivo = personaSql.Getconcecutivo(parts[2]);
		 // if (parts[2].equals("Bancario") ) {
			 xxx.get(0).setPdfBytes(  LaboralPdf(datosBasicosPdfList , listconcecutivo.get(0).getConcecutivo()).getPdfBytes());
			
		//  }
		  return 	 xxx;
	

	    }
	  
	  /**
	     * Generates and returns a PDF certificate containing employee labor details and functions.
	     *
	     * This method compiles a Jasper report template, fills it with data fetched from the database,
	     * and exports it to a PDF file. The PDF is then converted to a byte array and returned as part
	     * of a Certificado object.
	     *
	     * @return A Certificado object containing the generated PDF bytes.
	     */	
	  @GetMapping("/pdf_laboral_funcioness") 
	  public  Certificado pdf_laboral_funcioness() {
		  String destinacionPath = "src" + File.separator +
					"main" + File.separator +
					"resources" + File.separator +
					 "static" + File.separator +
					 "ReportGenerated.pdf" ;

	String filePath = "src" + File.separator +
	"main" + File.separator +
	"resources" + File.separator +
	"templates" + File.separator +
	"report" + File.separator +
	 "certificadolaboral.jrxml" ;

	Map <String, Object> parametros = new HashMap <>();
	
	//Que - -  identificada con la Cédula de Ciudadanía No. - de - , viene prestando sus servicios a la Institución desde -, en  la fecha se desempeña como - de la Universidad de Cundinamarca.
	//sexo 
	//nombres completos
	//numero documento
	//lugar de expedicion
	//ultima fecha de contrato
	//lugar-ultimo_donde_trabajo
	// programa-academico
	String sexo ="";
	String nombre ="";
	if( personaSql.empleados().get(0).getSexo() == "Femenino") {
		sexo = "La señora";
		nombre = "Ana Lucia Rios Rodriguez";
		
	}else {
		sexo = "El señor";
		nombre = "Libardo Helman  Ochoa Diaz";
	}
	String cargotoales = "";
	  for (ServiciosUniversidad servicio : personaSql.serviciosuniversidad()) {
		  String cargos = "Mediante Anexo No. 579 Condiciones Generales de Contratación de Personal Docente Hora Cátedra, "+servicio.getPeriodoContratacion()+", orientó los núcleos temáticos de: "+servicio.getNucleoTematico()+"; Con una intensidad horaria de doce ("+servicio.getIntensidadHoraria()+") horas semanales \n \n";
		  cargotoales = cargotoales + cargos;
	  
	  }
String datos_personales = sexo+ " "+nombre+" , identificado con Cédula de Ciudadanía No. "+personaSql.empleados().get(0).getNumeroDocumento()+" expedida en "+ personaSql.empleados().get(0).getLugarExpedicionDocumento()+"), de acuerdo con los Archivos de Gestión y las Bases de Datos de la Dirección de Talento Humano viene prestando sus servicios a la Universidad de Cundinamarca, Seccional Girardot, Programa Académico de Administración de Empresas como se relaciona a continuación ";
	parametros.put("datos_del_certificado", datos_personales);
	parametros.put("especificaciones_del_cargo", cargotoales);
	String nombre_del_solicitante = "Esta certificación se expide a solicitud de "+" "+nombre;
	parametros.put("nombre_del_solicitante", nombre_del_solicitante );
	parametros.put("ImageDir", "classpath:/static/images/");
	parametros.put("codigo_derecho", "ADOr005_V7");
	parametros.put("codigo_izquierdo", "33 – 1139");
	try {
	JasperReport report = JasperCompileManager.compileReport(filePath);
	JasperPrint  print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
	JasperExportManager.exportReportToPdfFile(print,destinacionPath);
	
    byte[] pdfBytes = convertirAPDF(print);
    String base64String = convertirABase64(pdfBytes);
	JasperExportManager.exportReportToPdfFile(print,destinacionPath);
	System.out.println("---------------------  1  ---------------------");	
	Certificado certificado = new Certificado();
	certificado.setPdfBytes(pdfBytes);
	return certificado;
	

	} catch (JRException e) {
	
	e.printStackTrace();
	Certificado certificado = new Certificado();
	
	return certificado;
	}

	    }
	
	    /**
	     * Verifies login information.
	     *
	     * This method takes login data as input, performs verification, and returns a list of verification results.
	     *
	     * @param datos A list containing login information: [codigo_generado, cedula, fecha_nacimiento].
	     * @return A list of verificar_loging objects representing the verification results.
	     * @throws ParseException If there is an error parsing the date from the input data.
	     */  
	  
	  
	  @PostMapping("/loog") 
		 // @ResponseBody
		  public  	 List<verificar_loging>  Datos_verificacionn_doss(@RequestBody  List<String> datos) throws ParseException {	  
	
		// List<verificar_loging>  personaSql.verificar_existencia(codigo_generado)
		
		  //, datos.get(0), datos.get(2)
		    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		    // Convertir el String a java.util.Date
            java.util.Date fechaUtil = formato.parse(datos.get(2));
    
            // Convertir java.util.Date a java.sql.Date
            Date fechaSQL = new Date(fechaUtil.getTime());
        
		    List<verificar_loging> xxx = personaSql.verificar_existencia(datos.get(1),datos.get(0),datos.get(2));

		 
		    this.ultimosdigitoscedula=  (String.valueOf(datos.get(1)));
		    	System.out.println(ultimosdigitoscedula + "-------");
		    return xxx;
		    }
	  
	
		  /**
		     * Retrieves personal data based on the last digits of the cedula.
		     *
		     * This method fetches personal data from the database based on the last digits of the cedula
		     * stored in the instance variable 'ultimosdigitoscedula'.
		     *
		     * @return A list of datospersonales objects containing personal data.
		     */
		  
	 // esta funcion trae los datos personales del usuario con imagen correspondiente

/**
 * Handles a GET request to retrieve personal data based on the last digits of the identification number.
 * 
 * @return A list of personal data records.
 */
		  @GetMapping("/datospersonales") 
		  public List<datospersonales> datosPersonales() {
			  List<datospersonales> xxx = personaSql.datosPersonales(this.ultimosdigitoscedula);
				return 	 xxx;
		    }
		  /**
		   * Handles a POST request to retrieve basic data and generate a PDF based on the request type.
		   *
		   * @param pege_id The request body containing the data to verify.
		   * @return A list of basic data records with generated PDF bytes.
		   */
	  @PostMapping("/DatoBasicos") 
	  public List<DatoBasicos> DatoBasico(@RequestBody DatosVerificarreques  pege_id) {
	
		  String[] parts =   pege_id.getDatosVerificar().split("-");
		  List<datosBasicosPdf> datosBasicosPdfList = personaSql.datosBasicosPdfSql(parts[0]);
		  List<DatoBasicos> xxx = personaSql.DatoBasicoSql( parts[0]);
		  List<Concecutivo> listconcecutivo = personaSql.Getconcecutivo(parts[1]);
		 
		  System.out.println("fds ---");
		  if (parts[2].equals("Bancario") ) {
			  System.out.println("fds");
			  
			  xxx.get(0).setPdfBytes(  pdfBancario(datosBasicosPdfList ,   listconcecutivo.get(0).getConcecutivo()).getPdfBytes());
			
		  }
		  else {
			  xxx.get(0).setPdfBytes(  LaboralPdf(datosBasicosPdfList ,  listconcecutivo.get(0).getConcecutivo()).getPdfBytes());
		  }
		
		  
			return 	 xxx;
			
	    }
	  
	  
	  
	  /**
	   * Handles a POST request to verify a certificate and generate a PDF based on the certificate type.
	   *
	   * @param concecutivo The request body containing the consecutive identifier to verify.
	   * @return A list of basic data records with generated PDF bytes, or null if an exception occurs.
	   */
	  @PostMapping("/VerficarCertificado") 
	  public List<DatoBasicos> VerficarCertificado(@RequestBody DatosVerificarreques  concecutivo) {
		  try {
			  System.out.println(concecutivo.getDatosVerificar());
			  List<VerificarCertificado> xxx = personaSql.GetvervicarConcecutivo( concecutivo.getDatosVerificar());
			    List<datosBasicosPdf> datosBasicosPdfList = personaSql.datosBasicosPdfSql(xxx.get(0).getIdentificador());
			    List<DatoBasicos> datobasico = personaSql.DatoBasicoSql( xxx.get(0).getIdentificador());
			 
			  System.out.println(xxx.get(0).getTipo());
			  if (xxx.get(0).getTipo().equals("107")   ) {
				  datobasico.get(0).setPdfBytes(    LaboralPdf(datosBasicosPdfList, concecutivo.getDatosVerificar()).getPdfBytes());
					
				  System.out.println("14--");
				  //LaboralPdf(datosBasicosPdfList , "dd");
				//  xxx.get(0).setPdfBytes(  LaboralPdf(datosBasicosPdfList ,  pege_id.getDatosVerificar()).getPdfBytes());
					
				 
			  }
			  else {
				//  xxx.get(0).setPdfBytes(  pdfBancario(datosBasicosPdfList ,   "g").getPdfBytes());
				  datobasico.get(0).setPdfBytes(    pdfBancario(datosBasicosPdfList, concecutivo.getDatosVerificar()).getPdfBytes());
				  System.out.println("otro ---");
					
				  }
			
			  
				return 	 datobasico;
		  }
		  catch(Exception e) {
				return null;  
		  }

		
			
	    }  
	  
	  
	  
	  
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  /**
	   * Handles a POST request to modify the status of an entity.
	   *
	   * @param pege_id The request body containing the data to verify and modify the status.
	   * @return A string indicating the operation result.
	   */
	  @PostMapping("/ModificarEstado") 
	  public String ModificarEstado(@RequestBody DatosVerificarreques  pege_id) {
		   personaSql.ModificarEstadosql(pege_id.getDatosVerificar());
		  return "True";
	  }
	  //269692
	  
	  
	    /**
	     * Generates a CAPTCHA code along with its corresponding image.
	     *
	     * This method generates a random CAPTCHA code and creates an image representing the code.
	     * The CAPTCHA code is a string of 5 random hexadecimal characters. The image contains the CAPTCHA code
	     * drawn with a random font, along with some random noise dots.
	     *
	     * @return A list containing a single codigocapcha object, representing the generated CAPTCHA code and image.
	     * @throws IOException If there is an error writing the image to byte array.
	     */
	    
	    @GetMapping("/c") 
	    public List<codigocapcha> codigo() throws IOException {

	    System.out.println(this.ultimosdigitoscedula);
	    	 codigocapcha codigoC =  new codigocapcha();
	    	 int width = 200;
		        int height = 50;
		       Random rdm = new Random();
		       int rl = rdm.nextInt(); // genera el numero aleatorio
		       byte[] imageBytes;
		       BufferedImage cpimg;
		       String hash1 = Integer.toHexString(rl); // convierte un entero de decimal a exadecimal 
		         String capstr = hash1.substring(0, 5); // solo toma los primeros 4 caracteres de la cadena sumistrada        
		         // graficos 
		         Color background = new Color(250, 250, 250);
		         Color fbl = new Color(0, 0, 0);
		         Color relle = new Color(200, 200, 200);
		         Font fnt = new Font("Arial", 2, 40);
		          cpimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		         Graphics g = cpimg.createGraphics(); 
		         g.setColor(background);
		         g.fillRect(0, 0, width, height);
		         g.setColor(fbl);
		         g.setFont(fnt);
		         g.setColor(background);
		         g.drawLine(50, 17, 80, 17);
		         g.drawLine(50, 22, 80, 22);
		         g.setColor(fbl);
		         g.drawString(capstr, 45, 35);
		         g.setColor(relle);
		             for (int a = 0; a < 50; a++) {
		             g.drawOval((int)(Math.random() * 200), (int)(Math.random() * 50), 15, 15);
		         }
		         ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    	 ImageIO.write(cpimg, "jpeg", baos);
		         imageBytes = baos.toByteArray(); 
		         codigoC.setCodigo(capstr);
		         codigoC.setImageBytes(imageBytes);
	    	List <codigocapcha> lista = new ArrayList<codigocapcha>();	    	
	    	lista.add(codigoC);	    	
			return  lista;		
			    }
	    
	  
	    
	 
}
