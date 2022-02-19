import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {HttpClient,HttpResponse} from "@angular/common/http";
import {UserInfos} from "../models/user-infos";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private url="http://localhost:8080"
  constructor(private _httpClient: HttpClient) { }

  public login(user:User):Observable<HttpResponse<any>>{
    console.log(user)
    return this._httpClient.post(`${this.url}`+'/login',user,{observe: "response"});

  }
  public signup(user:UserInfos):Observable<object>{
    console.log(user)
    return this._httpClient.post(`${this.url}`+'/signup',user);

  }
}
