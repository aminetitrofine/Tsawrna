import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { MatMenuTrigger } from '@angular/material/menu'
import { ImageService } from './services/image.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'moroccanpixels_frontend';                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
  constructor(private _authService:AuthenticationService,private _router:Router,private _route:ActivatedRoute,private _imageService : ImageService){
  }

  ngOnInit() {
    this._authService.setAuthenticated();
  }
  onSignOut(){
    this._authService.signOut();
  }
  authenticated(){
    return this._authService.authenticated();
  }
  authenticatedUsername() {
    return this._authService.authenticatedUsername();
  }
  authenticatedUserRole() {
    return this._authService.authenticatedUserRole();
  }

  onSearch(query:string){
    this._imageService.search(query);
  }
  url(){
    return this._authService.serverUrl();
  }
}
