import { Component,  Input, SimpleChanges } from '@angular/core';
import { Plato } from '../../../core/models/plato';
import { PlatoService } from '../../../core/services/plato.service';
import { CommonModule } from '@angular/common';
import { CarritoService } from '../../../core/services/carrito.service';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-menu',
  imports: [CommonModule,MatProgressSpinnerModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  platos: Plato[] = []
  isProcessing=false;
  platoSeleccionado='';
  @Input() categoria: string = '';
  constructor(
    private readonly platoService: PlatoService,
    private readonly carritoService: CarritoService,
  ){}

  agregarAlCarrito(plato: Plato){
    this.carritoService.agregar(plato);
    console.log('Plato agregado al carrito:', plato);
  }


  ngOnChanges(changes: SimpleChanges) {
    console.log('Cambios en el componente Menu:', changes);
    if (changes['categoria']) {
      if(this.categoria=='todos'){
        this.listarPlatos()
      } else{

      this.listarPlatosPorCategoria();
      }
    }
  }
  ngOnInit(){
    this.listarPlatos();
  }

  listarPlatos(){
    this.isProcessing=true;
    this.platoService.listarPlatos().subscribe({
      next: (response) => {
        this.isProcessing=false;
        this.platos = response;
      }
      , error: (error) =>{
        this.isProcessing=false;
        console.error('Error al listar los platos', error);
      }
    })
  }

  mostrarDescripcion(plato:Plato){
    this.platoSeleccionado=plato.nombre;
  }
  ocultarDescripcion(){

    this.platoSeleccionado=''
  }

  listarPlatosPorCategoria(){
    this.isProcessing=true;
    this.platoService.buscarPlatosPorCategoria(this.categoria).subscribe({
      next: (response) => {
        this.platos = response;
        this.isProcessing=false;
      }
      , error: (error) =>{
        console.error('Error al listar los platos', error);
        this.isProcessing=false;
      }
    })
  }
}