import {   HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";


import { TokenService } from "../../auth/services/token.service";



export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService = inject(TokenService);
  const token = tokenService.getToken();
  //console.log(token)
  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  return next(req);
};

