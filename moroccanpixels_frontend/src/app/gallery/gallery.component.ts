import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Image} from "../models/image";
import {AuthenticationService} from "../services/authentication.service";
import {ImageService} from "../services/image.service";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {

  tiles: any;
  images : Image[][]=[[]];
  firstColImages : Image[]=[];
  secondeColImages : Image[]=[];
  thirdColImages : Image[]=[];

  constructor(private _authService: AuthenticationService,private _imageService : ImageService) { }

  ngOnInit(){
    this._authService.authenticatedUser().subscribe({
      next: (data) => {
        this.loadImages(data.username);
      }
    })
  }

  ngAfterViewInit() {
  
  }

  loadImages(username:string){
    let url = this._authService.serverUrl();
    this._imageService.userGallery(username).subscribe({
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



}
