import { HttpResponseBase, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'moroccanpixels_frontend';
  authenticated = false;

  constructor(private _authService:AuthenticationService){
  }

  ngOnInit() {
    this._authService.isAuthenticated().subscribe({
      next: () => {
        this.authenticated=true;
        console.log(this.authenticated);
      }
    })
  }
}
