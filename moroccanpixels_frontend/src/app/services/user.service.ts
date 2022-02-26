import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserSettings } from '../models/user-settings';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  host = this._authService.serverUrl();
  constructor(private _httpClient: HttpClient, private _authService: AuthenticationService) { }

  getUser() {
    return this._httpClient.get<UserSettings>(`${this.host}/user`,
      {headers: new HttpHeaders({ Authorization: this._authService.authToken() })}
    );
  }
}
