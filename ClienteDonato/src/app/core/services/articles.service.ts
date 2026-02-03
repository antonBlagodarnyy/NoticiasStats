import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { IArticle} from 'src/app/shared/models/Article';
import { INewspaper } from 'src/app/shared/models/INewspaper';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  constructor(private httpClient: HttpClient) { }

  private url: string = "http://localhost:8080/api/";
  private today: string = new Date().toISOString().slice(0, 10);

  findAllNewsToday(): Observable<IArticle[]> {
    // return of(this.products);
    return this.httpClient.get(this.url + "article/by-date", {
      params: {
        date: this.today
      }
    }).pipe(
      map((response: any) => response.articles as IArticle[]),
    );
  }

  findAllNewspaper(): Observable<INewspaper[]> {
    return this.httpClient.get(this.url + "newspapers/get-all").pipe(
      map((response: any) => response.newspapers as INewspaper[]));
  }
}
