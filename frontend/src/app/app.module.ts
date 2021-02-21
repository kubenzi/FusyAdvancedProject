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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import { SortTableComponent } from './components/sort-table/sort-table.component';
import { DialogFormComponent } from './components/dialog-form/dialog-form.component';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogFormCategoryComponent } from './components/dialog-form-category/dialog-form-category.component';
import { DialogFormNewOperationComponent } from './components/dialog-form-new-operation/dialog-form-new-operation.component';


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
    FileUploadComponent,
    SortTableComponent,
    DialogFormComponent,
    DialogFormCategoryComponent,
    DialogFormNewOperationComponent,
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
        MatIconModule,
        MatTableModule,
        MatSortModule,
        MatFormFieldModule,
        MatDialogModule,
        MatInputModule,
        ReactiveFormsModule

    ],
  providers: [CookieService, MatInputModule,
    MatTableModule,
    MatSortModule, MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
