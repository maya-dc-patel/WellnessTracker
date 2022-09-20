import { trigger, state, style, transition, animate } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Journal } from 'src/app/models/journal';
import { JournalService } from '../journal.service';

@Component({
  selector: 'app-view-all-journals',
  templateUrl: './view-all-journals.component.html',
  styleUrls: ['./view-all-journals.component.css'],
  animations:  [
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
export class ViewAllJournalsComponent implements OnInit {
  journals!: Journal[];
  public image = '';
  
  mobileMenuOpen = false;
  
  get openCloseTrigger() {
    return this.mobileMenuOpen ? 'open' : 'closed';
  }
  onFileSelected(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.uploadFile(reader.result as string);
    };
    reader.readAsDataURL(file);
  }
  ngOnInit():void {
    this.getJournals();
  }

  uploadFile(file: any) {
    this.image = file;
  }
  formatImageUrl() {
    return this._sanitizer.bypassSecurityTrustResourceUrl(this.image);
  }
  viewJournalEntries(name: String) {
    this.router.navigate([`journal/${name}`]);
  }
  constructor(
    private _sanitizer: DomSanitizer,
    private journalService: JournalService,
    private router: Router
  ) {}
  getJournals() {
    this.journalService.getJournals().subscribe((data) => {
      console.log('data', data);
      this.journals = data;
    });
  }

}
