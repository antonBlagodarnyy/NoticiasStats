import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { Article } from '../Model/Article';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private httpClient = inject(HttpClient);
  private articles = new BehaviorSubject<Article[]>([]);
  articlesSubject$ = this.articles.asObservable();

  constructor() {}

  getArticles$() {
    const params = new HttpParams().set('date', '2026-01-28');
    return this.httpClient
      .get<{ articles: Article[] }>(environment.apiUrl + '/article/byDate', { params })
      .pipe(
        tap((res) => {
          console.log(res);
          this.articles.next(res.articles);
        }),
      );
  }
}
