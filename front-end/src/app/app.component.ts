import { Component } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private appService: AppService) {}
  title = 'ingredients';
  ngOnInit() {
    this.appService.getData().subscribe((data) => {
      console.log(data);
    });
    this.appService.getJournals().subscribe((data) => {
      console.log('journals', data);
    });
  }
}
