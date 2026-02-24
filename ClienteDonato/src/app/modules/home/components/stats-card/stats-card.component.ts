import { Component, Input, OnInit } from '@angular/core';
import { Stats } from 'src/app/shared/models/IStats';

@Component({
  selector: 'app-stats-card',
  templateUrl: './stats-card.component.html',
  styleUrls: ['./stats-card.component.css']
})
export class StatsCardComponent implements OnInit {

  constructor() {}

  @Input()
  items: {title: string, value: number | string}[] = [];

  ngOnInit(): void {
  }
}
