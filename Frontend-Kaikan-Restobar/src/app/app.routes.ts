import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/auth/components/login/login.component';

export const routes: Routes = [
    {
        path: '',
        children: [
            { path: '', component: HomeComponent },
            { path: 'login', component: LoginComponent }
        ]
      }
];
