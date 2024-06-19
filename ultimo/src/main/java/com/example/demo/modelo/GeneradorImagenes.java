package com.example.demo.modelo;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.util.Base64Utils;

public class GeneradorImagenes {
	  int width = 200;
      int height = 50;
     Random rdm = new Random();
     int rl = rdm.nextInt(); // genera el numero aleatorio
     String base64Image ;
     byte[] imageBytes;
     BufferedImage cpimg;
	public GeneradorImagenes() {
		super();
		 try {
		       
	         String hash1 = Integer.toHexString(this.rl); // convierte un entero de decimal a exadecimal 
	         String capstr = hash1.substring(0, 5); // solo toma los primeros 4 caracteres de la cadena sumistrada        
	         // graficos 
	         Color background = new Color(250, 250, 250);
	         Color fbl = new Color(0, 0, 0);
	         Color relle = new Color(200, 200, 200);
	         Font fnt = new Font("Arial", 2, 40);
	          this.cpimg = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
	         Graphics g = this.cpimg.createGraphics(); 
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

	 /***
	  * 
	  *  // Especificar el archivo de salida
         File outputFile = new File("output.jpg");

         // Escribir la imagen en formato JPEG
         ImageIO.write(cpimg, "jpg", outputFile);
         
	  * ***/
	     
	     // Obtener los bytes de la imagen en formato JPEG como un array de bytes
	    this.imageBytes = baos.toByteArray();

	     // Convertir los bytes a Base64
	      this.base64Image = Base64.getEncoder().encodeToString(this.imageBytes);

	   
	     } catch (Exception ex) {
	         System.out.println(ex.getMessage());
	     }
	}
	public String getBase64Image() {
		return base64Image;
	}
	
	
	
	
}
