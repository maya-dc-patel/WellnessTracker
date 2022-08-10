import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Ingredient } from './models/ingredient';

@Injectable()
export class StoreService {
  private ingredientList = new BehaviorSubject<Ingredient[]>([]);

  public getIngredientList(): Observable<Ingredient[]> {
    return this.ingredientList.asObservable();
  }

  public setIngredientList(data: any): void {
    this.ingredientList.next(data);
  }
}
