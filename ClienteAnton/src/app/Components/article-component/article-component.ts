import { Component, input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { DatePipe } from '@angular/common';
import { MatChipsModule } from '@angular/material/chips';
import { Article } from '../../Services/article-api.service';

@Component({
  selector: 'app-article-component',
  imports: [MatCardModule, DatePipe, MatChipsModule],
  template: `<mat-card
    ><mat-card-header
      ><mat-card-title>{{ article().newspaperName }}</mat-card-title></mat-card-header
    >
    <mat-card-content
      ><a href="{{ article().url }}">{{ article().headline }}</a></mat-card-content
    >
    <mat-card-footer>
      <mat-chip-set>
        <mat-chip>{{ article().category }}</mat-chip>
        <mat-chip>{{ article().publishedAt | date }}</mat-chip>
      </mat-chip-set></mat-card-footer
    >
  </mat-card>`,
  styleUrl: './article-component.scss',
})
export class ArticleComponent {
  article = input.required<Article>();
}
