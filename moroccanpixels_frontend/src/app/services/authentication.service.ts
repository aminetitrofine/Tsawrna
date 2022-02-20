import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {HttpClient,HttpResponse,HttpHeaders } from "@angular/common/http";
import {UserInfos} from "../models/user-infos";
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private url="http://localhost:8080"
  constructor(private _httpClient: HttpClient,private _cookieService: CookieService) { }

  authToken(){
    return this._cookieService.get("Authorization");
  }
  public login(user:User):Observable<HttpResponse<any>>{
    console.log(user)
    return this._httpClient.post(`${this.url}`+'/login',user,{observe: "response"});

  }
  public signup(user:UserInfos):Observable<object>{
    console.log(user);
    return this._httpClient.post(`${this.url}`+'/signup',user);
  }
  public isAuthenticated():Observable<any>{
    let headers = new HttpHeaders({'Authorization':this.authToken()});
    let httpOptions = {headers : headers}
    return this._httpClient.get(this.url,httpOptions);
  }
}
