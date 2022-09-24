import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Service/login.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  title = 'test';
  isLogged = false;
  user: any = null;

  constructor(public login: LoginService) { }

  ngOnInit(): void {

    if (this.login.isLoggedIn()) {
      this.isLogged = true;
      this.user = this.login.getUser();
    }

  }
  
}
