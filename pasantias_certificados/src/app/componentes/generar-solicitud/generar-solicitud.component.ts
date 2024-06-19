import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Subject } from 'rxjs';
import { AppComponent } from 'src/app/app.component';
import { DatosPersonales } from 'src/app/modelos/datos-personales';
import { DtoTipoSolicitud } from 'src/app/modelos/dto-tipo-solicitud';
import { ObservarSolicitud } from 'src/app/modelos/observar-solicitud';
import { Pdf } from 'src/app/modelos/pdf';
import { SolicitudVisualizar } from 'src/app/modelos/solicitud-visualizar';
import { CodigoCapchaService } from 'src/app/servcios/codigo-capcha.service';
import { PersonaService } from 'src/app/servcios/persona.service';
import { TipoSolicitudService } from 'src/app/servcios/tipo-solicitud.service';

@Component({
  selector: 'app-generar-solicitud',
  templateUrl: './generar-solicitud.component.html',
  styleUrls: ['./generar-solicitud.component.scss']
})
export class GenerarSolicitudComponent  implements OnInit{

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  solicitudVisualizar: SolicitudVisualizar[];
  datosPersonales:DatosPersonales[]; // trae los datos de la persona con su imagen incluida
  dtoTipoSolicitud: DtoTipoSolicitud[];
  pdf :Pdf ;
  pdfdos :Pdf ;
  ObservarSolicitudM:ObservarSolicitud[];
  constructor(private tipoSolicitudService: TipoSolicitudService, 
    private codigoCapchaService:CodigoCapchaService, private personaservicio:PersonaService
   ) { }
  ngOnInit(): void {
    this.tipoSolicitudService.traer_datos_personales().subscribe(dato => {
     this.datosPersonales = dato; 
    });
    
    
    this.tipoSolicitudService.Solicitudestotales().subscribe(dato => {
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
 mostrar( soliid:any , identificador:any, tiposervi:any){
    
  this.personaservicio.ObservarSolicitud(identificador +"-"+ soliid+"-"+tiposervi).subscribe(dato => {
    this.ObservarSolicitudM = dato;
 
  }); 
 }

 ModificarEstado(soliid:any ){

  this.personaservicio.ModificarEstadoSolicitud(soliid).subscribe(
    dato => {
       console.log( dato);
   
    }
  ); 
  //this.ngOnInit();
  }
  
}
