import { Component, computed, inject, OnInit } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ArticleStore } from '../../Stores/article.store';
import { AsyncPipe } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { filter, take } from 'rxjs';

@Component({
  selector: 'app-article-filters',
  imports: [
    AsyncPipe,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    ReactiveFormsModule,
  ],
  template: ` <div class="container">
    <mat-form-field appearance="outline">
      <mat-label>Buscar palabra clave</mat-label>
      <input matInput />
    </mat-form-field>
    <mat-form-field appearance="outline">
      <mat-label>Noticiero</mat-label>
      <mat-select
        [value]="articleStore.newspaper | async"
        (selectionChange)="articleStore.setNewspaper($event.value)"
      >
        @for (newspaper of articleStore.newspapers | async; track newspaper) {
          <mat-option [value]="newspaper">{{ newspaper }}</mat-option>
        }
      </mat-select>
    </mat-form-field>

    <mat-form-field appearance="outline">
      <mat-label>Escoja un rango de fechas</mat-label>
      <mat-date-range-input [formGroup]="dateRange" [rangePicker]="picker">
        <input matStartDate formControlName="start" placeholder="Start date" />
        <input matEndDate formControlName="end" placeholder="End date" />
      </mat-date-range-input>
      <mat-datepicker-toggle matIconSuffix [for]="picker"></mat-datepicker-toggle>
      <mat-date-range-picker #picker></mat-date-range-picker>
    </mat-form-field>
  </div>`,
  styleUrl: './article-filters.scss',
})
export class ArticleFilters implements OnInit {
  protected articleStore = inject(ArticleStore);

  readonly dateRange = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  ngOnInit() {
    // One-time initialization from store
    this.articleStore.todaysRange.pipe(take(1)).subscribe(([start, end]) => {
      this.dateRange.setValue({
        start: new Date(start),
        end: new Date(end),
      });
    });

    // User → Store
    this.dateRange.valueChanges.pipe(filter((r) => !!r.start && !!r.end)).subscribe((r) => {
      console.log(r.start);
      this.articleStore.setDateRange([
        r.start!.toLocaleDateString('sv-SE'),
        r.end!.toLocaleDateString('sv-SE'),
      ]);
    });
  }
}
