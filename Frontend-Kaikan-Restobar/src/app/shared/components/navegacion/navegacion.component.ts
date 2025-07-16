import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navegacion',
  imports: [RouterLink],
  templateUrl: './navegacion.component.html',
  styleUrl: './navegacion.component.css'
})
export class NavegacionComponent {

  @Input() isLoguedIn=false;
  @Output() datosEmitidos = new EventEmitter<boolean>();
  enviarDatos(opcion:boolean) {
    console.log(opcion)
    this.datosEmitidos.emit(opcion);
  }

}
