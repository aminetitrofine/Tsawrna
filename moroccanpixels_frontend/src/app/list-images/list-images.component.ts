import { Component, Inject, Input, OnInit, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import {Image} from '../models/image'
import { AuthenticationService } from '../services/authentication.service';
import { ImageService } from '../services/image.service';
import { DOCUMENT } from '@angular/common';
import {MatDialog} from '@angular/material/dialog';
import { ImageDialogComponent } from '../image-dialog/image-dialog.component';
@Component({
  selector: 'app-list-images',
  templateUrl: './list-images.component.html',
  styleUrls: ['./list-images.component.css']
})
export class ListImagesComponent implements OnInit {

  @Input() images !: Image[][];

  constructor(private _renderer:Renderer2,private _authService: AuthenticationService,
    private _imageService : ImageService,@Inject(DOCUMENT) private _document: Document,
    public dialog: MatDialog) { }

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
  authenticatedUsername() {
    return this._authService.authenticatedUsername();
  }
  url(){
    return this._authService.serverUrl();
  }

  openImageDialog(img:Image){
    const dialogRef = this.dialog.open(ImageDialogComponent,{
      data : img
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
