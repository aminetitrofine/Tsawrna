import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserInfos} from "../models/user-infos";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private url="http://localhost:8080"
  constructor(private httpClient: HttpClient) { }

  public login(user:User):Observable<object>{
    console.log(user)
    return this.httpClient.post(`${this.url}`+'/login',user);

  }
  public signup(user:UserInfos):Observable<object>{
    console.log(user)
    return this.httpClient.post(`${this.url}`+'/signup',user);

  }
}
