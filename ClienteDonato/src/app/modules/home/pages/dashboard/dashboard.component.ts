import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { Period } from 'src/app/shared/models/period.enum';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private service: ArticlesService) { }

  totArtToday: number = 0;
  totArtWeek: number = 0;
  totArtMonth: number = 0;

  ngOnInit(): void {
    forkJoin({
      today: this.service.countArticlesByPeriod(Period.TODAY),
      week: this.service.countArticlesByPeriod(Period.LAST_WEEK),
      month: this.service.countArticlesByPeriod(Period.LAST_MONTH)
    }).subscribe(({ today, week, month }) => {
      this.totArtToday = today;
      this.totArtWeek = week;
      this.totArtMonth = month;
    });
  }
}
