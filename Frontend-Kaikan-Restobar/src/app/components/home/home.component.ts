import { Component, ElementRef, ViewChild } from '@angular/core';
import { NavegacionComponent } from '../../shared/components/navegacion/navegacion.component';
import { CommonModule } from '@angular/common';
import { MenuComponent } from '../../shared/components/menu/menu.component';
import { Categoria } from '../../core/models/categoria';
import { CategoriaService } from '../../core/services/categoria.service';


@Component({
  selector: 'app-home',
  imports: [NavegacionComponent, CommonModule, MenuComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',


})
export class HomeComponent {
  // variables para almacenar las categorias
  categoria: string = 'todos';
  categorias:Categoria[] = [new Categoria(1,'Makis','https://th.bing.com/th/id/OIP.lJeUtARGoa08QAfhixSTXgAAAA?rs=1&pid=ImgDetMain')];
  @ViewChild('scrollContainer', { static: false }) scrollContainer!: ElementRef;

  constructor(private readonly categoriaService:CategoriaService){
    this.listarCategorias();
  }



scrollIzquierda() {
  this.scrollContainer.nativeElement.scrollBy({
    left: -230,
    behavior: 'smooth'
  });
}

scrollDerecha() {
  this.scrollContainer.nativeElement.scrollBy({
    left: 250,
    behavior: 'smooth'
  });
}

  setCategoria(nombre: string) {
    this.categoria =  nombre;
  }
  listarCategorias(){
    this.categoriaService.listarCategorias().subscribe({
      next:(res)=>{
        this.categorias=res;
      },
      error:(err)=>{
        console.error(err);
        alert("Hubo un error listando las categorias")
      }
    })
  }
}
