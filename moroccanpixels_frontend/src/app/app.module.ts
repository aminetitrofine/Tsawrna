import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { PricingComponent } from './pricing/pricing.component';
import { ContributorComponent } from './contributor/contributor.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from "@angular/material/grid-list";

import {MatButtonModule} from "@angular/material/button";
import { UploadImageComponent } from './upload-image/upload-image.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { GalleryComponent } from './gallery/gallery.component';
import { SearchComponent } from './search/search.component';
import {NotifierModule, NotifierOptions} from 'angular-notifier';
import { InsightsComponent } from './insights/insights.component';
import { CategorieComponent } from './categorie/categorie.component';
import { SettingsComponent } from './settings/settings.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {NgChartsModule} from "ng2-charts";

import {MatDividerModule} from '@angular/material/divider';
import {MatDialogModule} from '@angular/material/dialog';
import { ListImagesComponent } from './list-images/list-images.component';
import { ImageDialogComponent } from './image-dialog/image-dialog.component';


const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: 'right',
      distance: 12
    },
    vertical: {
      position: 'top',
      distance: 12,
      gap: 10
    }
  },
  theme: 'material',
  behaviour: {
    autoHide: 5000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
};
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    PricingComponent,
    ContributorComponent,
    UploadImageComponent,
    GalleryComponent,
    SearchComponent,
    InsightsComponent,
    CategorieComponent,
    SettingsComponent,
    ListImagesComponent,
    ImageDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatIconModule,
    MatGridListModule,
    MatButtonModule,
    FlexLayoutModule,
    NotifierModule.withConfig(customNotifierOptions),
    NgbModule,
    NgChartsModule,
    MatDividerModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule{

}
