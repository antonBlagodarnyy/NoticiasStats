import { Routes } from '@angular/router';
import { NewsPage } from './Pages/news-page/news-page';
import { WelcomePage } from './Pages/welcome-page/welcome-page';
import { StatisticsPage } from './Pages/statistics-page/statistics-page';

export const routes: Routes = [
  {path: '', redirectTo: 'inicio', pathMatch:'full'},
  { component: WelcomePage, path: 'inicio' },
  { component: NewsPage, path: 'noticias' },
    { component: StatisticsPage, path: 'estadisticas' },
];
