import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Categoria } from '../models/categoria';
import { url } from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private readonly apiUrl = `${url}/categoria`

  constructor(private readonly http:HttpClient) { 

  }

  listarCategorias(){
    return this.http.get<Categoria[]>(this.apiUrl);
  }
}
