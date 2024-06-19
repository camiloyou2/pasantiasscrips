import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonaComponent } from './componentes/persona/persona.component';
import { GenerarSolicitudComponent } from './componentes/generar-solicitud/generar-solicitud.component';


import { AppComponent } from './app.component';
import { authGuard } from './guard/auth.guard';
import { VerSolicitudesComponent } from './componentes/ver-solicitudes/ver-solicitudes.component';
import { authseconGuard } from './guard/authsecon.guard';
import { AutentificarComponent } from './componentes/autentificar/autentificar.component';
import { VerificarComponent } from './componentes/verificar/verificar.component';


const routes: Routes = [


    {path : 'Solicitudes' ,component:PersonaComponent },//
    {path : 'Verificar' ,component:VerificarComponent },//
    {path : 'GenerarSolicitud' ,component:GenerarSolicitudComponent },//
    {path : 'Solicitudes_realizadas' ,component:VerSolicitudesComponent },//
    {path : 'autentificar' ,component:AutentificarComponent },//
    { path: '',   redirectTo: 'autentificar', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
