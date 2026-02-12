import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { debounceTime } from 'rxjs';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { IArticle } from 'src/app/shared/models/IArticle';
import { INewspaper } from 'src/app/shared/models/INewspaper';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });
  newspaperControl = new FormControl<number | null>(null);

  articles: IArticle[] = [];
  newspapers: INewspaper[] = [];

  page: number = 0;
  size: number = 12;
  totElements: number = 0;

  constructor(private service: ArticlesService) { }

  ngOnInit(): void {
    this.service.findAllNewsToday().subscribe(articles => {
      this.articles = articles;
    });

    this.service.findAllNewspaper().subscribe(newspapers => {
      this.newspapers = newspapers;
    });

    this.newspaperControl.valueChanges
      .pipe(debounceTime(300))
      .subscribe(() => {
        this.applyFilters();
      });

    this.range.valueChanges
      .pipe(debounceTime(300))
      .subscribe(() => {
        this.applyFilters();
      });
  }

  onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.totElements = event.length;
    this.applyFilters();
  }

  applyFilters(): void {
    const newspaperId = this.newspaperControl.value ? Number(this.newspaperControl.value) : null;
    const startDate = this.range.value.start ? this.formatDate(this.range.value.start) : this.formatDate(new Date());
    const endDate = this.range.value.end ? this.formatDate(this.range.value.end) : this.formatDate(new Date());
    
    this.service.findNewsByNewspaperIdAndDate(newspaperId, startDate, endDate, this.page, this.size)
      .subscribe(res => {
        this.articles = res.articles;
        this.page = res.page;
        this.size = res.size;
        this.totElements = res.totElements;
      });
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); //Añade el 0 si el mes es menor a 10, para que siempre tenga dos dígitos
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

}
