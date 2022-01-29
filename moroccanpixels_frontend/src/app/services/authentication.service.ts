import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private users=[
    {username:'admin',password:'admin',roles:['ADMIN','CONTRIBUTOR','USER']},
    {username:'user1',password:'1234',roles:['CONTRIBUTOR','USER']},
    {username:'user2',password:'1234',roles:['USER']},
  ];

  public isAuthenticated!:boolean;
  public userAuthenticated!:any;

  constructor() { }

  public login(username:string,password:string){
    let user;
    this.users.forEach(u=>{
      if(u.username == username && u.password == password){
        user = u;
      }
    });
    if (user) {
      this.isAuthenticated = true;
      this.userAuthenticated = user;
    } else {
      this.isAuthenticated = false;
      this.userAuthenticated = undefined;
    }
  }
}
