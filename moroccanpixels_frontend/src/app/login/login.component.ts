import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthenticationService, private router:Router,private cookieService: CookieService ) { }

  ngOnInit(): void {
  }

  onLogin(dataForm: any) {
    this.authService.login(dataForm).subscribe(resp =>{
      let authToken = resp.headers.get('Authorization')
      if(authToken!=null){
        this.cookieService.set( 'Authorization', authToken,15,"/",undefined,true,"Strict");
      }
      alert("Login successfully")
    },error => alert("there is a problem"))

  }
}
