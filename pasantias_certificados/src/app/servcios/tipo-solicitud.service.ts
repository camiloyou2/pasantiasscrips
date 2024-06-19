import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DtoTipoSolicitud } from '../modelos/dto-tipo-solicitud';
import { Observable } from 'rxjs';
import { SolicitudVisualizar } from '../modelos/solicitud-visualizar';
import { DatosPersonales } from '../modelos/datos-personales';

@Injectable({
  providedIn: 'root'
})
export class TipoSolicitudService {
  
  private UrlSolicitudTotal ="http://localhost:8090/Solicitudestotales";

  private url_solicutudes_generadas = "http://localhost:8090/solicitues_visualizar";
// traer datos persnales de la persona

private datospersonalesurl = "http://localhost:8090/datospersonales";

datosPersonales:DatosPersonales[];
  constructor( private httpclient: HttpClient) { 


  }
 
  solicitudes_generadas():Observable<SolicitudVisualizar[]>{
    return this.httpclient.get<SolicitudVisualizar[]>(`${this.url_solicutudes_generadas}`);
  }

  Solicitudestotales():Observable<SolicitudVisualizar[]>{
    return this.httpclient.get<SolicitudVisualizar[]>(`${this.UrlSolicitudTotal}`);
  }
 

 // trae los datos personales del usuario con su fotografia
  traer_datos_personales():Observable<DatosPersonales[]>{
    return this.httpclient.get<DatosPersonales[]>(`${this.datospersonalesurl}`);
  }

 
     verificarauth():boolean{
      this.traer_datos_personales().subscribe(dato => {
        this.datosPersonales = dato; 
     
         })
           
if( this.datosPersonales[0].pege_ID === '192235'){
  return true;
}
else{
  return false;
}

         
    }




}
