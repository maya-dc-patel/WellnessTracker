import {
  trigger,
  state,
  style,
  transition,
  animate
} from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Journal } from '../models/journal';
import { JournalService } from './journal.service';

@Component({
  selector: 'app-journal',
  templateUrl: './journal.component.html',
  styleUrls: ['./journal.component.css'],
  animations: [
    trigger('openClose', [
      // ...
      state(
        'open',
        style({
          opacity: 1,
          transform: 'scale(1, 1)'
        })
      ),
      state(
        'closed',
        style({
          opacity: 0,
          transform: 'scale(0.95, 0.95)'
        })
      ),
      transition('open => closed', [animate('100ms ease-in')]),
      transition('closed => open', [animate('200ms ease-out')])
    ])
  ]
})
export class JournalComponent implements OnInit {
  @Input() name!: String;
  @Input() owner!: String;

  constructor(
    private _sanitizer: DomSanitizer,
    private journalService: JournalService,
    private router: Router
  ) {}

  ngOnInit(): void {}
  viewJournalEntries(name: String) {
    this.router.navigate([`journal/${name}`]);
  }
  createJournal() {
    let journal = { name: this.name, owner: this.owner, ingredients: [] };
    console.log('journal', journal);

    this.journalService.sendJournalData(journal).subscribe((data) => {
      console.log('journals', data);
    });
  }
}
