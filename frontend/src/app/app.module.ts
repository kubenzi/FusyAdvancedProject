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
import {PieChartComponent} from './components/pie-chart/pie-chart.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { CategoriesComponent } from './components/categories/categories.component';
import { ScheduledComponent } from './components/scheduled/scheduled.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import {AuthModule} from './authentication/auth.module';
import { CookieService } from 'ngx-cookie-service';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';


@NgModule({
  declarations: [
    AppComponent,
    UserComponentComponent,
    MenuComponent,
    SectionComponent,
    NavigationComponent,
    LineChartComponent,
    PieChartComponent,
    CategoriesComponent,
    ScheduledComponent,
    AccountsComponent,
    FileUploadComponent
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
        AuthModule,
        MatButtonModule,
        MatIconModule
    ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
