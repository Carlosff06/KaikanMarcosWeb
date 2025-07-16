import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Plato } from '../models/plato';

@Injectable({ providedIn: 'root' })
export class CarritoService {
  private carritoSubject = new BehaviorSubject<Plato | null>(null);
  carrito$ = this.carritoSubject.asObservable();

  agregar(plato: Plato) {
    console.log(plato)
    this.carritoSubject.next(plato);
  }


}
