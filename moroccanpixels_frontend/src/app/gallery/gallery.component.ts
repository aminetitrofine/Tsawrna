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
  trendImages : Image[]=[];

  @ViewChild('one') d1!: ElementRef;
  @ViewChild('two') d2 !:ElementRef ;
  @ViewChild('three')d3 !:ElementRef ;
  @ViewChild('four')d4 !:ElementRef ;

  constructor(private _authService: AuthenticationService,private _imageService : ImageService) { }

  ngOnInit(){
  }

  ngAfterViewInit() {
    let url = this._authService.serverUrl();
    this._imageService.userGallery().subscribe({
      next :(data:Image[])=> {
        for (let i=0;i<data.length;i=i+4){
          this.d1.nativeElement.insertAdjacentHTML('beforeend', `<img mat-card-image class="img" src="${url+data[i].filePath}">`);
          this.d2.nativeElement.insertAdjacentHTML('beforeend', `<img mat-card-image class="img" src="${url+data[i+1].filePath}">`);
          this.d3.nativeElement.insertAdjacentHTML('beforeend', `<img mat-card-image class="img" src="${url+data[i+2].filePath}">`);
          this.d4.nativeElement.insertAdjacentHTML('beforeend', `<img mat-card-image class="img" src="${url+data[i+3].filePath}">`);
        }
      }
    });
  }
  authenticated(): any {
    return this._authService.authenticated();
  }

}
