import { Component, Input, OnInit, Renderer2 } from '@angular/core';
import {Image} from '../models/image'
import { AuthenticationService } from '../services/authentication.service';
import { ImageService } from '../services/image.service';
@Component({
  selector: 'app-list-images',
  templateUrl: './list-images.component.html',
  styleUrls: ['./list-images.component.css']
})
export class ListImagesComponent implements OnInit {

  @Input() images !: Image[][];

  constructor(private _renderer:Renderer2,private _authService: AuthenticationService,private _imageService : ImageService) { }

  ngOnInit(): void {
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

  onClick(){
    alert('clicked');
  }
  authenticatedUsername() {
    return this._authService.authenticatedUsername();
  }
  url(){
    return this._authService.serverUrl();
  }
}
