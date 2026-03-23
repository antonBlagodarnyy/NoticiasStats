import { Component, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { StatsStore } from '../../Services/stats.store';
import { toSignal } from '@angular/core/rxjs-interop';
import { KeyValuePipe } from '@angular/common';
import { ArticleCountResponse } from '../../Services/stats-api';

@Component({
  selector: 'app-statistics-page',
  imports: [MatCardModule],
  template: `
  <h2>Conteo</h2>
  <div class="container-stats">
  <mat-card appearance="outlined">
    <mat-card-header>
      <mat-card-title>Total de noticias</mat-card-title>
    </mat-card-header>
    <mat-card-content>
     <h3>Hoy: {{countStats()?.today?.articleCount}}</h3>
     <h3>Semana: {{countStats()?.week?.articleCount}}</h3>
     <h3>Mes: {{countStats()?.month?.articleCount}}</h3>
    </mat-card-content>
  </mat-card>
  <mat-card appearance="outlined">
    <mat-card-header>
      <mat-card-title>Noticiero con mas noticias</mat-card-title>
    </mat-card-header>
    <mat-card-content>
     <h3>Hoy: {{countStats()?.today?.mostFrequentNewspaper}}</h3>
     <h3>Semana: {{countStats()?.week?.mostFrequentNewspaper}}</h3>
     <h3>Mes: {{countStats()?.month?.mostFrequentNewspaper}}</h3>
    </mat-card-content>
  </mat-card>
  </div>`,
  styleUrl: './statistics-page.scss',
})
export class StatisticsPage {
  private statsStore = inject(StatsStore);
  countStats = toSignal<ArticleCountResponse | null>(this.statsStore.count);
}
