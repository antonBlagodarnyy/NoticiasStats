import { IArticle } from "./IArticle";

export interface IArticlesResponse {
  articles: IArticle[];
  page: number;
  size: number;
  totElements: number;
  totPages: number;
}