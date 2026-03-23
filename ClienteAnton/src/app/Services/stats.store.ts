import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ArticleCountResponse, CountInfo, StatsApiService } from './stats-api';

@Injectable({
  providedIn: 'root',
})
export class StatsStore {

 private count$ = new BehaviorSubject<ArticleCountResponse | null>(null);

  readonly count = this.count$.asObservable();

  constructor(private api: StatsApiService) {
    this.loadCounts();
  }

   private loadCounts() {
    this.api.getCounts().subscribe((res: ArticleCountResponse) => {
      this.count$.next(res);
    });
  }
  refresh() {
    this.loadCounts();
  }
}