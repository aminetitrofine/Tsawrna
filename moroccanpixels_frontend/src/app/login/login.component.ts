import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../services/authentication.service";
import { ActivatedRoute, Route, Router } from "@angular/router";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  constructor(private _authService: AuthenticationService, private _cookieService: CookieService,private _router:Router,private _route:ActivatedRoute) { }
  onLogin(dataForm:any) {
    this._authService.login(dataForm);
    this._router.navigate([''])
  }
}
