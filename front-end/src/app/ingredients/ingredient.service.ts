import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ingredient } from '../models/ingredient';


@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  public httpOptions = {
    headers: new HttpHeaders({ 
      'Access-Control-Allow-Origin':'*',
      'Authorization':'authkey',
      'userid':'1'
    })
  }
  constructor(private http: HttpClient) { }

  rootURL = '/api';



  createIngredient(ingredient: Ingredient) {
  
    return this.http.post<any>("http://localhost:8080/Practice/api/v1/ingredients", ingredient, this.httpOptions);
     }

     



    sendIngredient() {
      let ing ={id: 4, ingredient: "Ketchup", amount: 3};
      console.log('ingredient sending', ing);
      return this.http.post<any>("http://localhost:8080/Practice/api/v1/ingredients", ing, this.httpOptions);
       }
       getAllIngredients() {

        return this.http.get<any>("http://localhost:8080/Practice/api/v1/ingredients",  this.httpOptions);
         }
       
   
}
