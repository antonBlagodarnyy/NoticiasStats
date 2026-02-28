import { Injectable } from '@angular/core';
import { BehaviorSubject, combineLatest, filter, switchMap } from 'rxjs';
import { Article, ArticleApiService, Newspaper, Page } from '../Services/article-api.service';

@Injectable({ providedIn: 'root' })
export class ArticleStore {
  private newspapers$ = new BehaviorSubject<Newspaper[]>([]);
  private keyword$ = new BehaviorSubject('');
  private newspaper$ = new BehaviorSubject<Newspaper | undefined>(undefined);
  private dateRange$ = new BehaviorSubject<[string, string]>([
    this.defaultStart(),
    this.defaultEnd(),
  ]);
  private page$ = new BehaviorSubject(0);
  private size$ = new BehaviorSubject(10);

  private articles$ = new BehaviorSubject<Article[]>([]);
  private totalElements$ = new BehaviorSubject(0);

  // Exponer como readonly observables
  readonly newspapers = this.newspapers$.asObservable();
  readonly articles = this.articles$.asObservable();
  readonly totalElements = this.totalElements$.asObservable();
  readonly page = this.page$.asObservable();

  readonly newspaper = this.newspaper$.asObservable();
  readonly todaysRange = this.dateRange$.asObservable();

  constructor(private api: ArticleApiService) {
    this.loadNewspapers();
    this.connectToApi();
  }

  // ----------------- mutations -----------------

  setKeyword(v: string) {
    this.page$.next(0);
    this.keyword$.next(v);
  }

  setNewspaper(n: Newspaper) {
    this.page$.next(0);
    this.newspaper$.next(n);
  }

  setDateRange(r: [string, string]) {
    this.page$.next(0);
    this.dateRange$.next(r);
  }

  setPage(p: number) {
    this.page$.next(p);
  }

  setSize(s: number) {
    this.size$.next(s);
  }

  updatePage(p: number, s: number) {
    this.setSize(s);
    this.setPage(p);
  }
  // ----------------- private logic -----------------

  private loadNewspapers() {
    this.api.getNewspapers().subscribe((res) => {
      this.newspapers$.next(res);
      if (res.length) this.newspaper$.next(res[0]); // default
    });
  }

  private connectToApi() {
    combineLatest([this.keyword$, this.newspaper$, this.dateRange$, this.page$, this.size$])
      .pipe(
        filter(([_, newspaper]) => {
          return newspaper ? (newspaper.id ? true : false) : false;
        }),
        switchMap(([keyword, newspaper, [start, end], page, size]) => {
          return this.api.getArticles({
            start,
            end,
            page,
            size,
            newspaperId: newspaper!.id,
            keyword,
          });
        }),
      )
      .subscribe((res: Page) => {
        this.articles$.next(res.articles);
        this.totalElements$.next(res.totalElements);
      });
  }

  private defaultStart() {
    const d = new Date();
    d.setDate(d.getDate() - 7);
    return d.toLocaleDateString('sv-SE');
  }

  private defaultEnd() {
    return new Date().toLocaleDateString('sv-SE');
  }

  public parseDate(d: string): string {
    return new Date(d).toLocaleDateString('sv-SE');
  }
}
