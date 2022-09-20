import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateJournalComponent } from './journal/create-journal/create-journal.component';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { InventoryComponent } from './inventory/inventory.component';
import { JournalEntryComponent } from './journal/journal-entry/journal-entry.component';
import { JournalComponent } from './journal/journal.component';
import { ViewAllJournalsComponent } from './journal/view-all-journals/view-all-journals.component';

const routes: Routes = [
  { path: 'ingredients', component: IngredientsComponent },
  { path: 'inventory', component: InventoryComponent },
  { path: 'journal', component: JournalComponent },
  { path: 'journal/:name', component: JournalEntryComponent },
  { path: 'create-journal', component: CreateJournalComponent },
  { path: 'view-all-journals', component: ViewAllJournalsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
