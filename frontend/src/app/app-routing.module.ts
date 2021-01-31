import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from './components/menu/menu.component';
import {CategoriesComponent} from './components/categories/categories.component';

const routes: Routes = [
  {path: 'dashboard', component: MenuComponent},
  {path: ':type', component: MenuComponent},
  {path: 'categories/:id', component: CategoriesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
