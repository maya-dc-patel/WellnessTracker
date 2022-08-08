import { Ingredient } from "./ingredient";

export interface Journal {
  name: String;
  owner: String;
  ingredients: Ingredient[];
}
