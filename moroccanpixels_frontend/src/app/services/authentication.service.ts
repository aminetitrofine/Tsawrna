import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private url="http://localhost:8080/login"
  constructor(private httpClient: HttpClient) { }

  public login(user:User):Observable<object>{
    console.log(user)
    return this.httpClient.post(`${this.url}`,user);

  }
}
