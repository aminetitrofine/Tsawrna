import { Component, Inject, OnInit } from '@angular/core';
import {Image} from '../models/image'
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { AuthenticationService } from '../services/authentication.service';
import { ImageService } from '../services/image.service';
import { DOCUMENT } from '@angular/common';
import { NotifierService } from 'angular-notifier';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-image-dialog',
  templateUrl: './image-dialog.component.html',
  styleUrls: ['./image-dialog.component.css']
})
export class ImageDialogComponent implements OnInit {

  constructor(  @Inject(MAT_DIALOG_DATA) public img : Image,public dialogRef: MatDialogRef<ImageDialogComponent>,
  private _authService:AuthenticationService,private _imageService:ImageService,@Inject(DOCUMENT) private _document: Document,
  private notifier:NotifierService,private _router:Router,private _route:ActivatedRoute) { }

  ngOnInit(): void {
  }
  url(){
    return this._authService.serverUrl();
  }

  unsaveImage(img:Image){
    this._imageService.unsaveImage(img.id).subscribe({
      next : ()=>{
        img.saved = false;
      }
    })
  }
  saveImage(img:Image){
    this._imageService.saveImage(img.id).subscribe({
      next : ()=>{
        img.saved = true;
      }
    })
  }
  downloadImage(img:Image){
    this._document.location.href = `${this._authService.serverUrl()}/image/${img.id}/download`
  }
  deleteImage(img:Image){
    this._imageService.deleteImage(img.id).subscribe({
      next : ()=> {
        this.notifier.notify('success','Image deleted successfully');
        window.location.reload();
      },
      error : ()=>this.notifier.notify('error','Deleting image failed')
    })
  }
  authenticatedUsername() {
    return this._authService.authenticatedUsername();
  }
}
