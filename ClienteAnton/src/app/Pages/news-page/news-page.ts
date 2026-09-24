import { Component, inject, OnInit } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { ArticleComponent } from '../../Components/article-component/article-component';
import { MatGridListModule } from '@angular/material/grid-list';
import { ArticleFilters } from "../../Components/article-filters/article-filters";
import { ArticlePaginator } from "../../Components/article-paginator/article-paginator";
import { ArticleStore } from '../../Stores/article.store';

@Component({
  selector: 'app-news-page',
  imports: [ArticleComponent, MatGridListModule, ArticleFilters, ArticlePaginator],
  template: `
  <h2>Articulos</h2>
  <app-article-filters/>


    @for (article of articles(); track $index) {
   
      <app-article-component [article]="article" />
   
    }

 

  <app-article-paginator/>
  `,
  styleUrl: './news-page.scss',
})
export class NewsPage  {
  private articleStore = inject(ArticleStore);
  articles = toSignal(this.articleStore.articles);

}
