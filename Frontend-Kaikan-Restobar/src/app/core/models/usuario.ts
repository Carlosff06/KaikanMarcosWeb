export class Usuario{
  id:number|null;
  nombre:string;
  apellido:string;
  rol:string;
  direccion:string;
  contrasenia:string;
  email:string;
  telefono:string;

  constructor(id:number|null,nombre:string,apellido:string,role:string,direccion:string,
    contrasenia:string,email:string,telefono:string
  ){
    this.id=id;
    this.nombre=nombre;
    this.apellido=apellido;
    this.rol=role;
    this.direccion=direccion;
    this.contrasenia=contrasenia;
    this.email=email;
    this.telefono=telefono;
  }
}
