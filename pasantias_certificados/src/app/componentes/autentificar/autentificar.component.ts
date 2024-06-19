import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CodigoCapcha } from 'src/app/modelos/codigo-capcha';
import { DatosPersonales } from 'src/app/modelos/datos-personales';
import { DatosVerificar } from 'src/app/modelos/datos-verificar';
import { Pdf } from 'src/app/modelos/pdf';
import { CodigoCapchaService } from 'src/app/servcios/codigo-capcha.service';
import { TipoSolicitudService } from 'src/app/servcios/tipo-solicitud.service';

@Component({
  selector: 'app-autentificar',
  templateUrl: './autentificar.component.html',
  styleUrls: ['./autentificar.component.scss']
})
export class AutentificarComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  pdf :Pdf ;

 
title = "pasantia" ;

mensaje_error = "";
codigoCapcha: CodigoCapcha[];
 datosverificar: DatosVerificar[] = [];
  reurnvalor:String ="";
nuevo = [];
 lista: String[] = [];

public verificar:DatosVerificar = new DatosVerificar();
 // formulario del capcha
  formulariologin = new FormGroup ({
    cedula: new FormControl('', Validators.required),
    fecha_de_expedicion: new FormControl('', Validators.required),
    Codigo: new FormControl('', Validators.required),
    tipo_de_documento  : new FormControl('', Validators.required),
  });

  imgcapcha = "";
  constructor( private codigoCapchaService:CodigoCapchaService,private route:Router, private tipoSolicitudService:TipoSolicitudService){
  }

  verificar_login(){
    this.codigoCapchaService.codigoCapcgaEntrante().subscribe(dato => {
      this.codigoCapcha = dato;
    });
}
cargarr(comparar:String){

  let data = this.formulariologin.value;
  this.codigoCapcha[0].codigo

  if(data.Codigo  === this.codigoCapcha[0].codigo){
  
 
   let valor = "true";
   if(comparar === valor){
  

 
    this.route.navigate(['Solicitudes'])
   }
   else{
    this.mensaje_error="datos_erroneos";
    this.verificar_login();
   }
  
  }
  else{
    this.mensaje_error="capcha";
    this.verificar_login();
    
  


  }
}




verificar_login_ultimo(){

  let data = this.formulariologin.value;
  var valor:String ;
  this.lista = [];
  this.lista.push(String(data.tipo_de_documento));
  this.lista.push(String(data.cedula));
  this.lista.push(String(data.fecha_de_expedicion));
  this.codigoCapchaService.sendDataToSpring(this.lista).subscribe(response => {
     valor= Object.values(response)[0].existe_REGISTRO

    this.cargarr(valor);  // este lo documente porque es de manera local
   

      } );

}


ngOnInit(): void {
 
  
  this.verificar_login();
}



}
