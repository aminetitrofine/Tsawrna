import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm = new FormGroup({
    firstName : new FormControl(),
    lastName : new FormControl(),
    username : new FormControl(),
    email : new FormControl(),
    birthDate : new FormControl(),
    role : new FormControl(),
    password : new FormControl(),
    passwordConfirmation : new FormControl()
  });

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  onSignup(dataForm: any) {
    this.authService.signup(dataForm);
  }
}
