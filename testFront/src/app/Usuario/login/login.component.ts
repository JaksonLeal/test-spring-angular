import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  loginData = {
    "email": '',
    "clave": '',
  }

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.loginService.isLoggedIn();
  }

  formSubmit() {
    if (this.loginData.email.trim() == '' || this.loginData.email.trim() == null) {
      console.log('El nombre de usuario es requerido !!');
      return;
    }

    if (this.loginData.clave.trim() == '' || this.loginData.clave.trim() == null) {
     console.log('La contraseña es requerida!!');
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);
        this.loginService.loginUser(data.token)
        this.loginService.getCurrentUser().subscribe((user: any) => {
          this.loginService.setUser(user);
          console.log(user);

          if (this.loginService.getUserRole() == "1") {
            //dashboard admin
            //window.location.href = "/admin";
            this.router.navigate(['adminsys']);
            this.loginService.loginStatusSubjec.next(true);
          } else if (this.loginService.getUserRole() == "2") {
            //user dashboard
            //window.location.href = "/user-dashboard";
            this.router.navigate(['asistente']);
            this.loginService.loginStatusSubjec.next(true);
          } else {
            this.loginService.logout();
          }
        })
      }, (error) => {
        console.log(error);
       console.log("detalles invalidos! intente nuevamente");
      }
    );
  }
  
}
