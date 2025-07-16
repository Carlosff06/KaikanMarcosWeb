import { Plato } from "./plato";

export class DetallePedido{



    plato:Plato;
    cantidad:number;

    constructor(plato:Plato, cantidad:number){
      this.plato=plato;
      this.cantidad=cantidad;
    }
}
