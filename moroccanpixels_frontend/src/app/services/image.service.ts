import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { CookieService } from "ngx-cookie-service";
import { AuthenticationService } from "./authentication.service";
import { Image } from "../models/image"
@Injectable({
  providedIn: 'root'
})
export class ImageService {

  public host: string = "http://localhost:8080"

  constructor(private http: HttpClient, private authService: AuthenticationService) {
  }

  upload(file: File): Observable<HttpEvent<{}>> {
    let formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<string[]>(`${this.host}/image`, formData, {
      headers: new HttpHeaders({ Authorization: this.authService.authToken() }),
      reportProgress: true,
      observe: 'events'
    });
  }

  trendImages() {
    return this.http.get<Image[]>(`${this.host}/image`, {
      headers: new HttpHeaders({ Authorization: this.authService.authToken() })
    });
  }
  userGallery(username: string) {

    return this.http.get<Image[]>(`${this.host}/${username}/gallery`, {
      headers: new HttpHeaders({ Authorization: this.authService.authToken() })
    });
  }

  search(query: string) {
    return this.http.get<Image[]>(`${this.host}/search`, {
      headers: new HttpHeaders({ Authorization: this.authService.authToken() }),
      params: { 'q': query }
    });
  }
  saveImage(id: number) {
    return this.http.post(`${this.host}/image/${id}/save`,
      {},
      {
        headers: new HttpHeaders({ Authorization: this.authService.authToken() })
      }
    )
  }
  unsaveImage(id: number) {
    return this.http.post(`${this.host}/image/${id}/unsave`,
      {},
      {
        headers: new HttpHeaders({ Authorization: this.authService.authToken() })
      }
    )
  }
  downloadImage(id: number) {
    return this.http.get(`${this.host}/image/${id}/download`,
      {
        headers: new HttpHeaders({ Authorization: this.authService.authToken() })
      })
  }
}
