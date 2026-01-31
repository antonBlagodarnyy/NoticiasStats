import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleFilters } from './article-filters';

describe('ArticleFilters', () => {
  let component: ArticleFilters;
  let fixture: ComponentFixture<ArticleFilters>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleFilters]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleFilters);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
