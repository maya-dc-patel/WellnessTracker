import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { IngredientService } from './ingredients/ingredient.service';
import { AppService } from './app.service';
import { InventoryComponent } from './inventory/inventory.component';
import { InventoryService } from './inventory/inventory.service';
import { JournalComponent } from './journal/journal.component';
import { JournalService } from './journal/journal.service';
import { HeaderComponent } from './header/header.component';
import { JournalEntryComponent } from './journal/journal-entry/journal-entry.component';

@NgModule({
  declarations: [
    AppComponent,
    IngredientsComponent,
    InventoryComponent,
    JournalComponent,
    HeaderComponent,
    JournalEntryComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [AppService, IngredientService, InventoryService, JournalService],
  bootstrap: [AppComponent],
})
export class AppModule {}
