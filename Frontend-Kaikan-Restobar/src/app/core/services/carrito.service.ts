import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Plato } from '../models/plato';

@Injectable({ providedIn: 'root' })
export class CarritoService {
  private items: (Plato & { cantidad: number })[] = [];
  private carritoSubject = new BehaviorSubject<(Plato & { cantidad: number })[]>([]);

  carrito$ = this.carritoSubject.asObservable();

  agregar(plato: Plato) {
    const index = this.items.findIndex(p => p.id === plato.id);
    if (index !== -1) {
      this.items[index].cantidad++;
    } else {
      this.items.push({ ...plato, cantidad: 1 });
    }
    this.carritoSubject.next(this.items);
  }

  reducir(plato: Plato) {
    const index = this.items.findIndex(p => p.id === plato.id);
    if (index !== -1) {
      this.items[index].cantidad--;
      if (this.items[index].cantidad <= 0) {
        this.items.splice(index, 1);
      }
      this.carritoSubject.next(this.items);
    }
  }

  eliminar(plato: Plato) {
    this.items = this.items.filter(p => p.id !== plato.id);
    this.carritoSubject.next(this.items);
  }

  limpiar() {
    this.items = [];
    this.carritoSubject.next([]);
  }

  obtenerItems(): (Plato & { cantidad: number })[] {
    return this.items;
  }

  total(): number {
    return this.items.reduce((sum, item) => sum + item.precio * item.cantidad, 0);
  }
}
