import { Component } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-article-filters',
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, MatDatepickerModule],
  template: ` <div class="container">
    <mat-form-field appearance="outline">
      <mat-label>Buscar palabra clave</mat-label>
      <input matInput />
    </mat-form-field>
    <mat-form-field appearance="outline">
      <mat-label>Noticiero</mat-label>
      <mat-select [(value)]="selectedNewspaper">
        <mat-option value="RTVE">RTVE</mat-option>
        <mat-option value="20Minutos">20 Minutos</mat-option>
      </mat-select>
    </mat-form-field>

    <mat-form-field appearance="outline">
      <mat-label>Escoja un rango de fechas</mat-label>
      <mat-date-range-input [rangePicker]="picker">
        <input matStartDate placeholder="Start date" />
        <input matEndDate placeholder="End date" />
      </mat-date-range-input>
      <mat-datepicker-toggle matIconSuffix [for]="picker"></mat-datepicker-toggle>
      <mat-date-range-picker #picker></mat-date-range-picker>
    </mat-form-field>
  </div>`,
  styleUrl: './article-filters.scss',
})
export class ArticleFilters {
  //TODO The newspapers could come from the api
  selectedNewspaper = 'RTVE';

  //TODO Chande date format to DD/MM/YYYY
}
