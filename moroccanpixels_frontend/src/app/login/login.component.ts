import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../services/authentication.service";
import { Router } from "@angular/router";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  constructor(private _authService: AuthenticationService, private _cookieService: CookieService) { }
  onLogin(dataForm: any) {
    this._authService.login(dataForm).subscribe(
      {
        next: (resp) => {
          let authToken = resp.headers.get('Authorization')
          if (authToken != null) {
            this._cookieService.set('Authorization', authToken, 15, "/", undefined, true, "Strict");
          }
          alert("Login successfully")
        },
        error: (error) => { alert("there is a problem") }
      }
    )
  }
}
