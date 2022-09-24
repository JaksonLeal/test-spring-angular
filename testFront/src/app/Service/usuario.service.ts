import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../Modelo/Usuario';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
 
  constructor(private http:HttpClient) {}

  private Url = 'http://localhost:8080/api/usuarios';
  //`
  getListUsuarios() {
    return this.http.get<Usuario[]>(this.Url);
  }

  registrarUsuario(usuario:Usuario){
    return this.http.post<Usuario>(`${this.Url}/registrar`, usuario);
  }

  eliminarUsuario(usuario:Usuario){
    return this.http.delete<Usuario>(this.Url+"/"+usuario.cedula);
  }
  
  actualizarUsuario(usuario:Usuario){
    return this.http.put<Usuario>(`${this.Url}/${usuario.cedula}`, usuario);
  }

  findById(cedula:number){
    return this.http.get<Usuario>(this.Url+"/"+cedula);
  }

}
