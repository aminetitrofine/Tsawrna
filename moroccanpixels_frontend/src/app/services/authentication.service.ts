import { Injectable } from '@angular/core';
import { User } from "../models/user";
import { BehaviorSubject, Observable } from "rxjs";
import { HttpClient, HttpResponse, HttpHeaders } from "@angular/common/http";
import { UserInfos } from "../models/user-infos";
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  authenticated$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private url = "http://localhost:8080"
  constructor(private _httpClient: HttpClient, private _cookieService: CookieService) { }

  authToken() {
    return this._cookieService.get("Authorization");
  }

  public login(user: User): void {
    console.log(user)
    this._httpClient.post(`${this.url}` + '/login', user, { observe: "response" }).subscribe({
      next: (resp) => {
        let authToken = resp.headers.get('Authorization')
        if (authToken != null) {
          this._cookieService.set('Authorization', authToken, 15, "/", undefined, true, "Strict");
        }
        this.authenticated$.next(true);
      },
      error: (error) => { alert("there is a problem") }
    })
  }

  public signup(user: UserInfos): Observable<object> {
    console.log(user);
    return this._httpClient.post(`${this.url}` + '/signup', user);
  }
  public signOut(): void {
    this._cookieService.delete('Authorization');
    this.authenticated$.next(false);
  }
  public authenticated() {
    return this.authenticated$;
  }
  public setAuthenticated() {
    let headers = new HttpHeaders({ 'Authorization': this.authToken() });
    let httpOptions = { headers: headers }
    this._httpClient.get(this.url, httpOptions).subscribe({
      next: () => this.authenticated$.next(true),
      error : ()=>this.authenticated$.next(false)
    });
  }
}
