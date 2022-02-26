import { Component, OnInit } from '@angular/core';
import { UserSettings } from '../models/user-settings';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  url = this._authService.serverUrl();

  constructor(private _userService:UserService,private _authService:AuthenticationService) { }

  userSettings !: UserSettings;

  ngOnInit(): void {
    this._userService.getUser().subscribe({
      next : (user:UserSettings)=>{
        this.userSettings=user;
        console.log(this.userSettings);
      }
    })
  }



  updateInfo(){

  }


  onChangeEmail(dataForm:any) {

  }
}
