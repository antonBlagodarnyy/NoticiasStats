import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './pages/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { ArticleCardComponent } from './components/article-card/article-card.component';
import {MatCardModule} from  '@angular/material/card';
import { PaginatorComponent } from './components/paginator/paginator.component' ;
import {MatPaginatorModule} from '@angular/material/paginator';


@NgModule({
  declarations: [
    HomeComponent,
    ArticleCardComponent,
    PaginatorComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatDividerModule,
    MatButtonModule,
    MatCardModule,
    MatPaginatorModule
  ],
  exports: [
    HomeComponent,
  ]
})
export class HomeModule { }
