import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { HttpClient } from '@angular/common/http';
import { ILogin, ILoginResponse } from '../models/auth.model';

import { map } from 'rxjs';
import { UsuarioService } from './usuario.service';
import { url } from '../../core/constants/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl=`${url}`
  constructor(private readonly tokenService: TokenService, private readonly http:HttpClient,
    private readonly usuarioService:UsuarioService
  ) { }

  onLogin(data: ILogin){
    console.log(data)
    return this.http
      .post<ILoginResponse>(`${this.apiUrl}/login`, data)
      .pipe(
        map((response)=> {
          if(response){
            console.log(response.access_token)
            console.log(response)
        this.tokenService.setToken(response.access_token);
            //////console.log(data.userid)
          this.usuarioService.setUsuario(data.email);
          this.usuarioService.setUsuarioId(response.user_id)
          this.usuarioService.setRol(response.user_role);
            //////console.log(this.usuarioService.getUsuario())
          }
          return response;
        })
      );
  }

  onLogout(){
    this.tokenService.removeToken()
  }
}
