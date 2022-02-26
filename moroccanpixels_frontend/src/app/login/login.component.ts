import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../services/authentication.service";
import { ActivatedRoute, Route, Router } from "@angular/router";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  constructor(private _authService: AuthenticationService, private _cookieService: CookieService,
    private _router:Router,private _route:ActivatedRoute,private notifier:NotifierService) { }
  onLogin(dataForm:any) {
    this._authService.login(dataForm).subscribe({
      next: (resp) => {
        let authToken = resp.headers.get('Authorization')
        if (authToken != null) {
          this._cookieService.set('Authorization', authToken, 15, "/", undefined, true, "Strict");
        }

        this._authService.setAuthenticated();
        this._router.navigate(['']);
      },
      error: (error) => {
        this.notifier.notify('error', 'Invalid username or password');
      }
    });
    
  }
}
