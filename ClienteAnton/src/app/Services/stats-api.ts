import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

export interface CountInfo {
  articleCount: number;
  mostFrequentNewspaper: string;
}

export interface ArticleCountResponse {
  today: CountInfo;
  week: CountInfo;
  month: CountInfo;
}

@Injectable({
  providedIn: 'root',
})
export class StatsApiService {

  private http = inject(HttpClient);

  getCounts() {
    return this.http.get<ArticleCountResponse>(
      environment.apiUrl + '/api/articles/count'
    );
  }

}