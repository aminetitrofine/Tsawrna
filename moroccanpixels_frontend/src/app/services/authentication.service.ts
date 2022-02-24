import { Injectable } from '@angular/core';
import { User } from "../models/user";
import { BehaviorSubject, Observable } from "rxjs";
import { HttpClient, HttpResponse, HttpHeaders } from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service';
import { AuthenticatedUser } from '../models/authenticated-user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  authenticated$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  _authenticatedUser!:AuthenticatedUser;
  private url = "http://localhost:8080"
  constructor(private _httpClient: HttpClient, private _cookieService: CookieService) { }

  serverUrl(){
    return this.url;
  }
  authToken() {
    return this._cookieService.get("Authorization");
  }

  public login(user: User): void {
    console.log(user)
    this._httpClient.post(`${this.url}` + '/login', user, { observe: "response" , responseType:'text'}).subscribe({
      next: (resp) => {
        let authToken = resp.headers.get('Authorization')
        if (authToken != null) {
          this._cookieService.set('Authorization', authToken, 15, "/", undefined, true, "Strict");
        }

        this.setAuthenticated();
      },
      error: (error) => {
      }
    })
  }


  public signup(userForm: any) {
    console.log(userForm);
    this._httpClient.post(`${this.url}` + '/signup', userForm).subscribe({
      next: () => {
        alert("signup successful")
      },
      error: () => alert("there is a problem")
    });
  }
  public signOut(): void {
    this._cookieService.delete('Authorization');
    this.authenticated$.next(false);
  }
  public authenticated() {
    return this.authenticated$;
  }
  public authenticatedUsername() {
    return this._authenticatedUser.username;
  }
  public authenticatedUserRole() {
    return this._authenticatedUser.role;
  }
  public setAuthenticated(){
    this.authenticatedUser().subscribe({
      next: (data) => {
        this.authenticated$.next(true);
        this._authenticatedUser = data ;
      },
      error: () => this.authenticated$.next(false)
    })
  }
  public authenticatedUser() {
    let headers = new HttpHeaders({ 'Authorization': this.authToken() });
    let httpOptions:Object = { headers: headers, responseType: 'json'}
    return this._httpClient.get<AuthenticatedUser>(this.url, httpOptions);
  }
}
