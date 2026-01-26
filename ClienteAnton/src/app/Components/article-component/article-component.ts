import { Component, input } from '@angular/core';
import { Article } from '../../Model/Article';
import { MatCardModule } from '@angular/material/card';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-article-component',
  imports: [MatCardModule, DatePipe],
  template: `<mat-card
    ><mat-card-header
      ><a href="{{ article().url }}">{{ article().headline }}</a></mat-card-header
    >
    <mat-card-footer>{{article().publishedAt | date}}</mat-card-footer></mat-card
  >`,
  styleUrl: './article-component.scss',
})
export class ArticleComponent {
  article = input.required<Article>();
}
