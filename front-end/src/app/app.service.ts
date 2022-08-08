import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ingredient } from './models/ingredient';
import { Journal } from './models/journal';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      Authorization: 'authkey',
      userid: '1',
    }),
  };
  constructor(private http: HttpClient) {}

  rootURL = '/api';

  getUsers() {
    return this.http.get(this.rootURL + '/users');
  }

  createIngredient(ingredient: Ingredient) {
    return this.http.post<any>(
      'http://localhost:8080/Practice/api/v1/ingredients',
      ingredient,
      this.httpOptions
    );
  }

  addUser(user: any, id: number) {
    user.id = id;
    return this.http.post(this.rootURL + '/user', user);
  }

  getData() {
    return this.http.get<any>(
      'http://localhost:8080/Practice/api/v1/inventory',
      this.httpOptions
    );
  }

  sendIngredient() {
    let ing = { id: 4, ingredient: 'Ketchup', amount: 3 };
    console.log('ingredient sending', ing);
    return this.http.post<any>(
      'http://localhost:8080/Practice/api/v1/ingredients',
      ing,
      this.httpOptions
    );
  }
  getAllIngredients() {
    return this.http.get<any>(
      'http://localhost:8080/Practice/api/v1/ingredients',
      this.httpOptions
    );
  }

  sendJournalData(journal: Journal) {
    return this.http.post<any>(
      'http://localhost:8080/Practice/api/v1/journals',
      journal,
      this.httpOptions
    );
  }

  getJournals() {
    return this.http.get<any>(
      'http://localhost:8080/Practice/api/v1/journals',
      this.httpOptions
    );
  }
}
