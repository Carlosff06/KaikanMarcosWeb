import { Injectable } from '@angular/core';
import { Pedido } from '../models/pedido';
import { HttpClient } from '@angular/common/http';
import { url } from '../constants/constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private readonly apiUrl = `${url}/pedido`
  constructor(private readonly http:HttpClient) { }

  listarPedidosPorEmail(email: string): Observable<Pedido[]> {
  const emailEncoded = encodeURIComponent(email);
  return this.http.get<Pedido[]>(`${this.apiUrl}/listar-por-email?email=${emailEncoded}`);
}

  listarPedidos():Observable<Pedido[]>{
    return this.http.get<Pedido[]>(this.apiUrl);
  }

  actualizarPedido(pedido:Pedido){
    return this.http.put(`${this.apiUrl}/actualizar-pedido`, pedido);
  }

  crearPedido(pedido:Pedido){
    return this.http.post(`${this.apiUrl}/crear`, pedido);
  }
}
