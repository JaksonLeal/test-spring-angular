import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/Modelo/Usuario';
import { ServiceService } from 'src/app/Service/usuario.service';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {

  usuario:Usuario = new Usuario();
  usuarioNuevo:Usuario = new Usuario();
  constructor(private router:Router, private service:ServiceService) {}

  ngOnInit(): void {   
    let aux = ""+localStorage.getItem("cedula"); 
    let cedula = parseInt(aux);   
    this.service.findById(cedula).subscribe(data=>{this.usuario=data})
  }

  Actualizar(){
    this.usuarioNuevo.cedula = this.usuario.cedula;
    this.usuarioNuevo.primerNombre = this.usuario.primerNombre;
    this.usuarioNuevo.segundoNombre = this.usuario.segundoNombre;
    this.usuarioNuevo.primerApellido = this.usuario.primerApellido;
    this.usuarioNuevo.segundoApellido = this.usuario.segundoApellido;
    this.usuarioNuevo.clave = this.usuario.clave;
    this.usuarioNuevo.email = this.usuario.email;
    this.usuarioNuevo.nitEmpresa = this.usuario.nitEmpresa;
    this.usuarioNuevo.rol = this.usuario.rol;
    this.usuarioNuevo.estado = this.usuario.estado;
    this.usuarioNuevo.username = this.usuario.username;
    //console.log("usuarioservice"+JSON.stringify(this.usuarioNuevo.username))
    this.service.actualizarUsuario(this.usuarioNuevo).subscribe(data=>{
      console.log(data);
      alert("se actualizo con exito");
      this.router.navigate(["listar"])});
  }

}
