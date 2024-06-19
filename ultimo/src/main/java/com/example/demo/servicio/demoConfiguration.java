package com.example.demo.servicio;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.modelo.GeneradorImagenes;



@Configuration
public class demoConfiguration {
	@Bean
	public ModelMapper  modelMapper() {
		return new ModelMapper();
	}

	
	   //
/**¨

   
	    
	    
	    ///
	    
	  
	    
	    */
	    
	    @Bean(name = "dsMaster")
	    @Primary
	    public DataSource masterDataSource() {
	        return DataSourceBuilder.create()
	        		
	        		.url("jdbc:oracle:thin:@//172.16.8.122:1521/prueba").
	        		username("GENERAL")
	        		.password("Migra2023") 
	        		.driverClassName("oracle.jdbc.OracleDriver").build();

	    }
	 
	    @Bean(name = "jdbcMaster")
	    @Primary
	    @Autowired
	    public JdbcTemplate masterJdbcTemplate(@Qualifier("dsMaster") DataSource dsMaster) {
	        return new JdbcTemplate(dsMaster);
	    }
	  
 
	  @Bean(name = "local")
	   
	    public DataSource local() {
	   return DataSourceBuilder.create().url("jdbc:oracle:thin:@//localhost:1521/xe")
			   .username("SYSTEM")
			   .password("12345") 
			   .driverClassName("oracle.jdbc.OracleDriver").build();
	    }
	  
	  
 //
	
	  
	    @Bean(name = "localsave")
	    @Autowired
	    public JdbcTemplate localsave(@Qualifier("local") DataSource dsSlave) {
	        return new JdbcTemplate(dsSlave);
	    }
	    
	    
	  
	    

	    @Bean(name = "dsSlave")
		   
	    public DataSource slaveDataSource() {
	    	
	   return DataSourceBuilder.create()
			   .url("jdbc:oracle:thin:@//172.16.8.122:1521/prueba")
			   .username("GLOBALUDEC")
				.password("Migra2023") 
			   .driverClassName("oracle.jdbc.OracleDriver").build();
	    }
   //
	    @Bean(name = "jdbcSlave")
	    @Autowired
	    public JdbcTemplate slaveJdbcTemplate(@Qualifier("dsSlave") DataSource dsSlave) {
	        return new JdbcTemplate(dsSlave);
	    }
	 	    
	 	    
}
