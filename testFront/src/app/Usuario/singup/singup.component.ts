import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/Modelo/Usuario';
import { ServiceService } from 'src/app/Service/usuario.service';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {

  usuario: Usuario = new Usuario();
  constructor(private router: Router, private service: ServiceService) { }

  ngOnInit(): void {
  }

  Guardar() {
    this.service.registrarUsuario(this.usuario).subscribe();
    alert("Se agrego con exito");
    this.router.navigate(["/"]);
  }

}
