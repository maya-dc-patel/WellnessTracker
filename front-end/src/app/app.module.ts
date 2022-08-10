import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppService } from './app.service';
import { CreateJournalComponent } from './create-journal/create-journal.component';
import { HeaderComponent } from './header/header.component';
import { IngredientService } from './ingredients/ingredient.service';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { InventoryComponent } from './inventory/inventory.component';
import { InventoryService } from './inventory/inventory.service';
import { JournalEntryComponent } from './journal/journal-entry/journal-entry.component';
import { JournalComponent } from './journal/journal.component';
import { JournalService } from './journal/journal.service';

@NgModule({
  declarations: [
    AppComponent,
    IngredientsComponent,
    InventoryComponent,
    JournalComponent,
    HeaderComponent,
    JournalEntryComponent,
    CreateJournalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatTabsModule
  ],
  providers: [
    AppService,
    IngredientService,
    MatCardModule,
    InventoryService,
    JournalService
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {}
