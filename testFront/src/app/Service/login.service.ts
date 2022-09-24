import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helperURL';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubjec = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  public generateToken(loginData:any){
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  public getCurrentUser(){
    return this.http.get(`${baseUrl}/actual-usuario`);
  }

  //iniciamos sesión y establecemos el token en el localStorage
  public loginUser(token:any){
    localStorage.setItem('token', token);
    return true;
  }

  public isLoggedIn(){ //comprueba si esta o no esta conectado
    let tokenStr = localStorage.getItem('token');
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null){
      return false;
    }else{
      return true;
    }
  }

  //cerramos sesion y eliminamos el token del localStorage
  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //obtenemos el token
  public getToken(){
    return localStorage.getItem('token');
  }

  public setUser(user:any){
    localStorage.setItem('user', JSON.stringify(user)); //convierte json a string
  }

  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr != null){
      return JSON.parse(userStr); //convierte de string a json
    }else{
      this.logout();
      return null;
    }
  }

  public getUserRole(){
    let user = this.getUser();
    return user.authorities[0].authority;
  }

}
