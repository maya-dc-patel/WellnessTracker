import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ingredient } from '../models/ingredient';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      Authorization: 'authkey',
      userid: '1'
    })
  };
  constructor(private http: HttpClient) {}

  rootURL = '/api';

  getData() {
    return this.http.get<any>(
      'http://localhost:8080/Practice/api/v1/inventory',
      this.httpOptions
    );
  }
}
