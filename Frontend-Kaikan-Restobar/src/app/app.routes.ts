import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/auth/components/login/login.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { authGuard } from './core/guards/auth.guard';


export const routes: Routes = [
    {
        path: '',
        children: [
            { path: '', component: HomeComponent },

            { path: 'pedidos', component: PedidosComponent, canActivate: [authGuard] }
        ]
      }
];
