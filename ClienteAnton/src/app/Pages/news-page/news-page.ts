import { Component, inject, OnInit } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { ArticleService } from '../../Services/article-service';
import { ArticleComponent } from '../../Components/article-component/article-component';

@Component({
  selector: 'app-news-page',
  imports: [ArticleComponent],
  template: `
    @for (article of articles(); track $index) {
      <app-article-component [article]="article" />
    }
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
