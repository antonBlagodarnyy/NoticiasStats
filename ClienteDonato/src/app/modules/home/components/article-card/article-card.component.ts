import { Component, Input, OnInit } from '@angular/core';
import { IArticle } from 'src/app/shared/models/IArticle';

@Component({
  selector: 'app-article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.css']
})
export class ArticleCardComponent implements OnInit {

  @Input('article-card')
  article: IArticle = {
    headline: '',
    url: '',
    category: '',
    publishedAt: new Date(),
    newspaperName: ''
  }

  constructor() { }
  ngOnInit(): void {
  }

}
