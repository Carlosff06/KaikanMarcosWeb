import { Component, ElementRef, ViewChild } from '@angular/core';
import { NavegacionComponent } from '../../shared/components/navegacion/navegacion.component';
import { CommonModule } from '@angular/common';
import { MenuComponent } from '../../shared/components/menu/menu.component';
import { Categoria } from '../../core/models/categoria';
import { CategoriaService } from '../../core/services/categoria.service';
import { CarritoDetalleComponent } from '../../shared/carrito-detalle/carrito-detalle.component';
import { LoginComponent } from '../../auth/auth/components/login/login.component';
import { UsuarioService } from '../../auth/services/usuario.service';
import { TokenService } from '../../auth/services/token.service';
import { Usuario } from '../../core/models/usuario';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavegacionComponent, CommonModule, MenuComponent, CarritoDetalleComponent, LoginComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'

})
export class HomeComponent {
  // variables para almacenar las categorias
  categoria: string = 'todos';
  usuario:Usuario = new Usuario(null,'','','','','','','')
  categorias:Categoria[] = [new Categoria(1,'Makis','https://th.bing.com/th/id/OIP.lJeUtARGoa08QAfhixSTXgAAAA?rs=1&pid=ImgDetMain')];
  @ViewChild('scrollContainer', { static: false }) scrollContainer!: ElementRef;
  isLoguedIn=false;
  mostrarLogin=false;
  constructor(private readonly categoriaService:CategoriaService,private tokenService:TokenService, private usuarioService:UsuarioService){
    this.listarCategorias();
    if(this.tokenService.getToken()){
      this.isLoguedIn=true;
    }
    this.usuarioService.getRol$().subscribe((nuevoRol) => {
      
      this.usuario.rol = nuevoRol ?? '';

    });
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

recibirLogin(event:boolean){
  if(event===true){
    this.isLoguedIn=true;
    this.mostrarLogin=false;
  }
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

  recibirDatosNavegacion(event:boolean){
     console.log(event)
    if(event){

      this.mostrarLogin=true;
      this.isLoguedIn=false;
    }
  }

  recibirDatos(datos:boolean){
    if(datos){
      this.mostrarLogin=false;
      this.isLoguedIn=true;
    }
    else{
      this.mostrarLogin=true;
      this.isLoguedIn=false;
    }
  }
}
