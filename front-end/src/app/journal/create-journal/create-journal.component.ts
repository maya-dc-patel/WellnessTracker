import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JournalService } from '../journal.service';

@Component({
  selector: 'app-create-journal',
  templateUrl: './create-journal.component.html'
})
export class CreateJournalComponent implements OnInit {
  public name = '';
  public owner = '';
  public image = '';

  public journalForm: FormGroup = this.fb.group({
    name: [this.name, [Validators.required]],
    owner: [this.owner, [Validators.required]]
  });
  constructor(
    private journalService: JournalService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {}

  createJournal() {
    console.log('form', this.journalForm);
    console.log('createJournal');

    let journal = {
      name: this.journalForm.controls['name'].value,
      owner: this.journalForm.controls['owner'].value,
      ingredients: []
    };
    console.log('journal', journal);

    this.journalService
      .sendJournalData(journal)
      .subscribe((next) => alert('Journal saved successfully'));
  }
}
