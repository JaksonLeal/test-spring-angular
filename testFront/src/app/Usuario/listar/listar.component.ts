import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/Modelo/Usuario';
import { ServiceService } from '../../Service/usuario.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  usuarios: Usuario[];
  constructor(private service: ServiceService, private router: Router) { }

  ngOnInit(): void {
    this.service.getListUsuarios().subscribe(respuesta => { this.usuarios = respuesta });
  }

  Eliminar(usuario: Usuario) {
    let pregunta = "¿Esta seguro que desea eliminar el usuario con cedula " + usuario.cedula + "?";
    let verificar = confirm(pregunta);

    if (verificar) {
      this.service.eliminarUsuario(usuario).subscribe();
      this.usuarios = this.usuarios.filter(u => u !== usuario);
      alert("usuario eliminado");
    }
  }

  Editar(usuario: Usuario) {
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

}
