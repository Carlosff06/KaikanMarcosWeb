import { Injectable } from '@angular/core';
import { url } from '../constants/constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Plato } from '../models/plato';

@Injectable({
  providedIn: 'root'
})
export class PlatoService {
  private readonly apiUrl = `${url}/platos`;

  constructor(private readonly http:HttpClient) { }

  listarPlatos(): Observable<Plato[]>{
    return this.http.get<Plato[]>(this.apiUrl);
  }

  buscarPlatosPorCategoria(categoria: string): Observable<Plato[]>{
    return this.http.get<Plato[]>(`${this.apiUrl}/${categoria}`);
  }
}
