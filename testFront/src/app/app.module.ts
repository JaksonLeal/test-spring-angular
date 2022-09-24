import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuscarComponent } from './Usuario/buscar/buscar.component';
import { EditarComponent } from './Usuario/editar/editar.component';
import { ListarComponent } from './Usuario/listar/listar.component';
import { LoginComponent } from './Usuario/login/login.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { IndexComponent } from './Usuario/index/index.component';
import { NavbarComponent } from './IU/navbar/navbar.component';
import { SingupComponent } from './Usuario/singup/singup.component';
import { AdminsysComponent } from './Dashboard/adminsys/adminsys.component';
import { AsistenteComponent } from './Dashboard/asistente/asistente.component';
import { authInterceptorProviders } from './Service/auth.interceptor';
import { SidebarComponent } from './IU/sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    BuscarComponent,
    EditarComponent,
    IndexComponent,
    ListarComponent,
    LoginComponent,
    SingupComponent,
    NavbarComponent,
    SidebarComponent,
    AdminsysComponent,
    AsistenteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
