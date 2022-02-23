import { Component, OnInit, Renderer2 } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import {Image} from '../models/image'
import { AuthenticationService } from '../services/authentication.service';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  url = this._authService.serverUrl();
  images : Image[][]=[[]];
  firstColImages : Image[]=[];
  secondeColImages : Image[]=[];
  thirdColImages : Image[]=[];

  query : string="";

  constructor(private _imageService:ImageService,private _route:ActivatedRoute, private _renderer:Renderer2,private _authService : AuthenticationService) { }

  ngOnInit(): void {
    this._route.paramMap.subscribe((params:ParamMap)=>{
      let q = params.get('q');
      this.query = (q!=null)?q:"";
    });
    this._imageService.search(this.query).subscribe({
      next :(data:Image[])=> {
        for (let i=0;i<data.length;i=i+3){
          this.firstColImages.push(data[i]);
          if(i+1<data.length) this.secondeColImages.push(data[i+1]);
          if(i+2<data.length) this.thirdColImages.push(data[i+2]);
        }
        this.images =[this.firstColImages,this.secondeColImages,this.thirdColImages];
      }
    });
  }

  showOwner(event:any){
    const container = event.srcElement;
    const element = container.nextSibling;
    this._renderer.setStyle(element,'display','flex');
  }
  hideOwner(event:any){
    const container = event.srcElement;
    const element = container.nextSibling;
    this._renderer.setStyle(element,'display','none');
  }

}
