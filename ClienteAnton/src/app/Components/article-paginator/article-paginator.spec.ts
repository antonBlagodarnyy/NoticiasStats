import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticlePaginator } from './article-paginator';

describe('ArticlePaginator', () => {
  let component: ArticlePaginator;
  let fixture: ComponentFixture<ArticlePaginator>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticlePaginator]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticlePaginator);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
