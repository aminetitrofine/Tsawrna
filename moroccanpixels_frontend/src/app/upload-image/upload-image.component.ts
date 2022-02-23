import { Component, OnInit } from '@angular/core';
import {ImageService} from "../services/image.service";
import {AuthenticationService} from "../services/authentication.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {
  selectedFile!:any
  private currentFileUpload!: File;
  private progress!: number ;
  timestamp:number=0;
  constructor(private imageService: ImageService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSelected(event:any) {
    console.log(event)
    this.selectedFile = event.target.files;
  }

  onUpload() : void {
    this.progress=0
    this.currentFileUpload = this.selectedFile.item(0)
    this.imageService.upload(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        // @ts-ignore
        this.progress = Math.round(100 * event.loaded / event.total);
        console.log(this.progress)
      } else if (event instanceof HttpResponse) {
        this.timestamp=Date.now();
        this.router.navigate(['/gallery',{successUrl: this.route.url}])

      }
    },err=>{
      alert("Problème de chargement");
    })

    }
}
