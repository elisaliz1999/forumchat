import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from './modules/material/material.module';
import { NavbarComponent } from './shared/navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
