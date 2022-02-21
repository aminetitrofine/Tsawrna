import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-contributor',
  templateUrl: './contributor.component.html',
  styleUrls: ['./contributor.component.css']
})
export class ContributorComponent implements OnInit {

  constructor(private authService:AuthenticationService, private router: Router,private  route: ActivatedRoute ) { }

  ngOnInit(): void {
    if (!this.authService.authenticated().getValue()){
      this.router.navigate(['/login',{successUrl: this.route.url}])
    }
  }

}
