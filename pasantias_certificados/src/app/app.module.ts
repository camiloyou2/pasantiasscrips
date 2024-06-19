import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonaComponent } from './componentes/persona/persona.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GenerarSolicitudComponent } from './componentes/generar-solicitud/generar-solicitud.component';

import {DataTablesModule} from   'angular-datatables'

import { VerSolicitudesComponent } from './componentes/ver-solicitudes/ver-solicitudes.component';
import { AutentificarComponent } from './componentes/autentificar/autentificar.component';
import { VerificarComponent } from './componentes/verificar/verificar.component';
@NgModule({
  declarations: [
    AppComponent,
    PersonaComponent,
    GenerarSolicitudComponent,
    VerSolicitudesComponent,
    AutentificarComponent,
    VerificarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule,  ReactiveFormsModule, FormsModule, DataTablesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
