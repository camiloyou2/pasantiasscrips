import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ObservarSolicitud } from 'src/app/modelos/observar-solicitud';
import { PersonaService } from 'src/app/servcios/persona.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-verificar',
  templateUrl: './verificar.component.html',
  styleUrls: ['./verificar.component.scss']
})
export class VerificarComponent {
  formulariologin = new FormGroup ({
    concecutivo: new FormControl('', Validators.required),
  
  });

  obbservarsolicitud: ObservarSolicitud[]
  constructor( private personaservicio:PersonaService
   ) { }
  verificar(){
    let data = this.formulariologin.value;

     this.personaservicio.verficarCertificado(data.concecutivo).subscribe(dato => {
      this.obbservarsolicitud = dato;
      if (this.obbservarsolicitud == null){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Certificado no existente'
        });
      }
      else{
        const url = 'data:application/pdf;base64,'+this.obbservarsolicitud[0].pdfBytes;
 
        // Open the new page in a new window or tab
        window.open(url, '_blank');
      }
      
    }); 
  }
}
