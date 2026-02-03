import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { IArticle } from 'src/app/shared/models/Article';
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

  constructor(private service: ArticlesService) { }

  articles: IArticle[] = [];
  newspapers: INewspaper[] = [];

  ngOnInit(): void {
    this.service.findAllNewsToday().subscribe(articles => {
      this.articles = articles;
    });

    this.service.findAllNewspaper().subscribe(newspapers => {
      this.newspapers = newspapers;
    });
  }
}
