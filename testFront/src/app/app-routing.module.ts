import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminsysGuard } from './Service/Guard/adminsys.guard';
import { AsistenteGuard } from './Service/Guard/asistente.guard';
import { AdminsysComponent } from './Dashboard/adminsys/adminsys.component';
import { AsistenteComponent } from './Dashboard/asistente/asistente.component';
import { BuscarComponent } from './Usuario/buscar/buscar.component';
import { EditarComponent } from './Usuario/editar/editar.component';
import { ListarComponent } from './Usuario/listar/listar.component';
import { LoginComponent } from './Usuario/login/login.component';
import { SingupComponent } from './Usuario/singup/singup.component';
import { IndexComponent } from './Usuario/index/index.component';

const routes: Routes = [

  {path: 'buscar', component: BuscarComponent},
  {path: 'editar', component: EditarComponent},
  {path: 'listar', component: ListarComponent},
  {path: 'login', component: LoginComponent},
  {path: 'singup', component: SingupComponent},
  {path: '', component: IndexComponent},
  
  {
    path: "adminsys",
    component: AdminsysComponent,
    pathMatch: "full",
    canActivate: [AdminsysGuard] //registrar el guard
  },
  {
    path: "asistente",
    component: AsistenteComponent,
    pathMatch: "full",
    canActivate: [AsistenteGuard] //registrar el guard
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
