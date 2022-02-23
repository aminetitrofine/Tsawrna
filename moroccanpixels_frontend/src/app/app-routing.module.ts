import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {HomeComponent} from "./home/home.component";
import {PricingComponent} from "./pricing/pricing.component";
import {ContributorComponent} from "./contributor/contributor.component";
import {UploadImageComponent} from "./upload-image/upload-image.component";
import {GalleryComponent} from "./gallery/gallery.component";
import { SearchComponent } from './search/search.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path:'signup', component: SignupComponent},
  {path:'pricing', component: PricingComponent},
  {path:'contributor', component: ContributorComponent},
  {path:'uploadImage', component: UploadImageComponent},
<<<<<<< HEAD
  {path:'gallery', component: GalleryComponent}
=======
  {path:'search', component: SearchComponent}
>>>>>>> add search component
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    onSameUrlNavigation: 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
