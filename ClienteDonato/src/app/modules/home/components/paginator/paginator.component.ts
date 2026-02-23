import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit {

  @Input()
  page!: number;
  @Input()
  size!: number;
  @Input()
  totElements!: number;

  @Output()
  pageChange = new EventEmitter<PageEvent>();

  constructor() { }

  ngOnInit(): void {
  }

  onPage(event: PageEvent) {
    this.pageChange.emit(event);
  }

}
