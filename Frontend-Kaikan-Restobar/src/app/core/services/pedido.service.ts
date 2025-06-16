import { Injectable } from '@angular/core';
import { Pedido } from '../models/pedido';
import { HttpClient } from '@angular/common/http';
import { url } from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private readonly apiUrl = `${url}/pedido`
  constructor(private readonly http:HttpClient) { }

  guardarPedido(pedido:Pedido){
    return this.http.post(this.apiUrl, pedido);
  }
}
