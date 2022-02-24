import { Component, ElementRef, OnInit, ViewChild , Renderer2} from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { ImageService } from '../services/image.service';
import {Image} from '../models/image'
import {NotifierService} from "angular-notifier";
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private readonly notifier!: NotifierService;
  tiles: any;
  trendImages : Image[][]=[[]];
  firstColImages : Image[]=[];
  secondeColImages : Image[]=[];
  thirdColImages : Image[]=[];

  constructor(private _authService: AuthenticationService,private _imageService : ImageService,private _router:Router) { }

  ngOnInit(){
    this._imageService.trendImages().subscribe({
      next :(data:Image[])=> {

        for (let i=0;i<data.length;i=i+3){
          this.firstColImages.push(data[i]);
          if(i+1<data.length) this.secondeColImages.push(data[i+1]);
          if(i+2<data.length) this.thirdColImages.push(data[i+2]);
        }
        this.trendImages =[this.firstColImages,this.secondeColImages,this.thirdColImages];
      }
    });
  }

  ngAfterViewInit() {

  }
  authenticated(): any {
    return this._authService.authenticated();
  }

  

  search(query:string){
    this._router.navigate(['/search',query]);
  }



}
