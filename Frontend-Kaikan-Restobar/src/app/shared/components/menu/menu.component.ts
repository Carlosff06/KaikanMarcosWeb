import { Component,  EventEmitter,  Input, Output, SimpleChanges } from '@angular/core';
import { Plato } from '../../../core/models/plato';
import { PlatoService } from '../../../core/services/plato.service';
import { CommonModule } from '@angular/common';
import { CarritoService } from '../../../core/services/carrito.service';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { UsuarioService } from '../../../auth/services/usuario.service';
import { TokenService } from '../../../auth/services/token.service';
import { Usuario } from '../../../core/models/usuario';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CategoriaService } from '../../../core/services/categoria.service';
import { Categoria } from '../../../core/models/categoria';

@Component({
  selector: 'app-menu',
  imports: [CommonModule,MatProgressSpinnerModule, ReactiveFormsModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  platos: Plato[] = []
  isProcessing=false;
  platoSeleccionado='';
  @Input() categoria: string = '';
  isLoguedIn = false;
  categorias:Categoria[]=[]
  platoEditando=-1;
  usuario:Usuario = new Usuario(null,'','','','','','','');
  platoAgregar = new FormGroup({
    descripcion:new FormControl('', Validators.required),
    img_url:new FormControl('', Validators.required),
    nombre:new FormControl('', Validators.required),
    precio:new FormControl('', [Validators.required, Validators.min(0)]),
    categoria_id:new FormControl(1, Validators.required)
  })
  platoEditar = new FormGroup({
    descripcion:new FormControl('', Validators.required),
    img_url:new FormControl('', Validators.required),
    nombre:new FormControl('', Validators.required),
    precio:new FormControl(0, [Validators.required, Validators.min(0)]),
    categoria_id:new FormControl(1, Validators.required)
  })
  @Output() datosEmitidos = new EventEmitter<boolean>();
  constructor(
    private readonly platoService: PlatoService,
    private readonly carritoService: CarritoService,
    private readonly usuarioService:UsuarioService,
    private readonly tokenService:TokenService,
    private readonly categoriaService:CategoriaService
  ){

  }

  agregarAlCarrito(plato: Plato){
    if(this.tokenService.getToken()){

      this.enviarDatos(true);
    this.carritoService.agregar(plato);
    } else{
      this.enviarDatos(false);
    }

  }

  enviarDatos(opcion:boolean) {

    this.datosEmitidos.emit(opcion);
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
    this.listarCategorias();
    this.usuarioService.getRol$().subscribe((nuevoRol) => {
      console.log(nuevoRol)
      this.usuario.rol = nuevoRol ?? '';

    });

  }

  cancelarEdicion(){
    this.platoEditando=-1;
    this.platoEditar.reset();
  }

  activarEdicion(plato:Plato){
    if(plato.id){
      this.platoEditando=plato.id;
      this.platoEditar.patchValue({
        descripcion: plato.descripcion,
        img_url:plato.img_url,
        nombre:plato.nombre,
        precio:plato.precio,
        categoria_id:plato.categoria.id
      })
    }
  }

  eliminarPlato(id:number| null){
    if (id) {
    this.platoService.eliminarPlato(id).subscribe({
      next:(res)=>{
        alert('Plato eliminado correctamente');
        this.listarPlatos();
        this.listarPlatosPorCategoria();
      },
      error:(err)=>{
        alert('Error al eliminar el plato');
        console.error('Error al eliminar el plato', err);
      }
    })
  } else{
    alert("Hubo un error al eliminar el plato, ID no válido");
  }
  }

  listarCategorias(){
    this.categoriaService.listarCategorias().subscribe({
      next: (response) => {
        this.categorias = response;
      },
      error: (error) => {
        console.error('Error al listar las categorias', error);
      }
    });
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

  agregarPlato() {
    if (this.platoAgregar.valid) {
      const nuevoPlato = new Plato(
        null,
        this.platoAgregar.value.nombre ?? '',
        this.platoAgregar.value.descripcion ?? '',
        this.platoAgregar.value.img_url ?? '',
        parseFloat(this.platoAgregar.value.precio ?? '0') ?? 0,
        new Categoria(this.platoAgregar.value.categoria_id ?? 0, '', '')
      )
      this.platoService.guardarPlato(nuevoPlato).subscribe({
        next: (response) => {
          alert('Plato agregado correctamente');
          this.listarPlatos();
          this.platoAgregar.reset();
          this.listarPlatosPorCategoria();
        },
        error: (error) => {
          console.error('Error al agregar el plato', error);
          alert('Hubo un error al agregar el plato');
        }
      });
    }
  }

  actualizarPlato() {
    this.platoEditar.markAllAsTouched()
    if (this.platoEditar.valid) {
      const nuevoPlato = new Plato(
        this.platoEditando,
        this.platoEditar.value.nombre ?? '',
        this.platoEditar.value.descripcion ?? '',
        this.platoEditar.value.img_url ?? '',
        this.platoEditar.value.precio ?? 0,
        new Categoria(this.platoEditar.value.categoria_id ?? 0, '', '')
      )
      this.platoService.guardarPlato(nuevoPlato).subscribe({
        next: (response) => {
          alert('Plato actualizado correctamente');
          this.listarPlatos();
          this.platoEditar.reset();
          this.platoEditando=-1
          this.listarPlatosPorCategoria();
        },
        error: (error) => {
          console.error('Error al actualizar el plato', error);
          alert('Hubo un error al actualizar el plato');
        }
      });
    }
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
