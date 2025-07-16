import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { UsuarioService } from '../../../services/usuario.service';


@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm:FormGroup;
   @Output() datosEmitidos = new EventEmitter<boolean>();

  constructor(private formBuilder:FormBuilder, private router:Router,
    private authService:AuthService, private usuarioService:UsuarioService
  ){
    this.loginForm = this.formBuilder.group({
      email: new FormControl('carlos@gmail.com', [Validators.required, Validators.email]),
      contrasenia: new FormControl('1234', [Validators.required])
    });
  }

  onLogin(){

    if(this.loginForm.valid){

      this.authService.onLogin(this.loginForm.value).subscribe({
        next: (value) => {

        this.enviarDatos(true)
        }, error: (err) => {
          alert('Usuario Incorrecto')
        }
      })
  }
  else {
    ////console.log('invalidado')
    this.loginForm.markAllAsTouched();
  }
  }

  enviarDatos(logueado:boolean) {

    this.datosEmitidos.emit(logueado);
  }

  registrarse(){

  }
}
