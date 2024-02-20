import { Routes } from '@angular/router';
import { DefaultComponent } from './shared/layouts/default/default.component';
import { LoginComponent } from './components/login/login.component';
import { PrincipalComponent } from './shared/layouts/principal/principal.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
  {
    path: '',
    component: DefaultComponent,
    children: [
      {
        path: '',
        component: LoginComponent,
      },
    ],
  },
  {
    path: '',
    component: PrincipalComponent,
    children: [
      {
        path: '/home',
        component: HomeComponent,
      },
    ],
  },
];
