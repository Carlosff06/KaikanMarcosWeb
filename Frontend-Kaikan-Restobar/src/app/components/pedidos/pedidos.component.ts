import { Component } from '@angular/core';
import { PedidoService } from '../../core/services/pedido.service';
import { UsuarioService } from '../../auth/services/usuario.service';
import { Pedido } from '../../core/models/pedido';
import { CommonModule } from '@angular/common';
import { NavegacionComponent } from '../../shared/components/navegacion/navegacion.component';
import { Usuario } from '../../core/models/usuario';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pedidos',
  imports: [CommonModule, NavegacionComponent, FormsModule],
  templateUrl: './pedidos.component.html',
  styleUrl: './pedidos.component.css'
})
export class PedidosComponent {
  private email:string='';
  pedidos:Pedido[]=[]
  usuario:Usuario = new Usuario(null,'','','','','','','')
  pedidoEditar=new Pedido(null,'','','',new Usuario(null,'','','','','','',''))
  constructor(private pedidoService:PedidoService, private usuarioService:UsuarioService){
    this.email= this.usuarioService.getUsuario() ?? "";

    this.usuarioService.getRol$().subscribe((nuevoRol) => {

      this.usuario.rol = nuevoRol ?? '';
      if(this.usuario.rol==='ADMIN'){
        this.listarPedidos();
      } else{
        this.listarPedidosPorEmail();
      }
    });
  }

  listarPedidosPorEmail(){
    this.pedidoService.listarPedidosPorEmail(this.email).subscribe({
      next:(res)=>{
        console.log(res)
        this.pedidos=res;
      }, error:(err)=>{
        console.error(err);
      }
    })
  }

  actualizarPedido(pedido:Pedido){
    this.pedidoService.actualizarPedido(pedido).subscribe({
      next:(res)=>{

      }, error:(err)=>{
        console.error(err);
        alert('Hubo un error actualizando el pedido')
      }
    })
  }

  listarPedidos(){
    this.pedidoService.listarPedidos().subscribe({
      next:(res)=>{
        this.pedidos=res;
      }, error:(err)=>{
        console.error(err);
        alert("Se ha producido un error listando los pedidos");
      }
    })
  }



}
