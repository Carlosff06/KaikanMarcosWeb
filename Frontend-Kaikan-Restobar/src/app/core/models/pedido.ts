import { Usuario } from "./usuario";

export class Pedido{
  id:number|null;
  estado:string;
  fecha_pago:string;
  fecha_pedido:string;
  usuario:Usuario;

  constructor(id:number|null,estado:string,fecha_pago:string,
    fecha_pedido:string,usuario:Usuario
  ){
    this.id=id;
    this.estado=estado;
    this.fecha_pago=fecha_pago;
    this.fecha_pedido=fecha_pedido;
    this.usuario=usuario
  }
}
