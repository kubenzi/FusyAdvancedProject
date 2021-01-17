import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserComponentComponent } from './components/user-component/user-component.component';
import { HttpClientModule } from '@angular/common/http';
import {MenuComponent} from './components/menu/menu.component';
import { SectionComponent } from './components/section/section.component';
import { NavigationComponent } from './components/navigation/navigation.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponentComponent,
    MenuComponent,
    SectionComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
