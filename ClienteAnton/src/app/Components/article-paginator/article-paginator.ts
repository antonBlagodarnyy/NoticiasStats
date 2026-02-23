import { Component, inject } from '@angular/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ArticleStore } from '../../Stores/article.store';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-article-paginator',
  imports: [MatPaginatorModule, AsyncPipe],
  template: ` <div class="container">
    <mat-paginator  [length]="articleStore.totalElements | async"
  [pageIndex]="((articleStore.page  | async) ?? 0) "
  [pageSize]="10"
  [pageSizeOptions]="[5, 10, 25, 100]"
  (page)="onPageChange($event)" />
  </div>`,
  styleUrl: './article-paginator.scss',
})
export class ArticlePaginator {
  protected articleStore = inject(ArticleStore);

  onPageChange(event: any) {
  // event = PageEvent
  // event.pageIndex → new page (zero-based)
  // event.pageSize → new page size
  const newPage = event.pageIndex ;
  this.articleStore.updatePage(newPage, event.pageSize); // your store method
}
}
