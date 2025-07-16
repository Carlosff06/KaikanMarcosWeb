import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { constants } from '../../core/constants/constants';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private readonly  http:HttpClient) {
    const rol = sessionStorage.getItem(constants.CURRENT_USER_ROLE);
  if (rol) {
    this.rolSubject.next(rol);
  }
  }

  private rolSubject = new BehaviorSubject<string | null>(null);

  getUsuarioId(){
    return sessionStorage.getItem(constants.CURRENT_USERID) ?? null;
  }


  setUsuarioId(usuarioId:string){
    sessionStorage.setItem(constants.CURRENT_USERID, usuarioId);
  }

  setUsuario(usuario:string){

    sessionStorage.setItem(constants.CURRENT_USER, usuario);
  }

  setRol(rol:string){
    sessionStorage.setItem(constants.CURRENT_USER_ROLE, rol)
    this.rolSubject.next(rol);
  }

 getRol$() {

    return this.rolSubject.asObservable();
    
  }

  getUsuario(): string | null {
    return sessionStorage.getItem(constants.CURRENT_USER) ?? null;
  }

  removeUsuario(){

    return sessionStorage.removeItem(constants.CURRENT_USER);
  }


}
