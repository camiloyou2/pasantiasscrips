import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { TipoSolicitudService } from '../servcios/tipo-solicitud.service';
import { DatosPersonales } from '../modelos/datos-personales';

export const authGuard: CanActivateFn = (route, state) => {
  const tipoSolicitudService = inject(TipoSolicitudService);
  
    return tipoSolicitudService.verificarauth();
};
