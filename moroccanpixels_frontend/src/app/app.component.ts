import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Route, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'moroccanpixels_frontend';

  constructor(private _authService:AuthenticationService,private _router:Router,private _route:ActivatedRoute){
  }

  ngOnInit() {
    this._authService.setAuthenticated();
  }
  onSignOut(){
    this._authService.signOut();
  }
  authenticated(): any {
    return this._authService.authenticated();
  }
}
