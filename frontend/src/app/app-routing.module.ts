import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from './components/menu/menu.component';
import {CategoriesComponent} from './components/categories/categories.component';
import {ScheduledComponent} from './components/scheduled/scheduled.component';
import {SectionComponent} from './components/section/section.component';

const routes: Routes = [
  {path: 'dashboard', component: MenuComponent},
  {path: 'navigation', component: SectionComponent, outlet: 'nav'},
  {path: 'scheduled', component: ScheduledComponent},
  // {path: ':type', component: MenuComponent},
  {path: 'categories/:id', component: CategoriesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
