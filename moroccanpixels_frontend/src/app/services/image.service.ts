import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {AuthenticationService} from "./authentication.service";
@Injectable({
  providedIn: 'root'
})
export class ImageService {
  public host: string = "http://localhost:8080"

  constructor(private http: HttpClient, private _cookieService: CookieService, private authService: AuthenticationService) {
  }

  upload(file: File): Observable<HttpEvent<{}>> {
    let formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<string[]>(`${this.host}/image`, formData, {
      headers : new HttpHeaders({ 'Authorization': this.authService.authToken() }),
      reportProgress: true,
      observe: 'events'
    });
  }

}
