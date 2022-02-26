import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private authService: AuthenticationService,
    private fb :FormBuilder,private notifierService: NotifierService,
    private _router:Router) { }

  signUpForm = this.fb.group({
    firstName : ['',Validators.required],
    lastName : ['',Validators.required],
    username : ['',Validators.required],
    email : ['',Validators.required],
    birthDate : ['', Validators.required],
    role : ['',Validators.required],
    password : ['',Validators.required],
    passwordConfirmation : ['',Validators.required],
    country:['',Validators.required],
    city:['',Validators.required]
  })

  ngOnInit(): void {
  }

  onSignup(){
    this.authService.signup(this.signUpForm.value).subscribe({
      next: () => {
        this.notifierService.notify('success','Sign up successful');
        this._router.navigate(['']);
      },
      error: () => this.notifierService.notify('success','Sign up successful')
    });;
  }

  firstName(){
    return  this.signUpForm.get('firstName');
  }
  lastName(){
    return this.signUpForm.get('lastName');
  }
  username(){
    return this.signUpForm.get('username');
  }
  email(){
    return this.signUpForm.get('email');
  }
  role(){
    return this.signUpForm.get('role');
  }
  birthDate(){
    return this.signUpForm.get('birthdate');
  }
  password(){
    return this.signUpForm.get('password');
  }
  passwordConfirmation(){
    return this.signUpForm.get('passwordConfirmation');
  }
  country(){
    return this.signUpForm.get('country');
  }
  city(){
    return this.signUpForm.get('city');
  }
}
