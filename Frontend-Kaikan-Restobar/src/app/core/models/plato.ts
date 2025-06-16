import { Categoria } from "./categoria";

export class Plato {
    id: number | null;
    nombre: string;
    descripcion: string;
    img_url: string;
    precio: number;
    categoria: Categoria;

    constructor( id:number | null, nombre: string, descripcion: string, img_src: string, precio: number, categoria: Categoria ){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img_url = img_src;
        this.precio = precio;
        this.categoria = categoria;
    }

}