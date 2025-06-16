export class Categoria{
    id: number | null;
    nombre: string;
    img_url:string;

    constructor(id: number | null, nombre: string, img_url:string){
        this.id = id;
        this.nombre = nombre;
        this.img_url=img_url;
    }
}