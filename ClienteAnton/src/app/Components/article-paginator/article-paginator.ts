import { Component } from '@angular/core';
import { MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-article-paginator',
  imports: [MatPaginatorModule],
  template: ` <div class="container"><mat-paginator /></div>`,
  styleUrl: './article-paginator.scss',
})
export class ArticlePaginator {}
