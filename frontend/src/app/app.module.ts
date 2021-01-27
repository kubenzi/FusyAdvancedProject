import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponentComponent} from './components/user-component/user-component.component';
import {HttpClientModule} from '@angular/common/http';
import {MenuComponent} from './components/menu/menu.component';
import {SectionComponent} from './components/section/section.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {AppRoutingModule} from './app-routing.module';
import {LineChartComponent} from './components/line-chart/line-chart.component';
import {FormsModule} from '@angular/forms';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthModule} from './authentication/auth.module';

@NgModule({
  declarations: [
    AppComponent,
    UserComponentComponent,
    MenuComponent,
    SectionComponent,
    NavigationComponent,
    LineChartComponent,
    PieChartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxChartsModule,
    FormsModule,
    BrowserAnimationsModule,
    AuthModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
