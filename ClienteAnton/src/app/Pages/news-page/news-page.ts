import { Component, inject, OnInit } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { ArticleService } from '../../Services/article-service';
import { ArticleComponent } from '../../Components/article-component/article-component';
import { MatGridListModule } from '@angular/material/grid-list';
import { ArticleFilters } from "../../Components/article-filters/article-filters";
import { ArticlePaginator } from "../../Components/article-paginator/article-paginator";

@Component({
  selector: 'app-news-page',
  imports: [ArticleComponent, MatGridListModule, ArticleFilters, ArticlePaginator],
  template: `
  <h2>Articulos</h2>
  <app-article-filters/>
  <mat-grid-list cols="5" rowHeight="2:1">

    @for (article of articles(); track $index) {
      <mat-grid-tile>
      <app-article-component [article]="article" />
      </mat-grid-tile>
    }

  </mat-grid-list>

  <app-article-paginator/>
  `,
  styleUrl: './news-page.scss',
})
export class NewsPage implements OnInit {
  private articleService = inject(ArticleService);
  articles = toSignal(this.articleService.articlesSubject$, { initialValue: [] });
  ngOnInit(): void {
    this.articleService.getArticles$().subscribe();
  }
}
