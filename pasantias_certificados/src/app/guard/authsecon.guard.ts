import { CanActivateChildFn } from '@angular/router';
import { TipoSolicitudService } from '../servcios/tipo-solicitud.service';
import { inject } from '@angular/core';

export const authseconGuard: CanActivateChildFn = (childRoute, state) => {
  const tipoSolicitudService = inject(TipoSolicitudService);
  
  return true;

};
// tipoSolicitudService.verificarauth()