import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Journal } from '../models/journal';
import { JournalService } from './journal.service';

@Component({
  selector: 'app-journal',
  templateUrl: './journal.component.html',
  styleUrls: ['./journal.component.css']
})
export class JournalComponent implements OnInit {
  public name = '';
  public owner = '';
  public image = '';
  journalList!: Journal[];
  constructor(
    private _sanitizer: DomSanitizer,
    private journalService: JournalService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getJournals();
  }
  getJournals() {
    this.journalService.getJournals().subscribe((data) => {
      console.log('data', data);
      this.journalList = data;
    });
  }
  viewJournalEntries(name: String) {
    this.router.navigate([`journal/${name}`]);
  }
  createJournal() {
    let journal = { name: this.name, owner: this.owner, ingredients: [] };
    console.log('journal', journal);

    this.journalService.sendJournalData(journal).subscribe((data) => {
      console.log('journals', data);
      this.getJournals();
    });
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.uploadFile(reader.result as string);
    };
    reader.readAsDataURL(file);
  }

  uploadFile(file: any) {
    this.image = file;
  }
  formatImageUrl() {
    return this._sanitizer.bypassSecurityTrustResourceUrl(this.image);
  }
}
