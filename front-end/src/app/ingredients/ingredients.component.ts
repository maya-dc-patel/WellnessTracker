import { Component, OnInit } from '@angular/core';

import { AppService } from '../app.service';
import { IngredientService } from './ingredient.service';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {
allIngredients: any;
  constructor(private ingService:IngredientService ) { }

  ngOnInit(): void {
    this.ingService.getAllIngredients().subscribe(data=>{console.log("all ingredients: ", data); this.allIngredients = data});
  }

}
