import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

export interface Newspaper {
  id:number;
  name: string;
}

export interface Article {
  headline: string;
  url: string;
  category: string;
  publishedAt: string;
  newspaper: Newspaper;
}

export interface Page {
  articles: Article[];
  totalElements: number;
  totalPages: number;
}


@Injectable({
  providedIn: 'root',
})
export class ArticleApiService {
  private http = inject(HttpClient);

  constructor() {}

  getNewspapers() {
    return this.http.get<Newspaper[]>(environment.apiUrl +'/api/newspapers/get-all');
  }

  getArticles(params: {
    start: string;
    end: string;
    page: number;
    size: number;
    newspaperId: number;
    keyword?: string;
  }) {
    let httpParams = new HttpParams()
      .set('start', params.start)
      .set('end', params.end)
      .set('page', params.page)
      .set('size', params.size)
      .set('newspaperId', params.newspaperId);

    if (params.keyword) {
      httpParams = httpParams.set('keyword', params.keyword);
    }

    return this.http.get<Page>(environment.apiUrl +'/api/articles/filter', { params: httpParams });
  }
}
