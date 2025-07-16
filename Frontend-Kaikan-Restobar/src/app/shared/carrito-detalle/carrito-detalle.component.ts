import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarritoService } from '../../core/services/carrito.service';
import { Plato } from '../../core/models/plato';
import { Pedido } from '../../core/models/pedido';
import { Usuario } from '../../core/models/usuario';
import { UsuarioService } from '../../auth/services/usuario.service';
import { DetallePedido } from '../../core/models/detallepedido';
import { PedidoService } from '../../core/services/pedido.service';



@Component({
  selector: 'app-carrito-detalle',
  imports: [CommonModule],
  templateUrl: './carrito-detalle.component.html',
  styleUrl: './carrito-detalle.component.css'
})
export class CarritoDetalleComponent implements OnInit {

  total = 0;
  minimizado = false;
  pedido:Pedido = new Pedido(null,"Pendiente","","", new Usuario(null,"","","","","","",""));

  constructor(private readonly carritoService: CarritoService,
    private pedidoService:PedidoService,
    private usuarioService:UsuarioService) {
    this.pedido.usuario.email= this.usuarioService.getUsuario() ?? "";
  }

  ngOnInit(): void {
    this.carritoService.carrito$.subscribe((plato) => {
      console.log(plato)
      if(plato!=undefined&&plato!==null){
      const existente = this.pedido.detallesPedido.find(
        (detalle) => detalle.plato.id === plato.id
      );

      if (existente) {
        existente.cantidad++;
      } else {
        const nuevoDetalle = new DetallePedido(plato, 1);
        this.pedido.detallesPedido.push(nuevoDetalle);
      }


      this.total = this.pedido.detallesPedido.reduce((sum, item) => sum + item.plato.precio * item.cantidad, 0);
      }
    });
  }


  toggle() {
    this.minimizado = !this.minimizado;
  }

  aumentarCantidad(detalle:DetallePedido) {
    detalle.cantidad++;
    this.total = this.pedido.detallesPedido.reduce((sum, item) => sum + item.plato.precio * item.cantidad, 0);
  }

  disminuirCantidad(detalle: DetallePedido) {
  if (detalle.cantidad > 1) {
    detalle.cantidad--;
  } else {
    this.eliminar(detalle)
  }
  this.total = this.pedido.detallesPedido.reduce((sum, item) => sum + item.plato.precio * item.cantidad, 0);
}


  eliminar(detalle: DetallePedido) {
    const index = this.pedido.detallesPedido.indexOf(detalle);
    if (index !== -1) {
      this.pedido.detallesPedido.splice(index, 1);
    }
    this.actualizarTotal()
  }
  actualizarTotal(){
    this.total = this.pedido.detallesPedido.reduce((sum, item) => sum + item.plato.precio * item.cantidad, 0);
  }
  registrarPedido(){
    if(this.pedido.detallesPedido.length>0){
      this.pedido.usuario.email=this.usuarioService.getUsuario() ?? "";
      console.log(this.pedido)
    this.pedidoService.crearPedido(this.pedido).subscribe({
      next:(res)=>{
        alert("Pedido registrado Correctamente");
        this.pedido.detallesPedido=[];
        this.actualizarTotal();
      }, error:(err)=>{
        console.error(err);
        alert("Hubo un error registrando el pedido");
      }
    })
    }
  }
}
