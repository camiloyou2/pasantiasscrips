
import { Component, OnInit, OnDestroy,HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { SolicitudVisualizar } from 'src/app/modelos/solicitud-visualizar';
import { TipoSolicitudService } from 'src/app/servcios/tipo-solicitud.service';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { DatosPersonales } from 'src/app/modelos/datos-personales';
import { CodigoCapchaService } from 'src/app/servcios/codigo-capcha.service';
import { Pdf } from 'src/app/modelos/pdf';
import { ObservarSolicitud } from 'src/app/modelos/observar-solicitud';

@Component({
  selector: 'app-ver-solicitudes',
  templateUrl: './ver-solicitudes.component.html',
  styleUrls: ['./ver-solicitudes.component.scss']
})
export class  VerSolicitudesComponent implements OnInit {
  datosPersonales:DatosPersonales[]; // trae los datos de la persona con su imagen incluida
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  pdf :ObservarSolicitud [];
  pdfdos :Pdf ;
  solicitudVisualizar: SolicitudVisualizar[];

 
  constructor( private codigoCapchaService:CodigoCapchaService,private tipoSolicitudService:TipoSolicitudService,private route:Router, private http: HttpClient){
  }
 
  
  ngOnInit(): void {
   this.tipoSolicitudService.traer_datos_personales().subscribe(dato => {
      this.datosPersonales = dato; 
    });
    this.tipoSolicitudService.solicitudes_generadas().subscribe(dato => {
      this.solicitudVisualizar = dato;
      this.dtTrigger.next(null);
     
    })
    

    
    this.dtOptions = {
      
      language: {
        url: '//cdn.datatables.net/plug-ins/2.0.2/i18n/es-ES.json',
   
    },
    
    lengthMenu:[5,10,15],
    searching: false ,
    pagingType: "simple"
    };
   

    
   
   

     
   
  }

   openPdf(soliid: any , tipoPdf:any , pege_id : any) {

   
   if ( tipoPdf === "Bancario"){
    this.codigoCapchaService.pdf(pege_id +"-"+tipoPdf+"-"+soliid).subscribe(dato => {
      this.pdf = dato;
     
         const url = 'data:application/pdf;base64,'+this.pdf[0].pdfBytes;
 
     // Open the new page in a new window or tab
     window.open(url, '_blank');
      });
     // URL of the page to open
   }else{
  
    this.codigoCapchaService.pdfdos(pege_id +"-"+tipoPdf+"-"+soliid).subscribe(dato => {
      this.pdf = dato;

         const url = 'data:application/pdf;base64,'+this.pdf[0].pdfBytes;
 
     // Open the new page in a new window or tab
     window.open(url, '_blank');
      });
   }
  

   
}
 neuevo(){

 }
   
  }

