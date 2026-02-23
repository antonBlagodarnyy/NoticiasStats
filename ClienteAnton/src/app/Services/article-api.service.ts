import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

export interface Article {
  id: number;
  headline: string;
  url: string;
  category: string;
  publishedAt: string;
  newspaperName: string;
}

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}


@Injectable({
  providedIn: 'root',
})
export class ArticleApiService {
  private http = inject(HttpClient);

  constructor() {}

  getNewspapers() {
    return this.http.get<string[]>(environment.apiUrl +'/api/newspapers/get-all');
  }

  getArticles(params: {
    start: string;
    end: string;
    page: number;
    size: number;
    newspaperName: string;
    keyword?: string;
  }) {
    let httpParams = new HttpParams()
      .set('start', params.start)
      .set('end', params.end)
      .set('page', params.page)
      .set('size', params.size)
      .set('newspaperName', params.newspaperName);

    if (params.keyword) {
      httpParams = httpParams.set('keyword', params.keyword);
    }

    return this.http.get<Page<Article>>(environment.apiUrl +'/api/article/filter', { params: httpParams });
  }
}
