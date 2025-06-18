import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarritoService } from '../../core/services/carrito.service';
import { Plato } from '../../core/models/plato';

type PlatoConCantidad = Plato & { cantidad: number };

@Component({
  selector: 'app-carrito-detalle',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carrito-detalle.component.html',
  styleUrls: ['./carrito-detalle.component.css']
})
export class CarritoDetalleComponent implements OnInit {
  items: PlatoConCantidad[] = [];
  total = 0;
  minimizado = false;

  constructor(private carritoService: CarritoService) {}

  ngOnInit(): void {
    this.carritoService.carrito$.subscribe((items) => {
      this.items = items;
      this.total = items.reduce((sum, item) => sum + item.precio * item.cantidad, 0);
    });
  }

  toggle() {
    this.minimizado = !this.minimizado;
  }

  aumentarCantidad(plato: PlatoConCantidad) {
    this.carritoService.agregar(plato);
  }

  disminuirCantidad(plato: PlatoConCantidad) {
    if (plato.cantidad > 1) {
      this.carritoService.reducir(plato);
    } else {
      this.eliminar(plato);
    }
  }

  eliminar(plato: PlatoConCantidad) {
    this.carritoService.eliminar(plato);
  }
}
