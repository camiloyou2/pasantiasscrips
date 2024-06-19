package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UltimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UltimoApplication.class, args);
		
		
		
		
		
		 /**
		  	System.out.println("  ------------------          -------------------------------------");
		
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
 "report.jrxml" ;

Map <String, Object> parametros = new HashMap <>();
parametros.put("señor_seoñara", "el Señor");
parametros.put("nombre", "Camilo Ochoa Pedroza");
parametros.put("numerocc", "1000241043");
parametros.put("lugarexpedicion", "Bogota");
parametros.put("fechadesdequetrabaja", "2 de Juliop del 2002");
parametros.put("cargo", "Ingeniero");
parametros.put("sueldomensual", "24");
parametros.put("primaantiguedad", "24");
parametros.put("primaalojamiento", "24");
parametros.put("primanavidad", "24");
parametros.put("vacaciones", "24");
parametros.put("primavacaciones", "24");
parametros.put("bonificaciondiciembre", "24");
parametros.put("bonificacionjulio", "24");
parametros.put("retroactivo", "24");
parametros.put("primasemestral", "24");
parametros.put("senoroseñorados", "El señor");
parametros.put("encargadotalentohumano", "Campo Elli");
parametros.put("ImageDir", "classpath:/static/images/");
try {
JasperReport report = JasperCompileManager.compileReport(filePath);
JasperPrint  print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
JasperExportManager.exportReportToPdfFile(print,destinacionPath);
System.out.println("  ------------------    nn      -------------------------------------");

} catch (JRException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
		  */
	
	
	}

}
