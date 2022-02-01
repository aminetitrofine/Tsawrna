import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthenticationService, private router:Router) { }

  ngOnInit(): void {
  }

  onLogin(dataForm: any) {
    this.authService.login(dataForm).subscribe(data =>{
      alert("Login successfully")
    },error => alert("there is a problem"))

  }
}
