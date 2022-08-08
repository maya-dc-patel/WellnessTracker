import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { InventoryComponent } from './inventory/inventory.component';
import { JournalEntryComponent } from './journal/journal-entry/journal-entry.component';
import { JournalComponent } from './journal/journal.component';

const routes: Routes = [
  { path: 'ingredients', component: IngredientsComponent },
  { path: 'inventory', component: InventoryComponent },
  { path: 'journal', component: JournalComponent },
  { path: 'journal/:name', component: JournalEntryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
