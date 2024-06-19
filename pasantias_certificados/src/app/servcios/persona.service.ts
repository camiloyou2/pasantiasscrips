import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Dtopersona } from '../modelos/dtopersona';
import { Imagencapcha } from '../modelos/imagencapcha';
import { Solicitud } from '../modelos/solicitud';
import { ObservarSolicitud } from '../modelos/observar-solicitud';
@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  
  // en esta parte se enlaza la url con el banckend de donde se traen los resultados



// url post solicitud
private postsolicitud = "http://localhost:8090/post_solicitud";
// url post solicitud
private ObservarSolicitudurl = "http://localhost:8090/DatoBasicos";
private verficarCertificadoUrl = "http://localhost:8090/VerficarCertificado";
private ModificarEstadoUrl = "http://localhost:8090/ModificarEstado";



  constructor( private httpclient: HttpClient ) { 
  }
  // 

 
  //


  // post de solicitudes 
//solicitud:Solicitud
  possolicitud(solicitud:Solicitud):Observable<object>{
   
    return this.httpclient.post(`${this.postsolicitud}`,solicitud);
     
  }
 // ObservarSolicitud(datosVerificar: String): Observable<object> {
  
   // return this.httpclient.post(`${this.ObservarSolicitudurl}`, datosVerificar);
    
  //}
  ObservarSolicitud(datosVerificar: string): Observable<ObservarSolicitud[]> {
    return this.httpclient.post<ObservarSolicitud[]>(this.ObservarSolicitudurl, { datosVerificar });
  }

  verficarCertificado(datosVerificar: any): Observable<ObservarSolicitud[]> {
    console.log(" ---------fnsbkjbkjn");
    return this.httpclient.post<ObservarSolicitud[]>(this.verficarCertificadoUrl, { datosVerificar });
  }

  
  ModificarEstadoSolicitud(datosVerificar: string): Observable<String> {
    console.log("  datos --------------------");
    return this.httpclient.post<String>(this.ModificarEstadoUrl, { datosVerificar });
  }

}
