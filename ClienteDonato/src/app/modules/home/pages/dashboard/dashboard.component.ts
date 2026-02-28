import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { Stats } from 'src/app/shared/models/IStats';
import { Period } from 'src/app/shared/models/period.enum';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private service: ArticlesService) { }

  totNews: Stats[] = [
    { title: 'Total noticias del día', value: 0 },
    { title: 'Total noticias de la semana', value: 0 },
    { title: 'Total noticias del mes', value: 0 }
  ];

  topNews: Stats[] = [
    { title: 'Noticiero con más noticias del día', value: '' },
    { title: 'Noticiero con más noticias de la semana', value: '' },
    { title: 'Noticiero con más noticias del mes', value: '' }
  ];

  private periods = [
    Period.TODAY,
    Period.LAST_WEEK,
    Period.LAST_MONTH
  ];

  ngOnInit(): void {
    forkJoin(
      this.periods.map(period => this.service.countArticlesByPeriod(period))
    ).subscribe(results => {
      this.totNews = this.totNews.map((item, index) => ({
        ...item,
        value: results[index]
      }));
    });

    forkJoin(
      this.periods.map(period => this.service.topNewspaperByPeriod(period))
    ).subscribe(results => {
      this.topNews = this.topNews.map((item, index) => ({
        ...item,
        value: results[index]
      }));
    });
  }
}
