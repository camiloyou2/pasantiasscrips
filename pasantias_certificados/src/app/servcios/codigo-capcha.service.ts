import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CodigoCapcha } from '../modelos/codigo-capcha';
import { Observable } from 'rxjs';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Pdf } from '../modelos/pdf';
import { ObservarSolicitud } from '../modelos/observar-solicitud';


@Injectable({
  providedIn: 'root'
})
export class CodigoCapchaService {
  safeImageUrl: SafeResourceUrl;
  constructor( private httpclient: HttpClient,private sanitizer: DomSanitizer) { }
  private base_url = "http://localhost:8090/c";
  private base_url_posts = "http://localhost:8090/loog";
  private url_pdf = "http://localhost:8090/pdf_bancario";
  private url_pdfbancario = "http://localhost:8090/pdf_laboral_funciones";

  codigoCapcgaEntrante():Observable<CodigoCapcha[]>{
  
    return this.httpclient.get<CodigoCapcha[]>(`${this.base_url}`);
  }


  verificar():Observable<String>{
    return this.httpclient.get<String>(`${this.base_url}`);
  }

 //pdf():Observable<Pdf>{
   // return this.httpclient.get<Pdf>(`${this.url_pdf}`);
  //}
  pdf(datosVerificar: string): Observable<ObservarSolicitud[]> {
    console.log(" --  ,mmm" + datosVerificar);
    return this.httpclient.post<ObservarSolicitud[]>(this.url_pdf, { datosVerificar });
  }
 
  pdfdos(datosVerificar: string): Observable<ObservarSolicitud[]> {
    console.log(" --  ,mmm" + datosVerificar);
    return this.httpclient.post<ObservarSolicitud[]>(this.url_pdfbancario, { datosVerificar });
  }

   sendDataToSpring(datosVerificar: any): Observable<object> {
  
    return this.httpclient.post(`${this.base_url_posts}`, datosVerificar);
    
  }


}
