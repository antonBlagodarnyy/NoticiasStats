import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { IArticle } from 'src/app/shared/models/IArticle';
import { IArticlesResponse } from 'src/app/shared/models/IArticlesResponse';
import { INewspaper } from 'src/app/shared/models/INewspaper';
import { INewspaperResponse } from 'src/app/shared/models/INewspaperResponse';
import { Period } from 'src/app/shared/models/period.enum';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  constructor(private httpClient: HttpClient) { }

  private url: string = "http://localhost:8080/api/";
  private today: string = new Date().toISOString().slice(0, 10);

  findAllNewsToday(): Observable<IArticle[]> {
    return this.httpClient.get<IArticlesResponse>(this.url + "articles/by-date", {
      params: {
        date: this.today
      }
    }).pipe(
      map(response => response.articles),
    );
  }

  findAllNewspaper(): Observable<INewspaper[]> {
    return this.httpClient.get<INewspaperResponse>(this.url + "newspapers/get-all").pipe(
      map(response => response.newspapers));
  }

  findNewsByNewspaperIdAndDate(newspaperId: number | null, startDate: string, endDate: string, page: number, size: number) {
    let params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate)
      .set('page', page)
      .set('size', size);

    if (newspaperId !== null) {
      params = params.set('newspaperId', newspaperId);
    }

    return this.httpClient.get<IArticlesResponse>(this.url + "articles/by-newspaperid-and-date", { params });
  }

  countArticlesByPeriod(period: Period) {
    return this.getWithPeriod<number>("articles/count-by-period", period);
  }

  topNewspaperByPeriod(period: Period) {
    return this.getWithPeriod<{ name: string }>("articles/top-newspaper", period).pipe(
      map(resp => resp.name)
    );
  }

  private getWithPeriod<T>(endpoint: string, period: Period) {
    const params = new HttpParams().set('period', period);
    return this.httpClient.get<T>(this.url + endpoint, { params });
  }
}
