import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/Modelo/Usuario';
import { ServiceService } from 'src/app/Service/usuario.service';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  usuarioRes: Usuario = new Usuario;
  
  constructor(private router: Router, private service: ServiceService) { }

  ngOnInit(): void {
  }

  Buscar(cedula:number) {
    this.service.findById(cedula).subscribe(resultado => { this.usuarioRes = resultado });
  }

  Eliminar(usuario: Usuario) {
    let pregunta = "¿Esta seguro que desea eliminar el usuario?";
    let verificar = confirm(pregunta);

    if (verificar) {
      this.service.eliminarUsuario(usuario).subscribe();   
      alert("usuario eliminado");
      location.reload();
    }
    
  }

  Editar(usuario: Usuario): void {
    localStorage.setItem("cedula", usuario.cedula.toString());
    this.router.navigate(["editar"]);
  }

  Rol(rol: number) {
    let aux;
    switch (rol) {
      case 1: aux = "adminsys"; break;
      case 2: aux = "asistente"; break;
      case 3: aux = "operaciones"; break;
      case 4: aux = "coordinador"; break;
      case 5: aux = "jefe"; break;
      case 6: aux = "director"; break;
      case 7: aux = "gerente"; break;
    }
    return aux;
  }

  Estado(estado: number) {
    let aux;
    switch (estado) {
      case 1: aux = "activo"; break;
      case 0: aux = "inactivo"; break;
    }
    return aux;
  }

}
