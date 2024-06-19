import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DatosPersonales } from 'src/app/modelos/datos-personales';
import { Solicitud } from 'src/app/modelos/solicitud';
import { CodigoCapchaService } from 'src/app/servcios/codigo-capcha.service';
import { PersonaService } from 'src/app/servcios/persona.service';
import { TipoSolicitudService } from 'src/app/servcios/tipo-solicitud.service';


@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.scss']
})
export class PersonaComponent implements OnInit {
  solicitud: Solicitud = new Solicitud();
  datosPersonales:DatosPersonales[]; // trae los datos de la persona con su imagen incluida

  formulario_solicitud = new FormGroup({
    serv_id: new FormControl('', Validators.required),
    fecha_inicio: new FormControl('', Validators.required),
    fecha_fin: new FormControl('', Validators.required)
  });


  constructor(private PersonaServicio: PersonaService, private route: Router, private tipoSolicitudService: TipoSolicitudService
    , private cosigocapcga: CodigoCapchaService 
  ) { }

  
  generar_solicitud() {

    let data = this.formulario_solicitud.value; // traigo los valores del form
    
    this.solicitud.serv_ID = String(data.serv_id);
    this.solicitud.fecha_inicio = String(data.fecha_inicio);
    this.solicitud.fecha_fin = String(data.fecha_fin);

    // post form
   this.PersonaServicio.possolicitud(this.solicitud).subscribe(dato => {
    });

  }
 
  ngOnInit(): void { 

    this.tipoSolicitudService.traer_datos_personales().subscribe(dato => {
      this.datosPersonales = dato; 
    });
  }









}
